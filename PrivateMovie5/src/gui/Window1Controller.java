package gui;

import be.Category;
import be.Movie;
import dal.DatabaseData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Window1Controller implements Initializable {

    @FXML
    private Label labelMovieId, labelMovieTitle, labelMovieRating, labelMovieFileLink, labelMoviePersonalRating;
    @FXML
    private TreeView<String> treeView;
    @FXML
    private MediaView mediaViewPreview;
    @FXML
    private TableColumn<Movie, String> columnId, columnTitle, columnRating, columnFileLink, columnPersonalRating;
    @FXML
    private TableView<Movie> tableView;

    private TreeItem<String> root;
    private DatabaseData databaseData;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        root = new TreeItem<>("Movies", new ImageView(new Image(getClass().getResource("billede1.png").toString())));
        treeView.setRoot(root);

        databaseData = new DatabaseData();
        List<Category> categories = databaseData.getAllDataOfCategories();

        for (Category category : categories) {
            TreeItem<String> categoryTreeItem = new TreeItem<>(category.getName(), new ImageView(new Image(getClass().getResource("billede1.png").toString())));
            root.getChildren().add(categoryTreeItem);

            List<Movie> movies = databaseData.getAllDataOfMovies(category.getId());

            for (Movie movie : movies) {
                TreeItem<String> movieTreeItem = new TreeItem<>(movie.getTitle(), new ImageView(new Image(getClass().getResource("billede3.png").toString())));
                categoryTreeItem.getChildren().add(movieTreeItem);

                getTheMoviePreview(movie);
                openTheMovieToWatch(movie);
            }
        }

        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        columnRating.setCellValueFactory(new PropertyValueFactory<>("rating"));
        columnFileLink.setCellValueFactory(new PropertyValueFactory<>("fileLink"));
        columnPersonalRating.setCellValueFactory(new PropertyValueFactory<>("personalRating"));

        tableView.getItems().addAll(databaseData.getAllMovies());
    }

    private void getTheMoviePreview(Movie movie) {
        treeView.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getClickCount() == 1) {
                TreeItem<String> moviePreview = treeView.getSelectionModel().getSelectedItem();
                if (moviePreview != null && moviePreview.getValue().equals(movie.getTitle())) {
                    File file = new File(movie.getFileLink());
                    Media media = new Media(file.toURI().toString());
                    MediaPlayer mediaPlayer = new MediaPlayer(media);
                    mediaViewPreview.setMediaPlayer(mediaPlayer);

                    labelMovieId.setText(String.valueOf("Movie id:" + movie.getId()));
                    labelMovieTitle.setText("Movie title:" + movie.getTitle());
                    labelMovieRating.setText(String.valueOf("Movie rating:" + movie.getRating()));
                    labelMovieFileLink.setText("Movie fileLink:" + movie.getFileLink());
                    labelMoviePersonalRating.setText(String.valueOf("Movie personalRating:" + movie.getPersonalRating()));
                }
            }
        });
    }

    private void openTheMovieToWatch(Movie movie) {
        treeView.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getClickCount() == 2) {
                TreeItem<String> movieToWatch = treeView.getSelectionModel().getSelectedItem();
                if (movieToWatch != null && movieToWatch.getValue().equals(movie.getTitle())) {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("Window2.fxml"));
                        AnchorPane root = loader.load();
                        Scene scene = new Scene(root);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.show();

                        Window2Controller window2Controller = loader.getController();
                        window2Controller.setMovieToWatch(movie);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @FXML
    private void buttonAddCat() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Window3.fxml"));
            AnchorPane root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

            Window3Controller window3Controller = loader.getController();
            window3Controller.setWindow1Controller(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateTheTreeViewCategories(Category category) {
        TreeItem<String> newCategory = new TreeItem<>(category.getName(), new ImageView(new Image(getClass().getResource("billede1.png").toString())));
        root.getChildren().add(newCategory);
    }

    @FXML
    private void buttonRemoveCat() {
        root.getChildren().remove(treeView.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void buttonAddMovie() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Window4.fxml"));
            AnchorPane root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

            Window4Controller window4Controller = loader.getController();
            window4Controller.setWindow1Controller(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateTheTreeViewMovies(Category category, Movie movie) {
        TreeItem<String> categoryTreeItem = findCategoryTreeItem(category);
        if (categoryTreeItem != null) {
            TreeItem<String> newMovie = new TreeItem<>(movie.getTitle(), new ImageView(new Image(getClass().getResource("billede3.png").toString())));
            categoryTreeItem.getChildren().add(newMovie);
        }
    }

    @FXML
    private void buttonRemoveMovie() {
        TreeItem<String> selectedItem = treeView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            TreeItem<String> parent = selectedItem.getParent();
            if (parent != null) {
                parent.getChildren().remove(selectedItem);
            }
        }
    }

    private TreeItem<String> findCategoryTreeItem(Category category) {
        for (TreeItem<String> item : root.getChildren()) {
            if (item.getValue().equals(category.getName())) {
                return item;
            }
        }
        return null;
    }
}
