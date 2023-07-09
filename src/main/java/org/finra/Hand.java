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

    public int getScore() {
        int score = 0;
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
        final int size = cards.size();
        for (int i = 0; i < cards.size(); i++) {
            final Card card = cards.get(i);
            final Rank rank = card.getRank();
            final boolean nan = rank.equals(Rank.Ace) ||
                    rank.equals(Rank.King) ||
                    rank.equals(Rank.Queen) ||
                    rank.equals(Rank.Jack);
            sb.append(nan ? card.getRank().toString() : card.getRank().getVal()).append(" ").append(card.getSuit());
            if (i != size - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

}
