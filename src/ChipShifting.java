import java.util.Arrays;
import java.util.Scanner;

/**
 * @author kuang
 * @description 筹码移位 奇偶数判断
 * @date 2022/12/21  9:41
 */
public class ChipShifting {
    private static final Scanner scanner = new Scanner(System.in);
    private static int[] nums;
    
    private static void preProcess() {
        String str = scanner.nextLine();
        str = str.replace(" ", "");
        scanner.close();
        nums = Arrays.stream(str.split("[,，]")).mapToInt(Integer::parseInt).toArray();
    }
    
    public static void main(String[] args) {
        preProcess();
        int odd = 0, even = 0;
        for (int num : nums) {
            if (num % 2 == 0) {
                even++;
            } else {
                odd++;
            }
        }
        System.out.println(Math.min(odd, even));
    }
}
