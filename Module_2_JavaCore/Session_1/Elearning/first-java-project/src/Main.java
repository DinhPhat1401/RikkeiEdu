import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        for(int i=0;i<10;i++) {
            System.out.println("Number: " + i);

        }
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int number = scanner.nextInt();
//        Dùng để loại bỏ ký tự xuống dòng sau khi nhập số, nếu không sẽ bị bỏ qua khi nhập chuỗi tiếp theo
        scanner.nextLine();
        System.out.println("You entered: " + number);
        System.out.println("-----------------------------");
        System.out.println("Enter a string: ");
        String input = scanner.nextLine();
        System.out.println("You entered: " + input);

    }
}
