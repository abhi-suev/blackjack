package org.finra;

import lombok.Getter;

public class Player {

    @Getter
    private final String name;

    @Getter
    private final Hand hand;

    public Player(final String name) {
        this.name = name;
        this.hand = new Hand();
    }

    public void addCard(final Card card) {
        hand.addCard(card);
    }

    public int getScore() {
        return hand.getScore();
    }

}
