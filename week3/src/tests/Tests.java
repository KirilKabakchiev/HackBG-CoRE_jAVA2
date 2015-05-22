package tests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import week3.fileutils.FileUtils;

public class Tests {

    static FileUtils utils = FileUtils.getInstance();
    static File properties = new File("properties.properties");

    @BeforeClass
    public static void initiateFiles() throws IOException {
        File test = new File("test.txt");
        utils.writeTo("test", test);
        File properties = new File("properties.properties");
        utils.writeTo("# this=comment\na1=b1\na2 =b2\na3    =    b3\na4 = b4\na5=b6=b7=b8\n"
                + "a6=b9 #comment\na7==b10", properties);
    }

    @Test
    public void readFromTest() throws FileNotFoundException, IOException {
        File test = new File("test.txt");
        utils.writeTo("test", test);
        File file = new File("test.txt");
        String result = utils.readFrom(file);
        System.out.println(result.length());
        assertEquals("test", result);
        utils.writeTo("Azis e pederas", file);
        result = utils.readFrom(FileSystems.getDefault().getPath("test.txt"));
        assertEquals("Azis e pederas", result);
    }

    @Test
    public void parsePropertiesTest() throws FileNotFoundException, IOException {

        File properties = new File("properties.properties");
        HashMap<String, String> expectedMap = new HashMap<String, String>();

        expectedMap.put("a1", "b1");
        expectedMap.put("a2 ", "b2");
        expectedMap.put("a3    ", "    b3");
        expectedMap.put("a4 ", " b4");
        expectedMap.put("a5", "b6=b7=b8");
        expectedMap.put("a6", "b9 #comment");
        expectedMap.put("a7", "=b10");

        HashMap<String, String> output = utils.parseProperties(properties);

        assertEquals(expectedMap, output);
    }

    @Test
    public void wordCountTest() throws FileNotFoundException, IOException {
        int lines = utils.getLineCount(properties);
        int words = utils.getWordCount(properties);
        File file = new File("test.txt");
        utils.writeTo("Azis e pederas", file);
        int characters = utils.getCharacterCount(file);
        assertEquals(8, lines);
        assertEquals(15, words);
        assertEquals(14, characters);
    }
    
 
    
}
