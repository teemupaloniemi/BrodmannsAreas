package ba;

import fi.jyu.mit.fxgui.Dialogs;
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

    
    @FXML private void print() {
        Dialogs.showMessageDialog("Ei osata hakea tietoja!");
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
     * @param tulostus tulostettava teskti
     */
    public static void print(String tulostus) {
        ModalController.showModeless(PrintViewController.class.getResource("PrintView.fxml"),
                "Print", tulostus);
    }
    
    
    @Override
    public void handleShown() {
        // TODO Auto-generated method stub
    }

}
