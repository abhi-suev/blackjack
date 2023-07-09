package org.finra;


public class Blackjack {

    public static void main(final String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Missing number of players argument, should be 1-3");
        }
        final int numberOfPlayers;
        try {
            numberOfPlayers = Integer.parseInt(args[0]);
        } catch (final NumberFormatException nfe) {
            throw new IllegalArgumentException("Invalid number of players argument, should be 1-3");
        }
        if (numberOfPlayers < 1 || numberOfPlayers > 3) {
            throw new IllegalArgumentException("Invalid number of players argument, should be 1-3");
        }
        final Game game = new Game();
        game.init(numberOfPlayers);
    }

}
