public class SimpleLogistics extends Logistics {
    private final TransportMode mode;

    public SimpleLogistics(TransportMode mode) {
        this.mode = mode;
    }

    @Override
    public Transport createTransport() {
        switch (mode) {
            case ROAD: return new Truck();
            case SEA:  return new Ship();
            case AIR:  return new Airplane();
            default: throw new IllegalStateException("Unknown mode: " + mode);
        }
    }

    @Override
    public String toString() {
        return "SimpleLogistics(" + mode + ")";
    }
}
