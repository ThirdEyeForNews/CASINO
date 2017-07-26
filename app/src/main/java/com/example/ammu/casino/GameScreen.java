package com.example.ammu.casino;

import com.androidgames.framework.Game;
import com.androidgames.framework.Graphics;
import com.androidgames.framework.Input;
import com.androidgames.framework.Screen;
import com.example.ammu.casino.beans.Card;
import com.example.ammu.casino.beans.CardValue;
import com.example.ammu.casino.beans.DuckOfCard;
import com.example.ammu.casino.beans.Player;
import com.example.ammu.casino.beans.Possession;
import com.example.ammu.casino.beans.Suits;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by ammu on 7/21/2017.
 */

public class GameScreen extends Screen {

    List<Player> players;
    Player me;
    boolean gameStarted = false;
    boolean divideCard = false;
    DuckOfCard duckOfCard;
    static int cardIndex = 0;
    int cardAngle = 0;
    int playerIndex = 0;
    Card selectedCard;
    public GameScreen(Game game){

        super(game);
        duckOfCard = new DuckOfCard();
        duckOfCard.shuffle();
        players = new ArrayList<>();
        me = new Player(Settings.myProfile.getName(),Settings.myProfile.getGender().equalsIgnoreCase("MALE")?Assets.ME_BOY:Assets.ME_GIRL,new Possession(190,276,0,0,0),new Possession(215,245,20,25,0));
        players.add(new Player("ROBBEN",Assets.ROBBEN,new Possession(20,230,0,0,0),new Possession(75,235,15,20,0)));
        players.add(new Player("SANIYA",Assets.SANIYA,new Possession(60,155,0,0,0),new Possession(75,195,15,20,0)));
        players.add(new Player("SACHIN",Assets.SACHIN,new Possession(410,155,0,0,0),new Possession(375,190,15,20,0)));
        players.add(new Player("MARTINA",Assets.MARTINA,new Possession(430,250,0,0,0),new Possession(395,250,15,20,0)));

    }


    @Override
    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();
        int len;
        for (int i=0;i<(len=touchEvents.size());i++){
            Input.TouchEvent event = touchEvents.get(i);
            if(!gameStarted && !divideCard) {
                if (event.type == Input.TouchEvent.TOUCH_UP) {
                    if (inBounds(event, 230, 190, 15, 20)) {
                        duckOfCard.shuffle();
                    }
                }
                if (event.type == Input.TouchEvent.TOUCH_DRAGGED) {
                    if (inBounds(event, 230, 190, 15, 20)) {
                        divideCard = true;
                    }
                }
            }else if(gameStarted){
                if (event.type == Input.TouchEvent.TOUCH_UP) {
                    if(null != (selectedCard=selectedCard(event))){
                        if (!selectedCard.isSelected()) {
                            for (Card card:
                                 me.getCardlist()) {
                                card.setSelected(false);
                            }
                            selectedCard.setSelected(true);

                        }else{
                            selectedCard.setSelected(false);
                        }
                    }

                }
            }
        }

        if (divideCard){
            int speed = 20;
            if(moveCard(duckOfCard.getCard(cardIndex).getPossession().x,duckOfCard.getCard(cardIndex).getPossession().y,playerIndex==4?me.getCardPos().x:players.get(playerIndex).getCardPos().x,playerIndex==4?me.getCardPos().y:players.get(playerIndex).getCardPos().y,speed)) {
                if (playerIndex==4)
                    me.getCardlist().add(duckOfCard.getCard(cardIndex));
                else
                    players.get(playerIndex).getCardlist().add(duckOfCard.getCard(cardIndex));
                cardIndex++;
                if(cardIndex >=52) {
                    divideCard = false;
                    gameStarted=true;
                    cardIndex=0;
                }
                playerIndex++;
                if(playerIndex>=5)
                    playerIndex = 0;
            }
        }
        if(gameStarted){
            Collections.sort(me.getCardlist(), new Comparator<Card>() {
                @Override
                public int compare(Card c1, Card c2) {
                    return c1.compareTo(c2);
                }
            });
            for (Card card:
                 me.getCardlist()) {
                card.setOpened(true);
            }
            int myCardStartX = 120,myCardEndX = 360;
            int dx = (myCardEndX-myCardStartX)/me.getCardlist().size();
            int inc = 0;
            for (Card card:
                    me.getCardlist()) {
                if (!card.isSelected())
                    card.setPossession(new Possession((myCardStartX+(inc++)*dx),245,40,60,0));
                else
                    card.setPossession(new Possession((myCardStartX+(inc++)*dx),235,40,60,0));

            }
        }

    }

    private Card selectedCard(Input.TouchEvent event) {
        int size;
        for (int i=0;i<(size=me.getCardlist().size());i++) {
            Card thisCard = me.getCardlist().get(i);
            int width = (i>=size-1)?thisCard.getPossession().width:me.getCardlist().get(i+1).getPossession().x-thisCard.getPossession().x;
            if (inBounds(event,thisCard.getPossession().x,thisCard.getPossession().y,width,thisCard.getPossession().hight)){
                return thisCard;
            }
        }
        return null;
    }

    private boolean moveCard(int startX,int startY,int endX,int endY,int speed) {
        Card movingCard = duckOfCard.getCard(cardIndex);
        float divX = endX-startX;
        float divY = endY-startY;
        int newX,newY;
        float angle = (float)Math.atan2(divY,divX);
        newX=startX+(int)Math.round(speed*Math.cos(angle));
        newY=startY+(int)Math.round(speed*Math.sin(angle));
        movingCard.setPossession(new Possession(newX,newY,movingCard.getPossession().width,movingCard.getPossession().hight,0));
        //cardAngle = cardAngle>360?0:cardAngle;
        return (endX-newX<=2)&&(endY-newY<=1)?true:false;
    }

    private boolean inBounds(Input.TouchEvent event,int x, int y, int width, int hight){
        return (event.x>x && event.x<x +width-1 && event.y>y && event.y<y+hight-1)?true:false;
    }

    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();
        g.drawPixmap(Assets.GAME_PAGE_BACKGROUND,0,0,480,320,0,0,Assets.GAME_PAGE_BACKGROUND.getWidth(),Assets.GAME_PAGE_BACKGROUND.getHight());
        for (int i=0;i<52;i++) {
            Card card = duckOfCard.getCard(i);
            if(me.getCardlist().contains(card))
                continue;
            if (card.isOpened()) {
                displayOpenCard(g,card);
            }else
                g.drawPixmap(Assets.CARD_BACK,card.getPossession().x,card.getPossession().y,card.getPossession().width,card.getPossession().hight,0,0,Assets.CARD_BACK.getWidth(),Assets.CARD_BACK.getHight(),card.getPossession().angle);

        }
        for (Card card:
             me.getCardlist()) {
            if (card.isOpened()) {
                displayOpenCard(g,card);
            }else
                g.drawPixmap(Assets.CARD_BACK,card.getPossession().x,card.getPossession().y,card.getPossession().width,card.getPossession().hight,0,0,Assets.CARD_BACK.getWidth(),Assets.CARD_BACK.getHight(),card.getPossession().angle);

        }
        for (Player player:
             players) {
            g.drawPixmap(Assets.PLAYER_BK,player.getPlayerPos().x,player.getPlayerPos().y,50,40,0,0,Assets.PLAYER_BK.getWidth(),Assets.PLAYER_BK.getHight());
            g.drawPixmap(player.getIcon(),player.getPlayerPos().x+5,player.getPlayerPos().y+5,40,30,player.getPlayerPos().width,player.getPlayerPos().hight,145,145);
        }
        g.drawPixmap(Assets.PLAYER_BK,me.getPlayerPos().x,me.getPlayerPos().y,60,50,0,0,Assets.PLAYER_BK.getWidth(),Assets.PLAYER_BK.getHight());
        g.drawPixmap(me.getIcon(),me.getPlayerPos().x+5,me.getPlayerPos().y+5,50,40,me.getPlayerPos().width,me.getPlayerPos().hight,145,145);
    }



    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }
private void displayOpenCard(Graphics g, Card card) {
    Suits suit;
    int x = 0;
    for (CardValue cardValue: CardValue.values()
            ) {
        if(card.getValue() == cardValue)
            break;
        else
            x += 167;
    }
    int  y = (suit=card.getSuit())== Suits.CLUBS?0:suit == Suits.DIAMONDS?244:suit == Suits.HEARTS ? 488:suit == Suits.SPADES?732:0;
    g.drawPixmap(Assets.CARD_FRONT,card.getPossession().x,card.getPossession().y,card.getPossession().width,card.getPossession().hight,x,y,167,244);

}
    @Override
    public void dispose() {

    }
}
