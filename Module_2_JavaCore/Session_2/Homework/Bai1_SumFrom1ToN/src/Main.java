import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Chương trình tính tổng từ 1 đến N");
        while (true) {
            System.out.println("Nhập một số nguyên dương N: ");
            int N = sc.nextInt();
            if (N <= 0) {
                System.out.println("Vui lòng nhập một số nguyên dương. Hãy thử lại.");
                continue;
            }
            int sum = 0;
            for (int i = 1; i <= N; i++) {
                sum += i;
            }
            System.out.println("Tổng từ 1 đến N là: " + sum);
            System.out.print("Bạn có muốn tính tiếp không? (y/n): ");
            String choice = sc.next();
            if (!choice.equalsIgnoreCase("y")) {
                System.out.println("Thoát chương trình. Tạm biệt!");
                break;
            }

        }
    }
}