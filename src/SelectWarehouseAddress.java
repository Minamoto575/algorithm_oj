import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author kuang
 * @description 快餐店仓库选址 dp
 * @date 2022/12/9  15:08
 */
public class SelectWarehouseAddress {
    private static final Scanner scanner = new Scanner(System.in);
    private static int k;
    private static int[] nums;
    
    private static void preProcess() {
        String str = scanner.nextLine();
        nums = Arrays.stream(str.split(" ")).mapToInt(Integer::parseInt).toArray();
        k = scanner.nextInt();
        scanner.close();
    }
    
    public static void main(String[] args) {
        preProcess();
        Arrays.sort(nums);
        
        // 计算任意两点的距离花费
        int[][] costs = new int[nums.length + 1][nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int alias = nums[(i + j) / 2];
                int cost = 0;
                for (int k = i; k <= j; k++) {
                    cost += Math.abs(nums[k] - alias);
                }
                costs[i + 1][j + 1] = cost;
            }
        }
        
        
        // dp[i][j]  考虑i个仓库对前j个店的最小距离
        int[][] dp = new int[k + 1][nums.length + 1];
        for (int i = 1; i <= nums.length; i++) {
            dp[1][i] = costs[1][i];
        }
        
        for (int i = 2; i <= k; i++) {
            for (int j = i; j <= nums.length; j++) {
                dp[i][j] = dp[i - 1][j];
                // 求出中位数的餐馆地址
                for (int m = i; m <= j; m++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][m - 1] + costs[m][j]);
                }
            }
        }
        showResult(dp, costs);
    }
    
    /**
     * @param dp
     * @param costs
     * @description 倒序遍历求选址餐馆的地址
     * @author kuang
     * @date 2022/12/13
     */
    public static void showResult(int[][] dp, int[][] costs) {
        Deque<Integer> deque = new LinkedList<>();
        int m = dp.length - 1, n = dp[0].length - 1;
        int cur = dp[m][n];
        while (m > 0 && n > 0) {
            m--;
            int tempN = n--;
            while (dp[m][n] + costs[n + 1][tempN] != cur) {
                n--;
            }
            deque.push(nums[(n + 1 + tempN) / 2 - 1]);
            cur = dp[m][n];
        }
        while (!deque.isEmpty()) {
            System.out.print(deque.pop());
            if (!deque.isEmpty()) {
                System.out.print(" ");
            }
        }
    }
}
