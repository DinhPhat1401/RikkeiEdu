import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Chương trình tính hóa đơn siêu thị.");
        System.out.print("Nhập tên khách hàng: ");
        String cusName = sc.nextLine();
        System.out.print("Nhập tên sản phẩm: ");
        String proName = sc.nextLine();
        System.out.print("Nhập giá sản phẩm: ");
        double price = Double.parseDouble(sc.nextLine());
        System.out.print("Nhập số lượng: ");
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
        DecimalFormat df = new DecimalFormat("#,###");
        System.out.println("Khách hàng: " + cusName);
        System.out.println("Sản phẩm: " + proName);
        System.out.println("Giá: " + df.format(price).replace(",",".")  + " VNĐ");
        System.out.println("Số lượng: " + quantity);
        System.out.println("Thành tiền: " + df.format(total).replace(",",".") + " VNĐ");
        System.out.println("Giảm giá: " + df.format(discount).replace(",",".") );
        System.out.println("Tiền VAT: " + df.format(vat).replace(",","."));
        System.out.println("Tổng tiền thanh toán: " + df.format((total - discount + vat)).replace(",",".") + " VNĐ");
    }
}