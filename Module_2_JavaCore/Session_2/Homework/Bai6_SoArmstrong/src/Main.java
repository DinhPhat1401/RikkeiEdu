import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Chương trình tìm số Armstrong trong khoảng từ 1 đến N");
        int n;
        try{
            System.out.print("Nhập N: ");
            n = Integer.parseInt(scanner.nextLine());
            if (n <= 0) throw new Exception();
        } catch (Exception e) {
            System.out.println("Vui lòng nhập một số nguyên dương hợp lệ.");
            while(true) {
                try {
                    System.out.print("Nhập lại N: ");
                   n = Integer.parseInt(scanner.nextLine());
                    if (n > 0) {
                        break;
                    } else {
                       throw new Exception();
                    }
                } catch (Exception ex) {
                    System.out.println("Vui lòng nhập một số nguyên dương hợp lệ.");
                }
            }
        }
            System.out.println("Các số Armstrong từ 1 đến N là:");
            for (int i = 0; i <= n; i++) {
                if (isArmstrong(i)) {
                    System.out.print(i + " ");
                }
            }
    }

    public static boolean isArmstrong(int n) {
        int sum = 0;
        int temp = n;
        int digits = String.valueOf(n).length();
        while (temp > 0) {
            int digit = temp % 10;
            sum += Math.pow(digit, digits);
            temp /= 10;
        }
        return n == sum;
    }
}