import java.util.Arrays;
import java.util.Scanner;

/**
 * @author kuang
 * @description 分裂数组（2） 暴力
 * @date 2022/12/6  10:55
 */
public class Problem10 {
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
    
    private static boolean dfs(int step, int val, int target) {
        if (step == nums.length) {
            return val == target;
        }
        return dfs(step + 1, val + nums[step], target) || dfs(step + 1, val, target);
    }
    
    public static void main(String[] args) {
        preProcess();
        if (sum % 2 == 1) {
            System.out.println("false");
            return;
        }
        System.out.println(dfs(0, 0, sum / 2));
    }
}
