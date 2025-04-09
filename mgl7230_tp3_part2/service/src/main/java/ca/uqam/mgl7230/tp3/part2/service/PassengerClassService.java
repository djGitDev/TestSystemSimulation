package ca.uqam.mgl7230.tp3.part2.service;

import ca.uqam.mgl7230.tp3.part2.model.passenger.PassengerClass;

public class PassengerClassService {

    public PassengerClass getPassengerClass(String passengerType) {
        return switch (passengerType) {
            case "first" -> PassengerClass.FIRST_CLASS;
            case "business" -> PassengerClass.BUSINESS_CLASS;
            default -> PassengerClass.ECONOMY_CLASS;
        };
    }
}
