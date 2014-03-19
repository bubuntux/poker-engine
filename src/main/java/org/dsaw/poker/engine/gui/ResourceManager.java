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

import java.net.URL;

import javax.swing.ImageIcon;

import org.dsaw.poker.engine.Card;

/**
 * Utility class responsible for retrieving resource files.
 * 
 * @author Oscar Stigter
 */
public abstract class ResourceManager {
    
    private static final String IMAGE_PATH_FORMAT = "/images/card_%s.png"; 
    
    /**
     * Returns the image of a specific card.
     * 
     * @param card
     *            The card.
     * 
     * @return The image.
     */
    public static ImageIcon getCardImage(Card card) {
        // Use image order, which is different from value order.
        int sequenceNr = card.getSuit() * Card.NO_OF_RANKS + card.getRank();
        String sequenceNrString = String.valueOf(sequenceNr);
        if (sequenceNrString.length() == 1) {
            sequenceNrString = "0" + sequenceNrString;
        }
        String path = String.format(IMAGE_PATH_FORMAT, sequenceNrString);
        return getIcon(path);
    }
    
    /**
     * Returns an image resource.
     * 
     * @param path
     *            The path on the classpath.
     * 
     * @return The image resource.
     * 
     * @throws RuntimeException
     *             If the resource could not be found.
     */
    public static ImageIcon getIcon(String path) {
        URL url = ResourceManager.class.getResource(path);
        if (url != null) {
            return new ImageIcon(url);
        } else {
            throw new RuntimeException("Resource file not found: " + path);
        }
    }
    
}
