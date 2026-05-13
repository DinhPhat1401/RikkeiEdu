import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hãy nhập vào tháng bạn muốn biết số ngày: ");
        int month = sc.nextInt();
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                System.out.println("Tháng " + month + " có 31 ngày.");
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                System.out.println("Tháng " + month + " có 30 ngày.");
                break;
            case 2:
                System.out.println("Tháng " + month + " có 28 hoặc 29 ngày.");
                break;
            default:
                System.out.println("Tháng không hợp lệ. Vui lòng nhập từ 1 đến 12.");
        }
    }
}