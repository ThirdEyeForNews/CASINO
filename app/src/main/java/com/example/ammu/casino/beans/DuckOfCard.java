package com.example.ammu.casino.beans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by Manu on 7/21/2017.
 */

public class DuckOfCard {
    List<Card> duckOfCard;

    public DuckOfCard() {
        this.duckOfCard = new ArrayList<>();
        for (Suits suit: Suits.values()
             ) {
            for (CardValue cardValue: CardValue.values()
                    ) {
                duckOfCard.add(new Card(((suit==Suits.CLUBS || suit==Suits.SPADES)?1:0),suit,cardValue));
            }
        }
        Collections.shuffle(duckOfCard);
    }

    public void shuffle(){
        Collections.shuffle(duckOfCard);
    }

    public Card getCard(int i){
        return duckOfCard.get(i);
    }
}
