package ra.mobileshopmanagementsystem.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class CustomUtil {
    Scanner scanner = new Scanner(System.in);
    public String getString(String message) {
        System.out.print(message);
        return scanner.nextLine();
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

}
