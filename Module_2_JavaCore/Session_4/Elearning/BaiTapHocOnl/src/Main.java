import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n;
        System.out.print("Nhập kích thước ma trận vuông: ");
        while (true) {
            try {
                n = scanner.nextInt();
                if (n <= 0) {
                    System.out.println("Vui lòng nhập với N lớn hơn 0");
                    continue;
                }

            } catch (Exception e) {
                System.out.println("Vui lòng nhập với N là một số nguyên dương");
                scanner.nextLine();
                continue;
            }
            int count = 0;
            int[][] matrix = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = ++count;
                }
            }
            while (true) {
                System.out.println("Thao tác với ma trận vuông");
                System.out.println("1. In ma trận vuông");
                System.out.println("2. In đường chéo chính của ma trận vuông");
                System.out.println("3. In đường biên của ma trận vuông");
                System.out.println("4. Thoát");

                int choice;
                try {
                    choice = scanner.nextInt();
                    if (choice < 1 || choice > 4) {
                        System.out.println("Vui lòng lựa chọn từ 1 - 4");
                        continue;
                    }
                } catch (Exception e) {
                    System.out.println("Vui lòng lựa chọn từ 1 - 4");
                    scanner.nextLine();
                    continue;
                }
                switch (choice) {
                    case 1:
                        System.out.println("Ma trận vuông:");
                        for (int i = 0; i < n; i++) {
                            for (int j = 0; j < n; j++) {
                                System.out.printf("%4d", matrix[i][j]);
                            }
                            System.out.println();
                        }
                        break;
                    case 2:
                        System.out.println("Đường chéo chính của ma trận vuông:");
                        for (int i = 0; i < n; i++) {
                            for (int j = 0; j < n; j++) {
                                if (i == j )  System.out.printf("%4d", matrix[i][j]); else System.out.print("    ");
                            }
                            System.out.println();
                        }
                        break;
                    case 3:
                        System.out.println("Đường biên của ma trận vuông:");
                        for (int i = 0; i < n; i++) {
                            for (int j = 0; j < n; j++) {
                                if (i == 0 || i == n - 1 || j == 0 || j == n - 1) {
                                    System.out.printf("%4d", matrix[i][j]);
                                } else {
                                    System.out.print("    ");
                                }
                            }
                            System.out.println();
                        }
                        break;
                    case 4:
                        System.out.println("Thoát chương trình? (Y/N)");
                        String confirm = scanner.next();
                        if (confirm.equalsIgnoreCase("Y")) {
                            System.out.println("Cảm ơn bạn đã sử dụng chương trình!");
                            scanner.close();
                            System.exit(0);
                        } else {
                            System.out.println("Tiếp tục sử dụng chương trình.");
                        }
                }
            }
        }
    }
}