package week3.fileutils;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {
        File file = new File("file.txt");
        FileUtils utils = FileUtils.getInstance();
        Path path =  FileSystems.getDefault().getPath("lost.s04e11.hdtv.xvid-2hd.html");
        Path path3 = FileSystems.getDefault().getPath("test.txt");
         //utils.fixEncoding(path);
         //Path path2 = Paths.get("/home/kiril/JavaCode/");
         //utils.findBrokenFiles(path2);
       // Path path = (Path) FileSystems.getDefault().getPath("test.txt");
        utils.compress(path3);
        // System.out.println(path);
    }

}
