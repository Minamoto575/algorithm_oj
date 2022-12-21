import java.util.Arrays;
import java.util.Scanner;

/**
 * @author kuang
 * @description 分裂数组（1） 暴力
 * @date 2022/12/5  11:19
 */
public class SplitArray1 {
    private static final Scanner scanner = new Scanner(System.in);
    private static int n;
    private static int[] nums;
    private static int sum;
    
    private static void preProcess() {
        n = scanner.nextInt();
        scanner.nextLine();
        String str = scanner.nextLine();
        scanner.close();
        nums = Arrays.stream(str.split("[,，]")).mapToInt(Integer::parseInt).toArray();
        sum = Arrays.stream(nums).sum();
    }
    
    private static boolean dfs(int step, int left, int val, int target) {
        if (left == 0) {
            return val == target;
        }
        if (step == nums.length) {
            return false;
        }
        return dfs(step + 1, left - 1, val + nums[step], target) || dfs(step + 1, left, val, target);
    }
    
    public static void main(String[] args) {
        preProcess();
        if (sum % 2 == 1) {
            System.out.println("false");
            return;
        }
        System.out.println(dfs(0, nums.length / 2, 0, sum / 2));
    }
}
