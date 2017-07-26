package com.example.ammu.casino.beans;

import android.support.annotation.NonNull;

import com.androidgames.framework.Pixmap;

/**
 * Created by Manu on 7/21/2017.
 */

public class Card implements Comparable{

    private int color;
    private Suits suit;
    private CardValue value;
    private Possession possession = new Possession(230,190,15,20,0);
    private boolean isOpened = false;
    private boolean isSelected = false;

    public Card(int color, Suits suit, CardValue value) {
        this.color = color;
        this.suit = suit;
        this.value = value;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public Suits getSuit() {
        return suit;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public Possession getPossession() {
        return possession;
    }

    public void setPossession(Possession possession) {
        this.possession = possession;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public void setOpened(boolean opened) {
        isOpened = opened;
    }

    public void setSuit(Suits suit) {
        this.suit = suit;
    }

    public CardValue getValue() {
        return value;
    }

    public void setValue(CardValue value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        Card card = null;
        if(obj instanceof Card)
            card = (Card)obj;
        else
            return false;
        return this.suit == card.suit?this.value==card.value?true:false:false;
    }

    @Override
    public int compareTo(@NonNull Object obj) {
        Card card = null;
        if(obj instanceof Card)
            card = (Card)obj;
        else
            throw new RuntimeException("Not Compare");

        if(this.suit==card.suit){
            return this.value.value-card.value.value;
        }else{
             return this.suit.value-card.suit.value;
        }
    }
}
