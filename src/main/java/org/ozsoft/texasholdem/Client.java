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

package org.ozsoft.texasholdem;

import java.util.List;
import java.util.Set;

import org.ozsoft.texasholdem.actions.Action;

/**
 * A player client showing the table information and acting on behalf of the
 * player. <br />
 * <br />
 * 
 * Must be implemented by any client representing a player, either human or bot.
 * 
 * @author Oscar Stigter
 */
public interface Client {
    
    /**
     * Handles a game message.
     * 
     * @param message
     *            The message.
     */
    void messageReceived(String message);

    /**
     * Handles the player joining a table.
     * 
     * @param type
     *            The table type (betting structure).
     * @param bigBlind
     *            The table's big blind.
     * @param players
     *            The players at the table (including this player).
     */
    void joinedTable(TableType type, int bigBlind, List<Player> players);
    
    /**
     * Handles the start of a new hand.
     * 
     * @param dealer
     *            The dealer.
     */
    void handStarted(Player dealer);
    
    /**
     * Handles the rotation of the actor (the player who's turn it is).
     * 
     * @param actor
     *            The new actor.
     */
    void actorRotated(Player actor);
    
    /**
     * Handles an update of this player.
     * 
     * @param player
     *            The player.
     */
    void playerUpdated(Player player);
    
    /**
     * Handles an update of the board.
     * 
     * @param cards
     *            The community cards.
     * @param bet
     *            The current bet.
     * @param pot
     *            The current pot.
     */
    void boardUpdated(List<Card> cards, int bet, int pot);
    
    /**
     * Handles the event of a player acting.
     * 
     * @param player
     *            The player that has acted.
     */
    void playerActed(Player player);

    /**
     * Requests this player to act, selecting one of the allowed actions.
     * 
     * @param minBet
     *            The minimum bet.
     * @param currentBet
     *            The current bet.
     * @param allowedActions
     *            The allowed actions.
     * 
     * @return The selected action.
     */
    Action act(int minBet, int currentBet, Set<Action> allowedActions);

}
