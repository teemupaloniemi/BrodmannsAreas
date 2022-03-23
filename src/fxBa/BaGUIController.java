package fxBa;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
import ba.Area;
import ba.Ba;
import ba.Function;
import ba.Lf;
import ba.Location;
import ba.Neighbour;
import ba.TilaException;

/**
 * @author tealjapa
 * @version 24.1.2022
 *
 */
public class BaGUIController implements Initializable {
           
    @FXML private ListView<?> areaText;
    @FXML private ListChooser<Area> chooserAreas;
    @FXML private ListChooser<Function> chooserFunctions;
    @FXML private ListChooser<Area> chooserNeighbours;
    @FXML private ListChooser<Function> chooserFunctionsN;
    @FXML private TextField nameText;
    @FXML private TextField locationText;
    @FXML private Button confirmButton;

    
    @FXML void handleNewArea() {
        newArea();
    }
    
    
    @FXML private void handlePrintArea() {
        PrintViewController.print(kanta.Mallit.TESTI_ALUE);
    } 

    
    @FXML private void handlePrintSearch() {   
        PrintViewController.print(kanta.Mallit.TESTI_HAKU);
    }                                        
    
    
    @FXML private void handleAddFunction() {
        addFunction();
    }
    
    
    @FXML void handleAddNeighbour() {
        addNeighbour();
    }
    
    
    @FXML void handleSave() {
        save();
    }


    @FXML void handleClose() {
        save();
        Platform.exit();
    }


    @FXML void handleDelete() {
        delete();
    }
    

    @FXML void handleConditionF() {
        conditionF();
    }

    
    @FXML void handleConditionL() {
        conditionL();
    }

    
    @FXML void handleSearch() {
        search(0);
    }
    
    
    @FXML void handleEdit() {
        edit();
    }
   

    @FXML void handleEditConfirm() {
        confirmEdit();
    }
    
    
    @FXML void handleHelp() {
        help();
    }
    
    
    @FXML void handleDeleteNeighbour() {
        deleteNeighbour();
    }
    
    @FXML void handleDeleteFunction() {
        deleteFunction();
    }

    
    
    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        reset();      
    }

//===============================================================================================
//Muut toteutukset  
    
    private Ba ba;
    private boolean clicked;
    private Location startLocation;
    
    /**
     * @param ba luodaan Brodmannin alueet kanta
     */
    public void setBa(Ba ba) {
        this.ba = ba; // luodaan uusi
        this.readFile(); // haetaan tiedot 
        this.startLocation = new Location().register().setName("Auto Generated");
        try {
            this.ba.add(startLocation);
        } catch (TilaException e) {
            //
        }
        this.search(0); // etsitään
        this.write(); // päivitetään käyttöliittymä
    }
    
    
    private void readFile() {
        try {
            this.ba.readFileAll();
        } catch (TilaException e) {
            Dialogs.showMessageDialog(e.getMessage());
        }
    }

    
    /**
     * Alustetaan aluevalikko uusilla tiedoilla
     */
    protected void reset() {
        this.chooserAreas.clear(); // tyhjennetään käyttöliittymän valitsijalista
        this.chooserAreas.addSelectionListener(e -> write()); // kun valitaan alue, päivitetään käyttöliittymä sen tiedoilla
    }


    /**
     * Täytetään alueen tiedot näyttöön
     * TODO tämä näyttää hankalalta ylläpidettävältä, joku fiksumpi ratkaisu
     */
    protected void write() {
        Area selectedArea = this.chooserAreas.getSelectedObject(); // otetaan listasta valittu alue
        if (selectedArea == null) return; // jos tyhjä niin häippästään
        try {
            // Valitun alueen nimi
            this.nameText.setText(selectedArea.getName());
            // Valitun alueen sijainnin nimi
            this.locationText.setText(ba.getLocation(selectedArea.getLid()).getName()); 
            // hoidellaan tehtävät
            var funcs = this.ba.findFunctions(selectedArea.getLid()); // pyydetään etsimään funktiot joita sijainnilla on
            this.chooserFunctions.clear(); // tyhjennetään funktio lista käyttöliittymässä
            for (int i = 0; i < funcs.size(); i++) this.chooserFunctions.add(funcs.get(i).getName(), funcs.get(i)); // Lisätään pyydetyt funktiot käyttöliittymään
            // hoidellaan naapurit
            var neighbours = this.ba.findNeighbours(selectedArea.getID()); // pyydetään etsimään funktiot joita sijainnilla on
            this.chooserNeighbours.clear(); // tyhjennetään naapuri lista käyttöliittymässä
            for (int i = 0; i < neighbours.size(); i++) this.chooserNeighbours.add(neighbours.get(i).getName(), neighbours.get(i)); // Lisätään pyydetyt funktiot käyttöliittymään
            // Naaupurin tehtävät
            Area n = this.chooserNeighbours.getSelectedObject();
            if (n != null) { // jos on olemassa naapuri
                var funcsN = this.ba.findFunctions(n.getLid()); // pyydetään etsimään funktiot joita sijainnilla on
                this.chooserFunctionsN.clear(); // tyhjennetään funktio lista käyttöliittymässä
                for (int i = 0; i < funcsN.size(); i++) this.chooserFunctionsN.add(funcsN.get(i).getName(), funcsN.get(i)); // Lisätään pyydetyt funktiot käyttöliittymään
            }
        } catch (IndexOutOfBoundsException e) { // jos ongelmia niin näytetään mitä 
            Dialogs.showMessageDialog("Ongelmia: " + e.getMessage()); // jos ei löydy
        }
    }
    
    
   /**
    * lisäään uusi alue 
    */
    protected void newArea() {
        Area a = new Area().register().setName("Brodmann's Area 00").setLid(0);// luodaan uusi
        try {
            this.ba.add(a);
        } catch (TilaException e) {
            Dialogs.showMessageDialog("Ongelmia: " + e.getMessage());
        } // pyydetään ba luokkaa lisäämään alue alueistoon
        setIndexForAreas(a.getID()); // asetetaan aluelistan indeksi alueelle
    }
    
    
    /**
     * lisäään uusi sijainti
     * @return uuden sijainnin viitteen
     */
     protected Location newLocation() {
         Location l = new Location().register();
         try {
            this.ba.add(l);
        } catch (TilaException e) {
            Dialogs.showMessageDialog("Ongelmia: " + e.getMessage());
        } // pyydetään ba luokkaa lisäämään sijainti sijainti-listaan 
         return l; // palautetaan luotu sijainti
     }
     

    /**
     * lisätään jo olemassa oleva funktio
     */
    public void addFunction() {
         String name = this.askDialog("Function name: ", "");
         if (name == null || "".equals(name)) return;
         Function f = this.ba.getFunction(name);
         try {
            this.ba.add(new Lf(this.chooserAreas.getSelectedObject().getLid(),f.getID()));
         } catch (TilaException e) {
             huomautus("Tämä on jo listassa!");
             return;
         }
         this.chooserFunctions.add(name, f);
    }
    
    
    /**
     * lisätään jo olemassa oleva funktio
     */
    public void addNeighbour() {
         String name = this.askDialog("Neighbour name: ", "Brodmann's Area");
         if (name == null || "".equals(name)) return;
         Area a = this.ba.getArea(name);
         try {
            this.ba.add(new Neighbour(this.chooserAreas.getSelectedObject().getID(),a.getID()));
         } catch (TilaException e) {
             huomautus("Nämä ovat jo naapurit!");
             return;
         }
         this.chooserNeighbours.add(name, a);
    }
     
    
    /**
     * poistetaan naapuri
     */
    public void deleteNeighbour() {
        if (this.chooserNeighbours.getSelectedObject() == null) return;
        int i = this.chooserAreas.getSelectedIndex();
        int a = this.chooserNeighbours.getSelectedObject().getID();
        int b = this.chooserAreas.getSelectedObject().getID();
        this.ba.deleteNeighbour(a,b);
        this.search(0);
        this.chooserAreas.setSelectedIndex(i);
    }
    
    
    /**
     * poistetaan lohko funktio pari
     */
    public void deleteFunction() {
        if (this.chooserFunctions.getSelectedObject() == null) return;
        int i = this.chooserAreas.getSelectedIndex();
        int l = this.chooserAreas.getSelectedObject().getLid();
        int f = this.chooserFunctions.getSelectedObject().getID();
        this.ba.deleteLf(l,f);
        this.search(0);
        this.chooserAreas.setSelectedIndex(i);
    }
    
    
     
    private String askDialog(String question, String modelText) {
        TextInputDialog dialog = new TextInputDialog(modelText);
        dialog.setHeaderText(null);
        dialog.setTitle("Answer");
        dialog.setContentText(question);
        Optional<String> answer = dialog.showAndWait();
        return answer.isPresent() ? answer.get().trim() : null;
    }
          
    
    /**
     * @param Aid Alueen tunnusnumero
     */
    protected void setIndexForAreas(int Aid) {
        this.chooserAreas.clear(); // tyhjennetään alueiden lista käyttöliittymästä
        int index = 0; // alkuindeksi
        for (int i = 0; i < ba.getAreaCount(); i++) {  // käydään alueet läpi
            Area area = ba.getArea(i); // alue jonka kohdalla ollaan käymässä läpi 
            if (area.getID() == Aid) index = i; // jos täsmää, alustetaan indeksi alueiston indeksin mukaan
            this.chooserAreas.add(area.getName(), area); // päivitetään käyttöliittymä
        }
        this.chooserAreas.setSelectedIndex(index); // ja hypätään listassa äsken tarkistettuun kohtaan
     }
    
    
    /**
     * tallennetaan kaikki tallennettavissa olevat tiedot,
     * muokattava tai lisättävä alue hyppää listaan
     * @return jos ongelmia palautetaan mikä meni pieleen
     */
    public String save() {
        try {
            this.ba.saveAll("tiedostot");
            Dialogs.showMessageDialog("Tallennettu.");
            return null;
        } catch (TilaException ex) {
            Dialogs.showMessageDialog("Tallennuksessa ongelmia! " + ex.getMessage());
            return ex.getMessage();
        }
    }

    
    /**
     * tuhotaan valittu alue
     */
    public void delete() {
        Area selectedArea = this.chooserAreas.getSelectedObject();
        this.ba.delete(selectedArea);
        this.search(0);
    }
    
    
    /**
     * valitaan hakuehdoksi funktio
     */
    public void conditionF() {
        huomautus("En osaa vielä päättää!");
    }
    
    
    /**
     * valitaan hakuehdoksi sijainti
     */
    public void conditionL() {
        huomautus("En osaa vielä päättää!");
    }
    
    
    /**
     * haetaan kauehdon ja hakuentän tiedoilla sopivat tiedot
     * @param searchID id hakunumero
     */
    public void search(int searchID) {        
        this.chooserAreas.clear();
        int index = 0;
        for (int i = 0; i < this.ba.getAreaCount(); i++) {
            Area area = this.ba.getArea(i);
            if (area.getID() == searchID) index = i;
            this.chooserAreas.add(area.getName(), area);
        }
        this.chooserAreas.setSelectedIndex(index); 
    }
    
    
    /**
     * muokataan valittua aluetta
     */
    private void edit() {
        if (this.clicked) { 
            this.editOff();
            this.clicked = false;
            return; 
        } 
        this.editOn();
        this.clicked = true;
    }
    
    
    private void confirmEdit() {
        Area selectedArea = this.chooserAreas.getSelectedObject();
        if (selectedArea == null) return;
        String futureName = this.nameText.getText();
        if (this.ba.duplicateCheck(selectedArea.getID(), futureName)) {
            Dialogs.showMessageDialog("Saman niminen alue on jo olemassa! " + futureName);
            return;
        }
        selectedArea.setName(futureName);
        selectedArea.setLid(ba.getLocation(this.locationText.getText()).getID());
        this.editOff();
    }
    
   
    private void editOn() {
        this.nameText.getStyleClass().add("edit");
        this.nameText.editableProperty().set(true);
        this.locationText.getStyleClass().add("edit");
        this.locationText.editableProperty().set(true);
        this.confirmButton.visibleProperty().set(true);
    }
    
    
    private void editOff() {
        int i = this.chooserAreas.getSelectedIndex();
        this.nameText.getStyleClass().removeAll("edit");
        this.nameText.editableProperty().set(false);
        this.locationText.getStyleClass().removeAll("edit");
        this.locationText.editableProperty().set(false);
        this.confirmButton.visibleProperty().set(false);
        this.search(0);
        this.chooserAreas.setSelectedIndex(i);
    }
    
    
    /**
     * Näytetään ohjelman suunnitelma selaimessa.
     */
    public void help() {
        Desktop desktop = Desktop.getDesktop();
        try {
            URI uri = new URI("https://tim.jyu.fi/view/kurssit/tie/ohj2/2022k/ht/tealjapa");
            desktop.browse(uri);
        } catch (URISyntaxException e) {
            return;
        } catch (IOException e) {
            return;
        }
    }
    
    
    /**
     * Tulostetaan huomautus-dialogi halutun tekstin kanssa
     * @param text tulostettava teksti
     */
    public static void huomautus(String text) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Huomautus");
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }
}