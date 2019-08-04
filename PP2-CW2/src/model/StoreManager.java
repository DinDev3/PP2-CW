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

/*
References:
https://www.javatpoint.com/abstract-class-in-java
https://docs.oracle.com/javase/tutorial/java/IandI/abstract.html
https://docs.oracle.com/javase/tutorial/java/IandI/createinterface.html
*/