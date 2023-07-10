package org.finra;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Rank {

    Ace((byte) 1),
    Two((byte) 2),
    Three((byte) 3),
    Four((byte) 4),
    Five((byte) 5),
    Six((byte) 6),
    Seven((byte) 7),
    Eight((byte) 8),
    Nine((byte) 9),
    Ten((byte) 10),
    Jack((byte) 10),
    Queen((byte) 10),
    King((byte) 10);

    @Getter
    private final byte val;

}
