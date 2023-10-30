// Name: Saniyah Smith
// Computing ID: xjk8nm@virginia.edu
// Assignment Name: HW08
// Resources: ChatGPT september version , fixed (made two passwords for parameter) differences, and made setcurrentPassword(), fixes to small mistakes in setPassword () and isValid(), pointed out missing validation in Password constructor, pointed out wrong assignment(was checking if current pass had digit instead of new pass)

import java.util.Random;
import java.util.Scanner;

// class with instance variables
public class Password {
    String curPassword;
    String newPassword;

    // constructor
    public Password(String firstPassword) {
        curPassword = "";
        newPassword = "";
        if (isValid(firstPassword)) {
            setCurPassword(firstPassword);
        }
    }

    // empty constructor
    public Password() {
        curPassword = "";
        newPassword = "";

    }

    private boolean hasDigit(String password) {
        // for elements in current password (single chars), if the characteris a digit,
        // return true else return false
        for (char n : password.toCharArray()) {
            if (Character.isDigit(n)) {
                return true;
            }
        }
        return false;

    }

    private boolean isSamePassword(String curPassword, String newPassword, int index) {
        // if index equals current password length or new password length, return
        // current password length equals new password length
        if (index >= curPassword.length() || index >= newPassword.length()) {
            return curPassword.length() == newPassword.length();
        }
        // if the char at the index is not equal to the char at the new password index,
        // return false
        if (curPassword.charAt(index) != newPassword.charAt(index)) {
            return false;
            // else if the index equals current password length - 1 or index equal new
            // password length - 1, return false
        } else if (index == curPassword.length() - 1 || index == newPassword.length() - 1) {
            return false;
        }
        // else return, index + 1
        else {
            return isSamePassword(curPassword, newPassword, index + 1);
        }
    }

    private int differences(String newPassword, String curPassword, int index) {
        // if condition, if the index equals current password len or new password length
        // then return subtract the two length
        // simply checks if index is on the last element
        if (index >= curPassword.length() || index >= newPassword.length()) {
            return Math.abs(curPassword.length() - newPassword.length());
        }
        // if the current password's char at the index does not equal the new password's
        // char at the index, return 1 plus the differences and plus 1 index
        if (curPassword.charAt(index) != newPassword.charAt(index)) {
            return 1 + differences(newPassword, curPassword, index + 1);
        }
        // in cases the char at index is the same, and end of passwords are not met
        return differences(newPassword, curPassword, index + 1);
    }

    boolean isDifferentEnough(String newPassword, String curPassword, int minDifferences) {
        // return difference method greater than or equal to minDifferences
        // simply checks if the differences is greater than or equal to the min
        // differences
        return differences(newPassword, curPassword, 0) >= minDifferences;

    }

    public String getPassword() {
        // simply returns current password
        return curPassword;
    };

    public boolean setPassword(String newPassword) {
        // sets current password to new password if valid, returns true if valid and
        // false if invalid
        if (isValid(newPassword) && hasDigit(newPassword) && isDifferentEnough(newPassword, curPassword, 3)) {

            return true;
        }
        return false;
    }

    public void setEmpty(String emptyString) {
        curPassword = "";
    }

    private boolean isValid(String newPassword) {
        // if the new password is different enough and it is not the same password
        if (isDifferentEnough(newPassword, newPassword, 0) == true) {
            if (isSamePassword(newPassword, newPassword, 0) == false) {
                return true;
            }
        }
        // if a condition is not true, then empty the new password, and return false
        setEmpty(newPassword);
        return false;
    }

    public String toString() {

        char firstCharacter = curPassword.charAt(0);
        char lastCharacter = curPassword.charAt(curPassword.length() - 1);
        char symbol = '*';

        // gets random number (1, 20)
        Random randomInteger = new Random();
        int answer = randomInteger.nextInt(20) + 1;
        // build string with desired chars
        StringBuilder passwordString = new StringBuilder();
        passwordString.append(firstCharacter);
        // loop to make multiple symbols
        for (int i = 0; i < answer; i++) {
            passwordString.append(symbol);
        }
        passwordString.append(lastCharacter);
        return passwordString.toString();

    }

    public boolean equals(Password other) {
        // if other is null, returns false
        if (other == null) {
            return false;
        }
        // else return the passwords equal
        return this.curPassword.equals(other.curPassword);
    }

    public void setCurPassword(String curPassword) {
        // if current password is not null and the current password have a digit,
        // then....
        if (curPassword != null && hasDigit(curPassword)) {
            this.curPassword = curPassword;
        }
    }

    public static void main(String[] args) {
        // simply checks input
        try (Scanner passwordInput = new Scanner(System.in)) {
            System.out.println("Enter current password: ");
            // store the input from the line as current password
            String curPassword = passwordInput.nextLine();
            Password password = new Password(curPassword);

            System.out.println("Enter new password:  ");
            String newPassword = passwordInput.nextLine();
            String string = password.toString();
            System.out.println(string);

            if (password.hasDigit(string)) {
                System.out.println("The password contains a digit");
            } else {
                System.out.println("The password does not contain a digit");
            }
            if (password.isSamePassword(newPassword, newPassword, 0))

            {
                System.out.println("The passwords matched!");
            }

            else {
                int differences = password.differences(newPassword, string, 0);
                System.out.println("The password is different in " + differences + " place.");
            }
            int minDifferences = 3;
            if (password.isDifferentEnough(newPassword, string, minDifferences)) {
                System.out.println("The new password is different enough.");
            } else {
                System.out.println("The new password is not different enough.");
            }

            if (password.setPassword(newPassword)) {
                System.out.println("Password changed successfully.");
            } else {
                System.out.println("Password change failed.");
            }

        }
    }
}