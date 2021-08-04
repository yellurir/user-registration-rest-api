package com.gamesys.codingtest.userregistrationrestapi.service;

import com.gamesys.codingtest.userregistrationrestapi.exception.BadRequestException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The UserRegistrationService class implements the business logic
 * checks performed on parameters-
 * 1. “username” (alphanumerical, no spaces)
 * 2. “password” (at least four characters, at least one lower case character, at least one upper case character,
 * at least one number)
 * 3.  “dateOfBirth” (ISO 8601)
 *  @author Revanth Yelluri
 */

public class UserFieldValidation {

    //checks whether the username is alphanumeric or not, no spaces allowed
    public static boolean validateUsername(String username){
        if (!username.contains(" ") &&
                username.matches("[a-zA-Z0-9]+")){
            return true;
        }
        return false;
    }

    /*checks whether the password meets the given criteria (at least four characters, at least one lower case character,
     at least one upper case character, at least one number)*/
    public static boolean validatePassword(String password){
        if (password.length()>=4 &&
                !password.equals(password.toLowerCase()) &&
                !password.equals(password.toUpperCase()) &&
                password.matches(".*\\d.*")){
            return true;
        }
        return false;
    }

    //checks whether the date has a valid ISO 8601 format
    public static boolean validateDateOfBirth(String dateOfBirth){
        try{
            LocalDate.parse(dateOfBirth, DateTimeFormatter.ISO_LOCAL_DATE);
        }catch(DateTimeParseException e){
            throw new BadRequestException("Failed validation - invalid date");
        }
        return true;
    }
}
