package io.github.rysefoxx.potpvp.core.util;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnegative;

/**
 * @author Rysefoxx | Rysefoxx#6772
 * @since 7/8/2022
 */
@UtilityClass
public class Utils {

    public @NotNull String buildMessage(String @NotNull [] args, int offset) {
        String[] split = new String[args.length - offset];
        System.arraycopy(args, offset, split, 0, split.length);
        return String.join(" ", split);
    }

    @Contract(pure = true)
    public String numerous(@NotNull Number value, @NotNull String singular, @NotNull String plural) {
        return value.longValue() == 1 ? singular : plural;
    }
}
