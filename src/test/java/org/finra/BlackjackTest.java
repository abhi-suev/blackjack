package org.finra;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BlackjackTest {

    @Test
    void testWithEmptyArgs() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Blackjack.main(new String[]{}));
    }

    @Test
    void testWithInvalidArgs() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Blackjack.main(new String[]{"0"}));
        Assertions.assertThrows(IllegalArgumentException.class, () -> Blackjack.main(new String[]{"zero"}));
        Assertions.assertThrows(IllegalArgumentException.class, () -> Blackjack.main(new String[]{"4"}));
    }

}
