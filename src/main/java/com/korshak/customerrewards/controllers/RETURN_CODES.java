package com.korshak.customerrewards.controllers;

/**
 * Codes for return.
 */

import java.util.HashMap;
import java.util.Map;

/**
 * return codes.
 */
public enum RETURN_CODES {

    ILLEGAL_ARGUMENTS(-1, "Illegal argument."),
    DATABASE_ERROR(-2, "A database error has occurred.");

    static Map<Integer, String> codeToDescription = new HashMap<>();

    static {
        for (RETURN_CODES errCode : values()) {
            codeToDescription.put(errCode.getCode(), errCode.getDescription());
        }
    }

    public static String getDescriptionByCode(int key) {
        return codeToDescription.get(key);
    }

    private final int code;
    private final String description;

    private RETURN_CODES(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return code + ": " + description;
    }
}