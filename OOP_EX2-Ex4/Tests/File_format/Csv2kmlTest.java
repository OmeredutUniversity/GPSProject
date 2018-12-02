package File_format;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class Csv2kmlTest {

    Csv2kml csv2kml;

    @Before
    public void before(){
        csv2kml = new Csv2kml("/Users/omeredut/Desktop/Ex2/Ex2/data/WigleWifi_20171203085618.csv");
    }

    @Test
    public void readCsvFileTest(){
        csv2kml.readCsvFile();
    }

    @Test
    public void writeCsvTest(){
        csv2kml.readCsvFile();
        csv2kml.writeFileKML("/Users/omeredut/Desktop/Ex2/test.kml");
    }

}