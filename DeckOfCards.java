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
    System.out.println(Arrays.equals(previousDeck, currentDeck) + "\n");

    previousDeck = deck.cards.clone();
    deck.shuffle();
    currentDeck = deck.cards.clone();

    System.out.println("Previous deck: " + Arrays.toString(previousDeck));
    System.out.println("Current deck: " + Arrays.toString(currentDeck));
    System.out.println("Current deck should not be the same");
    System.out.println(Arrays.equals(previousDeck, currentDeck)); 

    System.out.println("\nNumber of cards in deck: " + currentDeck.length);

    // Test that shuffle works after dealing card
    // Deal to 60 to test that it works after all cards have been dealt
    for (int x = 0; x < 60; x++) {
      Card deal = deck.dealOneCard();
      System.out.println("\nDeal one card... dealed a " + deal);
      previousDeck = deck.cards.clone();
      deck.shuffle();
      currentDeck = deck.cards.clone();
      System.out.println("Deck after deal: " + Arrays.toString(previousDeck));
      System.out.println("Deck after shuffle: " + Arrays.toString(currentDeck));
      System.out.println("Number of cards in deck: " + currentDeck.length);
    }

    // Test a deck larger than the standard 52 card deck
    Deck bigDeck = new Deck();
    Card[] doubleDeck = new Card[52 * 2];
    for (int x = 0; x < 52; x++) {
      doubleDeck[x] = doubleDeck[x + 52] = bigDeck.cards[x];
    }

    bigDeck.cards = doubleDeck;

    System.out.println("Double deck: " + bigDeck);
    bigDeck.shuffle();
    System.out.println("Double deck shuffled: " + bigDeck);
    System.out.println("Number of cards in deck: " + bigDeck.length());
    for (int x = 0; x < 110; x++) {
      Card deal = bigDeck.dealOneCard();
      System.out.println("\nDeal one card... dealed a " + deal);
      previousDeck = bigDeck.cards.clone();
      bigDeck.shuffle();
      currentDeck = bigDeck.cards.clone();
      System.out.println("Deck after deal: " + Arrays.toString(previousDeck));
      System.out.println("Deck after shuffle: " + Arrays.toString(currentDeck));
      System.out.println("Number of cards in deck: " + currentDeck.length);
    } 
  }

  // Cards are represented in form of rank and suit, so for instance "2♦" is 2 of diamonds,
  // "A♠" is Ace of spades, "Q♥" is Queen of hearts, etc.

  // Diamonds, Clubs, Hearts, Spades
  static String[] SUITS = new String[] {"♦", "♣", "♥", "♠"};
  // Ace, 2-10, Jack, Queen, King
  static String[] RANKS = new String[] {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

  static class Deck {
    Card[] cards = new Card[52];

    // Constructor for deck, initializes all of the cards in the deck
    Deck() {
      int cardIndex = 0;
      for (int i = 0; i < SUITS.length; i++) {
        for (int j = 0; j < RANKS.length; j++) {
          cards[cardIndex] = new Card(RANKS[j], SUITS[i]);
          cardIndex++;
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

    public String toString() {
      return Arrays.toString(this.cards);
    }

    int length() {
      return this.cards.length;
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
