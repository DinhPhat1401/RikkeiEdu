import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n, m, sumc = 0, suml = 0;
        while (true){
            System.out.println("Nhập vào số hàng của ma trận: ");

            try{
                n = Integer.parseInt(sc.nextLine());
                if (n <= 0){
                    System.out.println("Vui lòng nhập vào một số nguyên dương.");
                } else break;
            }catch (NumberFormatException e){
                System.out.println("Vui lòng nhập vào một số nguyên hợp lệ.");
            }

        }
        while (true){
            System.out.println("Nhập vào số cột của ma trận: ");
            try{
                m = Integer.parseInt(sc.nextLine());
                if (m <= 0){
                    System.out.println("Vui lòng nhập vào một số nguyên dương.");
                } else break;
            }catch (NumberFormatException e){
                System.out.println("Vui lòng nhập vào một số nguyên hợp lệ.");
            }

        }
        int[][] matrix = new int[n][m];
        System.out.println("Nhập vào các phần tử của ma trận: ");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                while (true){
                    System.out.printf("Phần tử [%d][%d]: ", i, j);
                    try{
                        matrix[i][j] = Integer.parseInt(sc.nextLine());
                        if (matrix[i][j] % 2 ==  0) sumc += matrix[i][j];
                        else suml += matrix[i][j];
                        break;
                    }catch (NumberFormatException e){
                        System.out.println("Vui lòng nhập vào một số nguyên hợp lệ.");
                    }
                }

            }
        }
        System.out.println("Tổng các số chẵn: " + sumc);
        System.out.println("Tổng các số lẻ: " + suml);
    }
}