import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int firstNumber, secondNumber;
        System.out.println("Nhập vào số thứ nhất: ");
        firstNumber = sc.nextInt();
        System.out.println("Nhập vào số thứ hai: ");
        secondNumber = sc.nextInt();
        System.out.println("Số thứ nhất bạn nhập là: " + firstNumber);
        System.out.println("Số thứ hai bạn nhập là: " + secondNumber);
        System.out.println("------Kết quả tính toán------");
        System.out.println("Tổng = " + (firstNumber + secondNumber));
        System.out.println("Hiệu = " + (firstNumber - secondNumber));
        System.out.println("Tích = " + (firstNumber * secondNumber));
        System.out.println("Thương = " + (firstNumber / secondNumber));
        System.out.println("Phần dư = " + (firstNumber % secondNumber));
    }
}

