import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập số phần tử của mảng: ");
        int n;
        try {
            n = sc.nextInt();
            if (n <= 0) throw new Exception();
        } catch (Exception e) {
            System.out.println("Số phần tử phải là một số nguyên dương!");
            return;
        }
        int[] arr = new int[n];
        System.out.println("Nhập các phần tử của mảng: ");
        for (int i = 0; i < n; i++) {
            System.out.print("Phần tử thứ " + (i + 1) + ": ");
            try {
                arr[i] = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Phần tử phải là một số nguyên!");
                --i;
                sc.nextLine();
            }
        }
        int[] sortedArr = selectionSort(arr);
        System.out.println("Mảng sau khi sắp xếp giảm dần: ");
        for (int num : sortedArr) {
            System.out.print(num + " ");
        }
        System.out.print("\nNhập số cần tìm: ");
        int target;
        while (true) {
            try {
                target = sc.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Số cần tìm phải là một số nguyên!");
            }
        }
        if(linearSearch(arr, target) == -1){
            System.out.println("Số cần tìm không tồn tại trong mảng!");
        } else {
            System.out.println("Tìm kiếm tuyến tính: " + "Số " + target + "có ở vị trí " + linearSearch(arr, target));
            System.out.println("Tìm kiếm nhị phân: " + "Số " + target + "có ở vị trí " +  binarySearch(sortedArr, target));
        }

    }
    public static int[] selectionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int max = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] > arr[max]) {
                    max = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[max];
            arr[max] = temp;
        }
        return arr;
    }
    public static int binarySearch(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }
    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }
}