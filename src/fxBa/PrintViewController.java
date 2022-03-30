package fxBa;

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
     * @param print teksti joka printataan
     */
    public static void print(String print) {
        ModalController.showModeless(PrintViewController.class.getResource("PrintView.fxml"),
                "Print", print);
    }
    
    
    @Override
    public void handleShown() {
        // TODO Auto-generated method stub
    }

}
