package eu.opengovintelligence.admin.opengovintelligence.explorecubes;

import eu.opengovintelligence.admin.opengovintelligence.BasicVariables;

/**
 * Created by Admin on 28/6/2017.
 *
 * Parameter: none

 Description: returns all the available cubes of an RDF repository (including the aggregated cubes created by the Data Cube Aggregator)

 Example request:

 GET http://wapps.islab.uom.gr:8084/JSON-QB-REST-API/cubes
 */

public class Cube extends BasicVariables {

    public Cube(String id, String label) {
        super(id, label);
    }
}
