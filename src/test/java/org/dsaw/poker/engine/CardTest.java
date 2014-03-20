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

import junit.framework.Assert;
import org.junit.Test;

/**
 * Test suite for the Card class.
 * 
 * @author Oscar Stigter
 */
public class CardTest {
    
    /**
     * Tests the basics (good-weather).
     */
    @Test
    public void basics() {
        Card card = new Card(Card.TEN, Card.HEARTS);
        Assert.assertNotNull(card);
        Assert.assertEquals(Card.TEN, card.getRank());
        Assert.assertEquals(Card.HEARTS, card.getSuit());
        Assert.assertEquals("Th", card.toString());
        card = new Card("   As "); // Automatic trimming.
        Assert.assertNotNull(card);
        Assert.assertEquals(Card.ACE, card.getRank());
        Assert.assertEquals(Card.SPADES, card.getSuit());
        Assert.assertEquals("As", card.toString());
    }
    
    /**
     * Tests the constructors (bad-weather).
     */
    @Test
    public void testConstructors() {
        // Numeric rank too low.
        try {
             new Card(-1, 0);
            Assert.fail("No exception thrown");
        } catch (IllegalArgumentException e) {
            // OK.
        }

        // Numeric rank too high.
        try {
             new Card(Card.NO_OF_RANKS, 0);
            Assert.fail("No exception thrown");
        } catch (IllegalArgumentException e) {
            // OK.
        }

        // Numeric suit too low.
        try {
             new Card(0, -1);
            Assert.fail("No exception thrown");
        } catch (IllegalArgumentException e) {
            // OK.
        }
        
        // Numeric suit too high.
        try {
             new Card(0, Card.NO_OF_SUITS);
            Assert.fail("No exception thrown");
        } catch (IllegalArgumentException e) {
            // OK.
        }
        
        // Null string.
        try {
             new Card(null);
            Assert.fail("No exception thrown");
        } catch (IllegalArgumentException e) {
            // OK.
        }
        
        // Empty string.
        try {
             new Card("");
            Assert.fail("No exception thrown");
        } catch (IllegalArgumentException e) {
            // OK.
        }
        
        // String too short.
        try {
             new Card("A");
            Assert.fail("No exception thrown");
        } catch (IllegalArgumentException e) {
            // OK.
        }
        
        // String too long.
        try {
             new Card("Ahx");
            Assert.fail("No exception thrown");
        } catch (IllegalArgumentException e) {
            // OK.
        }
        
        // Unknown rank character.
        try {
             new Card("xh");
            Assert.fail("No exception thrown");
        } catch (IllegalArgumentException e) {
            // OK.
        }
        
        // Unknown rank character.
        try {
             new Card("xh");
            Assert.fail("No exception thrown");
        } catch (IllegalArgumentException e) {
            // OK.
        }
        
        // Unknown suit character.
        try {
           new Card("Ax");
            Assert.fail("No exception thrown");
        } catch (IllegalArgumentException e) {
            // OK.
        }
    }
    
    /**
     * Tests the card ordering.
     */
    @Test
    public void sortOrder() {
        // Diamond is lower, Clubs is higher.
        Card _2d = new Card("2d");
        Card _3d = new Card("3d");
        Card _2c = new Card("2c");
        Card _3c = new Card("3c");
        Assert.assertEquals(_2d, _2d);
        Assert.assertFalse(_2d.equals(_3d));
        Assert.assertFalse(_2d.equals(_2c));
        Assert.assertEquals(0, _2d.hashCode());
        Assert.assertEquals(1, _2c.hashCode());
        Assert.assertEquals(4, _3d.hashCode());
        Assert.assertEquals(5, _3c.hashCode());
        Assert.assertTrue(_2d.compareTo(_2d) == 0);
        Assert.assertTrue(_2d.compareTo(_3d) < 0);
        Assert.assertTrue(_3d.compareTo(_2d) > 0);
        Assert.assertTrue(_2d.compareTo(_2c) < 0);
        Assert.assertTrue(_2c.compareTo(_2d) > 0);
    }

}
