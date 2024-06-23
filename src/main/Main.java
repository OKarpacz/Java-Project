package main;

import model.Library;
import view.LibraryGUI;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        LibraryGUI libraryGUI = new LibraryGUI(library);
        libraryGUI.setVisible(true);
    }
}
