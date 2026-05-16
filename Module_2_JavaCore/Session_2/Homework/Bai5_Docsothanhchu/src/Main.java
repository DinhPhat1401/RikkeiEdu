import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Chương trình đọc số 3 chữ số");
        while (true) {
            System.out.print("Nhập số có 3 chữ số: ");
            int number = sc.nextInt();
            if (number < 100 || number > 999) {
                System.out.println("Vui lòng nhập số có 3 chữ số!");
                continue;
            }
            int hundreds = number / 100;
            int tens = (number % 100) / 10;
            int units = number % 10;
            String result = "";
            switch (hundreds) {
                case 1:
                    result += "Một trăm ";
                    break;
                case 2:
                    result += "Hai trăm ";
                    break;
                case 3:
                    result += "Ba trăm ";
                    break;
                case 4:
                    result += "Bốn trăm ";
                    break;
                case 5:
                    result += "Năm trăm ";
                    break;
                case 6:
                    result += "Sáu trăm ";
                    break;
                case 7:
                    result += "Bảy trăm ";
                    break;
                case 8:
                    result += "Tám trăm ";
                    break;
                case 9:
                    result += "Chín trăm ";
                    break;
            }
            switch (tens) {
                case 0:
                    if (units != 0) {
                        result += "lẻ ";
                    }
                    break;
                case 1:
                    result += "mười ";
                    break;
                case 2:
                    result += "hai mươi ";
                    break;
                case 3:
                    result += "ba mươi ";
                    break;
                case 4:
                    result += "bốn mươi ";
                    break;
                case 5:
                    result += "năm mươi ";
                    break;
                case 6:
                    result += "sáu mươi ";
                    break;
                case 7:
                    result += "bảy mươi ";
                    break;
                case 8:
                    result += "tám mươi ";
                    break;
                case 9:
                    result += "chín mươi ";
                    break;
            }
            switch (units) {
                case 1:
                    if( tens !=0 && tens != 1) {
                        result += "mốt";
                    } else {
                        result += "một";
                    }
                    break;
                case 2:
                    result += "hai";
                    break;
                case 3:
                    result += "ba";
                    break;
                case 4:
                    if(tens > 1){
                        result += "tư";
                    } else {
                        result += "bốn";
                    }
                    break;
                case 5:
                    if(tens != 0){
                        result += "lăm";
                    } else {
                        result += "năm";
                    }
                    break;
                case 6:
                    result += "sáu";
                    break;
                case 7:
                    result += "bảy";
                    break;
                case 8:
                    result += "tám";
                    break;
                case 9:
                    result += "chín";
                    break;
        }
            System.out.println("Số " + number + " đọc là: " + result);
            break;
        }
        for (int i = 0; i < 10; i++) {
            System.out.println("Đây là vòng lặp thứ " + i);
        }
    }

}