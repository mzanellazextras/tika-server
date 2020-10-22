/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Modifications copyright (C) 2020 ContraxSuite, LLC
 */

package com.lexpredict.tika;

import java.lang.reflect.Field;

public class FieldLookup {

    // find field in passed class or one of his ancestors
    public static Object getFieldValue(Object obj, String fieldName) {
        Field f = findField(obj.getClass(), fieldName);
        if (f == null)
            return null;
        try {
            f.setAccessible(true);
            return f.get(obj);
        } catch (IllegalAccessException e) {
            return null;
        }
    }

    public static Field findField(Class<?> cls, String fieldName) {
        while (true) {
            try {
                return cls.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                // pass
            }
            cls = cls.getSuperclass();
            if (cls == null) break;
        }
        return null;
    }
}
