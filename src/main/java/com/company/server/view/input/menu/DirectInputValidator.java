package com.company.server.view.input.menu;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DirectInputValidator {

    public static boolean checkUsername(String username) {

        Pattern usernameFormat = Pattern.compile("^[A-Za-z]\\w{5,29}$");
        Matcher checkUsernameFormat = usernameFormat.matcher(username);

        return checkUsernameFormat.matches();
    }

    public static boolean checkEmailAddress(String emailAddress) {

        Pattern emailAddressFormat = java.util.regex.Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$");
        Matcher checkEmailAddressFormat = emailAddressFormat.matcher(emailAddress);

        return checkEmailAddressFormat.matches();
    }

    public static boolean checkPassword(String password) {

        if (password.length() >= 8) {
            Pattern uppercaseLetter = Pattern.compile("[A-Z]");
            Pattern lowercaseLetter = Pattern.compile("[a-z]");
            Pattern numericValue = Pattern.compile("[0-9]");
            Pattern specialCharacter = Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~-]");

            Matcher checkUppercaseLetter = uppercaseLetter.matcher(password);
            Matcher checkLowercaseLetter = lowercaseLetter.matcher(password);
            Matcher checkNumericValue = numericValue.matcher(password);
            Matcher checkSpecialCharacter = specialCharacter.matcher(password);

            return (checkUppercaseLetter.find() && checkLowercaseLetter.find() && checkNumericValue.find() && checkSpecialCharacter.find());
        }

        return false;
    }
}
