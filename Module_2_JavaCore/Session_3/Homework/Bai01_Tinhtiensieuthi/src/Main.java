import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Chương trình tính hóa đơn siêu thị.");
        System.out.println("Nhập tên khách hàng: ");
        String cusName = sc.nextLine();
        System.out.println("Nhập tên sản phẩm: ");
        String proName = sc.nextLine();
        System.out.println("Nhập giá sản phẩm: ");
        double price = Double.parseDouble(sc.nextLine());
        System.out.println("Nhập số lượng: ");
        int quantity = Integer.parseInt(sc.nextLine());
        boolean isMember = false;
        System.out.println("Khách hàng có phải là thành viên không? (Y/N): ");
        String memberInput = sc.nextLine();
        if (memberInput.equalsIgnoreCase("Y")) {
            isMember = true;
        }
        double total;
        double discount = 0;
        if(isMember) {
             discount = price * 0.1 * quantity;
        }
            total = price * quantity;
        double vat = total * 0.08;
        System.out.println("Khách hàng: " + cusName);
        System.out.println("Sản phẩm: " + proName);
        System.out.println("Giá: " + price);
        System.out.println("Số lượng: " + quantity);
        System.out.println("Thành tiền: " + total);
        System.out.println("Giảm giá: " + discount);
        System.out.println("Tiền VAT: " + vat);
        System.out.println("Tổng tiền thanh toán: " + (total - discount + vat));



    }
}