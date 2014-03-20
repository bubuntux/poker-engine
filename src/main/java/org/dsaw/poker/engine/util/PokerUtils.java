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

package org.dsaw.poker.engine.util;

import org.dsaw.poker.engine.Card;

/**
 * Contains various utility methods.
 * 
 * @author Oscar Stigter
 */
public abstract class PokerUtils {
    
    /**
     * Returns the value of the hole cards based on the Chen formula.
     * 
     * @param cards
     *            The hole cards.
     * 
     * @return The score based on the Chen formula.
     */
    public static double getChenScore(Card[] cards) {
        if (cards.length != 2) {
            throw new IllegalArgumentException("Invalid number of cards: " + cards.length);
        }
        
        // Analyze hole cards.
        int rank1 = cards[0].getRank();
        int suit1 = cards[0].getSuit();
        int rank2 = cards[1].getRank();
        int suit2 = cards[1].getSuit();
        int highRank = Math.max(rank1, rank2);
        int lowRank = Math.min(rank1, rank2);
        int rankDiff = highRank - lowRank;
        int gap = (rankDiff > 1) ? rankDiff - 1 : 0;  
        boolean isPair = (rank1 == rank2);
        boolean isSuited = (suit1 == suit2);
        
        double score;
        
        // 1. Base score highest rank only
        if (highRank == Card.ACE) {
            score = 10.0;
        } else if (highRank == Card.KING) {
            score = 8.0;
        } else if (highRank == Card.QUEEN) {
            score = 7.0;
        } else if (highRank == Card.JACK) {
            score = 6.0;
        } else {
            score = (highRank + 2) / 2.0;
        }
        
        // 2. If pair, double score, with minimum score of 5. 
        if (isPair) {
            score *= 2.0;
            if (score < 5.0) {
                score = 5.0;
            }
        }
        
        // 3. If suited, add 2 points.
        if (isSuited) {
            score += 2.0;
        }
        
        // 4. Subtract points for gap.
        if (gap == 1) {
            score -= 1.0;
        } else if (gap == 2) {
            score -= 2.0;
        } else if (gap == 3) {
            score -= 4.0;
        } else if (gap > 3) {
            score -= 5.0;
        }
        
        // 5. Add 1 point for a 0 or 1 gap and both cards lower than a Queen.
        if (!isPair && gap < 2 && rank1 < Card.QUEEN && rank2 < Card.QUEEN) {
            score += 1.0;
        }
        
        // Minimum score is 0.
        if (score < 0.0) {
            score = 0.0;
        }
        
        // 6. Round half point scores up.
        return Math.round(score);        
    }

}
