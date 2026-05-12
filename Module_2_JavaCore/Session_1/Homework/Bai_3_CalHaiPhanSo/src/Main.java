import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Chương trình tính tổng 2 phân số");
        System.out.print("Nhập tử số phân số thứ nhất: ");
        int num1 = sc.nextInt();
        System.out.print("Nhập mẫu số phân số thứ nhất: ");
        int den1 = sc.nextInt();
        System.out.print("Nhập tử số phân số thứ hai: ");
        int num2 = sc.nextInt();
        System.out.print("Nhập mẫu số phân số thứ hai: ");
        int den2 = sc.nextInt();
        System.out.println("-----Kết quả-----");
        System.out.printf("Tổng của hai phân số là: %d/%d ", num1*den2 + num2*den1, den1*den2 );
    }
}