package io.github.rysefoxx.potpvp.core.command.extension.annotation;

import io.github.rysefoxx.potpvp.core.command.extension.ExecutionType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface BaseExecution {

    ExecutionType type() default ExecutionType.PLAYER;

}
