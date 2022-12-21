import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author kuang
 * @description 相反数 排序+Set
 * @date 2022/12/21  11:40
 */
public class ReciprocalNumber {
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
        Arrays.sort(nums);
        int len = nums.length, i = 0;
        int ret = 0;
        Set<Integer> set = new HashSet<>();
        for (; nums[i] < 0; i++) {
            set.add(nums[i]);
        }
        for (; i < len; i++) {
            if (set.contains(-nums[i])) {
                ret++;
            }
        }
        System.out.println(ret);
    }
    
}
