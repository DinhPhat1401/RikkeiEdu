package ra.mobileshopmanagementsystem.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {
    private static final Properties PROP = new Properties() ;
    static {
        InputStream inputStream = DBUtil.class.getClassLoader().getResourceAsStream("db.properties");
        try {
            PROP.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = java.sql.DriverManager.getConnection(PROP.getProperty("db.url"),
                    PROP.getProperty("db.user"),
                    PROP.getProperty("db.password"));

        } catch (ClassNotFoundException e) {
            System.err.println("Không tìm thấy Driver của PostgreSQL! Bạn đã thêm thư viện chưa?");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Lỗi kết nối CSDL: Sai URL, User hoặc Password!");
            e.printStackTrace();
        }
        return conn;
    }


    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}

