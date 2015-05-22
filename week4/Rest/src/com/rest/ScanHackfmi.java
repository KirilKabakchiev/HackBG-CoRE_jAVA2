package com.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.xml.stream.XMLStreamException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.HttpResponse;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class ScanHackfmi {

    private static final String URL = "https://hackbulgaria.com/api/checkins/";

    public static String getContent(String url) throws ClientProtocolException, IOException {

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(url);
        HttpResponse response = httpclient.execute(httpget);

        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        StringBuilder sb = new StringBuilder();
        String line = "";
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }

        return sb.toString();
    }

    public static void printNamesOfMoreThan1Course(String content) {
        try {
            JSONArray array = new JSONArray(content);
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                JSONArray courseArr = (JSONArray) jsonObject.get("courses");
                if (courseArr.length() >= 2) {
                    System.out.println(jsonObject.get("name"));
                }
                // System.out.println(jsonObject);

            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static SortedSet<Map.Entry<String, Integer>> sortStudentsByVisits(String content) throws JSONException {
        Map<String, Integer> map = countStudentVisits(content);
        SortedSet<Map.Entry<String, Integer>> sortByVisits = new TreeSet<>(
                new Comparator<Map.Entry<String, Integer>>() {
                    @Override
                    public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
                        int result = o2.getValue() - o1.getValue();
                        if (result == 0) {
                            return 1;
                        } else {
                            return result;
                        }
                    }
                });

        sortByVisits.addAll(map.entrySet());

        return sortByVisits;
    }

    private static Map<String, Integer> countStudentVisits(String content) throws JSONException {
        Map<String, Integer> map = new HashMap<>();
        JSONArray array = new JSONArray(content);
        String name;
        for (int i = 0; i < array.length(); i++) {
            JSONObject jsonObject = (JSONObject) array.get(i);
            name = (String) jsonObject.get("student_name");
            if (map.containsKey(name)) {
                map.put(name, map.get(name) + 1);
            } else {
                map.put(name, 1);
            }
        }
        return map;
    }

    public static String studentsListedByVisitsCount(SortedSet<Map.Entry<String, Integer>> set) {
        StringBuilder listStudents = new StringBuilder();
        Iterator<Map.Entry<String, Integer>> iter = set.iterator();
        while (iter.hasNext()) {
            Map.Entry<String, Integer> current = iter.next();
            listStudents.append(current.getKey()).append("   ").append(current.getValue()).append("\n");
        }
        return listStudents.toString();
    }

    public static void main(String[] args) throws ClientProtocolException, JSONException, IOException {
        System.out.println(studentsListedByVisitsCount(sortStudentsByVisits(getContent(URL))));
        // System.out.println(getContent(URL));
    }
}
