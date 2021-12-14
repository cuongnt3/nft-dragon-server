package com.zitga.idle.utils;

import com.zitga.utils.StringUtils;

import java.util.regex.Pattern;

public class NameValidator {

    // conform with RFC 5322
    // https://howtodoinjava.com/regex/java-regex-validate-email-address/
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");

    private static final String ALLOWED_CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ{}()[]#:;^,.!|&_`~@$%\"'/\\?=+-*";

    public static boolean isValidEmail(String email) {
        if (StringUtils.isNullOrEmpty(email)) {
            return false;
        }

        return EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean isValidName(String name) {
        char[] characters = name.toUpperCase().toCharArray();
        for (char character : characters) {
            if (Character.getType(character) != Character.UPPERCASE_LETTER
                    && Character.getType(character) != Character.LOWERCASE_LETTER
                    && Character.getType(character) != Character.OTHER_LETTER
                    && Character.getType(character) != Character.TITLECASE_LETTER
                    && Character.getType(character) != Character.DECIMAL_DIGIT_NUMBER
                    && !ALLOWED_CHARACTERS.contains(Character.toString(character))) {
                return false;
            }
        }

        return true;
    }

    public static boolean isValidGuildSearchQuery(String name) {
        char[] characters = name.toUpperCase().toCharArray();
        for (char character : characters) {
            if (Character.getType(character) != Character.UPPERCASE_LETTER
                    && Character.getType(character) != Character.LOWERCASE_LETTER
                    && Character.getType(character) != Character.OTHER_LETTER
                    && Character.getType(character) != Character.TITLECASE_LETTER
                    && Character.getType(character) != Character.DECIMAL_DIGIT_NUMBER) {
                return false;
            }
        }

        return true;
    }

    public static boolean isValidCrewSearchQuery(String name) {
        char[] characters = name.toUpperCase().toCharArray();
        for (char character : characters) {
            if (Character.getType(character) != Character.UPPERCASE_LETTER
                    && Character.getType(character) != Character.LOWERCASE_LETTER
                    && Character.getType(character) != Character.OTHER_LETTER
                    && Character.getType(character) != Character.TITLECASE_LETTER
                    && Character.getType(character) != Character.DECIMAL_DIGIT_NUMBER
                    && !ALLOWED_CHARACTERS.contains(Character.toString(character))) {
                return false;
            }
        }

        return true;
    }

    public static boolean isValidAccountName(String name) {
        char[] characters = name.toUpperCase().toCharArray();
        for (char character : characters) {
            if (!ALLOWED_CHARACTERS.contains(Character.toString(character))) {
                return false;
            }
        }

        return true;
    }
}
