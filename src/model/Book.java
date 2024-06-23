package model;

public class Book {
    private String title;
    private String author;
    private String genre;
    private boolean favorite;

    public Book(String title, String author, String genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.favorite = false;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }
    public boolean isFavorite() { return favorite; }
    public void setFavorite(boolean favorite) { this.favorite = favorite; }

    @Override
    public String toString() {
        return title + " by " + author + " (" + genre + ")" + (favorite ? " [Favorite]" : "");
    }
}
