import java.util.Arrays;
import java.util.Scanner;

/**
 * @author kuang
 * @description 雨水 贪心
 * @date 2022/12/21  14:14
 */
public class RainWater {
    private static final Scanner scanner = new Scanner(System.in);
    
    private static int[] nums;
    
    private static void preProcess() {
        String str = scanner.nextLine();
        scanner.close();
        nums = Arrays.stream(str.split(",")).mapToInt(Integer::parseInt).toArray();
    }
    
    public static void main(String[] args) {
        preProcess();
        int len = nums.length;
        int[] leftMax = new int[len], rightMax = new int[len];
        int max = 0;
        for (int i = 0; i < len; i++) {
            leftMax[i] = max;
            max = Math.max(max, nums[i]);
        }
        max = 0;
        for (int i = len - 1; i >= 0; i--) {
            rightMax[i] = max;
            max = Math.max(max, nums[i]);
        }
        int ret = 0;
        for (int i = 0; i < len; i++) {
            int height = Math.min(leftMax[i], rightMax[i]);
            ret += Math.max(height - nums[i], 0);
        }
        System.out.println(ret);
    }
}
