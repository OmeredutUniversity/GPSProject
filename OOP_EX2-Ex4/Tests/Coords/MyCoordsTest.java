package Coords;

import Geom.Point3D;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MyCoordsTest {

    Point3D point3D_1;
    Point3D point3D_2;
    MyCoords mycoords;


    @Before
    public void before(){
        point3D_1 = new Point3D(32.103315, 35.209039, 670);
        point3D_2 = new Point3D(32.106352, 35.205225, 650);
        mycoords = new MyCoords();
    }

    @Test
    public void addTest() {
        Point3D point3D = new Point3D(337.699, -359.249, -20);
        System.out.println(mycoords.add(point3D_1, point3D));
    }

    @Test
    public void distance3dTest() {
        System.out.println(mycoords.distance3d(point3D_1, point3D_2));
    }

    @Test
    public void vector3DTest() {
        System.out.println("vector: "+mycoords.vector3D(point3D_1, point3D_2));
    }

    @Test
    public void azimuth_elevation_distTest() {
        double[] azi = mycoords.azimuth_elevation_dist(point3D_1, point3D_2);
        for (int i = 0; i < azi.length; i++) {
            System.out.print(azi[i] + ", ");
        }
    }

    @Test
    public void isValid_GPS_PointTest() {
        Point3D point3D_A = new Point3D(77.03284, 33.43264, -451);
        Point3D point3D_B = new Point3D(177.03284, 33.43264, 324);
        Point3D point3D_C = new Point3D(-177.03284, 33.43264, 324);
        Point3D point3D_D = new Point3D(77.03284, 185.43264, 324);
        Point3D point3D_E = new Point3D(-177.03284, -185.43264, 324);

        /*System.out.println("p_1: " + mycoords.isValid_GPS_Point(point3D_1));
        System.out.println("p_2: " + mycoords.isValid_GPS_Point(point3D_2));
        System.out.println("p_A: " + mycoords.isValid_GPS_Point(point3D_A));
        System.out.println("p_B: " + mycoords.isValid_GPS_Point(point3D_B));
        System.out.println("p_C: " + mycoords.isValid_GPS_Point(point3D_C));
        System.out.println("p_D: " + mycoords.isValid_GPS_Point(point3D_D));
        System.out.println("p_E: " + mycoords.isValid_GPS_Point(point3D_E));*/

    }
}