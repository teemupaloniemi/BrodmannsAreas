package ba;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
/**
 * @author tealjapa
 * @version 24.1.2022
 *
 */
public class BaGUIController {
       
    @FXML private ListView<?> areaText;
    
    @FXML private TextArea functionText;
    
    @FXML void ehtoEka() {
        huomautus();
    }


    @FXML void ehtoToka() {
        huomautus();
    }

    
    @FXML void haku() {
        functionText.setText(luoJono("function.dat"));
    }

    
    @FXML void muokkaa() {
        huomautus();
    }
 //===============================================================================================
 //Omat toteutukset  
    
    /*
    private static String etsiTeksti(String tiedosto) { 
        String[] a = luoJono(tiedosto);
        return "a";
    }*/
    

    
    private void huomautus() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Huomautus");
        alert.setHeaderText(null);
        alert.setContentText("Ei toimi vielä!");
        alert.showAndWait();
    }
    
    
    /**
     * @param tiedosto tiedosto joka halutaan tutkia
     * @return tiedoston sisältö
     * 
     */
    @SuppressWarnings("resource")
    public static String luoJono(String tiedosto) {
        Scanner fi;
        StringBuilder sb = new StringBuilder();
        
        try {
          fi = new Scanner(new FileInputStream(new File("C:/Users/Teemu/Kurssit/ohj2/ws/ba/" + tiedosto)));
        } catch (FileNotFoundException ex) {
          System.err.println("Tiedosto ei aukea! "+ex.getMessage());
          return null;
        }
        
        try {
            while (fi.hasNext()) {
                String s = fi.next();
                if (s.contains("|")) sb.append('\n');
                String[] a = s.trim().split("\\d+\\|");
                for (int i = 0; i < a.length; i++) sb.append(a[i] + " ");
            }
        } finally {
            fi.close();
        }
        return sb.toString().trim();
    } 
}