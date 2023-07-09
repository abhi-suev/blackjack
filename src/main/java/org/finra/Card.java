package org.finra;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Card {

    @Getter
    private Suit suit;

    @Getter
    private Rank rank;

}
