public class RiskCalculator {

    public static String calculateRisk(
            String status,
            int duration,
            double temperature,
            String personPresent,
            double power) {

        if (status.equalsIgnoreCase("no")) {
            return "SAFE";
        }

        if (temperature >= 70 || power >= 1000) {
            return "HIGH RISK";
        }

        if (personPresent.equalsIgnoreCase("no") && duration >= 20) {
            return "WARNING";
        }

        if (personPresent.equalsIgnoreCase("no")) {
            return "MONITOR";
        }

        return "NORMAL";
    }
}