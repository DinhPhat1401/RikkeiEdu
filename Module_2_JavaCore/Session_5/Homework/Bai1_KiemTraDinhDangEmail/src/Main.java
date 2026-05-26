import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập vào địa chỉ Email cần kiểm tra: ");
        String email = sc.nextLine();
        if (email.matches("^[A-Za-z0-9._]+@[A-Za-z0-9.]+\\.[A-Za-z]{2,6}$")){
            System.out.println("Email hợp lệ.");
        }else{
            System.out.println("Email không hợp lệ.");
        }
    }
}