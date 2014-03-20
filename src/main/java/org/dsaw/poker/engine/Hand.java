// This file is part of the 'texasholdem' project, an open source
// Texas Hold'em poker application written in Java.
//
// Copyright 2009 Oscar Stigter
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package org.dsaw.poker.engine;

import java.util.Collection;

/**
 * A generic hand of game cards. <br />
 * <br />
 * 
 * The cards are ordered highest to lowest. <br />
 * <br />
 *
 * <b>NOTE:</b> This class is implemented with the focus on performance (instead of clean design).
 * 
 * @author Oscar Stigter
 */
public class Hand {
    
    /** The maximum number of cards in a hand. */
    private static final int MAX_NO_OF_CARDS = 7;
    
    /** The cards in this hand. */
    private Card[] cards = new Card[MAX_NO_OF_CARDS];
    
    /** The current number of cards in this hand. */
    private int noOfCards = 0;
    
    /**
     * Constructor for an empty hand.
     */
    public Hand() {
        // Empty implementation.
    }
    
    /**
     * Constructor with an array of initial cards.
     * 
     * @param cards
     *            The initial cards.
     * 
     * @throws IllegalArgumentException
     *             If the array is null or the number of cards is invalid.
     */
    public Hand(Card[] cards) {
        addCards(cards);
    }
    
    /**
     * Constructor with a collection of initial cards.
     * 
     * @param cards
     *            The initial cards.
     */
    public Hand(Collection<Card> cards) {
        if (cards == null) {
            throw new IllegalArgumentException("Null array");
        }
        for (Card card : cards) {
            addCard(card);
        }
    }
    
    /**
     * Constructor with a string representing the initial cards.
     * 
     * The string must contain of one or more cards.
     * A card must be represented by a rank and a suit character.
     * The cards must be separated by a space character.
     * 
     * Example: "Kh 7d 4c As Js"
     * 
     * @param s
     *            The string to parse.
     * 
     * @throws IllegalArgumentException
     *             If the string could not be parsed or the number of cards is
     *             too high.
     */
    public Hand(String s) {
        if (s == null || s.length() == 0) {
            throw new IllegalArgumentException("Null or empty string");
        }
        
        String[] parts = s.split("\\s");
        if (parts.length > MAX_NO_OF_CARDS) {
            throw new IllegalArgumentException("Too many cards in hand");
        }
        for (String part : parts) {
            addCard(new Card(part));
        }
    }
    
    /**
     * Returns the number of cards.
     * 
     * @return The number of cards.
     */
    public int size() {
        return noOfCards;
    }
    
    /**
     * Adds a single card.
     * 
     * The card is inserted at such a position that the hand remains sorted
     * (highest ranking cards first).
     * 
     * @param card
     *            The card to add.
     * 
     * @throws IllegalArgumentException
     *             If the card is null.
     */
    public void addCard(Card card) {
        if (card == null) {
            throw new IllegalArgumentException("Null card");
        }
        
        int insertIndex = -1;
        for (int i = 0; i < noOfCards; i++) {
            if (card.compareTo(cards[i]) > 0) {
                insertIndex = i;
                break;
            }
        }
        if (insertIndex == -1) {
            // Could not insert anywhere, so append at the end.
            cards[noOfCards++] = card;
        } else {
            System.arraycopy(cards, insertIndex, cards, insertIndex + 1, noOfCards - insertIndex);
            cards[insertIndex] = card;
            noOfCards++;
        }
    }
    
    /**
     * Adds multiple cards.
     * 
     * The cards are inserted at such a position that the hand remains sorted
     * (highest ranking cards first).
     * 
     * @param cards
     *            The cards to add.
     */
    public void addCards(Card[] cards) {
        if (cards == null) {
            throw new IllegalArgumentException("Null array");
        }
        if (cards.length > MAX_NO_OF_CARDS) {
            throw new IllegalArgumentException("Too many cards");
        }
        for (Card card : cards) {
            addCard(card);
        }
    }
    
    /**
     * Adds multiple cards.
     * 
     * The cards are inserted at such a position that the hand remains sorted
     * (highest ranking cards first).
     * 
     * @param cards
     *            The cards to add.
     */
    public void addCards(Collection<Card> cards) {
        if (cards == null) {
            throw new IllegalArgumentException("Null collection");
        }
        if (cards.size() > MAX_NO_OF_CARDS) {
            throw new IllegalArgumentException("Too many cards");
        }
        for (Card card : cards) {
            addCard(card);
        }
    }
    
    /**
     * Returns the cards.
     *
     * @return The cards.
     */
    public Card[] getCards() {
        Card[] dest = new Card[noOfCards];
        System.arraycopy(cards, 0, dest, 0, noOfCards);
        return dest;
    }
    
    /**
     * Removes all cards.
     */
    public void removeAllCards() {
        noOfCards = 0;
    }
    
    /** {@inheritDoc} */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < noOfCards; i++) {
            sb.append(cards[i]);
            if (i < (noOfCards - 1)) {
                sb.append(' ');
            }
        }
        return sb.toString();
    }
    
}
