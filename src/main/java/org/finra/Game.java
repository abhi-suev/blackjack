package org.finra;

import java.util.Scanner;

public class Game {

    /**
     * Initializes deck, shuffles & calls other game's helpers
     *
     * @param numberOfPlayers
     */
    public void init(final int numberOfPlayers) {
        System.out.printf("Starting game with %d players.%n", numberOfPlayers);
        final Deck deck = new Deck();
        System.out.println("Shuffling.");
        deck.shuffle();
        final Player[] players = setupPlayersAndDealFirstCard(numberOfPlayers, deck);
        playTurns(players, deck);
        playDealerTurn(players[numberOfPlayers], deck);
        determineWinners(players);
    }

    /**
     * Creates players and deals first card to all (including dealer)
     *
     * @param numberOfPlayers
     * @param deck
     * @return players array (includes dealer)
     */
    private Player[] setupPlayersAndDealFirstCard(final int numberOfPlayers, final Deck deck) {
        final Player[] players = new Player[numberOfPlayers + 1];

        // deal first card to each player
        for (int i = 0; i < numberOfPlayers; i++) {
            final Player player = new Player("player " + (i + 1));
            player.addCard(deck.drawCard());
            players[i] = player;
            System.out.printf("Dealing to %s, card: %s%n", player.getName(), player.getHand());
        }

        // deal first card to dealer
        final Player dealer = new Player("computer");
        dealer.addCard(deck.drawCard());
        players[numberOfPlayers] = dealer;  //last position
        System.out.printf("Dealing to %s, card: face down%n", dealer.getName());

        return players;
    }

    /**
     * Deals second card to players and additional cards depending on input of hit/stand
     *
     * @param players
     * @param deck
     */
    private void playTurns(final Player[] players, final Deck deck) {
        final int numberOfPlayers = players.length - 1; //exclude dealer
        // player turns only
        final Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < numberOfPlayers; i++) {
            final Player player = players[i];
            player.addCard(deck.drawCard());
            System.out.printf("Dealing to %s, cards: %s. Hit or Stand? > ", player.getName(), player.getHand());
            String choice = scanner.nextLine().trim();
            while (!choice.equalsIgnoreCase("Stand")) {
                if (!choice.equalsIgnoreCase("Hit")) {
                    System.out.println("Invalid card argument, should be Hit/Stand");
                    System.out.printf("Dealing to %s, cards: %s. Hit or Stand? > ", player.getName(), player.getHand());
                } else {
                    player.addCard(deck.drawCard());
                    if (player.getScore() > 21) {
                        System.out.printf("Dealing to %s, cards: %s. Busted over 21.%n", player.getName(),
                                player.getHand());
                        break;
                    }
                    System.out.printf("Dealing to %s, cards: %s. Hit or Stand? > ", player.getName(), player.getHand());
                }
                choice = scanner.nextLine().trim();
            }
        }
    }

    /**
     * Adds card to existing hand if score is below 17
     *
     * @param dealer
     * @param deck
     */
    private void playDealerTurn(final Player dealer, final Deck deck) {
        while (true) {
            dealer.addCard(deck.drawCard());
            if (dealer.getScore() < 17) {
                System.out.printf("Dealing to %s, cards: %s. Dealer hits.%n", dealer.getName(), dealer.getHand());
            } else if (dealer.getScore() > 21) {
                System.out.printf("Dealing to %s, cards: %s. Busted over 21.%n", dealer.getName(), dealer.getHand());
                break;
            } else {
                System.out.printf("Dealing to %s, cards: %s. Dealer stands.%n", dealer.getName(), dealer.getHand());
                break;
            }
        }
    }

    /**
     * Compares score with dealer, if applicable and determines result
     *
     * @param players
     */
    private void determineWinners(final Player[] players) {
        final int numberOfPlayers = players.length - 1; //exclude dealer
        final Player dealer = players[numberOfPlayers]; //last position
        for (int i = 0; i < numberOfPlayers; i++) {
            final Player player = players[i];
            if (player.getScore() > 21) {
                System.out.printf("Scoring %s busted. Dealer wins.%n", player.getName());
            } else if (dealer.getScore() > 21) {
                System.out.printf("Scoring %s has %d, dealer busted. %s wins.%n", player.getName(), player.getScore(),
                        player.getName().substring(0, 1).toUpperCase() + player.getName().substring(1));
            } else if (player.getScore() > dealer.getScore()) {
                System.out.printf("Scoring %s has %d, dealer has %d. %s wins.%n", player.getName(), player.getScore(),
                        dealer.getScore(), player.getName().substring(0, 1).toUpperCase() +
                                player.getName().substring(1));
            } else {
                System.out.printf("Scoring %s has %d, dealer has %d. Dealer wins.%n", player.getName(),
                        player.getScore(), dealer.getScore());
            }
        }
    }

}
