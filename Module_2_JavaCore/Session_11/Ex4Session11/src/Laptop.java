public class Laptop extends Device implements Connectable, Chargeable {
    public Laptop(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public void turnOn() {
        System.out.println(name + " is turned on.");
    }

    @Override
    public void turnOff() {
        System.out.println(name + " is turned off.");
    }

    @Override
    public void connectWifi() {
        System.out.println(name + " is connected to WiFi.");
    }

    @Override
    public void charge() {
        System.out.println(name + " is charging.");
    }
}
