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

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import org.dsaw.poker.engine.TableType;
import org.dsaw.poker.engine.actions.Action;
import org.dsaw.poker.engine.actions.BetAction;
import org.dsaw.poker.engine.actions.RaiseAction;

/**
 * Panel with buttons to let a human player select an action.
 * 
 * @author Oscar Stigter
 */
public class ControlPanel extends JPanel implements ActionListener {
    
    /** Serial version UID. */
    private static final long serialVersionUID = 4059653681621749416L;
    
    /** The table type (betting structure). */
    private final TableType tableType;

    /** The Check button. */
    private final JButton checkButton;
    
    /** The Call button. */
    private final JButton callButton;
    
    /** The Bet button. */
    private final JButton betButton;
    
    /** The Raise button. */
    private final JButton raiseButton;
    
    /** The Fold button. */
    private final JButton foldButton;
    
    /** The Continue button. */
    private final JButton continueButton;
    
    /** The betting panel. */
    private final AmountPanel amountPanel;

    /** Monitor while waiting for user input. */
    private final Object monitor = new Object();
    
    /** The selected action. */
    private Action selectedAction;
    
    /**
     * Constructor.
     */
    public ControlPanel(TableType tableType) {
        this.tableType = tableType;
        setBackground(UIConstants.TABLE_COLOR);
        continueButton = createActionButton(Action.CONTINUE);
        checkButton = createActionButton(Action.CHECK);
        callButton = createActionButton(Action.CALL);
        betButton = createActionButton(Action.BET);
        raiseButton = createActionButton(Action.RAISE);
        foldButton = createActionButton(Action.FOLD);
        amountPanel = new AmountPanel();
    }
    
    /**
     * Waits for the user to click the Continue button.
     */
    public void waitForUserInput() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                removeAll();
                add(continueButton);
                repaint();
            }
        });
        Set<Action> allowedActions = new HashSet<>();
        allowedActions.add(Action.CONTINUE);
        getUserInput(BigDecimal.ZERO, BigDecimal.ZERO, allowedActions);
    }
    
    /**
     * Waits for the user to click an action button and returns the selected
     * action.
     * 
     * @param minBet
     *            The minimum bet.
     * @param cash
     *            The player's remaining cash.
     * @param allowedActions
     *            The allowed actions.
     * 
     * @return The selected action.
     */
    public Action getUserInput(BigDecimal minBet, BigDecimal cash, final Set<Action> allowedActions) {
        selectedAction = null;
        while (selectedAction == null) {
            // Show the buttons for the allowed actions.
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    removeAll();
                    if (allowedActions.contains(Action.CONTINUE)) {
                        add(continueButton);
                    } else {
                        if (allowedActions.contains(Action.CHECK)) {
                            add(checkButton);
                        }
                        if (allowedActions.contains(Action.CALL)) {
                            add(callButton);
                        }
                        if (allowedActions.contains(Action.BET)) {
                            add(betButton);
                        }
                        if (allowedActions.contains(Action.RAISE)) {
                            add(raiseButton);
                        }
                        if (allowedActions.contains(Action.FOLD)) {
                            add(foldButton);
                        }
                    }
                    repaint();
                }
            });
            
            // Wait for the user to select an action.
            synchronized (monitor) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    // Ignore.
                }
            }
            
            // In case of a bet or raise, show panel to select amount.
            if (tableType == TableType.NO_LIMIT && (selectedAction == Action.BET || selectedAction == Action.RAISE)) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        removeAll();
                        add(amountPanel);
                        repaint();
                    }
                });
                selectedAction = amountPanel.show(selectedAction, minBet, cash);
                if (selectedAction == Action.BET) {
                    selectedAction = new BetAction(amountPanel.getAmount());
                } else if (selectedAction == Action.RAISE) {
                    selectedAction = new RaiseAction(amountPanel.getAmount());
                } else {
                    // User cancelled.
                    selectedAction = null;
                }
            }
        }
        
        return selectedAction;
    }
    
    /*
     * (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == continueButton) {
            selectedAction = Action.CONTINUE;
        } else if (source == checkButton) {
            selectedAction = Action.CHECK;
        } else if (source == callButton) {
            selectedAction = Action.CALL;
        } else if (source == betButton) {
            selectedAction = Action.BET;
        } else if (source == raiseButton) {
            selectedAction = Action.RAISE;
        } else {
            selectedAction = Action.FOLD;
        }
        synchronized (monitor) {
            monitor.notifyAll();
        }
    }
    
    /**
     * Creates an action button.
     * 
     * @param action
     *            The action.
     * 
     * @return The button.
     */
    private JButton createActionButton(Action action) {
        String label = action.getName();
        JButton button = new JButton(label);
        button.setMnemonic(label.charAt(0));
        button.setSize(100, 30);
        button.addActionListener(this);
        return button;
    }

}
