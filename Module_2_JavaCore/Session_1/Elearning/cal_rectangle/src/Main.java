import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("------------------------------");
            System.out.println("Công cụ tính toán liên quan đến hình chữ nhật");
            System.out.println("1. Tính diện tích hình chữ nhật");
            System.out.println("2. Tính chu vi hình chữ nhật");
            System.out.println("3. Thoát");
            System.out.println("------------------------------");
            System.out.print("Lựa chọn của bạn: ");
            int choice = sc.nextInt();
            double length, width;
            do{
            System.out.print("Nhập chiều dài: ");
            length = sc.nextDouble();
            System.out.print("Nhập chiều rộng: ");
            width = sc.nextDouble();
            if(length <= 0 || width <= 0 || length<width){
                System.out.println("Chiều dài và chiều rộng phải là số dương và chiều dài phải lớn hơn chiều rộng. Vui lòng nhập lại.");
            } else {
                break;
            }
            } while (true);

            switch (choice){
                case 1:
                    double area = cal_Area(length, width);
                    System.out.println("Diện tích hình chữ nhật: " + area);
                    break;
                case 2:
                    double perimeter = cal_Perimeter(length, width);
                    System.out.println("Chu vi hình chữ nhật: " + perimeter);
                    break;
                case 3:
                    System.out.println("Thoát chương trình. Tạm biệt!");
                    sc.close();
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }


    }
    public static double cal_Area(double length, double width) {
        return length * width;
    }

    public static double cal_Perimeter(double length, double width) {
        return 2 * (length + width);
    }
}
