package io.github.rysefoxx.potpvp.core.util;

import org.jetbrains.annotations.NotNull;

/**
 * @author Rysefoxx | Rysefoxx#6772
 * @since 7/8/2022
 */
public class Maths {

    public static boolean isInteger(@NotNull String s, boolean withZero) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException nfe) {
            return false;
        }
        if (withZero) {
            return Integer.parseInt(s) >= 0;
        }
        return Integer.parseInt(s) >= 1;
    }

}
