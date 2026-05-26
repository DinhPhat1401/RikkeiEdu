import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = 0  , countPass = 0, countFail = 0, countSVG = 0;
        double min = 11, max= 0, sum = 0;
        double[] score = null;
        while (true){
            System.out.println("******* Quản Lý Điểm Sinh Viên *******");
            System.out.println("1. Nhập danh sách sinh viên");
            System.out.println("2. In danh sách sinh viên");
            System.out.println("3. Tính điểm trung bình của các sinh viên");
            System.out.println("4. Tìm điểm cao nhất và thấp nhất");
            System.out.println("5. Đếm số lượng sinh viên đạt và không đạt");
            System.out.println("6. Sắp xếp điểm tăng dần");
            System.out.println("7. Đếm số lượng sinh viên đạt loại xuất sắc");
            System.out.println("8. Thoát");
            System.out.print("Vui lòng chọn chức năng: ");

            int choice = Integer.parseInt(sc.nextLine());
            switch (choice){
                case 1:
                    while(true){
                        try{
                            System.out.print("Nhập vào số lượng học viên: ");
                            N = Integer.parseInt(sc.nextLine());
                            if(N<1){
                                System.out.println("Số lượng không hợp lệ, vui lòng nhập lại");
                                continue;
                            } else {
                                break;
                            }
                        } catch (Exception e){
                            System.out.println("Vui lòng nhập lại số lượng hợp lệ");
                        }
                    }
                    score =  new double [N];
                    for (int i = 0; i < N; i++) {
                        try{
                            System.out.print("Vui lòng nhập điểm cho học viên thứ " + (i+1) + ": ");
                            score[i] = Double.parseDouble(sc.nextLine());
                            if(score[i]<0 || score[i]>10) {
                                System.out.println("Điểm bạn nhập không hợp lệ, vui lòng nhập lại");
                                i--;
                            } else{
                                min = Math.min(min, score[i]);
                                max = Math.max(max, score[i]);
                                sum+= score[i];
                                if(score[i]<5){
                                    countFail++;
                                } else {
                                    countPass++;
                                    if (score[i]>= 8){
                                        countSVG++;
                                    }
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("Điểm bạn nhập không hợp lệ, vui lòng nhập lại");

                            i--;
                        }
                    }
                    break;
                case 2:
                    if(N<=0){
                        System.out.println("Vui lòng nhập danh sách sinh viên trước");
                        break;
                    }
                    System.out.println("Danh sách điểm của sinh viên: ");
                    for (int i = 0; i < N; i++) {
                        System.out.println("Sinh viên " + (i+1) + ": " + score[i]);
                    }
                    break;
                case 3:
                    if(N>0){
                        System.out.println("Điểm trung bình của các sinh viên là: " + (sum/N));
                    } else {
                        System.out.println("Vui lòng nhập danh sách sinh viên trước");
                    }
                    break;
                case 4:
                    if(N>0){
                        System.out.println("Điểm cao nhất là: " + max);
                        System.out.println("Điểm thấp nhất là: " + min);
                    } else {
                        System.out.println("Vui lòng nhập danh sách sinh viên trước");
                    }
                    break;
                case 5:
                    if(N>0){
                        System.out.println("Số lượng sinh viên đạt: " + countPass);
                        System.out.println("Số lượng sinh viên không đạt: " + countFail);
                    } else {
                        System.out.println("Vui lòng nhập danh sách sinh viên trước");
                    }
                    break;
                case 6:
                    if(N>0){
                        for (int i = 0; i < N-1; i++) {
                            for (int j = 0; j < N-i-1; j++) {
                                if(score[j]> score[j+1]){
                                    double temp = score[j];
                                    score[j] = score[j+1];
                                    score[j+1] = temp;
                                }
                            }
                        }
                        System.out.println("Danh sách điểm sau khi sắp xếp: ");
                        for (int i = 0; i < N; i++) {
                            System.out.println("Sinh viên " + (i+1) + ": " + score[i]);
                        }
                    } else {
                        System.out.println("Vui lòng nhập danh sách sinh viên trước");
                    }
                    break;
                case 7:
                    if(N>0){
                        System.out.println("Số lượng sinh viên đạt loại xuất sắc: " + countSVG);
                    } else {
                        System.out.println("Vui lòng nhập danh sách sinh viên trước");
                    }
                    break;
                case 8:
                    System.out.println("Bạn thật sự muốn thoát chương trình? (Y/N)");
                    String confirm = sc.nextLine();
                    if(confirm.equalsIgnoreCase("Y")){
                        System.out.println("Cảm ơn bạn đã sử dụng chương trình!");
                        System.exit(0);
                    } else {
                        System.out.println("Quay lại menu chính");
                    }
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng chọn lại");
            }
        }
    }
}

