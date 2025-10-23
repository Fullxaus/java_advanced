package ru.mentee.power.streams;

import java.util.List;

public class Book {
    private String title;
    private String author;
    private int year;
    private List<String> genres;

    public Book(String title, String author, int year, List<String> genres) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.genres = genres;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public List<String> getGenres() {
        return genres;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", genres=" + genres +
                '}';
    }
}
