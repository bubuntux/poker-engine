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

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * A pot to which one or more players have contributed. <br />
 * <br />
 * 
 * Each pot has an equal fixed amount for all contributing players. In case of a
 * partial call, bet or raise, the pot must be {@link #split}.
 * 
 * @author Oscar Stigter
 */
public class Pot {

    /** Bet for this pot. */
    private BigDecimal bet;

    /** Contributing players to this pot. */
    public final Set<Player> contributors;

    /**
     * Constructor.
     * 
     * @param bet
     *            The bet for this pot.
     */
    public Pot(BigDecimal bet) {
        this.bet = bet;
        contributors = new HashSet<>();
    }

    /**
     * Returns the bet.
     * 
     * @return The bet.
     */
    public BigDecimal getBet() {
        return bet;
    }
    
    /**
     * Returns the contributing players.
     * 
     * @return The conributing players.
     */
    public Set<Player> getContributors() {
        return Collections.unmodifiableSet(contributors);
    }

    /**
     * Adds a contributing player.
     * 
     * @param player
     *            The player.
     */
    public void addContributer(Player player) {
        contributors.add(player);
    }

    /**
     * Indicates whether a specific player has contributed to this pot.
     * 
     * @param player
     *            The player.
     * 
     * @return True if the player has contributed, otherwise false.
     */
    public boolean hasContributer(Player player) {
        return contributors.contains(player);
    }

    /**
     * Returns the total value of this pot.
     * 
     * @return The total value.
     */
    public BigDecimal getValue() {
        return bet.multiply(new BigDecimal(String.valueOf(contributors.size()))); //TODO??
    }

    /**
     * In case of a partial call, bet or raise, splits this pot into two pots,
     * with this pot keeping the lower bet and the other pot the remainder.
     * 
     * @param player
     *            The player with the partial call, bet or raise.
     * @param partialBet
     *            The amount of the partial bet.
     * 
     * @return The other pot, with the remainder.
     */
    public Pot split(Player player, BigDecimal partialBet) {
        Pot pot = new Pot(bet.subtract(partialBet));
        for (Player contributer : contributors) {
            pot.addContributer(contributer);
        }
        bet = partialBet;
        contributors.add(player);
        return pot;
    }

    /**
     * Clears this pot.
     */
    public void clear() {
        bet = BigDecimal.ZERO;
        contributors.clear();
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.valueOf(bet));
        sb.append(": {");
        boolean isFirst = true;
        for (Player contributor : contributors) {
            if (isFirst) {
                isFirst = false;
            } else {
                sb.append(", ");
            }
            sb.append(contributor.getName());
        }
        sb.append('}');
        sb.append(" (Total: ");
        sb.append(String.valueOf(getValue()));
        sb.append(')');
        return sb.toString();
    }

}
