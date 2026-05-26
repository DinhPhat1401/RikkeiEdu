import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.print("Nhập vào độ dài của mã giả: ");
        Scanner sc = new Scanner(System.in);
        int n;
        while(true){
            try {
                n = sc.nextInt();
                if (n < 1) {
                    System.out.print("Độ dài mã giả phải lớn hơn 0. Vui lòng nhập lại: ");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.print("Đầu vào không hợp lệ. Vui lòng nhập một số nguyên dương: ");
                sc.next(); // Clear the invalid input
            }
        }
        StringBuilder code = new StringBuilder();
        while (code.length() < n) {
            int choice = (int) (Math.random() * 3);
            switch (choice) {
                case 0:
                    char lowerCaseLetter = (char) ('a' + (int) (Math.random() * 26));
                    code.append(lowerCaseLetter);
                    break;
                case 1:
                    char upperCaseLetter = (char) ('A' + (int) (Math.random() * 26));
                    code.append(upperCaseLetter);
                    break;
                case 2:
                    int randomDigit = (int) (Math.random() * 10);
                    code.append(randomDigit);
                    break;
            }
        }
        System.out.println("Mã giả được tạo: " + code.toString());

    }
}