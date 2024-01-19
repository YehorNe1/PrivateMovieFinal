package gui;

import be.Category;
import dal.DatabaseData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Window3Controller implements Initializable {

    @FXML
    public TextField textFieldCatId, textFieldCatName;

    private Window1Controller window1Controller;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setWindow1Controller(Window1Controller window1Controller) {
        this.window1Controller = window1Controller;
    }

    @FXML
    private void buttonToSaveCategory() {
        int id = Integer.parseInt(textFieldCatId.getText());
        String name = textFieldCatName.getText();

        Category newCategory = new Category(id, name);
        window1Controller.updateTheTreeViewCategories(newCategory);

    }
}
