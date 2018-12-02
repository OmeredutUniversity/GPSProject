package Coords;

import Geom.Point3D;
import org.omg.CORBA.MARSHAL;

import javax.crypto.MacSpi;

public class MyCoords implements coords_converter {

    final static int RE = 6371000;

    @Override
    public Point3D add(Point3D gps, Point3D local_vector_in_meter) {

        double lonNorm = Math.cos(gps.x() * Math.PI /180);

        double rx = Math.asin(local_vector_in_meter.x() / RE);
        double dx = rx / Math.PI * 180;

        double ry = Math.asin(local_vector_in_meter.y() / lonNorm / RE);
        double dy = ry / Math.PI *180;


        Point3D point3D = new Point3D(gps);
        point3D.add(dx, dy, local_vector_in_meter.z());

        return point3D;
    }

    @Override
    public double distance3d(Point3D gps0, Point3D gps1) {
        Point3D vector = new Point3D(vector3D(gps0, gps1));
        double distance = Math.sqrt((vector.x()*vector.x()) + (vector.y()*vector.y()) + (vector.z()*vector.z()));
        return distance;
    }

    @Override
    public Point3D vector3D(Point3D gps0, Point3D gps1) {
        double lonNorm = Math.cos(gps0.x()*Math.PI/180);
        double dx = gps1.x() - gps0.x();
        double rx = dx*Math.PI/180;
        double mx = Math.sin(rx) * RE;

        double dy = gps1.y() - gps0.y();
        double ry = dy*Math.PI/180;
        double my = Math.sin(ry) * lonNorm * RE;

        double mz = gps1.z() - gps0.z();

        return new Point3D(mx, my, mz);
    }


    //var altitude = 90.0 - (180.0 / Math.PI)*Math.acos(bma.x*ap.nx + bma.y*ap.ny + bma.z*ap.nz);
    //                    $('div_Altitude').innerHTML = altitude.toFixed(4).replace(/-/g,'&minus;') + '&deg;';

    @Override
    public double[] azimuth_elevation_dist(Point3D gps0, Point3D gps1) {
        int azi = 0;
        int elv = 1;
        int dis = 2;

        Point3D aziPoint = vector3D(gps0, gps1);
        double distance = distance3d(gps0, gps1);
        double height = gps1.z() - gps0.z();
        double elevation = Math.acos(height/distance);

        double azimuth = 0;
        double alpha = (Math.atan(Math.abs(aziPoint.y())/Math.abs(aziPoint.x()))*180)/Math.PI;
        if (aziPoint.y() >= 0){
            if (aziPoint.x() >= 0){
                azimuth = alpha;
            } else {
                azimuth = 180 - alpha;
            }
        } else {
            if (aziPoint.x() >= 0) {
                azimuth = 360 - alpha;
            } else {
                azimuth = 180 + alpha;
            }
        }
        double[] aed = new double[3];
        ///////////////////////////////////////////////////
        aed[azi] = azimuth;
        aed[elv] = elevation;
        aed[dis] = distance;
        return aed;
    }

    @Override
    public boolean isValid_GPS_Point(Point3D p) {
        return (p.x() >= -90 && p.x() <= 90 && p.y() >= -180 && p.y() <= 180 && p.z() >= -450);
    }
}
