package com.example.MoodVerse;

import java.util.ArrayList;

public class User {

    private String userName;
    private String password;
    private ArrayList<Color> moodHistory;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Color> getMoodHistory() {
        return moodHistory;
    }

    public void setMoodHistory(Color moodHistory) {
        this.moodHistory.add(moodHistory);
    }

    public User(){
        //this.moodHistory = new ArrayList<>(); Think about it
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.moodHistory = new ArrayList<>();
    }


}//end of class
