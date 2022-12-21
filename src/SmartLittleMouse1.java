import java.util.Arrays;
import java.util.Scanner;

/**
 * @author kuang
 * @description 聪明的小老鼠（一） dp
 * @date 2022/12/21  9:08
 */
public class SmartLittleMouse1 {
    private static final Scanner scanner = new Scanner(System.in);
    private static int[] nums;
    
    private static void preProcess() {
        String str = scanner.nextLine();
        scanner.close();
        nums = Arrays.stream(str.split("[,，]")).mapToInt(Integer::parseInt).toArray();
    }
    
    public static void main(String[] args) {
        preProcess();
        int len = nums.length;
        if (len == 1) {
            System.out.println(nums[0]);
            return;
        }
        if (len == 2) {
            System.out.println(Math.max(nums[0], nums[1]));
            return;
        }
        int[] dp = new int[len];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < len; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        System.out.println(dp[len - 1]);
    }
}
