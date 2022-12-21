import java.util.Arrays;
import java.util.Scanner;

/**
 * @author kuang
 * @description 最短子数组和 前缀和+滑动窗口
 * @date 2022/12/21  9:21
 */
public class ShortestSubArraySum {
    private static final Scanner scanner = new Scanner(System.in);
    
    private static int[] prefixes;
    private static int s;
    
    private static void preProcess() {
        String str = scanner.nextLine();
        s = scanner.nextInt();
        scanner.close();
        int[] nums = Arrays.stream(str.split("[,，]")).mapToInt(Integer::parseInt).toArray();
        prefixes = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            prefixes[i + 1] = prefixes[i] + nums[i];
        }
    }
    
    public static void main(String[] args) {
        preProcess();
        int len = prefixes.length;
        int ret = Integer.MAX_VALUE;
        if (prefixes[len - 1] < s) {
            System.out.println(0);
            return;
        }
        int i = 0, j = 0;
        while (j < len) {
            while (prefixes[j] - prefixes[i] >= s) {
                ret = Math.min(ret, j - i);
                i++;
            }
            j++;
        }
        System.out.println(ret);
    }
}

