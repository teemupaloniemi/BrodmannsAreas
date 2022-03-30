package fxBa;

import ba.Area;
import ba.Ba;
import fi.jyu.mit.fxgui.ListChooser;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;


/**
 * Tulostus luokka
 * @author Teemu
 * @version 13.2.2022
 */
public class PrintViewController implements ModalControllerInterface<String> {
    @FXML TextArea printTextArea;
    
    @FXML private void handleOK() {
        ModalController.closeStage(printTextArea);
    }
    
    
    @FXML private void handleCancel() {
        ModalController.closeStage(printTextArea);
    }

    
    @Override
    public String getResult() {
        return null;
    } 

    
    @Override
    public void setDefault(String oletus) {
        if ( oletus == null ) return;
        printTextArea.setText(oletus);
    }

    
    /**
     * Näyttää tulostusalueessa tekstin
     * @param chooser chooser josta tulostettava tieto löytyy
     * @param ba ba joka hakee choooserin alkioiden tiedot
     * @param isSearch jos true tulostetaan vain kaikki alueet jota chooserissa
     */
    public static void print(ListChooser<Area> chooser, Ba ba, boolean isSearch) {
        Area area = chooser.getSelectedObject();
        if (area == null) return; 
        if (isSearch) {
            StringBuilder areas = new StringBuilder();
            for (var item : chooser.getItems()) areas.append(item.getName()+"\n  ");
            ModalController.showModeless(PrintViewController.class.getResource("PrintView.fxml"),
                    "Print", String.format("Searched areas:\n\n  %s", areas));
            return;
        }
        int lid = area.getLid();
        StringBuilder functions  = new StringBuilder();
        StringBuilder neighbours = new StringBuilder();
        for (var func : ba.findFunctions(lid)) functions.append(func.getName()+"\n  "); 
        for (var neig : ba.findNeighbours(area.getID())) neighbours.append(neig.getName()+"\n  "); 
        ModalController.showModeless(PrintViewController.class.getResource("PrintView.fxml"),
                "Print", String.format("Name: %s\nLocation: %s\n\nFunctions:\n  %s\nNeighbours:\n  %s", area.getName(), ba.getLocationName(area.getLid()), functions.toString(), neighbours.toString()));
    }
    
    
    @Override
    public void handleShown() {
        // TODO Auto-generated method stub
    }

}
