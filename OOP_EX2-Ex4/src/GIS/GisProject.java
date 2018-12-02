package GIS;

import Geom.Point3D;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class GisProject implements GIS_project, Meta_data {

    private LinkedList<GIS_layer> projects;
    private Date date;

    /**
     * Constructor
     * @param filesPath list of csv paths
     */
    public GisProject(LinkedList<String> filesPath){
        date = new Date();
        projects = new LinkedList<>();
        Iterator iterator = filesPath.iterator();
        while (iterator.hasNext()){
            String filePath = (String) iterator.next();
            if (filePath.endsWith(".csv")){
                projects.add(new GisLayer(filePath));
            } else {
                filesPath.remove(filePath);
            }
        }
    }


    /**
     * This method creat kml file in given path
     * @param output path to kml file
     */
    /*public void writeFileKML(String output) {
        try {
            String kmlStart = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n";
            FileWriter fileWriter = new FileWriter(output);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(kmlStart + kmlFile());
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    /**
     * This method create string of project kml
     * @return string of project kml
     */
    public String kmlFile() {
        ArrayList<String> content = new ArrayList<String>();
        Iterator iterator = projects.iterator();
        while (iterator.hasNext()){
            GisLayer currentLayer = (GisLayer) iterator.next();
            content.add(currentLayer.kmlFile());
        }
        StringBuilder stringBuilder = new StringBuilder();
        Iterator iterator1 = content.iterator();
        while (iterator1.hasNext()){
            stringBuilder.append(iterator1.next());
        }
        return stringBuilder.toString();
    }



    @Override
    public Meta_data get_Meta_data() {
        return this;
    }

    @Override
    public int size() {
        return projects.size();
    }

    @Override
    public boolean isEmpty() {
        return projects.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return projects.contains(o);
    }

    @Override
    public Iterator<GIS_layer> iterator() {
        return projects.iterator();
    }

    @Override
    public Object[] toArray() {
        return projects.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return projects.toArray(a);
    }

    @Override
    public boolean add(GIS_layer gis_layer) {
        return projects.add(gis_layer);
    }

    @Override
    public boolean remove(Object o) {
        return projects.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return projects.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends GIS_layer> c) {
        return projects.addAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return projects.retainAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return projects.removeAll(c);
    }

    @Override
    public void clear() {
        projects.clear();
    }

    @Override
    public long getUTC() {
        return date.getTime();
    }

    @Override
    public Point3D get_Orientation() {
        return null;
    }

    @Override
    public String toString() {
        String toString = "";
        Iterator iterator = projects.iterator();
        while (iterator.hasNext()){
            toString += iterator.next();
        }
        return toString;
    }
}
