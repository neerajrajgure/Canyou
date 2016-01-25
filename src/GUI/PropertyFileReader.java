package GUI;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JOptionPane;

public class PropertyFileReader {

    public static void main(String[] args) {
        try {
            File file = new File("./src/GUI/HiveCafe.properties");
            FileInputStream fileInput = new FileInputStream(file);
            Properties properties = new Properties();
            properties.load(fileInput);
            fileInput.close();
 
            // Enumeration enuKeys = properties.keys();
            // while (enuKeys.hasMoreElements()) {
            //    String key = (String) enuKeys.nextElement();
            {
                String majorVer = properties.getProperty("MajorVersion");
                System.out.println("Data ==> " + ": " + majorVer);
                String minorVer = properties.getProperty("MinorVersion");
                System.out.println("Data ==> " + ": " + minorVer);
                JOptionPane.showMessageDialog ( null, "The Hive - Billing System : " + majorVer + "." + minorVer, "HIVE CAFE", JOptionPane.PLAIN_MESSAGE); 
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
