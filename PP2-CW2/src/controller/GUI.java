package controller;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.CD;
import model.Date;
import model.MusicItem;

import static controller.WestminsterMusicStoreManager.itemsInStore;
import static controller.WestminsterMusicStoreManager.linearSearch;

@SuppressWarnings("Duplicates")

public class GUI extends Application {
    private static String itemIDVar;
    private static  String typeVar;
    private static String genreVar;
    private static Date dateVar;           //object is created here as it's required to be accessed from several methods in this class
    private static String artistVar;
    private static double priceVar;
    private static double durationVar;
    private static double speedVar;
    private static double diameterVar;
    private static double totalCostVar;
    private static String titleVar;



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

        TextField titleSearch = new TextField();
        searchInput.getChildren().add(titleSearch);

        Button searchClick = new Button("Search");
        searchInput.getChildren().add(searchClick);


        HBox idOutput = new HBox();
        searchSection.getChildren().add(idOutput);
        idOutput.getChildren().add(new Label("Item ID: "));
        Label idSelected = new Label();
        idOutput.getChildren().add(idSelected);

        HBox titleOutput = new HBox();
        searchSection.getChildren().add(titleOutput);
        titleOutput.getChildren().add(new Label("Title: "));
        Label titleSelected = new Label();
        titleOutput.getChildren().add(titleSelected);

        HBox genreOutput = new HBox();
        searchSection.getChildren().add(genreOutput);
        genreOutput.getChildren().add(new Label("Genre: "));
        Label genreSelected = new Label();
        genreOutput.getChildren().add(genreSelected);

        HBox releaseOutput = new HBox();
        searchSection.getChildren().add(releaseOutput);
        releaseOutput.getChildren().add(new Label("Release Date: "));
        Label releaseSelected = new Label();
        releaseOutput.getChildren().add(releaseSelected);

        HBox artistOutput = new HBox();
        searchSection.getChildren().add(artistOutput);
        artistOutput.getChildren().add(new Label("Artist: "));
        Label artistSelected = new Label();
        artistOutput.getChildren().add(artistSelected);

        HBox priceOutput = new HBox();
        searchSection.getChildren().add(priceOutput);
        priceOutput.getChildren().add(new Label("Price($): "));
        Label priceSelected = new Label();
        priceOutput.getChildren().add(priceSelected);

        HBox typeOutput = new HBox();
        searchSection.getChildren().add(typeOutput);
        typeOutput.getChildren().add(new Label("Type: "));
        Label typeSelected = new Label();
        typeOutput.getChildren().add(typeSelected);

        HBox durationOutput = new HBox();
        searchSection.getChildren().add(durationOutput);
        durationOutput.getChildren().add(new Label("Duration: "));
        Label durationSelected = new Label();
        durationOutput.getChildren().add(durationSelected);

        HBox speedOutput = new HBox();
        searchSection.getChildren().add(speedOutput);
        speedOutput.getChildren().add(new Label("Speed(RPM): "));
        Label speedSelected = new Label();
        speedOutput.getChildren().add(speedSelected);

        HBox diameterOutput = new HBox();
        searchSection.getChildren().add(diameterOutput);
        diameterOutput.getChildren().add(new Label("Diameter: "));
        Label diameterSelected = new Label();
        diameterOutput.getChildren().add(diameterSelected);


        //-----------------------------------------


        //Always item added first is given!!!!! not finding any object???????????
        //!!!!!!!!!!IF ITEM ISN'T IN ARRAYLIST, PROGRAM CRASHES!!!!!!!!!!!!!!!!!!

        MusicItem searchMusicItem = new CD(itemIDVar, titleSearch.getText(), genreVar, dateVar, artistVar, priceVar, typeVar, durationVar);
        MusicItem.count -= 1;         //making sure that count isn't increased for the temporary object created
        MusicItem itemToBeBought = itemsInStore.get(linearSearch(itemsInStore, searchMusicItem));

        searchClick.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                idSelected.setText(itemToBeBought.getItemID());
                titleSelected.setText(itemToBeBought.getTitle());
                genreSelected.setText(itemToBeBought.getGenre());
                releaseSelected.setText(itemToBeBought.getReleaseDate().toString());
                artistSelected.setText(itemToBeBought.getArtist());
//                priceSelected.setText(itemToBeBought.getPrice().toString);                    //FIX AFTER CHANGING TO BIG DECIMAL!!!!!
                typeSelected.setText(itemToBeBought.getType());
//                durationSelected.setText(itemToBeBought.getDurationOfSong());
                speedSelected.setText("-");
                diameterSelected.setText("-");
            }
        });

        //-----------------------------------------

        HBox parent = new HBox(tableOfItems, searchSection);
        Scene newScene = new Scene(parent);
        primaryStage.setScene(newScene);
        primaryStage.show();
    }

    private static void searchInfo(){

    }
}
