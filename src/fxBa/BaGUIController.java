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
   
    @FXML private TextField nameText;
    @FXML private TextField locationText;
    @FXML private TextField searchField; 
    
    @FXML private Button confirmButton;   
    @FXML private Button addFunctionButton;
    @FXML private Button addNeighbourButton;
    @FXML private Button deleteFunctionButton;
    @FXML private Button deleteNeighbourButton;
    @FXML private Button newAreaButton;

    
    @FXML void handleNewArea() {
        this.newArea();
    }
    
    
    @FXML private void handlePrintArea() {
        this.print(false);
    } 

    
    @FXML private void handlePrintSearch() {   
        this.print(true);
    }                                        
    
    
    @FXML private void handleAddFunction() {
        this.addFunction();
    }
    
    
    @FXML void handleAddNeighbour() {
        this.addNeighbour();
    }
    
    
    @FXML void handleSave() {
        this.save();
    }


    @FXML void handleClose() {
        this.save();
        Platform.exit();
    }


    @FXML void handleDelete() {
        this.delete();
    }
    

    @FXML void handleConditionF() {
        this.condition = 2;
    }

    
    @FXML void handleConditionL() {
        this.condition = 1;
    }
    
    
    @FXML void handleConditionN() {
        this.condition = 0;
    }

    
    @FXML void handleSearch() {
        this.currentSearch = this.searchField.getText().trim();
        if (this.currentSearch == null) this.currentSearch = "";
        this.search(this.currentSearch);
    }
    
    
    @FXML void handleEdit() {
        this.edit();
    }
   

    @FXML void handleEditConfirm() {
        this.confirmEdit();
    }
    
    
    @FXML void handleHelp() {
        this.help();
    }
    
    
    @FXML void handleDeleteNeighbour() {
        this.deleteNeighbour();
    }
    
    @FXML void handleDeleteFunction() {
        this.deleteFunction();
    }

    
    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        this.reset();      
    }

//===============================================================================================
//Muut toteutukset  
    
    private Ba ba;
    private boolean clicked;
    private int condition;
    private Location startLocation;
    private String currentSearch;
    private String folder;
    
    /**
     * @param ba "Brodmannin alueet" kanta joka luodaan
     * @param folder kansio josta luetaan
     */
    public void setBa(Ba ba, String folder) {
        this.ba = ba; // luodaan uusi
        this.folder = folder;
        this.readFile(); // haetaan tiedot 
        this.startLocation = new Location().register().setName("Auto Generated");
        try {
            this.ba.add(startLocation);
        } catch (TilaException e) {
            // jos nimi on jo olemassa (eli luotu ensimm??isell?? ajokerralla) 
            // niin ei tarvitse puuttua asiaan se on jo oikealla paikalla
        }
        this.currentSearch = "";
        this.search(this.currentSearch); // etsit????n
        this.write(); // p??ivitet????n k??ytt??liittym??
    }
    
    
    private void readFile() {
        try {
            this.ba.readFileAll(this.folder);
        } catch (TilaException e) {
            Dialogs.showMessageDialog(e.getMessage());
        }
    }

    
    /**
     * Alustetaan aluevalikko uusilla tiedoilla
     */
    protected void reset() {
        this.chooserAreas.clear(); // tyhjennet????n k??ytt??liittym??n valitsijalista
        this.chooserAreas.addSelectionListener(e -> write()); // kun valitaan alue, p??ivitet????n k??ytt??liittym?? sen tiedoilla
    }


    /**
     * T??ytet????n alueen tiedot n??ytt????n
     * TODO fiksumpi ratkaisu
     */
    protected void write() {
        Area selectedArea = this.chooserAreas.getSelectedObject(); // otetaan listasta valittu alue
        if (selectedArea == null) return; // jos tyhj?? niin h??ipp??st????n
        try {
            // Valitun alueen nimi
            this.nameText.setText(selectedArea.getName());
            // Valitun alueen sijainnin nimi
            this.locationText.setText(ba.getLocationName(selectedArea.getLid())); 
            // hoidellaan teht??v??t
            var funcs = this.ba.findFunctions(selectedArea.getLid()); // pyydet????n etsim????n funktiot joita sijainnilla on
            this.chooserFunctions.clear(); // tyhjennet????n funktio lista k??ytt??liittym??ss??
            for (int i = 0; i < funcs.size(); i++) this.chooserFunctions.add(funcs.get(i).getName(), funcs.get(i)); // Lis??t????n pyydetyt funktiot k??ytt??liittym????n
            // hoidellaan naapurit
            var neighbours = this.ba.findNeighbours(selectedArea.getID()); // pyydet????n etsim????n funktiot joita sijainnilla on
            this.chooserNeighbours.clear(); // tyhjennet????n naapuri lista k??ytt??liittym??ss??
            for (int i = 0; i < neighbours.size(); i++) this.chooserNeighbours.add(neighbours.get(i).getName(), neighbours.get(i)); // Lis??t????n pyydetyt funktiot k??ytt??liittym????n
        } catch (IndexOutOfBoundsException e) { // jos ongelmia niin n??ytet????n mit?? 
            Dialogs.showMessageDialog("Ongelmia: " + e.getMessage()); // jos ei l??ydy
        }
    }
    
    
   /**
    * lis??????n uusi alue 
    */
    protected void newArea() {
        try {
            Area a = new Area().register().setName("Brodmann's Area 00").setLid(0);// luodaan uusi
            this.ba.add(a);
            this.setIndexForAreas(a.getID()); // asetetaan aluelistan indeksi alueelle
        } catch (TilaException e) {
            Dialogs.showMessageDialog("Ongelmia: " + e.getMessage());
        } // pyydet????n ba luokkaa lis????m????n alue alueistoon
    }
    
    
    /**
     * lis??????n uusi sijainti
     * @return uuden sijainnin viitteen
     */
     protected Location newLocation() {
         Location l = new Location();
         try {
            this.ba.add(l);
         } catch (TilaException e) {
            Dialogs.showMessageDialog("Ongelmia lohkojen kanssa: " + e.getMessage());
         } // pyydet????n ba luokkaa lis????m????n sijainti sijainti-listaan 
         l.register();
         return l; // palautetaan luotu sijainti
     }
     

    /**
     * lis??t????n jo olemassa oleva funktio
     */
    public void addFunction() {
         String name = askDialog("Function name: ", "");
         if (name == null || "".equals(name)) return;
         Function f = this.ba.getFunction(name);
         try {
            this.ba.add(new Lf(this.chooserAreas.getSelectedObject().getLid(),f.getID()));
         } catch (TilaException e) {
             Dialogs.showMessageDialog("T??m?? on jo listassa!");
             return;
         }
         this.chooserFunctions.add(name, f);
    }
    
    
    /**
     * lis??t????n jo olemassa oleva funktio
     */
    public void addNeighbour() {
         String name = askDialog("Neighbour name: ", "Brodmann's Area");
         if (name == null || "".equals(name)) return;
         try {
             Area a = this.ba.getArea(name);
             this.ba.add(new Neighbour(this.chooserAreas.getSelectedObject().getID(),a.getID()));
             this.chooserNeighbours.add(name, a);
         } catch (TilaException e) {
             Dialogs.showMessageDialog("Ongelmia: " + e.getMessage());
             return;
         }
    }
     
    
    /**
     * poistetaan naapuri
     */
    public void deleteNeighbour() {
        if (this.chooserNeighbours.getSelectedObject() == null) return;    // 
                                                                           // 
        int a = this.chooserNeighbours.getSelectedObject().getID();        // 
        int b = this.chooserAreas.getSelectedObject().getID();             // 
                                                                           // 
        this.ba.deleteNeighbour(a,b);                                      // 
        this.search(this.currentSearch);                                   // 
    }                                                                      // 
                                                                           // 
                                                                           // 
    /**                                                                    // N??ytt???? samalta ==> 
     * poistetaan lohko funktio pari                                       // poistaminen saman rajapinnan kautta 
     */                                                                    // 
    public void deleteFunction() {                                         // 
        if (this.chooserFunctions.getSelectedObject() == null) return;     // 
                                                                           // 
        int l = this.chooserAreas.getSelectedObject().getLid();            // 
        int f = this.chooserFunctions.getSelectedObject().getID();         // 
                                                                           // 
        this.ba.deleteLf(l,f);                                             // 
        this.search(this.currentSearch);                                   // 
    }
    
    
     
    /**
     * luodaan dialogi joka paluttaa annetun tekstin 
     * @param question kysymys joka kysyt????n
     * @param modelText teksti joka on mallina 
     * @return tekstin jonka k??ytt??j?? antaa
     */
    public static String askDialog(String question, String modelText) {
        TextInputDialog dialog = new TextInputDialog(modelText);
        dialog.setHeaderText(null);
        dialog.setTitle("Answer");
        dialog.setContentText(question);
        Optional<String> answer = dialog.showAndWait();
        return answer.isPresent() ? answer.get().trim() : null;
    }
          
    
    /**
     * @param aid Alueen tunnusnumero
     */
    protected void setIndexForAreas(int aid) {
        this.chooserAreas.clear(); // tyhjennet????n alueiden lista k??ytt??liittym??st??
        int index = 0; // alkuindeksi
        for (int i = 0; i < ba.getAreaCount(); i++) {  // k??yd????n alueet l??pi
            Area area = ba.getArea(i); // alue jonka kohdalla ollaan k??ym??ss?? l??pi 
            if (area.getID() == aid) index = i; // jos t??sm????, alustetaan indeksi alueiston indeksin mukaan
            this.chooserAreas.add(area.getName(), area); // p??ivitet????n k??ytt??liittym??
        }
        this.chooserAreas.setSelectedIndex(index); // ja hyp??t????n listassa ??sken tarkistettuun kohtaan
     }
    
    
    /**
     * tallennetaan kaikki tallennettavissa olevat tiedot,
     * muokattava tai lis??tt??v?? alue hypp???? listaan 
     * @return jos ongelmia palautetaan mik?? meni pieleen
     */
    public String save() {
        try {
            this.ba.saveAll(this.folder);
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
        if (selectedArea == null) return;
        boolean accept = Dialogs.showQuestionDialog("Delete", "Delete: " + selectedArea.getName() + "?",
                                  "Delete", "Don't Delete");
        if (!accept) return;
        this.ba.delete(selectedArea);
        this.search(this.currentSearch);
    }

    
    private void clearFields() {
        this.chooserAreas.clear();
        this.chooserFunctions.clear();
        this.chooserNeighbours.clear();
        this.nameText.clear();
        this.locationText.clear();
    }
    
    
    /**
     * haetaan kauehdon ja hakuent??n tiedoilla sopivat tiedot
     * @param search haku
     */
    public void search(String search) {
        this.clearFields();
        int index = 0;
        
        for (int i = 0; i < this.ba.getAreaCount(); i++) {
            Area area = this.ba.getArea(i);
            String name = area.getName();
            
            if (condition == 1) name = this.ba.getLocationName(area.getLid());
            
            if (condition == 2) {
                for ( var function : this.ba.findFunctions(area.getLid()) ) {
                    if (function.getName().toLowerCase().matches(".*" + search.toLowerCase() + ".*")) { 
                        this.chooserAreas.add(area.getName(), area);
                        break;
                    }
                }
                continue;
            }
                
             
            if (name.toLowerCase().matches(".*" + search.toLowerCase() + ".*")) { 
                index = i;
                this.chooserAreas.add(area.getName(), area);
            }
        }
        
        this.chooserAreas.setSelectedIndex(index); 
    }
    
    
    /**
     * Vaihdetaan muokkaustila joko p????lle tai pois riippuen ohjelman tilasta,
     * jos muokkaus p????ll?? kusuminen kytkee sen pois, 
     * jos ei p????ll?? niin se laitetaan p????lle.
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
        
        // Onko alue jo olemassa
        String futureName = this.nameText.getText();
        if (this.ba.duplicateCheck(selectedArea.getID(), futureName)) {
            Dialogs.showMessageDialog("Saman niminen alue on jo olemassa! " + futureName);
            return;
        }
        
        // Onko nimi oikein kirjoitettu
        try {
            selectedArea.setName(futureName);
        } catch (TilaException e) {
            Dialogs.showMessageDialog("Tarkista nimen kirjoitus! " + futureName);
        }
        
        // Tarkistetaan onko location olemassa ja jos ei niin luodaan sen niminen
        Location location = ba.getLocation(this.locationText.getText());
        selectedArea.setLid(location.getID());
        this.editOff();
    }
    
   
    private void editOn() {
        this.nameText.getStyleClass().add("edit");                //
        this.nameText.editableProperty().set(true);               //
        this.locationText.getStyleClass().add("edit");            //
        this.locationText.editableProperty().set(true);           //
        this.confirmButton.visibleProperty().set(true);           //
        this.addFunctionButton.visibleProperty().set(false);      //
        this.addNeighbourButton.visibleProperty().set(false);     //
        this.deleteFunctionButton.visibleProperty().set(false);   //
        this.deleteNeighbourButton.visibleProperty().set(false);  //
        this.newAreaButton.visibleProperty().set(false);          //
    }                                                             //
                                                                  //
                                                                  //
    private void editOff() {                                      // TODO taulukoista
        int i = this.chooserAreas.getSelectedIndex();             //
        this.nameText.getStyleClass().removeAll("edit");          //
        this.nameText.editableProperty().set(false);              //
        this.locationText.getStyleClass().removeAll("edit");      //
        this.locationText.editableProperty().set(false);          //
        this.confirmButton.visibleProperty().set(false);          //
        this.addFunctionButton.visibleProperty().set(true);       //
        this.addNeighbourButton.visibleProperty().set(true);      //
        this.deleteFunctionButton.visibleProperty().set(true);    //
        this.deleteNeighbourButton.visibleProperty().set(true);   //
        this.newAreaButton.visibleProperty().set(true);           //
        this.search(this.currentSearch);                          //
        this.chooserAreas.setSelectedIndex(i);                    //
    }                                                             
    
    
    private void print(boolean isSearch) {
        Area area = chooserAreas.getSelectedObject();
        if (area == null) return; 
        
        if (isSearch) {
            StringBuilder areas = new StringBuilder();
            for (var item : this.chooserAreas.getItems()) 
                areas.append(ba.toString(item.getObject()));
            PrintViewController.print(areas.toString());
            return;
        }
        
        PrintViewController.print(ba.toString(area));
    }
    
    
    /**
     * N??ytet????n ohjelman suunnitelma selaimessa.
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
}