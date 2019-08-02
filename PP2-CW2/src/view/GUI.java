package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
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

        TableColumn<String, MusicItem> priceColumn = new TableColumn<>("Price($)");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<String, MusicItem> typeColumn = new TableColumn<>("Type");
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<String, MusicItem> durationColumn = new TableColumn<>("Duration");
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("durationOfSong"));

        TableColumn<String, MusicItem> speedColumn = new TableColumn<>(" Speed(RPM)");
        speedColumn.setCellValueFactory(new PropertyValueFactory<>("speed"));

        TableColumn<String, MusicItem> diameterColumn = new TableColumn<>(" Diameter");
        diameterColumn.setCellValueFactory(new PropertyValueFactory<>("diameter"));

        tableOfItems.getColumns().addAll(itemIDColumn, titleColumn, genreColumn, dateColumn, artistColumn, priceColumn, typeColumn, durationColumn, speedColumn, diameterColumn);


        tableOfItems.getItems().addAll(WestminsterMusicStoreManager.getItemsInStore());

        //-----------------------------------------
        VBox searchSection = new VBox();
        searchSection.setMinWidth(250);

        searchSection.getChildren().add(new Label("Search Title:"));

        HBox searchInput = new HBox();
        searchInput.setMinWidth(220);
        searchSection.getChildren().add(searchInput);

        searchInput.getChildren().add(new TextField());
        searchInput.getChildren().add(new Button("Search"));


        HBox idOutput = new HBox();
        searchSection.getChildren().add(idOutput);
        idOutput.getChildren().add(new Label("Item ID: "));
        idOutput.getChildren().add(new Label());    //display searched!!!

        HBox titleOutput = new HBox();
        searchSection.getChildren().add(titleOutput);
        titleOutput.getChildren().add(new Label("Title: "));
        titleOutput.getChildren().add(new Label());    //display searched!!!

        HBox genreOutput = new HBox();
        searchSection.getChildren().add(genreOutput);
        genreOutput.getChildren().add(new Label("Genre: "));
        genreOutput.getChildren().add(new Label());    //display searched!!!

        HBox releaseOutput = new HBox();
        searchSection.getChildren().add(releaseOutput);
        releaseOutput.getChildren().add(new Label("Release Date: "));
        releaseOutput.getChildren().add(new Label());    //display searched!!!

        HBox artistOutput = new HBox();
        searchSection.getChildren().add(artistOutput);
        artistOutput.getChildren().add(new Label("Artist: "));
        artistOutput.getChildren().add(new Label());    //display searched!!!

        HBox priceOutput = new HBox();
        searchSection.getChildren().add(priceOutput);
        priceOutput.getChildren().add(new Label("Price($): "));
        priceOutput.getChildren().add(new Label());    //display searched!!!

        HBox typeOutput = new HBox();
        searchSection.getChildren().add(typeOutput);
        typeOutput.getChildren().add(new Label("Type: "));
        typeOutput.getChildren().add(new Label());    //display searched!!!

        HBox durationOutput = new HBox();
        searchSection.getChildren().add(durationOutput);
        durationOutput.getChildren().add(new Label("Duration: "));
        durationOutput.getChildren().add(new Label());    //display searched!!!

        HBox speedOutput = new HBox();
        searchSection.getChildren().add(speedOutput);
        speedOutput.getChildren().add(new Label("Speed(RPM): "));
        speedOutput.getChildren().add(new Label());    //display searched!!!

        HBox diameterOutput = new HBox();
        searchSection.getChildren().add(diameterOutput);
        diameterOutput.getChildren().add(new Label("Diameter: "));
        diameterOutput.getChildren().add(new Label());    //display searched!!!


        //-----------------------------------------

        HBox parent = new HBox(tableOfItems,searchSection);
        Scene newScene = new Scene(parent);
        primaryStage.setScene(newScene);
        primaryStage.show();
    }
}
