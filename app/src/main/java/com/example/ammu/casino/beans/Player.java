package com.example.ammu.casino.beans;

import com.androidgames.framework.Pixmap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Manu on 7/21/2017.
 */

public class Player {
    String name;
    Pixmap icon;
    Possession playerPos;
    Possession cardPos;
    List<Card> cardlist;
    public Player(){
        cardlist   = new ArrayList<>();
    }

    public Possession getPlayerPos() {
        return playerPos;
    }

    public void setPlayerPos(Possession playerPos) {
        this.playerPos = playerPos;
    }

    public Player(String name, Pixmap icon, Possession playerPos,Possession cardPos) {
        this.name = name;
        cardlist   = new ArrayList<>();
        this.icon = icon;
        this.playerPos = playerPos;
        this.cardPos = cardPos;
    }

    public Possession getCardPos() {
        return cardPos;
    }

    public void setCardPos(Possession cardPos) {
        this.cardPos = cardPos;
    }

    public Pixmap getIcon() {
        return icon;
    }

    public void setIcon(Pixmap icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Card> getCardlist() {
        return cardlist;
    }

    public void setCardlist(List<Card> cardlist) {
        this.cardlist = cardlist;
    }
}
