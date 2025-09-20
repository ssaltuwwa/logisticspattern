public abstract class Logistics {
    public abstract Transport createTransport();

    public void planDelivery() {
        System.out.printf("[%s] Planning delivery...%n", this);
        Transport transport = createTransport();
        transport.deliver();
    }
}