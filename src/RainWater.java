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
        leftMax[0] = 0;
        for (int i = 1; i < len; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], nums[i - 1]);
        }
        rightMax[len - 1] = 0;
        for (int i = len - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], nums[i + 1]);
        }
        int ret = 0;
        for (int i = 0; i < len; i++) {
            int height = Math.min(leftMax[i], rightMax[i]);
            ret += Math.max(height - nums[i], 0);
        }
        System.out.println(ret);
    }
}
