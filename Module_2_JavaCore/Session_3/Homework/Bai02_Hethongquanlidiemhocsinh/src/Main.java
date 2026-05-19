import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("Hệ thống quản lý điểm học viên");
        Scanner sc = new Scanner(System.in);
        double min = 11 , max = 0, sum = 0;
        int count = 0;
        while(true){
            System.out.println("************* MENU NHẬP ĐIỂM *************");
            System.out.println("1. Nhập điểm học viên");
            System.out.println("2. Hiển thị thống kê");
            System.out.println("3. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            int choice;
            try{
                choice = sc.nextInt();
                if (choice<1 || choice>3) {
                    throw new Exception();
                }
            }catch (Exception e){
                System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập lại trong khoảng 1-3.");
                sc.nextLine();
                continue;

            }
            switch (choice) {
                case 1:
                    System.out.println("Nhập điểm học viên...");
                    System.out.println("Nhập số điểm từ 0-10, hoặc nhập -1 để dừng:");
                    while (true) {
                        double score;
                        try {
                            System.out.println("Nhập điểm học viên thứ " + (count + 1) + ": ");
                            score = sc.nextDouble();
                            if (score == -1) {
                                break;
                            }
                            if (score < 0 || score > 10) {
                                throw new Exception();
                            }
                        } catch (Exception e) {
                            System.out.println("Điểm không hợp lệ. Vui lòng nhập lại.");
                            sc.nextLine();
                            continue;

                        }
                        if(score <= 5) {
                            System.out.println("Học viên yếu");
                        } else if(score <= 7) {
                            System.out.println("Học viên trung bình");
                        } else if(score <= 8) {
                            System.out.println("Học viên khá");
                        } else if (score <= 9 ) {
                            System.out.println("Học viên giỏi");
                        } else {
                            System.out.println("Học viên xuất sắc");
                        }
                        sum += score;
                        count++;
                        min = Math.min(min, score);
                        max = Math.max(max, score);
                    }
                    break;
                case 2:
                    System.out.println("Hiển thị thống kê...");
                    if(count == 0) {
                        System.out.println("Chưa có điểm nào được nhập. Vui lòng nhập điểm trước khi xem thống kê.");
                        break;
                    }
                    System.out.println("Số học viên đã nhập: " + count);
                    System.out.println("Điểm trung bình của học viên: " + sum / count );
                    System.out.println("Điểm cao nhất: " + max);
                    System.out.println("Điểm thấp nhất: " + min);
                    break;
                case 3:
                    System.out.println("Bạn có chắc chắn muốn thoát không? (Y/N)");
                    String confirm = sc.next();
                    if (confirm.equalsIgnoreCase("Y")) {
                        System.out.println("Thoát chương trình. Tạm biệt!");
                        sc.close();
                        System.exit(0);
                    } else {
                        System.out.println("Hủy thoát. Quay lại menu.");
                    }
            }
        }
    }
}