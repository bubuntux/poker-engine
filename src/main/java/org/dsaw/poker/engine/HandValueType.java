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

/**
 * The hand value types in poker.
 * 
 * @author Oscar Stigter
 */
public enum HandValueType {
    
    /** Royal flush (Ace-high Straight Flush). */
    ROYAL_FLUSH("a Royal Flush", 9),
    
    /** Straight Flush (a Straight and a Flush, less than Ace-high). */
    STRAIGHT_FLUSH("a Straight Flush", 8),

    /** Four of a Kind (four cards of the same rank). */ 
    FOUR_OF_A_KIND("Four of a Kind", 7),
    
    /** Full House (a Three of a Kind and Two Pairs). */
    FULL_HOUSE("a Full House", 6),
    
    /** Flush (five cards of the same suit). */
    FLUSH("a Flush", 5),
    
    /** Straight (five cards in sequential order). */
    STRAIGHT("a Straight", 4),

    /** Three of a Kind (three cards of the same rank). */
    THREE_OF_A_KIND("Three of a Kind", 3),
    
    /** Two Pairs (two pairs). */
    TWO_PAIRS("Two Pairs", 2),

    /** One Pair (two cards of the same rank). */
    ONE_PAIR("One Pair", 1),
    
    /** Highest Card (the card with the highest rank). */
    HIGH_CARD("a High Card", 0),

    ;
    
    /** The description. */
    private String description;

    /** The hand value. */
    private int value;
    
    /**
     * Constructor.
     * 
     * @param description
     *            The description.
     * @param value
     *            The hand value.
     */
    HandValueType(String description, int value) {
        this.description = description;
        this.value = value;
    }
    
    /**
     * Returns the description.
     * 
     * @return The description.
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Returns the hand value.
     * 
     * @return The hand value.
     */
    public int getValue() {
        return value;
    }
    
}
