public class Television extends Device implements Connectable {
    public Television(int id, String name) {
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
        System.out.println(name + " is connected to Wi-Fi.");
    }

    
}
