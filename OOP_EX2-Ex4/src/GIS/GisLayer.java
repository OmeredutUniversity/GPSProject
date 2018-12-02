package GIS;

import Geom.Point3D;

import java.io.*;
import java.util.*;

public class GisLayer implements GIS_layer, Meta_data {

    private LinkedList<GIS_element> layers;
    private String csvPath;
    private BufferedReader bufferedReader;
    private String line;
    private String[] data;
    private Date date;


    /**
     * Constructor
     */
    public GisLayer(String csvPath){
        this.csvPath = csvPath;
        if (csvPath.endsWith(".csv")) {
            layers = new LinkedList<>();
            date = new Date();
            readCsvFile();
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * this method read csv file and create GISElement
     */
    private void readCsvFile() {
        try {
            bufferedReader = new BufferedReader(new FileReader(csvPath));
            bufferedReader.readLine();
            bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                layers.add(new GisElement(line));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * This method create kml file
     * @param output path of kml file
     */
    /*public void writeFileKML(String output) {
        try {
            String kmlStart = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n";
            FileWriter fileWriter = new FileWriter(output);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(kmlStart + kmlFile() + "</kml");
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    /**
     * This method create string kml of gislayer
     * @return string of kml gisElement
     */
    public String kmlFile() {
        ArrayList<String> content = new ArrayList<String>();
        Iterator iterator = layers.iterator();
        while (iterator.hasNext()){
            GisElement currentElement = (GisElement) iterator.next();
            content.add(currentElement.kmlElement());
        }
        StringBuilder stringBuilder = new StringBuilder();
        Iterator iterator1 = content.iterator();
        while (iterator1.hasNext()) {
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
        return layers.size();
    }

    @Override
    public boolean isEmpty() {
        return layers.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return layers.contains(o);
    }

    @Override
    public Iterator<GIS_element> iterator() {
        return layers.iterator();
    }

    @Override
    public Object[] toArray() {
        return layers.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return layers.toArray(a);
    }

    @Override
    public boolean add(GIS_element gis_element) {
        return layers.add(gis_element);
    }

    @Override
    public boolean remove(Object o) {
        return layers.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return layers.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends GIS_element> c) {
        return layers.addAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return layers.retainAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return layers.removeAll(c);
    }

    @Override
    public void clear() {
        layers.clear();
    }

    @Override
    public long getUTC() {
        return date.getTime();
    }

    @Override
    public String toString() {
        String toString = "";
        Iterator iterator = layers.iterator();
        while (iterator.hasNext()){
            toString += iterator.next() + "\n";
        }
        return toString;
    }

    @Override
    public Point3D get_Orientation() {
        return null;
    }
}
