package com.pyramid.game.core.validation;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.regex.Pattern;

/**
 * Created by Suvorov Vassilievitch
 * Date: 21/04/2024
 * Time: 10:57
 * Project Name: pyramid-game-api
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RegexValidation {

    public static boolean isValid(String stringToValidate) {
        // The string is not valid.
        return Pattern.compile("[a-zA-Z0-9]").matcher(stringToValidate).find();
        // The string is valid.
    }
}
