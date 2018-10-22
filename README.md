# Deck of Cards

The code sample in this repository is a representation of a deck of cards written in Java. In the `DeckOfCards` class, there are two classes: the `Card` class and the `Deck` class. 

The `Card` class represents a single card in a standard playing card deck. The `Card` can represent any one of 52 cards of 4 suits: hearts, diamonds, clubs, and spades, with each suit having cards of 13 ranks: 2-10, Jack, Queen, King, Ace. The `Deck` class contains the cards in the deck, and also is initialized by a constructor. 

For simplicity, we represent the cards in shorthand form: "2♦" represents the 2 of diamonds, "A♠" represents the Ace of spades, "Q♥" represents the Queen of hearts, etc. A new card deck can be created as follows: `Deck deck = new Deck()`. This initializes a new deck in order from Ace, 2-10, Jack, Queen, King, and the suits in the following order: diamonds, clubs, hearts, spades. 

Multiple `Deck`s can be created, and the cards in each `Deck` are distinct from the other `Deck`s. 

To shuffle the `Deck`, one simply would call `deck.shuffle()` to randomize the order of the cards in the `Deck`. The general algorithm for the shuffle function is that it goes through each key in the `Card` array for the `Deck`, selects a random integer within the range of the `Card` array length, then swaps the `Card` at the current position with the randomly selected `Card` before proceeding to the next key. This continues until we reach the end of the `Card` array. 

To deal a `Card` from the `Deck`, we simply call `deck.dealOneCard()`. This will set the return value from the `Deck` to the first `Card` in the `Card` array, then create a `Card` array that is one length shorter than the previous `Card` array. The original `Card` array will be shifted over and inserted into the new `Card` array, which will replace the existing `Card` array. 

Ideas for further development:
* Support for Jokers
* Full name output of cards ("Ace of spades" instead of "A♠")
* Interactive terminal prompt to shuffle & deal cards
* Keep shuffle history, enable undo shuffle
* Allow multiple `Deck`s to be combined
* Enable API to be extended for use in a card game
