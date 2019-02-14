package com.example.MoodVerse;

public class Movie {

    private String title;
    private long year;
    private Color color;
    private String imdb;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getYear() {
        return year;
    }

    public void setYear(long year) {
        this.year = year;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Movie() {
    }

    public Movie(String title, long year, Color color, String imdb) {
        this.title = title;
        this.year = year;
        this.color = color;
        this.imdb = imdb;
    }

}//end of class


