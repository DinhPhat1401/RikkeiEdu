import java.util.Scanner;

import static java.util.Collections.swap;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập số phần tử của mảng:");
        int n;
        try {
            n = sc.nextInt();
            if (n <= 0) throw new Exception();
        } catch (Exception e) {
            System.out.println("Số phần tử phải là một số nguyên dương!");
            return;
        }
        int[] arr = new int[n];
        System.out.println("Nhập các phần tử của mảng:");
        for (int i = 0; i < n; i++) {
            System.out.print("Phần tử thứ " + (i + 1) + ":");
            try {
                arr[i] = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Phần tử phải là một số nguyên!");
                --i;
                sc.nextLine();
            }
        }
        arr = swap(arr);
        System.out.println("Mảng sau khi sắp xếp:");
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    public static int[] swap(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] < arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }

        }
        return arr;
    }
}
