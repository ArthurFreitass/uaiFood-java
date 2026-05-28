package model.util;

import model.exceptions.DomainException;

public class SimpleValidation {

    public static boolean checkResponseUserWantOrderItem(char choice) {
        char lowerChoice = Character.toLowerCase(choice);
        if (lowerChoice != 's' & lowerChoice != 'n' & lowerChoice != 'y') {
            throw new DomainException("Error: Invalid choice enter with [s/Sim][n/Não]!");
        }
        return true;
    }
}
