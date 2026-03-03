package app;

import service.TaxiService;
public class Main {
    public static void main(String[] args) {
        TaxiService service = new TaxiService(4);
        System.out.println("Customer ID: 1 Pickup: A | Drop: B Pickup Time: 9");
        service.bookTaxi(1, 'A', 'B', 9);
        System.out.println("Customer ID: 2 Pickup: B | Drop: D Pickup Time: 9");
        service.bookTaxi(2, 'B', 'D', 9);
        System.out.println("Customer ID: 3 Pickup: B | Drop: C Pickup Time: 12");
        service.bookTaxi(3, 'B', 'C', 12);
        System.out.println("\n--- Taxi Details ---\n");
        service.displayTaxiDetails();
    }
}