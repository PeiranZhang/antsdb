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
package com.antsdb.saltedfish.sql.vdm;

import com.antsdb.saltedfish.cpp.FishObject;
import com.antsdb.saltedfish.cpp.Heap;
import com.antsdb.saltedfish.cpp.Value;
import com.antsdb.saltedfish.sql.DataType;

/**
 * converts a binary string to bytes
 * 
 * @author wgu0
 */
public class ToBytes extends UnaryOperator {

    public ToBytes(Operator upstream) {
        super(upstream);
    }

    @Override
    public long eval(VdmContext ctx, Heap heap, Parameters params, long pRecord) {
        if (upstream instanceof BinaryString) {
            return ((BinaryString)this.upstream).getBytes(heap);
        }
        long pValue = upstream.eval(ctx, heap, params, pRecord);
        if (pValue == 0) {
            return 0;
        }
        int type = Value.getType(heap, pValue);
        if (type == Value.TYPE_BYTES) {
            return pValue;
        }
        else if (type == Value.TYPE_BLOB) {
            return pValue;
        }
        else if (type == Value.TYPE_STRING) {
            long pBytes = FishObject.toBytes(heap, pValue);
            return pBytes;
        }
        else {
            return AutoCaster.toString(heap, pValue);
        }
    }

    @Override
    public DataType getReturnType() {
        return DataType.blob();
    }

}
