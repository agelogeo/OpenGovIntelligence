package eu.opengovintelligence.admin.opengovintelligence;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.IMarker;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.ChartHighlighter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.ArrayList;

/**
 * Created by Admin on 30/6/2017.
 */

public class GraphFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.graph_fragment,null);




        ArrayList<BarEntry> entries = new ArrayList<>();


        int[] data_values = { 860 , 918 , 902 , 30 , 1038 , 288 , 1324  };
        String[] headers = { "Friday" , "Monday" , "Saturday" , "Sunday" , "Thursday" , "Tuesday" , "Wednesday" };
        int dim_values_size = 7*10;
        CallHolder.setData(new int[dim_values_size]);
        CallHolder.setHeaders(new String[dim_values_size]);
        for(int i=0;i<dim_values_size;i++){
            CallHolder.getData()[i] = data_values[i%7];
            CallHolder.getHeaders()[i] = headers[i%7];
        }

        for(int i=0 ; i<CallHolder.getHeaders().length; i++){
            entries.add(new BarEntry(i,CallHolder.getData()[i]));
        }



        BarDataSet dataSet = new BarDataSet(entries, "# of Calls");

        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        /*ArrayList<String> labels = new ArrayList<String>();
        labels.add("January");
        labels.add("February");
        labels.add("March");
        labels.add("April");
        labels.add("May");
        labels.add("June");*/

        String[] labs = {"January","February","March","April","May","June"};


        // in this example, a LineChart is initialized from xml
        BarChart chart = (BarChart) v.findViewById(R.id.chart);

        IAxisValueFormatter xAxisFormatter = new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                if( Math.ceil(value) == value || Math.floor(value) == value)
                    return CallHolder.getHeaders()[Math.round(value)];
                else
                    return "";

                /*if(value==0)
                    return "Friday";
                else if(value==1)
                    return "Monday";
                else if(value==2)
                    return "Saturday";
                else if(value==3)
                    return "Sunday";
                else if(value==4)
                    return "Thursday";
                else if(value==5)
                    return "Tuesday";
                else if(value==6)
                    return "Wednesday";
                return "";*/


            }
        };



        chart.setPinchZoom(false);
        chart.getDescription().setText("# of times Alice called Bob");
        chart.setDrawGridBackground(false);


        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setForm(Legend.LegendForm.SQUARE);
        l.setFormSize(9f);
        l.setTextSize(11f);
        l.setXEntrySpace(4f);


        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setLabelCount(7, false);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setEnabled(false);


        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(6);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(xAxisFormatter);

        BarData data = new BarData(dataSet);
        data.setHighlightEnabled(true);
        chart.setData(data);
        chart.setHighlightFullBarEnabled(true);
        chart.setDrawMarkers(true);

        // chart.setDescription("# of times Alice called Bob");
        chart.animateY(2000);
        chart.invalidate();






        return v;
    }

}
