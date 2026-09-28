import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("================================");
        System.out.println("        SAFESWITCH");
        System.out.println(" Intelligent Appliance Safety");
        System.out.println("================================");

        System.out.print("Enter appliance name: ");
        String appliance = scanner.nextLine();

        System.out.print("Is the appliance ON? (yes/no): ");
        String status = scanner.nextLine();

        System.out.print("Enter running time in minutes: ");
        int duration = scanner.nextInt();

        System.out.print("Enter temperature in Celsius: ");
        double temperature = scanner.nextDouble();

        scanner.nextLine();

        System.out.print("Is a person present? (yes/no): ");
        String personPresent = scanner.nextLine();

        System.out.print("Enter power consumption in watts: ");
        double power = scanner.nextDouble();

        System.out.println();
        System.out.println("================================");
        System.out.println("         SAFESWITCH RESULT");
        System.out.println("================================");

        System.out.println("Appliance: " + appliance);
        System.out.println("Status: " + status);
        System.out.println("Running Time: " + duration + " minutes");
        System.out.println("Temperature: " + temperature + " °C");
        System.out.println("Person Present: " + personPresent);
       System.out.println("Power: " + power + " W");

String risk = RiskCalculator.calculateRisk(
        status,
        duration,
        temperature,
        personPresent,
        power
);

System.out.println();
System.out.println("Risk Level: " + risk);

scanner.close(); 
    }
}