package com.casino.beans;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Manu on 7/14/2017.
 */

public class Achievements {
    private int totalAchievements;
    private int competedAchievements;

    Map<String,Boolean> achievements = new LinkedHashMap<>();

    public Achievements(){

        achievements.put("10 Win",false);
        achievements.put("50 Win",false);
        achievements.put("100 Win",false);
        achievements.put("500 Win",false);
        achievements.put("1000 Win",false);
        achievements.put("10 First Win",false);
        achievements.put("50 First Win",false);
        achievements.put("100 First Win",false);
        achievements.put("500 First Win",false);
        achievements.put("10 Support Win",false);
        achievements.put("50 Support Win",false);
        achievements.put("100 Support Win",false);
        achievements.put("500 Support Win",false);
        achievements.put("1000 Points",false);
        achievements.put("5000 Points",false);
        achievements.put("100000 Points",false);
        achievements.put("500000 Points",false);
        totalAchievements = achievements.size();
        competedAchievements = 0;
    }

    public Map<String, Boolean> getAchievements() {
        return achievements;
    }

    public void setAchievements(Map<String, Boolean> achievements) {
        this.achievements = achievements;
    }

    public int getTotalAchievements() {
        return totalAchievements;
    }

    public void setTotalAchievements(int totalAchievements) {
        this.totalAchievements = totalAchievements;
    }

    public int getCompetedAchievements() {
        return competedAchievements;
    }

    public void setCompetedAchievements(int competedAchievements) {
        this.competedAchievements = competedAchievements;
    }
}
