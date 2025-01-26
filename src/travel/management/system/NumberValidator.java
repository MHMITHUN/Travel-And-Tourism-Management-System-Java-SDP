package travel.management.system;

public class NumberValidator {
    public static boolean isValidNumber(String number) {
        if (number.length() != 11) {
            return false;
        }

        if (number.charAt(0) != '0' || number.charAt(1) != '1') {
            return false;
        }

        char thirdChar = number.charAt(2);
        if (thirdChar != '3' && thirdChar != '4' && thirdChar != '5' &&
            thirdChar != '6' && thirdChar != '7' && thirdChar != '8' && thirdChar != '9') {
            return false;
        }

        for (int i = 3; i < 11; i++) {
            if (!Character.isDigit(number.charAt(i))) { 
                return false; 
            }
        }

        return true;
    }
}