package GIS;

import Coords.MyCoords;
import Geom.Geom_element;
import Geom.Point3D;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class GisElement implements GIS_element, Meta_data{


    private String splitBy;
    private Point3D point3D;
    private String[]  elements;


    /**
     * Constructor
     * @param line line of kml element
     */
    public GisElement(String line){
        splitBy = ",";
        elements = line.split(splitBy);
        point3D = new Point3D((Point3D) getGeom());
    }


    @Override
    public Geom_element getGeom() {
        point3D = new Point3D(elements[6]+","+elements[7]+","+elements[8]);
        return point3D;
    }
    @Override
    public Meta_data getData() {
        return this;
    }
    @Override
    public void translate(Point3D vec) {
        if (point3D != null) {
            MyCoords mycoords = new MyCoords();
            point3D = mycoords.add(point3D, vec);
        }
    }

    /**
     * This method create string of gisElement
     * @return string of kml gisElement
     */
    public String kmlElement(){
        String color = colorString();
        String kmlelement = "<Placemark>\n" +
                "<name><![CDATA[" + elements[1] + "]]></name>\n" +
                "<description>\n" +
                "<![CDATA[BSSID: <b>" + elements[0] + "</b><br/>Capabilities: <b>" + elements[2] + "</b><br/>Timestamp: <b>" + getUTC() + "</b><br/>Date: <b>" + elements[3]+ "</b>]]>\n" +
                "</description>\n" +
                "<styleUrl>" + colorString() + "</styleUrl>\n" +
                "<Point>\n" +
                "<coordinates>" + point3D.y() + "," + point3D.x() + "</coordinates>\n" +
                "</Point>\n" +
                "</Placemark>\n";
        return kmlelement;
    }

    private String colorString(){
        String colorString = "#red";
        if (Integer.valueOf(elements[5]) > -90){
            colorString = "#green";
        }
        return colorString;
    }


    @Override
    public long getUTC() {
        //DateFormat format = new SimpleDateFormat("dd/mm/yyyy  hh:mm:ss");
        DateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        Date date = null;
        try {
            date = format.parse(elements[3]);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    @Override
    public String toString() {
        String toString = "";
        if (elements.length > 0){
            toString += elements[0];
        }
        for (int i = 1; i < elements.length; i++) {
            toString += "," + elements[i];
        }
        return toString;
    }

    @Override
    public Point3D get_Orientation() {
        return null;
    }
}
