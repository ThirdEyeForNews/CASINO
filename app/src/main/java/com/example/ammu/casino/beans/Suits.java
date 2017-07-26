package com.example.ammu.casino.beans;

/**
 * Created by ammu on 7/21/2017.
 */

public enum Suits {
    HEARTS(1),SPADES(2),DIAMONDS(3),CLUBS(4);

    int value;
    Suits(int value){
        this.value = value;
    }
}
