/*-------------------------------------------------------------------------------------------------
 _______ __   _ _______ _______ ______  ______
 |_____| | \  |    |    |______ |     \ |_____]
 |     | |  \_|    |    ______| |_____/ |_____]

 Copyright (c) 2016, antsdb.com and/or its affiliates. All rights reserved. *-xguo0<@

 This program is free software: you can redistribute it and/or modify it under the terms of the
 GNU GNU Lesser General Public License, version 3, as published by the Free Software Foundation.

 You should have received a copy of the GNU Affero General Public License along with this program.
 If not, see <https://www.gnu.org/licenses/lgpl-3.0.en.html>
-------------------------------------------------------------------------------------------------*/
package com.antsdb.saltedfish.nosql;

/**
 * 
 * @author *-xguo0<@
 */
public class LogRetentionStrategy {
    public boolean shouldRetain(SpaceManager sm, Space space) {
        long spnow = sm.getAllocationPointer();
        int nowSpaceId = SpaceManager.getSpaceId(spnow);
        return nowSpaceId - space.id <= 1;
    }

    @Override
    public String toString() {
        return "minimum log retention strategy";
    }
}
