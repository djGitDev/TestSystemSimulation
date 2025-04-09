package ca.uqam.mgl7230.tp3.part1.service;

import ca.uqam.mgl7230.tp3.part1.model.passenger.PassengerClass;

public class PassengerClassService {

    public PassengerClass getPassengerClass(String passengerType) {
        return switch (passengerType) {
            case "first" -> PassengerClass.FIRST_CLASS;
            case "business" -> PassengerClass.BUSINESS_CLASS;
            default -> PassengerClass.ECONOMY_CLASS;
        };
    }


    public String getPassengerType(final PassengerClass passengerClass) {
        return switch (passengerClass) {
            case FIRST_CLASS -> "first";
            case BUSINESS_CLASS -> "business";
            default -> "economy";
        };
    }
}
