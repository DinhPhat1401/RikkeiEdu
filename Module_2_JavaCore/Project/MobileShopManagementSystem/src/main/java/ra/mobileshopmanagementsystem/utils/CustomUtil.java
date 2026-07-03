package ra.mobileshopmanagementsystem.utils;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class CustomUtil {
    Scanner scanner = new Scanner(System.in);
    public String getString(String message) {
        System.out.print(message);
        while(true) {
            message = scanner.nextLine();
            if(message.isBlank()){
                System.out.println("Vui lòng nhập dữ liệu! ");
            } else {
                return message;
            }
        }
    }
    public int getInt(String message) {
        System.out.print(message);
        while(true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Vui lòng nhập một số hợp lệ");
            }
        }
    }

    public String getEmail(String message){
        System.out.print(message);
        while(true) {
            message = scanner.nextLine();
            if (message.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                return message;
            } else {
                System.out.println("Vui lòng nhập một email hợp lệ");
            }
        }
    }
    public String getPassword(String message){
        System.out.print(message);
        while(true) {
            if(message.isBlank()){
                System.out.println("Mật khẩu không được để trống");
            } else if(message.length() < 8){
                System.out.println("Mật khẩu phải có ít nhất 5 ký tự");
            } else {
                return BCrypt.hashpw(message, BCrypt.gensalt());
            }
        }
    }
    public boolean checkPassword(String password, String hashPassword){
        return BCrypt.checkpw(password, hashPassword);
    }
    public double getDouble(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số hợp lệ!");
            }
        }
    }
}
