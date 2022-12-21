import java.util.Arrays;
import java.util.Scanner;

/**
 * @author kuang
 * @description 最大容积 暴力
 * @date 2022/12/21  14:36
 */
public class MaxVolume {
    private static final Scanner scanner = new Scanner(System.in);
    private static int[] nums;
    
    
    private static void preProcess() {
        String numsStr = scanner.nextLine().replace(" ", "");
        scanner.close();
        nums = Arrays.stream(numsStr.split("[,，]")).mapToInt(Integer::parseInt).toArray();
    }
    
    public static void main(String[] args) {
        preProcess();
        int len = nums.length;
        int ret = Integer.MIN_VALUE;
        for (int i = 0; i < len - 1; i++) {
            for (int j = 1; j < len; j++) {
                int height = Math.min(nums[i], nums[j]);
                int length = j - i;
                ret = Math.max(ret, height * length);
            }
        }
        System.out.println(ret);
    }
}
