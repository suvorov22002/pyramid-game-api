package com.pyramid.game.core.validation;

import com.pyramid.game.core.utils.Constants;
import com.pyramid.game.core.validation.impl.MandatoryFieldValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = MandatoryFieldValidator.class)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MandatoryField {

    String message() default Constants.VALUE_SHOULD_NOT_BE_NULL;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
