import java.lang.invoke.StringConcatFactory;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //Tính diện tích hình tròn
        System.out.println("-------------------------------");
        System.out.println("Công cụ tính toán diện tích hình tròn");
        while (true) {
            System.out.print("Nhập bán kính hình tròn: ");
            Scanner sc = new Scanner(System.in);
            double radius = sc.nextDouble();
            if (radius < 0) {
                System.out.println("Bán kính phải là số dương. Vui lòng nhập lại.");
            } else {
                double area = Math.PI * Math.pow(radius, 2);
//                System.out.println("Diện tích hình tròn có bán kính " + radius + " là: "+ String.format("%.2f",area));
                System.out.printf("Diện tích hình tròn có bán kính %.2f là: %.2f \n" , radius , area);
            }
            System.out.print("Bạn có muốn tiếp tục tính toán? (y/n): ");
            String continueChoice = sc.next();
            if (!continueChoice.equalsIgnoreCase("y")) {
                System.out.println("Thoát chương trình. Tạm biệt!");
                sc.close();
                break;
            }
        }
    }
}