package com.miquido.Validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CharacterValidator.class)
public @interface ValidCharacter {

    String message() default "Character must be higher than %s";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
