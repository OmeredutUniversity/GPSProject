package File_format;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class MultiCSVTest {


    MultiCSV multiCSV;

    @Before
    public void before(){
        multiCSV = new MultiCSV(new File("/Users/omeredut/Desktop/Ex2"));
    }



    @Test
    public void project2kml() {
        multiCSV.project2kml();
    }
}