package io.github.rysefoxx.potpvp.core.util;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

/**
 * @author Rysefoxx | Rysefoxx#6772
 * @since 7/8/2022
 */
@UtilityClass
public class Utils {

    public @NotNull String buildMessage(String @NotNull [] args, int offset) {
        StringBuilder builder = new StringBuilder();
        for (int i = offset; i < args.length; i++) {
            builder.append(args[i]);

            if (i != args.length - 1) {
                builder.append(" ");
            }
        }
        return builder.toString();
    }
}
