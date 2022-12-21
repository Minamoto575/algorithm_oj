import java.util.Arrays;
import java.util.Scanner;

/**
 * @author kuang
 * @description 相邻数对 排序
 * @date 2022/12/21  11:34
 */
public class NeighborNumberPair {
    private static final Scanner scanner = new Scanner(System.in);
    private static int[] nums;
    
    private static void preProcess() {
        scanner.nextLine();
        String str = scanner.nextLine();
        scanner.close();
        nums = Arrays.stream(str.split(" ")).mapToInt(Integer::parseInt).toArray();
    }
    
    public static void main(String[] args) {
        preProcess();
        int ret = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] + 1 == nums[i + 1]) {
                ret++;
            }
        }
        System.out.println(ret);
    }
}
