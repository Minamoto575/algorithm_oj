import java.util.Arrays;
import java.util.Scanner;

/**
 * @author kuang
 * @description 数组和下标 滑动窗口
 * @date 2022/12/21  14:24
 */
public class ArraySumSubscript {
    private static final Scanner scanner = new Scanner(System.in);
    private static int[] nums;
    private static int k, t;
    
    private static void preProcess() {
        String numsStr = scanner.nextLine().replace(" ", "");
        String ktStr = scanner.nextLine().replace(" ", "");
        scanner.close();
        nums = Arrays.stream(numsStr.split("[,，]")).mapToInt(Integer::parseInt).toArray();
        int[] kt = Arrays.stream(ktStr.split("[,，]")).mapToInt(Integer::parseInt).toArray();
        k = kt[0];
        t = kt[1];
    }
    
    public static void main(String[] args) {
        preProcess();
        int len = nums.length;
        int i = 0, j = 1;
        while (j < len) {
            while (j - i > k) {
                i++;
            }
            if (Math.abs(nums[j] - nums[i]) <= t) {
                System.out.println(1);
                return;
            }
            j++;
        }
        System.out.println(0);
    }
    
}
