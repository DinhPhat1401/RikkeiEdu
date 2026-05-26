import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String name = "";
        String email = "";
        String phone = "";
        String password = "";
        while (true) {
            System.out.println("********* Quản Lý Người Dùng *********");
            System.out.println("1. Nhập thông tin người dùng");
            System.out.println("2. Chuẩn hóa họ tên");
            System.out.println("3. Kiểm tra email hợp lệ");
            System.out.println("4. Kiểm tra số điện thoại hợp lệ");
            System.out.println("5. Kiểm tra mật khẩu hợp lệ");
            System.out.println("6. Thoát");
            System.out.print("Chọn chức năng: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    System.out.print("Nhập họ tên: ");
                    name = sc.nextLine();
                    System.out.print("Nhập email: ");
                    email = sc.nextLine();
                    System.out.print("Nhập số điện thoại: ");
                    phone = sc.nextLine();
                    System.out.print("Nhập mật khẩu: ");
                    password = sc.nextLine();
                    break;
                case 2:
                    if (name.isEmpty()) {
                        System.out.println("Vui lòng nhập họ tên trước");
                        break;
                    }
                    StringBuilder sb = new StringBuilder();
                    String[] words = name.trim().toLowerCase().split("\\s+");
                    for (String word : words) {
                        sb.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1)).append(" ");
                    }

                    name = sb.toString();
                    System.out.println("Họ tên chuẩn hóa: " + name);
                    break;
                case 3:
                    if (email.isEmpty()) {
                        System.out.println("Vui lòng nhập email trước");
                        break;
                    }
                    if (email.matches( "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
                        System.out.println("Email hợp lệ");
                    } else {
                        System.out.println("Email không hợp lệ");
                    }
                    break;
                case 4:
                    if (phone.isEmpty()) {
                        System.out.println("Vui lòng nhập số điện thoại trước");
                        break;
                    }
                    if (phone.matches("^(?:\\+84|0)(3[2-9]|5[25689]|7[06789]|8[1-9]|9[0-9])\\d{7}$")) {
                        System.out.println("Số điện thoại hợp lệ");
                    } else {
                        System.out.println("Số điện thoại không hợp lệ");
                    }
                    break;
                case 5:
                    if (password.isEmpty()) {
                        System.out.println("Vui lòng nhập mật khẩu trước");
                        break;
                    }
                    if (password.matches( "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$")) {
                        System.out.println("Mật khẩu hợp lệ");
                    } else {
                        System.out.println("Mật khẩu không hợp lệ");
                    }
                    break;
                case 6:
                    System.out.println("Thoát chương trình");
                    System.exit(0);
                default:
                    System.out.println("Lựa chọn không hợp lệ");
            }
        }
    }
}