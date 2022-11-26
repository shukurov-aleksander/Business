package com.ku.business.entity;

public enum OperationType {
    PURCHASE("P"),
    REMITTANCE("R"),
    BARTER("B"),
    OUTSOURCING("O");

    private String shortName;

    private OperationType(String shortName) {
        this.shortName = shortName;
    }

    public String getShortName() {
        return shortName;
    }

    public static OperationType fromShortName(String shortName) {
        switch (shortName) {
            case "P":
                return PURCHASE;

            case "R":
                return REMITTANCE;

            case "B":
                return BARTER;

            case "O":
                return OUTSOURCING;

            default:
                throw new IllegalArgumentException("ShortName [" + shortName
                        + "] not supported.");
        }
    }
}
