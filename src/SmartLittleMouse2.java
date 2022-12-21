import java.util.Arrays;
import java.util.Scanner;

/**
 * @author kuang
 * @description 聪明的小老鼠（二） 二次dp
 * @date 2022/12/21  9:08
 */
public class SmartLittleMouse2 {
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
        if (len == 3) {
            System.out.println(Math.max(Math.max(nums[0], nums[1]), nums[2]));
            return;
        }
        
        int[] dp1 = new int[len - 1];
        dp1[0] = nums[0];
        dp1[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < len - 1; i++) {
            dp1[i] = Math.max(dp1[i - 1], dp1[i - 2] + nums[i]);
        }
        
        int[] dp2 = new int[len - 1];
        dp2[0] = nums[1];
        dp2[1] = Math.max(nums[1], nums[2]);
        for (int i = 2; i < len - 1; i++) {
            dp2[i] = Math.max(dp2[i - 1], dp2[i - 2] + nums[i + 1]);
        }
        
        System.out.println(Math.max(dp1[len - 2], dp2[len - 2]));
    }
}
