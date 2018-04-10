package com.example.android.takehomeassignment10_yanranw;
public class Song {
    String title;
    String lyrics;
    int year;
    boolean available;

    public Song(){

    }

    public Song(String title,String lyrics,int year,boolean available){
        this.title = title;
        this.lyrics = lyrics;
        this.year = year;
        this.available = available;
    }
    @Override
    public String toString(){
        return "Song{" + "title=' " + title + "\'" + ", lyrics=' " + lyrics + "\'" + ", year=' " + year + "\'" + ", available=' " + available + "}";
    }
    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }
}
