package model.util;

import model.exceptions.DomainException;

public class CheckOrderNumber {

    public static boolean isValidOrderNumber(int num) {
        if (num >= 0 && num < 20) {
            return true;
        }
        throw new DomainException("Error: Invalid order number!");
    }
}
