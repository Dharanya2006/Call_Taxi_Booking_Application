package service;

import model.Taxi;
import model.Booking;

import java.util.ArrayList;
import java.util.List;

public class TaxiService {
    private List<Taxi> taxis;
    public TaxiService(int taxiCount) {
        taxis = new ArrayList<>();
        for (int i = 1; i <= taxiCount; i++) {
            taxis.add(new Taxi(i));
        }
    }
    private int calculateAmount(char from, char to) {
        int distance = Math.abs(to - from) * 15;
        if (distance <= 5)
            return 100;
        else
            return 100 + (distance - 5) * 10;
    }
    public void bookTaxi(int customerId, char from, char to, int pickupTime) {
        Taxi allocatedTaxi = null;
        int minDistance = Integer.MAX_VALUE;
        for (Taxi taxi : taxis) {
            if (taxi.getFreeTime() <= pickupTime) {
                int distance = Math.abs(taxi.getCurrentLocation() - from);
                if (distance < minDistance ||(distance == minDistance &&(allocatedTaxi == null ||
                     taxi.getTotalEarnings() < allocatedTaxi.getTotalEarnings()))) {
                    allocatedTaxi = taxi;
                    minDistance = distance;
                }
            }
        }
        if (allocatedTaxi == null) {
            System.out.println("Booking Rejected. No Taxi Available.");
            return;
        }
        int travelTime = Math.abs(to - from);
        int dropTime = pickupTime + travelTime;
        int amount = calculateAmount(from, to);
        Booking booking = new Booking(customerId, from, to, pickupTime, dropTime, amount);
        allocatedTaxi.addBooking(booking, to, dropTime);
        System.out.println("Taxi can be allotted. Taxi-" + allocatedTaxi.getTaxiId() + " is allotted.");
    }
    public void displayTaxiDetails() {
        for (Taxi taxi : taxis) {
            taxi.displayDetails();
        }
    }
}