package File_format;

import GIS.GisProject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class MultiCSV {

    private LinkedList<String> filenames;
    private File folder;

    /**
     * Constructor
     * @param folder path of folder
     */
    public MultiCSV(final File folder){
        this.folder = folder;
        filenames = new LinkedList<String>();
        listFilesForFolder(folder);
    }

    /**
     * This method read folder in recursive and find csv files
     * @param folder path of folder
     */
    private void listFilesForFolder(final File folder) {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                if(fileEntry.getName().endsWith(".csv"))
                    filenames.add(fileEntry.getPath());
            }
        }
    }

    /**
     * This method create list of GisProjects
     * @return GisProject
     */
    public GisProject getGisProject(){
        return new GisProject(filenames);
    }


    /**
     * This method write kml file in the main folder
     */
    public void project2kml(){
        File output = new File(folder.getPath());
        writeFileKML(output.getPath() + "/preoject.kml");
    }


    /**
     * This method write kml file to given output
     * @param output path to kml file
     */
    private void writeFileKML(String output) {
        GisProject gisProject = getGisProject();
        ArrayList<String> content = new ArrayList<String>();
        //String kmlStart = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + "<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n";
        String kmlStart = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> <kml xmlns=\"http://www.opengis.net/kml/2.2\"><Document><Style id=\"red\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/red-dot.png</href></Icon></IconStyle></Style><Style id=\"yellow\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/yellow-dot.png</href></Icon></IconStyle></Style><Style id=\"green\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/green-dot.png</href></Icon></IconStyle></Style><Folder><name>Wifi Networks</name>";
        content.add(kmlStart + gisProject.kmlFile());
        try {
            FileWriter fileWriter = new FileWriter(output);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            Iterator iterator = content.iterator();
            StringBuilder stringBuilder = new StringBuilder();
            while (iterator.hasNext()){
                stringBuilder.append(iterator.next());
            }
            stringBuilder.append("</Folder>\n</Document></kml>");
            String csv = stringBuilder.toString();
            bufferedWriter.write(csv);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
