//Đề bài yêu cầu 1000000 nhưng mà máy em nó chạy thấy tội nghiệp quá nên em
// giảm xuống còn 100000 thôi ạ
public class Main {
    public static void main(String[] args) {
        String string = "Hello";
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            string += " World";
        }
        long end = System.currentTimeMillis();
        System.out.println("Thời gian thực hiện với String: " + (end - start) + " ms");
        string = "Hello";
        start = System.currentTimeMillis();
        StringBuilder stringBuilder = new StringBuilder(string);
        for (int i = 0; i < 100000; i++) {
            stringBuilder.append(" World");
        }
        end = System.currentTimeMillis();
        System.out.println("Thời gian thực hiện với StringBuilder: " + (end - start) + " ms");
        string = "Hello";
        start = System.currentTimeMillis();
        StringBuffer stringBuffer = new StringBuffer(string);
        for (int i = 0; i < 100000; i++) {
            stringBuffer.append(" World");
        }
        end = System.currentTimeMillis();
        // KET QUA THU DUOC SAU KHI CHAY CHUONG TRINH LA:
        // Thời gian thực hiện với String: 2702 ms
        // Thời gian thực hiện với StringBuilder: 0 ms
        // Thời gian thực hiện với StringBuffer: 2 ms

        System.out.println("Thời gian thực hiện với StringBuffer: " + (end - start) + " ms");
        System.out.println("- Nhận xét: ");
        System.out.println("- String: Không hiệu quả cho phép nối chuỗi nhiều lần do tạo ra nhiều đối tượng mới.");
        System.out.println("- StringBuilder: Hiệu quả và nhanh chóng, thích hợp cho nhiều thao tác nối chuỗi trong một Luồng.");
        System.out.println("- StringBuffer: Tương tự StringBuilder nhưng có đồng bộ hóa, phù hợp cho môi trường đa luồng nhưng có thể chậm hơn một chút so với StringBuilder.");

    }

}