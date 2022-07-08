package io.github.rysefoxx.potpvp.core.command.extension.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AlternativeCommand {

    String command();

    String[] aliases() default {};

    String permission() default "";

}
