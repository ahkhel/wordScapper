package methods;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class CSVMethods {
    public static void saveCSV(String content, File file) {
//        try (FileWriter writer = new FileWriter(file)) {
//            writer.write(content);
//            System.out.println("CSV File saved: " + file.getAbsolutePath());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        
        
        try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file.getAbsoluteFile()), "UTF-8")) {
        	writer.write("\uFEFF"); 
        	writer.write(content);
            System.out.println("File written successfully with Arabic text!");
            System.out.println(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
