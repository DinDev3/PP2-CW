import java.util.ArrayList;

public class WestminsterMusicStoreManager {
    static ArrayList <String> items= new ArrayList<>();
    static ArrayList <String> shoppingCart= new ArrayList<>();

    public static void main(String[] args) {
        MusicItem newItem = new MusicItem() {
            @Override
            String itemID() {
                return null;
            }

            @Override
            String title() {
                return null;
            }

            @Override
            String genre() {
                return null;
            }

            @Override
            Date releaseDate() {
                return null;
            }

            @Override
            String artist() {
                return null;
            }

            @Override
            Double price() {
                return null;
            }
        }
    }
}
