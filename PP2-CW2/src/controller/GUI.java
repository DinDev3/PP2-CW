package controller;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.MusicItem;

import static controller.WestminsterMusicStoreManager.itemsInStore;


public class GUI extends Application {
    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("List of items in store");

        TableView tableOfItems = new TableView();

        TableColumn<String, MusicItem> itemIDColumn = new TableColumn<>("Item ID");
        itemIDColumn.setCellValueFactory(new PropertyValueFactory<>("itemID"));

        TableColumn<String, MusicItem> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<String, MusicItem> genreColumn = new TableColumn<>("Genre");
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));

        TableColumn<String, MusicItem> dateColumn = new TableColumn<>("Release Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));
        dateColumn.setMinWidth(100);

        TableColumn<String, MusicItem> artistColumn = new TableColumn<>("Artist");
        artistColumn.setCellValueFactory(new PropertyValueFactory<>("artist"));

        TableColumn<String, MusicItem> priceColumn = new TableColumn<>("Price($)");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<String, MusicItem> typeColumn = new TableColumn<>("Type");
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<String, MusicItem> durationColumn = new TableColumn<>("Duration(min)");
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("durationOfSong"));
        durationColumn.setMinWidth(120);

        TableColumn<String, MusicItem> speedColumn = new TableColumn<>(" Speed(RPM)");
        speedColumn.setCellValueFactory(new PropertyValueFactory<>("speed"));
        speedColumn.setMinWidth(100);

        TableColumn<String, MusicItem> diameterColumn = new TableColumn<>(" Diameter(cm)");
        diameterColumn.setCellValueFactory(new PropertyValueFactory<>("diameter"));
        diameterColumn.setMinWidth(120);

        tableOfItems.getColumns().addAll(itemIDColumn, titleColumn, genreColumn, dateColumn, artistColumn, priceColumn, typeColumn, durationColumn, speedColumn, diameterColumn);


        tableOfItems.getItems().addAll(WestminsterMusicStoreManager.getItemsInStore());

        //-----------------------------------------
        HBox searchSection = new HBox();
        searchSection.setMinWidth(220);
        searchSection.getChildren().add(new Label("Search Title:"));

        TextField titleSearch = new TextField();
        searchSection.getChildren().add(titleSearch);

        Button searchClick = new Button("Search");
        searchSection.getChildren().add(searchClick);
        Button resetClick = new Button("Reset");
        searchSection.getChildren().add(resetClick);

        //-----------------------------------------


        searchClick.setOnAction(new EventHandler<ActionEvent>() {           //actions when search button is clicked

            @Override
            public void handle(ActionEvent event) {

                MusicItem itemToBeBought = findItem(titleSearch.getText());
                System.out.println(itemToBeBought);

                tableOfItems.getItems().clear();
                tableOfItems.getItems().add(itemToBeBought);

            }
        });

        resetClick.setOnAction(new EventHandler<ActionEvent>() {           //actions when reset button is clicked

            @Override
            public void handle(ActionEvent event) {

                tableOfItems.getItems().clear();
                tableOfItems.getItems().addAll(itemsInStore);

                titleSearch.setText("");
            }
        });

        //-----------------------------------------

        VBox parent = new VBox(searchSection,tableOfItems);
        Scene newScene = new Scene(parent);
        primaryStage.setScene(newScene);
        primaryStage.show();
    }

    private static MusicItem findItem(String searchTitle) {
        for (MusicItem searchItem : itemsInStore) {
            if (searchItem.getTitle().equals(searchTitle)) {
                return searchItem;
            }
        }
        return null;
    }
}
