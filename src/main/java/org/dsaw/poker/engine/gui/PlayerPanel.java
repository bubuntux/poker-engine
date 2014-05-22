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

package org.dsaw.poker.engine.gui;

import org.dsaw.poker.engine.Card;
import org.dsaw.poker.engine.Player;
import org.dsaw.poker.engine.actions.Action;

import java.awt.*;
import java.math.BigDecimal;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

/**
 * Panel representing a player at the table.
 * 
 * @author Oscar Stigter
 */
public class PlayerPanel extends JPanel {
    
    /** The serial version UID. */
    private static final long serialVersionUID = 5851738752943098606L;

    /** Filled dealer button image when player is dealer. */
    private static final Icon BUTTON_PRESENT_ICON =
            ResourceManager.getIcon("/images/button_present.png");
    
    /** Empty dealer button image when player is not dealer. */
    private static final Icon BUTTON_ABSENT_ICON =
            ResourceManager.getIcon("/images/button_absent.png");
    
    private static final Icon CARD_PLACEHOLDER_ICON =
        ResourceManager.getIcon("/images/card_placeholder.png");

    private static final Icon CARD_BACK_ICON =
            ResourceManager.getIcon("/images/card_back.png");
    
    /** The border. */
    private static final Border BORDER = new EmptyBorder(10, 10, 10, 10);
    
    /** The label with the player's name. */
    private JLabel nameLabel;
    
    /** The label with the player's amount of cash. */
    private JLabel cashLabel;
    
    /** The label with the last action performed. */
    private JLabel actionLabel;
    
    /** The label with the player's current bet. */
    private JLabel betLabel;

    /** The label for the first hole card. */
    private JLabel card1Label;

    /** The label for the second hole card. */
    private JLabel card2Label;

    /** The label for the dealer button image. */
    private JLabel dealerButton;
    
    /**
     * Constructor.
     */
    public PlayerPanel() {
        setBorder(BORDER);
        setBackground(UIConstants.TABLE_COLOR);
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        
        nameLabel = new MyLabel();
        cashLabel = new MyLabel();
        actionLabel = new MyLabel();
        betLabel = new MyLabel();
        card1Label = new JLabel(CARD_PLACEHOLDER_ICON);
        card2Label = new JLabel(CARD_PLACEHOLDER_ICON);
        dealerButton = new JLabel(BUTTON_ABSENT_ICON);
        
        gc.gridx = 0;
        gc.gridy = 0;
        gc.gridwidth = 2;
        gc.gridheight = 1;
        gc.weightx = 1.0;
        gc.weighty = 1.0;
        gc.anchor = GridBagConstraints.CENTER;
        gc.fill = GridBagConstraints.NONE;
        add(dealerButton, gc);
        gc.gridx = 0;
        gc.gridy = 1;
        gc.gridwidth = 1;
        gc.gridheight = 1;
        gc.insets = new Insets(1, 1, 1, 1);
        gc.anchor = GridBagConstraints.CENTER;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.weightx = 1.0;
        gc.weighty = 1.0;
        add(nameLabel, gc);
        gc.gridx = 1;
        gc.gridy = 1;
        gc.gridwidth = 1;
        gc.gridheight = 1;
        gc.weightx = 1.0;
        gc.weighty = 1.0;
        gc.anchor = GridBagConstraints.CENTER;
        gc.fill = GridBagConstraints.HORIZONTAL;
        add(cashLabel, gc);
        gc.gridx = 0;
        gc.gridy = 2;
        gc.gridwidth = 1;
        gc.gridheight = 1;
        gc.weightx = 1.0;
        gc.weighty = 1.0;
        gc.anchor = GridBagConstraints.CENTER;
        gc.fill = GridBagConstraints.HORIZONTAL;
        add(actionLabel, gc);
        gc.gridx = 1;
        gc.gridy = 2;
        gc.gridwidth = 1;
        gc.gridheight = 1;
        gc.weightx = 1.0;
        gc.weighty = 1.0;
        gc.anchor = GridBagConstraints.CENTER;
        gc.fill = GridBagConstraints.HORIZONTAL;
        add(betLabel, gc);
        gc.gridx = 0;
        gc.gridy = 3;
        gc.gridwidth = 1;
        gc.gridheight = 1;
        gc.weightx = 1.0;
        gc.weighty = 1.0;
        gc.anchor = GridBagConstraints.CENTER;
        gc.fill = GridBagConstraints.NONE;
        add(card1Label, gc);
        gc.gridx = 1;
        gc.gridy = 3;
        gc.gridwidth = 1;
        gc.gridheight = 1;
        gc.weightx = 1.0;
        gc.weighty = 1.0;
        gc.anchor = GridBagConstraints.CENTER;
        gc.fill = GridBagConstraints.NONE;
        add(card2Label, gc);

        setInTurn(false);
        setDealer(false);
    }
    
    /**
     * Updates the panel.
     * 
     * @param player
     *            The player.
     */
    public void update(Player player) {
        nameLabel.setText(player.getName());
        cashLabel.setText("$ " + player.getCash());
      BigDecimal bet = player.getBet();
      if (bet.equals(BigDecimal.ZERO)) {
        betLabel.setText(" ");
        } else {
            betLabel.setText("$ " + bet);
        }
        Action action = player.getAction();
        if (action != null) {
            actionLabel.setText(action.getName());
        } else {
            actionLabel.setText(" ");
        }
        if (player.hasCards()) {
            Card[] cards = player.getCards();
            if (cards.length == 2) {
                // Visible cards.
                card1Label.setIcon(ResourceManager.getCardImage(cards[0]));
                card2Label.setIcon(ResourceManager.getCardImage(cards[1]));
            } else {
                // Hidden cards (face-down).
                card1Label.setIcon(CARD_BACK_ICON);
                card2Label.setIcon(CARD_BACK_ICON);
            }
        } else {
            // No cards.
            card1Label.setIcon(CARD_PLACEHOLDER_ICON);
            card2Label.setIcon(CARD_PLACEHOLDER_ICON);
        }
    }
    
    /**
     * Sets whether the player is the dealer.
     * 
     * @param isDealer
     *            True if the dealer, otherwise false.
     */
    public void setDealer(boolean isDealer) {
        if (isDealer) {
            dealerButton.setIcon(BUTTON_PRESENT_ICON);
        } else {
            dealerButton.setIcon(BUTTON_ABSENT_ICON);
        }
    }
    
    /**
     * Sets whether it's this player's turn to act.
     * 
     * @param inTurn
     *            True if it's the player's turn, otherwise false.
     */
    public void setInTurn(boolean inTurn) {
        if (inTurn) {
            nameLabel.setForeground(Color.YELLOW);
        } else {
            nameLabel.setForeground(Color.GREEN);
        }
    }
    
    /**
     * Custom label for a player panel.
     * 
     * @author Oscar Stigter
     */
    private static class MyLabel extends JLabel {

        /** Serial version UID. */
        private static final long serialVersionUID = 3607645928062082095L;

        /**
         * Constructor.
         */
        public MyLabel() {
            setBorder(UIConstants.LABEL_BORDER);
            setForeground(UIConstants.TEXT_COLOR);
            setHorizontalAlignment(SwingConstants.CENTER);
            setText(" ");
        }
    }
}
