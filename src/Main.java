public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            demo(new SimpleLogistics(TransportMode.ROAD));
            demo(new SimpleLogistics(TransportMode.SEA));
            demo(new SimpleLogistics(TransportMode.AIR));
            return;
        }

        String arg = args[0].trim().toLowerCase();
        TransportMode mode = null;
        if ("road".equals(arg)) mode = TransportMode.ROAD;
        else if ("sea".equals(arg)) mode = TransportMode.SEA;
        else if ("air".equals(arg)) mode = TransportMode.AIR;

        if (mode == null) {
            System.out.println("Unknown mode. Use: road | sea | air. Showing all:");
            demo(new SimpleLogistics(TransportMode.ROAD));
            demo(new SimpleLogistics(TransportMode.SEA));
            demo(new SimpleLogistics(TransportMode.AIR));
        } else {
            demo(new SimpleLogistics(mode));
        }
    }

    private static void demo(Logistics logistics) {
        logistics.planDelivery();
    }
}
