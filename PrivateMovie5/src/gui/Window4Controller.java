package gui;

import be.Category;
import be.Movie;
import dal.DatabaseData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Window4Controller implements Initializable {

    @FXML
    private TextField textFieldId, textFieldTitle, textFieldRating, textFieldFileLink, textFieldPersonalRating;
    @FXML
    private ComboBox<String> comboBoxCategories;

    private Window1Controller window1Controller;
    private DatabaseData databaseData;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        databaseData = new DatabaseData();
        List<Category> categories = databaseData.getAllDataOfCategories();
        for (Category category : categories) {
            comboBoxCategories.getItems().add(category.getName());
        }
    }

    public void setWindow1Controller(Window1Controller window1Controller) {
        this.window1Controller = window1Controller;
    }

    @FXML
    private void buttonToGetTheMoviePath() {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            textFieldFileLink.setText(file.getAbsolutePath());
        }
    }

    @FXML
    private void buttonToSaveMovie() {
        try {
            int id = Integer.parseInt(textFieldId.getText());
            String title = textFieldTitle.getText();
            double rating = Double.parseDouble(textFieldRating.getText());
            String fileLink = textFieldFileLink.getText();
            double personalRating = Double.parseDouble(textFieldPersonalRating.getText());

            String categoryName = comboBoxCategories.getValue();
            Category selectedCategory = databaseData.getCategoryByName(categoryName);

            Movie movie = new Movie(id, title, rating, fileLink, personalRating);
            window1Controller.updateTheTreeViewMovies(selectedCategory, movie);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
