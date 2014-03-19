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

/**
 * Supported table types (betting structures).
 * 
 * @author Oscar Stigter
 */
public enum TableType {
    
    /** Fixed-Limit Texas Hold'em. */
    FIXED_LIMIT("Fixed-Limit"),
    
    /** No-Limit Texas Hold'em. */
    NO_LIMIT("No-Limit"),
    
    ;
    
    /** Display name. */
    private String name;
    
    /**
     * Constructor.
     * 
     * @param name
     *            The display name.
     */
    TableType(String name) {
        this.name = name;
    }
    
    /**
     * Returns the display name.
     * 
     * @return The display name.
     */
    public String getName() {
        return name;
    }

}
