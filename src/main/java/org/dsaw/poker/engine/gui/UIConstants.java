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

import java.awt.Color;

import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 * Shared UI constants.
 * 
 * @author Oscar Stigter
 */
public interface UIConstants {

    /** The table color. */
    Color TABLE_COLOR = new Color(0, 128, 0); // Dark green

    /** The text color. */
    Color TEXT_COLOR = Color.GREEN;

    /** The border used around labels. */
    Border LABEL_BORDER = new LineBorder(Color.BLACK, 1);
    
    /** The border used around panels. */
    Border PANEL_BORDER = new CompoundBorder(
            new LineBorder(Color.BLACK, 1), new EmptyBorder(10, 10, 10, 10));

}
