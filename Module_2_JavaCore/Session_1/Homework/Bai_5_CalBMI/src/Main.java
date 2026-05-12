import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Công cụ tính toán BMI");
        System.out.println("Nhập cân nặng (kg): ");
        double weight = sc.nextDouble();
        System.out.println("Nhập chiều cao (m): ");
        double height = sc.nextDouble();
        double bmi = weight/(height*height);
        System.out.println("-----Kết quả-----");
        System.out.printf("Chỉ số BMI của bạn là: %.2f \n", bmi);
            if (bmi < 18.5) {
                System.out.println("Bạn đang thiếu cân.");
            } else if (bmi >= 18.5 && bmi < 25) {
                System.out.println("Bạn có cân nặng bình thường.");
            } else if (bmi >= 25 && bmi < 30) {
                System.out.println("Bạn đang thừa cân.");
            } else {
                System.out.println("Bạn đang béo phì.");
            }
    }
}