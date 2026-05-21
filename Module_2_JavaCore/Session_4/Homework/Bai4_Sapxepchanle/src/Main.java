import java.util.Scanner;
import java.util.stream.IntStream;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int countEven = 0, countOdd = 0;
        System.out.print("Nhập số phần tử của mảng:");
        int n;
        try {
            n = sc.nextInt();
            if (n < 0) throw new Exception();
        } catch (Exception e) {
            System.out.println("Số phần tử phải là một số nguyên dương!");
            return;
        }
        if (n == 0) {
            System.out.println("Mảng rỗng!");
            return;
        }

        int[] arr = new int[n];
        System.out.println("Nhập các phần tử của mảng:");
        for (int i = 0; i < n; i++) {
            System.out.print("Phần tử thứ " + (i + 1) + ":");
            try {
                arr[i] = sc.nextInt();
                if (arr[i] % 2 == 0) {
                    countEven++;
                } else {
                    countOdd++;
                }

            } catch (Exception e) {
                System.out.println("Phần tử phải là một số nguyên!");
                --i;
                sc.nextLine();
            }
        }
        int[] sortedArr = IntStream.concat(IntStream.of(evenArr(arr, countEven)), IntStream.of(oddArr(arr, countOdd))).toArray();
        System.out.println("Mảng sau khi sắp xếp: ");
        for (int num : sortedArr) {
            System.out.print(num + " ");
        }

    }

    public static int[] evenArr(int[] arr, int countEven) {
        int[] evenArr = new int[countEven];
        int index = 0;
        for (int num : arr) {
            if (num % 2 == 0) {
                evenArr[index++] = num;
            }
        }
        return evenArr;

    }

    public static int[] oddArr(int[] arr, int countOdd) {
        int[] oddArr = new int[countOdd];
        int index = 0;
        for (int num : arr) {
            if (num % 2 != 0) {
                oddArr[index++] = num;
            }
        }
        return oddArr;
    }

//    public static int[] sortArray(int[] arr) {
//        for (int i = 0; i < arr.length; i++) {
//            for (int j = i + 1; j < arr.length; j++) {
//                if (arr[j] % 2 !=0) {
//                    int temp = arr[i];
//                    arr[i] = arr[j];
//                    arr[j] = temp;
//                }
//            }
//        }
//        return arr;
//    }
}