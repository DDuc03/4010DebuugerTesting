import java.util.Scanner;
import java.util.InputMismatchException;

public class Testclass {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Input data");

        int n = sc.nextInt();

        try {

            if (n < 1 || n > 4) {
                throw new IllegalArgumentException("输入的数字必须在1到4之间！");
            }

            System.out.println("你输入的数字是: " + n);
        } catch (InputMismatchException e) {
            System.out.println("请输入一个有效的数字！");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }




}