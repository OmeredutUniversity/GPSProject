package File_format;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Csv2kml {

    private String csvPath;
    private BufferedReader bufferedReader;
    private String line;
    private String splitBy;
    private ArrayList<String[]> elements;

    public Csv2kml(String csvPath) {
        this.csvPath = csvPath;
        bufferedReader = null;
        line = "";
        splitBy = ",";
        elements = new ArrayList<>();
    }

    public void readCsvFile() {
        try {
            bufferedReader = new BufferedReader(new FileReader(csvPath));
            bufferedReader.readLine();
            bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                //String[] data = line.split("\n");
                elements.add(line.split(splitBy));
                /*for (int i = 0; i < data.length; i++) {
                    System.out.println(data[i]);
                }*/
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void writeFileKML(String output) {
        ArrayList<String> content = new ArrayList<String>();
        String kmlstart = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<Document>";
        content.add(kmlstart);

        String kmlend = "</Document>";
        try {
            FileWriter fileWriter = new FileWriter(output);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (int i = 1; i < elements.size(); i++) {
                String[] s = elements.get(i);
                DateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
                Date date = format.parse(s[3]);
                String kmlelement = "<Placemark>\n" +
                        "<name><![CDATA[" + s[1] + "]]></name>\n" +
                        "<description>\n" +
                        "<![CDATA[BSSID: <b>" + s[0] + "</b><br/>Capabilities: <b>" + s[2] + "</b><br/>Timestamp: <b>" + date.getTime() + "</b><br/>Date: <b>" + s[3]+ "</b>]]>\n" +
                        "</description>\n" +
                        "<styleUrl>#red</styleUrl>\n" +
                        "<Point>\n" +
                        "<coordinates>" + s[6] + "," + s[7] + "</coordinates>\n" +
                        "</Point>\n" +
                        "</Placemark>\n";
                content.add(kmlelement);
            }
            content.add(kmlend);
            Iterator iterator = content.iterator();
            StringBuilder stringBuilder = new StringBuilder();
            while (iterator.hasNext()){
                stringBuilder.append(iterator.next());
            }
            String csv = stringBuilder.toString();
            bufferedWriter.write(csv);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    private long getTime(String time){
        DateFormat format = new SimpleDateFormat("yyyy-mm-dd  hh:mm:ss");
        Date date = null;
        try {
            date = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }
}
