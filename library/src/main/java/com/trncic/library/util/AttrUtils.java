/* * Copyright (C) 2021 Huawei Device Co., Ltd.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.trncic.library.util;

import ohos.agp.components.AttrSet;
import ohos.agp.components.element.Element;
import ohos.agp.utils.Color;

public class AttrUtils {
    // Attr style params
    public static final String inactiveDot = "inactiveDot";
    public static final String activeDot = "activeDot";
    public static final String dotSize = "dotSize";
    public static final String spacing = "spacing";
    public static final String jumpingSpeed = "jumpingSpeed";
    public static final String activeDotIndex = "activeDotIndex";

    // Get int value from Attributes
    public static Integer getIntFromAttr(AttrSet attrs, String name, Integer defaultValue) {
        Integer value = defaultValue;
        try {
            if (attrs.getAttr(name).isPresent()) {
                value = attrs.getAttr(name).get().getIntegerValue();
            }
        } catch (NullPointerException e) {
            e.getMessage();
        }
        return value;
    }

    // Get dimen value from Attributes
    public static float getDimenFromAttr(AttrSet attrs, String name, float defaultValue) {
        float value = defaultValue;
        try {
            if (attrs.getAttr(name).isPresent()) {
                value = attrs.getAttr(name).get().getDimensionValue();
            }
        } catch (NullPointerException e) {
            e.getMessage();
        }
        return value;
    }

    // Get int Element from Attributes
    public static Element getElementFromAttr(AttrSet attrs, String name, Element defaultValue) {
        Element value = defaultValue;
        try {
            if (attrs.getAttr(name).isPresent()) {
                value = attrs.getAttr(name).get().getElement();
            }
        } catch (NullPointerException e) {
            e.getMessage();
        }
        return value;
    }

    // Get int Color from Attributes
    public static Color getColorFromAttr(AttrSet attrs, String name, Color defaultValue) {
        Color value = defaultValue;
        try {
            if (attrs.getAttr(name).isPresent()) {
                value = attrs.getAttr(name).get().getColorValue();
            }
        } catch (NullPointerException e) {
            e.getMessage();
        }
        return value;
    }
}