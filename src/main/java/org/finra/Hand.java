package org.finra;


import java.util.ArrayList;
import java.util.List;

public class Hand {

    private final List<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    public void addCard(final Card card) {
        cards.add(card);
    }

    public byte getScore() {
        byte score = 0;
        boolean hasAce = false;
        for (final Card card : cards) {
            score += card.getRank().getVal();
            if (!hasAce && card.getRank().equals(Rank.Ace)) {
                hasAce = true;
            }
        }
        if (hasAce && score + 10 <= 21) {
            score += 10;
        }
        return score;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        final byte size = (byte) cards.size();
        for (byte i = 0; i < size; i++) {
            final Card card = cards.get(i);
            final Rank rank = card.getRank();
            final boolean nan = Rank.Ace.equals(rank) ||
                    Rank.King.equals(rank) ||
                    Rank.Queen.equals(rank) ||
                    Rank.Jack.equals(rank); //not a number
            sb.append(nan ? card.getRank().toString() : card.getRank().getVal()).append(" ").append(card.getSuit());
            if (i != size - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

}
