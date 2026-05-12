import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Chương trình tính quãng đường mà bạn đã đi được");
        while (true) {
            System.out.print("Nhập vận tốc (km/h): ");
            double speed = sc.nextDouble();
            System.out.print("Nhập thời gian (giờ): ");
            double time = sc.nextDouble();
            if(speed <= 0 || time <= 0){
                System.out.println("Vận tốc và thời gian phải là số dương. Vui lòng nhập lại.");
                continue;
            }
            double distance = speed * time;
            System.out.println("Quãng đường bạn đã đi được: " + distance + " km");
            System.out.print("Bạn có muốn tính tiếp không? (y/n): ");
            String choice = sc.next();
            if (!choice.equalsIgnoreCase("y")) {
                System.out.println("Thoát chương trình. Tạm biệt!");
                break;
            }
        }
    }
}