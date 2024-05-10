package com.pyramid.game.core.validation.impl;

import com.pyramid.game.core.validation.MandatoryField;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Created by Suvorov Vassilievitch
 * Date: 15/04/2024
 * Time: 13:05
 * Project Name: pyramid-game-api
 */
public class MandatoryFieldValidator implements ConstraintValidator<MandatoryField, Object> {

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        if(value != null) {
            if(value instanceof String s) {
                return !s.isEmpty();
            }
            return true;
        }
        else{
            return false;
        }
    }

}
