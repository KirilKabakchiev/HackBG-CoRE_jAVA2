package week3.fileutils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

import java.util.regex.Matcher;

public class FileUtils {

    private static FileUtils instance = null;

    private FileUtils() {

    }

    public static FileUtils getInstance() {
        if (instance == null) {
            instance = new FileUtils();
        }

        return instance;
    }

    public String readFrom(File file) throws FileNotFoundException, IOException {
        String line = null;
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            line = reader.readLine();
            while (line != null) {
                sb.append(line);
                // sb.append(System.lineSeparator());
                line = reader.readLine();
            }
        }
        return sb.toString();
    }

    public String readFrom(Path path) throws FileNotFoundException, IOException {
        return readFrom(path.toFile());
    }

    public void writeTo(String str, File file) throws IOException {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(str);

        }
    }

    void writeTo(String str, Path path) throws IOException {
        writeTo(str, path.toFile());
    }

    public HashMap<String, String> parseProperties(File file) throws IOException {
        List<String> list = new ArrayList<>();
        String line = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                list.add(line);
            }
        }

        return generateMapFromList(list);
    }

    private HashMap<String, String> generateMapFromList(List<String> list) {
        HashMap<String, String> map = new HashMap<>();
        String key = null;
        String value = null;
        for (String str : list) {
            if (str.charAt(0) == '#') {
                continue;
            }
            boolean flag = true;
            for (int i = 0; i < str.length() && flag; i++) {
                if (str.charAt(i) == '=') {
                    flag = false;
                    key = str.substring(0, i);
                    value = str.substring(i + 1, str.length());
                    // System.out.println(key + "=" + value);
                }
            }
            map.put(key, value);
        }
        return map;
    }

    public Path reduceFilePath(Path path) {
        return path.normalize();
    }

    public int getLineCount(File file) throws FileNotFoundException, IOException {
        int lines = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while (reader.readLine() != null) {
                lines++;
            }
        }
        return lines;
    }

    public int getWordCount(File file) throws FileNotFoundException {
        int count = 0;
        try (Scanner sc = new Scanner(new FileInputStream(file))) {
            while (sc.hasNext()) {
                sc.next();
                count++;
            }
        }
        return count;
    }

    public int getCharacterCount(File file) throws FileNotFoundException, IOException {
        int numberOfCharacters = 0;
        try (FileReader reader = new FileReader(file)) {
            int characters = reader.read();
            while (characters != -1) {
                numberOfCharacters++;
                characters = reader.read();
            }
        }
        return numberOfCharacters;
    }

    public void fixEncoding(Path path) throws IOException {
        File file = path.toFile();

        byte[] encoded = Files.readAllBytes(path);
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"))) {
            bw.write(new String(encoded, "Windows-1251"));
        }
    }

    public void findBrokenFiles(Path path) throws IOException {     
        if (Files.isSymbolicLink(path)) {   
            Path p = Files.readSymbolicLink(path);         
            if (!Files.exists(p)) {
                System.out.println(p);
            }
        }
        else if (Files.isDirectory(path)) {
            
                try (DirectoryStream<Path> dirs = Files.newDirectoryStream(path)) {
                    for (Path currentPath : dirs) {
                        
                        findBrokenFiles(currentPath);
                    }
                }
            }
        }
    

    public void compress(Path path) throws FileNotFoundException, IOException {
        String text = this.readFrom(path);
        Pattern pattern = Pattern.compile("\\w+");
        Matcher matcher = pattern.matcher(text);
        Map<String, String> words = new HashMap<String, String>();
        int counter = 0;
        while (matcher.find()) {
            String word = matcher.group();
            if (!words.containsKey(word)) {
                words.put(word, ("~" + counter));
                counter++;
            }

            text = text.replaceFirst(word, words.get(word));
        }

        File file = new File(path.toString());
        this.writeTo(text, file);
        String name = file.getName();
        String newName = name.replaceFirst("[.][^.]+$", ".compr");
        File newFile = new File(name);
        file.renameTo(newFile);
    }
}
