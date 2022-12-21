import java.util.Arrays;
import java.util.Scanner;

/**
 * @author kuang
 * @description H指数 排序
 * @date 2022/12/21  12:02
 */
public class HIndex {
    private static final Scanner scanner = new Scanner(System.in);
    private static int[] nums;
    
    private static void preProcess() {
        String str = scanner.nextLine();
        scanner.close();
        nums = Arrays.stream(str.split(",")).mapToInt(Integer::parseInt).toArray();
        System.out.println(str);
    }
    
    public static void main(String[] args) {
        preProcess();
        Arrays.sort(nums);
        int len = nums.length;
        int i = len - 1;
        while (nums[i] >= len - i) {
            i--;
        }
        System.out.println(len - i - 1);
    }
}
