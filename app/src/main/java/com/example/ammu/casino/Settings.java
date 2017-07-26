package com.example.ammu.casino;

import com.androidgames.framework.FileIO;
import com.casino.beans.Achievements;
import com.casino.beans.Profile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Map;

/**
 * Created by Manu on 7/14/2017.
 */

public class Settings {
    public static boolean soundEnabled = true;
    public static Profile myProfile = new Profile();
    public static Achievements achievements = new Achievements();

    public static void load(FileIO file){
        try(BufferedReader in = new BufferedReader(new InputStreamReader(file.readFile(".casino")))){
            soundEnabled = Boolean.parseBoolean(in.readLine());

            myProfile.setName(in.readLine().toString());
            myProfile.setGender(in.readLine().toString());
            myProfile.setGamePlayed(Integer.parseInt(in.readLine()));
            myProfile.setGameWin(Integer.parseInt(in.readLine()));
            myProfile.setGameFirstWin(Integer.parseInt(in.readLine()));
            myProfile.setGameSupportWin(Integer.parseInt(in.readLine()));
            myProfile.setGameLosed(Integer.parseInt(in.readLine()));
            myProfile.setPoints(Integer.parseInt(in.readLine()));

            achievements.setCompetedAchievements(Integer.parseInt(in.readLine()));
            for (int i = 0;i<achievements.getTotalAchievements();i++) {
                achievements.getAchievements().put(in.readLine(),Boolean.parseBoolean(in.readLine()));
            }

        }catch (IOException e){
            //Use Default
        }catch (NumberFormatException e){
            //Use Default
        }finally {

        }
    }

    public static void updateName(String name){
        myProfile.setName(name);
    }

    public static void updateScore(int point){
        myProfile.setPoints(myProfile.getPoints()+point);

        if(point==0){
            myProfile.setGamePlayed(myProfile.getGamePlayed()+1);
            myProfile.setGameLosed(myProfile.getGameLosed()+1);
        }else if(point == 10) {
            myProfile.setGamePlayed(myProfile.getGamePlayed() + 1);
            myProfile.setGameWin(myProfile.getGameWin() + 1);
        }else if(point == 50){
            myProfile.setGamePlayed(myProfile.getGamePlayed()+1);
            myProfile.setGameSupportWin(myProfile.getGameSupportWin()+1);
            myProfile.setGameWin(myProfile.getGameWin()+1);
        }
        else if(point == 100){
            myProfile.setGamePlayed(myProfile.getGamePlayed()+1);
            myProfile.setGameFirstWin(myProfile.getGameFirstWin()+1);
            myProfile.setGameWin(myProfile.getGameWin()+1);
        }
    }

    public static void updateAchievements(){
        int completedAchievement = 0;
        if(myProfile.getGameWin() >=10) {
            achievements.getAchievements().put("10 Win", true);
            completedAchievement++;
        }
        if(myProfile.getGameWin() >=50) {
            achievements.getAchievements().put("50 Win", true);
            completedAchievement++;
        }
        if(myProfile.getGameWin() >=100) {
            achievements.getAchievements().put("100 Win", true);
            completedAchievement++;
        }
        if(myProfile.getGameWin() >=500) {
            achievements.getAchievements().put("500 Win", true);
            completedAchievement++;
        }
        if(myProfile.getGameWin() >=1000) {
            achievements.getAchievements().put("1000 Win", true);
            completedAchievement++;
        }
        if(myProfile.getGameFirstWin() >=10) {
            achievements.getAchievements().put("10 First Win", true);
            completedAchievement++;
        }
        if(myProfile.getGameFirstWin() >=50) {
            achievements.getAchievements().put("50 First Win", true);
            completedAchievement++;
        }
        if(myProfile.getGameFirstWin() >=10) {
            achievements.getAchievements().put("100 First Win", true);
            completedAchievement++;
        }
        if(myProfile.getGameFirstWin() >=500) {
            achievements.getAchievements().put("500 First Win", true);
            completedAchievement++;
        }
        if(myProfile.getGameSupportWin() >=10) {
            achievements.getAchievements().put("10 Support Win", true);
            completedAchievement++;
        }
        if(myProfile.getGameSupportWin() >=50) {
            achievements.getAchievements().put("50 Support Win", true);
            completedAchievement++;
        }
        if(myProfile.getGameSupportWin() >=100) {
            achievements.getAchievements().put("100 Support Win", true);
            completedAchievement++;
        }
        if(myProfile.getGameSupportWin() >=500) {
            achievements.getAchievements().put("500 Support Win", true);
            completedAchievement++;
        }
        if(myProfile.getPoints() >=1000) {
            achievements.getAchievements().put("1000 Points", true);
            completedAchievement++;
        }
        if(myProfile.getPoints() >=5000) {
            achievements.getAchievements().put("5000 Points", true);
            completedAchievement++;
        }
        if(myProfile.getPoints() >=100000) {
            achievements.getAchievements().put("100000 Points", true);
            completedAchievement++;
        }
        if(myProfile.getPoints() >=500000) {
            achievements.getAchievements().put("500000 Points", true);
            completedAchievement++;
        }
    }

    public static void save(FileIO fileIO){
        try(BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fileIO.writeFile("CASINO")))){
            out.write(Boolean.toString(soundEnabled));
            out.write(myProfile.getName());
            out.write(myProfile.getGender());
            out.write(myProfile.getGamePlayed());
            out.write(myProfile.getGameWin());
            out.write(myProfile.getGameFirstWin());
            out.write(myProfile.getGameSupportWin());
            out.write(myProfile.getGameLosed());
            out.write(myProfile.getPoints());
            for (Map.Entry<String,Boolean> entry: achievements.getAchievements().entrySet()) {
                out.write(entry.getKey());
                out.write(Boolean.toString(entry.getValue()));
            }
        }catch(IOException e){

        }

    }

}
