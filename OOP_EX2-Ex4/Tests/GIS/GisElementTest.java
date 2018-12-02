package GIS;

import Geom.Point3D;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GisElementTest {

    GisElement gisElement;

    @Before
    public void before(){
        gisElement = new GisElement("1c:b9:c4:15:42:68,Ariel_University,[ESS],03/12/2017 8:53,11,-85,32.10487307,35.21134308,692,3,WIFI");
    }

    @Test
    public void getGeomTest() {
        System.out.println(gisElement.getGeom());
    }

    @Test
    public void getDataTest() {
    }

    @Test
    public void translateTest() {
        Point3D vec = new Point3D(337.669, -359.249, -20);
        gisElement.translate(vec);
        System.out.println(gisElement.getGeom());
    }
    @Test
    public void get_Meta_Data_Test() {
        System.out.println(gisElement.getData());
    }
}