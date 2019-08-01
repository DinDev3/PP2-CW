package model;

public interface StoreManager {
    //constants
     int MAX_ITEMS = 1000;


    //methods
    void addItem();
    void deleteItem();
    void printList();
    void sortItem();
    void buyItem();
    void viewGUI();

}
