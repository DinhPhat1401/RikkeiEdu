//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.println("Chương trình kiểm tra loại tam giác");
        while(true){
            System.out.println("Nhập vào 3 cạnh của tam giác:");
            System.out.print("Cạnh a: ");
            double a = scanner.nextDouble();
            System.out.print("Cạnh b: ");
            double b = scanner.nextDouble();
            System.out.print("Cạnh c: ");
            double c = scanner.nextDouble();
            if (!(a + b > c && a + c > b && b + c > a)){
                System.out.println("Đây không phải là tam giác. Vui lòng nhập lại.");
                continue;
            }

                if(a == b && b == c){
                    System.out.println("Đây là tam giác đều.");
                } else if(a == b || a == c || b == c){
                    System.out.println("Đây là tam giác cân.");
                } else {
                    System.out.println("Đây là tam giác thường.");
                }
        }

    }
}
