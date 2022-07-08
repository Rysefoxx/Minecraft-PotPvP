/*
 * MIT License
 *
 * Copyright (c) 2021. Rysefoxx
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package io.github.rysefoxx.potpvp.core.util;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * @author Rysefoxx | Rysefoxx#6772
 * @since 1.0
 */
public class StringUtils {

    @NotNull
    public static String formatString(@NotNull String key) {
        key = key.toLowerCase(Locale.ROOT);
        if (key.contains("_")) {
            key = key.replace("_", " ");
        }


        String[] split = key.split(" ");
        StringBuilder finalName = new StringBuilder();
        boolean hasFirstEntry = false;

        for (String word : split) {
            if (!hasFirstEntry) {
                finalName.append(org.apache.commons.lang.StringUtils.capitalize(word));
                hasFirstEntry = true;
                continue;
            }
            finalName.append(" ").append(org.apache.commons.lang.StringUtils.capitalize(word));
        }

        return finalName.toString();
    }

}
