package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.MusicItem;
import controller.WestminsterMusicStoreManager;

public class GUI extends Application {
    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
//        VBox parent = new VBox();
//        parent.getChildren().add(new Label("Hey there folks!"));

        primaryStage.setTitle("List of items in store");
//        primaryStage.setHeight(800);

        TableView tableOfItems = new TableView();

        TableColumn<String, MusicItem> itemIDColumn = new TableColumn<>("Item ID");
        itemIDColumn.setCellValueFactory(new PropertyValueFactory<>("itemID"));

        TableColumn<String, MusicItem> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<String, MusicItem> genreColumn = new TableColumn<>("Genre");
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));

        TableColumn<String, MusicItem> dateColumn = new TableColumn<>("Release Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));

        TableColumn<String, MusicItem> artistColumn = new TableColumn<>("Artist");
        artistColumn.setCellValueFactory(new PropertyValueFactory<>("artist"));

        TableColumn<String, MusicItem> durationColumn = new TableColumn<>("Duration");
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));

        TableColumn<String, MusicItem> speedColumn = new TableColumn<>(" Speed");
        speedColumn.setCellValueFactory(new PropertyValueFactory<>("speed"));

        TableColumn<String, MusicItem> diameterColumn = new TableColumn<>(" Diameter");
        diameterColumn.setCellValueFactory(new PropertyValueFactory<>("diameter"));

        tableOfItems.getColumns().addAll(itemIDColumn, titleColumn, genreColumn, dateColumn, artistColumn, durationColumn, speedColumn, diameterColumn);


        tableOfItems.getItems().addAll(WestminsterMusicStoreManager.getItemsInStore());


        VBox parent = new VBox(tableOfItems);
        Scene newScene = new Scene(parent);
        primaryStage.setScene(newScene);
        primaryStage.show();
    }
}
