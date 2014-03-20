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

import java.util.ArrayList;
import java.util.Collection;
import junit.framework.Assert;
import org.junit.Test;

/**
 * Test suite for the Hand class.
 * 
 * @author Oscar Stigter
 */
public class HandTest {
    
    /**
     * Tests the basics (good-weather).
     */
    @Test
    public void basics() {
        Hand hand = new Hand();
        Assert.assertNotNull(hand);
        Assert.assertEquals(0, hand.size());
        
        Card[] cards = hand.getCards();
        Assert.assertNotNull(cards);
        Assert.assertEquals(0, cards.length);
        
        hand.addCard(new Card("Th"));
        Assert.assertEquals(1, hand.size());
        cards = hand.getCards();
        Assert.assertNotNull(cards);
        Assert.assertEquals(1, cards.length);
        Assert.assertNotNull(cards[0]);
        Assert.assertEquals("Th", cards[0].toString());

        hand.addCards(new Card[]{new Card("2d"), new Card("Jc")});
        Assert.assertEquals(3, hand.size());
        cards = hand.getCards();
        Assert.assertNotNull(cards);
        Assert.assertEquals(3, cards.length);
        Assert.assertEquals("Jc", cards[0].toString());
        Assert.assertEquals("Th", cards[1].toString());
        Assert.assertEquals("2d", cards[2].toString());
        
        hand.removeAllCards();
        Assert.assertEquals(0, hand.size());
    }
    
    /**
     * Tests the constructors (bad-weather).
     */
    @Test
    public void constructors() {

        // Null card array.
        try {
            new Hand((Card[]) null);
            Assert.fail("No exception thrown");
        } catch (IllegalArgumentException e) {
            // OK.
        }

        // Card array with a null card.
        try {
            Card[] cards = new Card[1];
            new Hand(cards);
            Assert.fail("No exception thrown");
        } catch (IllegalArgumentException e) {
            // OK.
        }

        // Card array with too many cards.
        try {
            Card[] cards = new Card[11];
            new Hand(cards);
            Assert.fail("No exception thrown");
        } catch (IllegalArgumentException e) {
            // OK.
        }

        // Null card collection.
        try {
            new Hand((Collection<Card>) null);
            Assert.fail("No exception thrown");
        } catch (IllegalArgumentException e) {
            // OK.
        }

        // Card collection with a null card.
        try {
            Collection<Card> cards = new ArrayList<>();
            cards.add(null);
            new Hand(cards);
            Assert.fail("No exception thrown");
        } catch (IllegalArgumentException e) {
            // OK.
        }

        // Card array with too many cards.
        try {
            Card[] cards = new Card[11];
            new Hand(cards);
            Assert.fail("No exception thrown");
        } catch (IllegalArgumentException e) {
            // OK.
        }
    }

}
