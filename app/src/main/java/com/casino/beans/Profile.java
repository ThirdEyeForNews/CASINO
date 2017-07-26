package com.casino.beans;

/**
 * Created by Manu on 7/14/2017.
 */

public class Profile {
    private String Name;
    private String gender;
    private int gamePlayed;
    private int gameLosed;
    private int gameWin;
    private int gameFirstWin;
    private int gameSupportWin;
    private int points;

    public Profile(){
        this.Name = "CASINO";
        this.gender = "MALE";
        this.gamePlayed = 0;
        this.gameLosed = 0;
        this.gameWin = 0;
        this.gameFirstWin = 0;
        this.gameSupportWin = 0;
        this.points = 0;
    }
    public Profile(String name, String gender, int gamePlayed, int gameLosed, int gameWin, int gameFirstWin, int gameSupportWin, int points) {
        this.Name = name;
        this.gender = gender;
        this.gamePlayed = gamePlayed;
        this.gameLosed = gameLosed;
        this.gameWin = gameWin;
        this.gameFirstWin = gameFirstWin;
        this.gameSupportWin = gameSupportWin;
        this.points = points;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public int getGameSupportWin() {
        return gameSupportWin;
    }

    public void setGameSupportWin(int supportWin) {
        this.gameSupportWin = supportWin;
    }

    public String getName() {
        return Name;
    }

    public int getGamePlayed() {
        return gamePlayed;
    }

    public int getGameLosed() {
        return gameLosed;
    }

    public int getGameWin() {
        return gameWin;
    }

    public int getGameFirstWin() {
        return gameFirstWin;
    }

    public int getPoints() {
        return points;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setGamePlayed(int gamePlayed) {
        this.gamePlayed = gamePlayed;
    }

    public void setGameLosed(int gameLosed) {
        this.gameLosed = gameLosed;
    }

    public void setGameWin(int gameWin) {
        this.gameWin = gameWin;
    }

    public void setGameFirstWin(int gameFirstWin) {
        this.gameFirstWin = gameFirstWin;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
