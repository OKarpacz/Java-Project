package view;

import model.Book;
import model.Library;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibraryGUI extends JFrame {
    private Library library;
    private JTextField titleField, authorField, genreField, searchField, removeField, favoriteField;
    private JTextArea resultArea;

    public LibraryGUI(Library library) {
        this.library = library;
        initialize();
    }

    private void initialize() {
        setTitle("Library Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new GridLayout(1, 2));
        JPanel leftPanel = new JPanel(new GridLayout(3, 1));
        JPanel rightPanel = new JPanel(new BorderLayout());

        JPanel addPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        addPanel.setBorder(BorderFactory.createTitledBorder("Add Book"));

        addPanel.add(new JLabel("Title:"));
        titleField = new JTextField();
        addPanel.add(titleField);

        addPanel.add(new JLabel("Author:"));
        authorField = new JTextField();
        addPanel.add(authorField);

        addPanel.add(new JLabel("Genre:"));
        genreField = new JTextField();
        addPanel.add(genreField);

        JButton addButton = new JButton("Add Book");
        addPanel.add(addButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String title = titleField.getText().trim();
                    String author = authorField.getText().trim();
                    String genre = genreField.getText().trim();

                    if (title.isEmpty() || author.isEmpty() || genre.isEmpty()) {
                        JOptionPane.showMessageDialog(LibraryGUI.this, "All fields must be filled out.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        library.addBook(new Book(title, author, genre));
                        titleField.setText("");
                        authorField.setText("");
                        genreField.setText("");
                        updateResultArea();
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(LibraryGUI.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JPanel removePanel = new JPanel(new GridLayout(2, 2, 5, 5));
        removePanel.setBorder(BorderFactory.createTitledBorder("Remove Book"));

        removePanel.add(new JLabel("Title:"));
        removeField = new JTextField();
        removePanel.add(removeField);

        JButton removeButton = new JButton("Remove Book");
        removePanel.add(removeButton);

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String title = removeField.getText().trim();
                    if (title.isEmpty()) {
                        JOptionPane.showMessageDialog(LibraryGUI.this, "Title field must be filled out.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        library.removeBook(title);
                        removeField.setText("");
                        updateResultArea();
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(LibraryGUI.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JPanel favoritePanel = new JPanel(new GridLayout(2, 2, 5, 5));
        favoritePanel.setBorder(BorderFactory.createTitledBorder("Mark as Favorite"));

        favoritePanel.add(new JLabel("Title:"));
        favoriteField = new JTextField();
        favoritePanel.add(favoriteField);

        JButton favoriteButton = new JButton("Mark as Favorite");
        favoritePanel.add(favoriteButton);

        favoriteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String title = favoriteField.getText().trim();
                    if (title.isEmpty()) {
                        JOptionPane.showMessageDialog(LibraryGUI.this, "Title field must be filled out.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        library.markAsFavorite(title);
                        favoriteField.setText("");
                        updateResultArea();
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(LibraryGUI.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        leftPanel.add(addPanel);
        leftPanel.add(removePanel);
        leftPanel.add(favoritePanel);

        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.setBorder(BorderFactory.createTitledBorder("Search Books"));

        searchField = new JTextField();
        searchPanel.add(searchField, BorderLayout.CENTER);

        JButton searchButton = new JButton("Search");
        searchPanel.add(searchButton, BorderLayout.EAST);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateResultArea();
            }
        });

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        rightPanel.add(searchPanel, BorderLayout.NORTH);
        rightPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);

        add(mainPanel, BorderLayout.CENTER);
    }

    private void updateResultArea() {
        String keyword = searchField.getText().trim();
        StringBuilder results = new StringBuilder();
        for (Book book : library.searchBooks(keyword)) {
            results.append(book).append("\n");
        }
        resultArea.setText(results.toString());
    }
}
