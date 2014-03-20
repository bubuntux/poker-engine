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
 * Test suite for the HandEvaluator class.
 * 
 * @author Oscar Stigter
 */
public class HandEvaluatorTest {
    
    /**
     * Tests the High Card hand type.
     */
    @Test
    public void highCard() {
        HandEvaluator evaluator;
        int value1, value2;
        
        // Base hand.
        evaluator = new HandEvaluator(new Hand("As Qh Tc 8d 5d 4h 2c"));
        Assert.assertEquals(HandValueType.HIGH_CARD, evaluator.getType());
        value1 = evaluator.getValue();

        // Different suits.
        evaluator = new HandEvaluator(new Hand("Ac Qd Td 8h 5s 4c 2d"));
        Assert.assertEquals(HandValueType.HIGH_CARD, evaluator.getType());
        value2 = evaluator.getValue();
        Assert.assertTrue(value1 == value2);

        // Major rank.
        evaluator = new HandEvaluator(new Hand("Ks Qh Tc 8d 5d 4h 2c"));
        Assert.assertEquals(HandValueType.HIGH_CARD, evaluator.getType());
        value2 = evaluator.getValue();
        Assert.assertTrue(value1 > value2);

        // Minor rank.
        evaluator = new HandEvaluator(new Hand("Ks Qh Tc 8d 4d 3h 2c"));
        Assert.assertEquals(HandValueType.HIGH_CARD, evaluator.getType());
        value2 = evaluator.getValue();
        Assert.assertTrue(value1 > value2);

        // Discarded cards (more than 5).
        evaluator = new HandEvaluator(new Hand("As Qh Tc 8d 5d 4h 3c"));
        Assert.assertEquals(HandValueType.HIGH_CARD, evaluator.getType());
        value2 = evaluator.getValue();
        Assert.assertTrue(value1 == value2);
    }
    
    /**
     * Tests the One Pair hand type.
     */
    @Test
    public void onePair() {
        HandEvaluator evaluator;
        int value1, value2;
        
        // Base hand.
        evaluator = new HandEvaluator(new Hand("Qs Qh 9c 7c 5d 3s 2h"));
        Assert.assertEquals(HandValueType.ONE_PAIR, evaluator.getType());
        value1 = evaluator.getValue();

        // Rank.
        evaluator = new HandEvaluator(new Hand("Js Jh 9c 7c 5d 3s 2h"));
        Assert.assertEquals(HandValueType.ONE_PAIR, evaluator.getType());
        value2 = evaluator.getValue();
        Assert.assertTrue(value1 > value2);
        
        // Major kicker.
        evaluator = new HandEvaluator(new Hand("Qs Qh 8c 7c 5d 3s 2h"));
        Assert.assertEquals(HandValueType.ONE_PAIR, evaluator.getType());
        value2 = evaluator.getValue();
        Assert.assertTrue(value1 > value2);
        
        // Minor kicker.
        evaluator = new HandEvaluator(new Hand("Qs Qh 9c 7c 4d 3s 2h"));
        Assert.assertEquals(HandValueType.ONE_PAIR, evaluator.getType());
        value2 = evaluator.getValue();
        Assert.assertTrue(value1 > value2);
        
        // Discarded cards (more than 5).
        evaluator = new HandEvaluator(new Hand("Qs Qh 9c 7c 5d 2d"));
        Assert.assertEquals(HandValueType.ONE_PAIR, evaluator.getType());
        value2 = evaluator.getValue();
        Assert.assertTrue(value1 == value2);
    }
    
    /**
     * Tests the Two Pairs hand type.
     */
    @Test
    public void twoPairs() {
        HandEvaluator evaluator;
        int value1, value2;

        // Base hand.
        evaluator = new HandEvaluator(new Hand("Ks Qh Tc 5d 5c 2h 2c"));
        Assert.assertEquals(HandValueType.TWO_PAIRS, evaluator.getType());
        value1 = evaluator.getValue();
        
        // High pair.
        evaluator = new HandEvaluator(new Hand("Ks Qh Tc 4d 4d 2h 2c"));
        Assert.assertEquals(HandValueType.TWO_PAIRS, evaluator.getType());
        value2 = evaluator.getValue();
        Assert.assertTrue(value1 > value2);
        
        // Low pair.
        evaluator = new HandEvaluator(new Hand("Ks Qh Tc 4d 4d 3h 3c"));
        Assert.assertEquals(HandValueType.TWO_PAIRS, evaluator.getType());
        value2 = evaluator.getValue();
        Assert.assertTrue(value1 > value2);
        
        // Major kicker.
        evaluator = new HandEvaluator(new Hand("As Qh Tc 5d 5d 2h 2c"));
        Assert.assertEquals(HandValueType.TWO_PAIRS, evaluator.getType());
        value2 = evaluator.getValue();
        Assert.assertTrue(value1 < value2);

        // Discarded cards (more than 5).
        evaluator = new HandEvaluator(new Hand("Ks Jh Tc 5d 5d 2h 2c"));
        Assert.assertEquals(HandValueType.TWO_PAIRS, evaluator.getType());
        value2 = evaluator.getValue();
        Assert.assertTrue(value1 == value2);
    }
    
    /**
     * Tests the Three of a Kind hand type.
     */
    @Test
    public void threeOfAKind() {
        HandEvaluator evaluator;
        int value1, value2;
        
        // Base hand.
        evaluator = new HandEvaluator(new Hand("Ah Qs Qh Qc Th 8s 6c"));
        Assert.assertEquals(HandValueType.THREE_OF_A_KIND, evaluator.getType());
        value1 = evaluator.getValue();

        // Rank.
        evaluator = new HandEvaluator(new Hand("Ah Js Jh Jc Th 8s 6c"));
        Assert.assertEquals(HandValueType.THREE_OF_A_KIND, evaluator.getType());
        value2 = evaluator.getValue();
        Assert.assertTrue(value1 > value2);

        // Major kicker.
        evaluator = new HandEvaluator(new Hand("Ks Qs Qh Qc Th 8s 6c"));
        Assert.assertEquals(HandValueType.THREE_OF_A_KIND, evaluator.getType());
        value2 = evaluator.getValue();
        Assert.assertTrue(value1 > value2);

        // Minor kicker.
        evaluator = new HandEvaluator(new Hand("As Qs Qh Qc 9h 8s 6c"));
        Assert.assertEquals(HandValueType.THREE_OF_A_KIND, evaluator.getType());
        value2 = evaluator.getValue();
        Assert.assertTrue(value1 > value2);

        // Discarded cards (more than 5).
        evaluator = new HandEvaluator(new Hand("As Qs Qh Qc Th 7s 6c"));
        Assert.assertEquals(HandValueType.THREE_OF_A_KIND, evaluator.getType());
        value2 = evaluator.getValue();
        Assert.assertTrue(value1 == value2);
     }

    /**
     * Tests the Straight hand type.
     */
    @Test
    public void straight() {
        HandEvaluator evaluator;
        int value1, value2;
        
        // Base hand.
        evaluator = new HandEvaluator(new Hand("Ks Th 9s 8d 7c 6h 4c"));
        Assert.assertEquals(HandValueType.STRAIGHT, evaluator.getType());
        value1 = evaluator.getValue();

        // Broadway (Ace-high Straight).
        evaluator = new HandEvaluator(new Hand("As Ks Qs Js Th 4d 2c"));
        Assert.assertEquals(HandValueType.STRAIGHT, evaluator.getType());
        value2 = evaluator.getValue();
        Assert.assertTrue(value2 > value1);

        // Different suit (tie).
        evaluator = new HandEvaluator(new Hand("Ks Tc 9d 8h 7d 6s 4c"));
        Assert.assertEquals(HandValueType.STRAIGHT, evaluator.getType());
        value2 = evaluator.getValue();
        Assert.assertTrue(value1 == value2);

        // Rank.
        evaluator = new HandEvaluator(new Hand("Ks 9d 8h 7d 6s 5c 2d"));
        Assert.assertEquals(HandValueType.STRAIGHT, evaluator.getType());
        value2 = evaluator.getValue();
        Assert.assertTrue(value1 > value2);

        // Discarded cards (more than 5).
        evaluator = new HandEvaluator(new Hand("As Th 9s 8d 7c 6h 4c"));
        Assert.assertEquals(HandValueType.STRAIGHT, evaluator.getType());
        value2 = evaluator.getValue();
        Assert.assertTrue(value1 == value2);

        // Wheel (5-high Straight with wheeling Ace).
        evaluator = new HandEvaluator(new Hand("Ad Qc Th 5s 4d 3h 2c"));
        Assert.assertEquals(HandValueType.STRAIGHT, evaluator.getType());
    }

    /**
     * Tests the Flush hand type.
     */
    @Test
    public void flush() {
        HandEvaluator evaluator;
        int value1, value2;
        
        // Base hand.
        evaluator = new HandEvaluator(new Hand("As Qs Ts 8s 6s 4d 2c"));
        Assert.assertEquals(HandValueType.FLUSH, evaluator.getType());
        value1 = evaluator.getValue();
        
        // Different suit (tie).
        evaluator = new HandEvaluator(new Hand("Ad Qd Td 8d 6d 4c 2h"));
        Assert.assertEquals(HandValueType.FLUSH, evaluator.getType());
        value2 = evaluator.getValue();
        Assert.assertTrue(value1 == value2);

        // Missing one.
        evaluator = new HandEvaluator(new Hand("Kh Jh Jd 8h 6d 5s 3h"));
        Assert.assertFalse(evaluator.getType() == HandValueType.FLUSH);
        value2 = evaluator.getValue();
        Assert.assertTrue(value1 > value2);
        
        // Major rank.
        evaluator = new HandEvaluator(new Hand("Ks Qs Ts 8s 6s 4d 2c"));
        Assert.assertEquals(HandValueType.FLUSH, evaluator.getType());
        value2 = evaluator.getValue();
        Assert.assertTrue(value1 > value2);

        // Minor rank.
        evaluator = new HandEvaluator(new Hand("As Qs Ts 8s 5s 4d 2c"));
        Assert.assertEquals(HandValueType.FLUSH, evaluator.getType());
        value2 = evaluator.getValue();
        Assert.assertTrue(value1 > value2);

        // Discarded cards (more than 5).
        evaluator = new HandEvaluator(new Hand("As Qs Ts 8s 6s 5s 2s"));
        Assert.assertEquals(HandValueType.FLUSH, evaluator.getType());
        value2 = evaluator.getValue();
        Assert.assertTrue(value1 == value2);
    }

    /**
     * Tests the Full House hand type.
     */
    @Test
    public void fullHouse() {
        HandEvaluator evaluator;
        int value1, value2;
        
        // Base hand.
        evaluator = new HandEvaluator(new Hand("As Qs Qh Qc Tc Td 4c"));
        Assert.assertEquals(HandValueType.FULL_HOUSE, evaluator.getType());
        value1 = evaluator.getValue();

        // Triple.
        evaluator = new HandEvaluator(new Hand("As Js Jh Jc Tc Td 4c"));
        Assert.assertEquals(HandValueType.FULL_HOUSE, evaluator.getType());
        value2 = evaluator.getValue();
        Assert.assertTrue(value1 > value2);

        // Pair.
        evaluator = new HandEvaluator(new Hand("As Qs Qh Qc 9c 9d 4c"));
        Assert.assertEquals(HandValueType.FULL_HOUSE, evaluator.getType());
        value2 = evaluator.getValue();
        Assert.assertTrue(value1 > value2);

        // Triple over pair.
        evaluator = new HandEvaluator(new Hand("As Js Jh Jc Kc Kd 4c"));
        Assert.assertEquals(HandValueType.FULL_HOUSE, evaluator.getType());
        value2 = evaluator.getValue();
        Assert.assertTrue(value1 > value2);

        // Discarded cards (more than 5).
        evaluator = new HandEvaluator(new Hand("Ks Qs Qh Qc Tc Td 4c"));
        Assert.assertEquals(HandValueType.FULL_HOUSE, evaluator.getType());
        value2 = evaluator.getValue();
        Assert.assertTrue(value1 == value2);
    }

    /**
     * Tests the Four of a Kind hand type.
     */
    @Test
    public void fourOfAKind() {
        HandEvaluator evaluator;
        int value1, value2;
        
        // Base hand.
        evaluator = new HandEvaluator(new Hand("As Ah Ac Ad Qs Th 8c"));
        Assert.assertEquals(HandValueType.FOUR_OF_A_KIND, evaluator.getType());
        value1 = evaluator.getValue();

        // Rank.
        evaluator = new HandEvaluator(new Hand("Ks Kh Kc Kd Qs Th 8c"));
        Assert.assertEquals(HandValueType.FOUR_OF_A_KIND, evaluator.getType());
        value2 = evaluator.getValue();
        Assert.assertTrue(value1 > value2);

        // Kicker.
        evaluator = new HandEvaluator(new Hand("As Ah Ac Ad Js Th 8c"));
        Assert.assertEquals(HandValueType.FOUR_OF_A_KIND, evaluator.getType());
        value2 = evaluator.getValue();
        Assert.assertTrue(value1 > value2);

        // Discarded cards (more than 5).
        evaluator = new HandEvaluator(new Hand("As Ah Ac Ad Qs 3d 2c"));
        Assert.assertEquals(HandValueType.FOUR_OF_A_KIND, evaluator.getType());
        value2 = evaluator.getValue();
        Assert.assertTrue(value1 == value2);
    }

    /**
     * Tests the Straight Flush hand type.
     */
    @Test
    public void straightFlush() {
        HandEvaluator evaluator;
        int value1, value2;
        
        // Base hand.
        evaluator = new HandEvaluator(new Hand("Ks Qs Js Ts 9s 4d 2c"));
        Assert.assertEquals(HandValueType.STRAIGHT_FLUSH, evaluator.getType());
        value1 = evaluator.getValue();
        
        // Rank.
        evaluator = new HandEvaluator(new Hand("Qh Jh Th 9h 8h 4d 2c"));
        Assert.assertEquals(HandValueType.STRAIGHT_FLUSH, evaluator.getType());
        value2 = evaluator.getValue();
        Assert.assertTrue(value1 > value2);

        // Discarded cards (more than 5).
        evaluator = new HandEvaluator(new Hand("Ks Qs Js Ts 9s 3d 2c"));
        Assert.assertEquals(HandValueType.STRAIGHT_FLUSH, evaluator.getType());
        value2 = evaluator.getValue();
        Assert.assertTrue(value1 == value2);

        // Steel Wheel (5-high Straight Flush with wheeling Ace).
        evaluator = new HandEvaluator(new Hand("As Qc Td 5s 4s 3s 2s"));
        Assert.assertEquals(HandValueType.STRAIGHT_FLUSH, evaluator.getType());
        
        // Wheel (5-high Straight with wheeling Ace), but no Steel Wheel.
        evaluator = new HandEvaluator(new Hand("Ah Qc Td 5s 4s 3s 2s"));
        Assert.assertEquals(HandValueType.STRAIGHT, evaluator.getType());

        // Separate Flush and Straight (but no Straight Flush).
        evaluator = new HandEvaluator(new Hand("Kh Qs Jh Th 9h 4h 2c"));
        Assert.assertEquals(HandValueType.FLUSH, evaluator.getType());
    }

    /**
     * Tests the Royal Flush hand type.
     */
    @Test
    public void royalFlush() {
        HandEvaluator evaluator;
        int value1, value2;
        
        // Base hand.
        evaluator = new HandEvaluator(new Hand("As Ks Qs Js Ts 4d 2c"));
        Assert.assertEquals(HandValueType.ROYAL_FLUSH, evaluator.getType());
        value1 = evaluator.getValue();

        // Discarded cards (more than 5).
        evaluator = new HandEvaluator(new Hand("As Ks Qs Js Ts 3d 2c"));
        Assert.assertEquals(HandValueType.ROYAL_FLUSH, evaluator.getType());
        value2 = evaluator.getValue();
        Assert.assertTrue(value1 == value2);

        // Separate Flush and Straight, but no Straight Flush or Royal Flush.
        evaluator = new HandEvaluator(new Hand("As Kh Qs Js Ts 4s 2c"));
        Assert.assertEquals(HandValueType.FLUSH, evaluator.getType());
    }

}
