import java.util.Random;
import java.util.Arrays;

public class DeckOfCards {
  public static void main(String[] args) { 
    Deck deck = new Deck(); 

    // Need to clone deck for shallow copy
    Card[] previousDeck = deck.cards.clone();
    Card[] currentDeck = deck.cards.clone();

    System.out.println("Previous deck: " + Arrays.toString(previousDeck));
    System.out.println("Current deck: " + Arrays.toString(currentDeck));
    System.out.println("Check that current deck is the same");
    System.out.println(Arrays.equals(previousDeck, currentDeck));

    previousDeck = deck.cards.clone();
    deck.shuffle();
    currentDeck = deck.cards.clone();

    System.out.println("Previous deck: " + Arrays.toString(previousDeck));
    System.out.println("Current deck: " + Arrays.toString(currentDeck));
    System.out.println("Deck length: " + currentDeck.length);
    System.out.println("Current deck should not be the same");
    System.out.println(Arrays.equals(previousDeck, currentDeck)); 

    // Test that shuffle works after dealing card
    for (int x = 0; x < 60; x++) {
      deck.dealOneCard();
      System.out.println("\nDeal one card...");
      previousDeck = deck.cards.clone();
      deck.shuffle();
      currentDeck = deck.cards.clone();
      System.out.println("Previous deck: " + Arrays.toString(previousDeck));
      System.out.println("Current deck: " + Arrays.toString(currentDeck));
      System.out.println("Deck length: " + currentDeck.length);
    }
  }

  // Cards are represented in form of rank and suit, so for instance "2d" is 2 of diamonds,
  // "As" is ace of spades, "Qh" is queen of hearts, etc.

  // Diamonds, Clubs, Hearts, Spades
  static String[] SUITS = new String[] {"d", "c", "h", "s"};
  // 2-10, Jack, Queen, King, Ace
  static String[] RANKS = new String[] {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};

  static class Deck {
    Card[] cards = new Card[52];

    // Constructor for deck, initializes all of the cards in the deck
    Deck() {
      int cardIdx = 0;
      for (int i = 0; i < SUITS.length; i++) {
        for (int j = 0; j < RANKS.length; j++) {
          cards[cardIdx] = new Card(RANKS[j], SUITS[i]);
          cardIdx++;
        }
      }
    }

    void shuffle() {
      // go through the deck, pick a random number for each index, then swap
      for (int i = 0; i < cards.length; i++) {
        Random rand = new Random();
        int n = rand.nextInt(cards.length);
        
        Card cardToSwap = cards[i];
        cards[i] = cards[n];
        cards[n] = cardToSwap;
      }
    }

    // Returns the next card in the deck, or null if the deck is empty
    Card dealOneCard() {
      if (cards.length == 0) {
        return null;
      }
      Card[] newDeck = new Card[cards.length - 1];
      // "Shift" all of the cards up
      for (int i = 0; i < cards.length - 1; i++) {
        newDeck[i] = cards[i + 1];
      }

      // Store the first card, then change the current deck
      Card cardToDeal = cards[0];
      cards = newDeck;

      return cardToDeal;
    }
  }

  static class Card {
    String suit;
    String rank;

    Card(String suit, String rank) {
      this.suit = suit;
      this.rank = rank;
    }

    // Override the toString function
    public String toString() {
      return this.suit + this.rank;
    }
  } 
}
