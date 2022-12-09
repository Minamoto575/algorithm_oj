import java.util.Arrays;
import java.util.Scanner;

/**
 * @author kuang
 * @description 峰值数1 一次遍历
 * @date 2022/12/6  11:06
 */
public class Problem07 {
    private static final Scanner scanner = new Scanner(System.in);
    private static int[] nums;
    
    private static void preProcess() {
        String str = scanner.nextLine();
        scanner.close();
        nums = Arrays.stream(str.split("[,，]")).mapToInt(Integer::parseInt).toArray();
    }
    
    private static boolean isPeek(int i) {
        if (i == 0) {
            return nums[i] > nums[i + 1];
        }
        if (i == nums.length - 1) {
            return nums[i] > nums[i - 1];
        }
        return nums[i] > nums[i + 1] && nums[i] > nums[i - 1];
    }
    
    public static void main(String[] args) {
        preProcess();
        int ret = 0;
        for (int i = 0; i < nums.length; i++) {
            if (isPeek(i)) {
                ret++;
            }
        }
        System.out.println(ret);
    }
}
