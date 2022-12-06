import java.util.Arrays;
import java.util.Scanner;

/**
 * @author kuang
 * @description 排序+贪心
 * @date 2022/12/6  10:42
 */
public class Problem02 {
    private static final Scanner scanner = new Scanner(System.in);
    private static int[] nums;
    private static int n;
    
    private static void preProcess() {
        n = scanner.nextInt();
        scanner.nextLine();
        String str = scanner.nextLine();
        scanner.close();
        nums = Arrays.stream(str.split("[,，]")).mapToInt(Integer::parseInt).toArray();
    }
    
    public static void main(String[] args) {
        preProcess();
        Arrays.sort(nums);
        int spiltIndex = n / 2;
        int sum1 = 0, sum2 = 0;
        for (int i = 0; i < spiltIndex; i++) {
            sum1 += nums[i];
        }
        for (int i = spiltIndex; i < n; i++) {
            sum2 += nums[i];
        }
        System.out.println(n % 2 + "," + (sum2 - sum1));
    }
}
