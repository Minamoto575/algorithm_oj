import java.util.Arrays;
import java.util.Scanner;

/**
 * @author kuang
 * @description 输油管道 排序
 * @date 2022/12/9  13:48
 */
public class Problem04 {
    private static final Scanner scanner = new Scanner(System.in);
    
    private static int[] nums;
    
    private static void preProcess() {
        String str = scanner.nextLine().replace(")(", " ");
        scanner.close();
        String[] strings = str.substring(1, str.length() - 1).split(" ");
        nums = new int[strings.length / 2];
        for (int i = 1; i < strings.length; i = i + 2) {
            nums[i / 2] = Integer.parseInt(strings[i]);
        }
    }
    
    public static void main(String[] args) {
        preProcess();
        Arrays.sort(nums);
        int len = nums.length;
        int ret = 0;
        for (int i = len - 1; i >= len / 2; i--) {
            ret += nums[i] - nums[len - i - 1];
        }
        System.out.println(ret);
    }
}
