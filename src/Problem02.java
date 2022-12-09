import java.util.*;

/**
 * @author kuang
 * @description 对称轴 模拟
 * @date 2022/12/9  11:10
 */
public class Problem02 {
    private static final Scanner scanner = new Scanner(System.in);
    private static Map<Integer, Set<Integer>> map = new HashMap<>();
    
    private static Double middle;
    
    private static void preProcess() {
        String str = scanner.nextLine();
        scanner.close();
        str = str.replace(")(", ",");
        String[] nums = str.substring(1, str.length() - 1).split(",");
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i = i + 2) {
            Integer x = Integer.parseInt(nums[i]), y = Integer.parseInt(nums[i + 1]);
            max = Math.max(max, x);
            min = Math.min(min, x);
            Set<Integer> set = map.getOrDefault(x, new HashSet<>());
            set.add(y);
            map.put(x, set);
        }
        middle = (((double) max + (double) min) / 2);
    }
    
    public static void main(String[] args) {
        preProcess();
        for (Integer x : map.keySet()) {
            Set<Integer> set = map.get(x);
            Integer xx = (int) (2 * middle - x);
            for (Integer y : set) {
                Set<Integer> s = map.get(xx);
                if (s == null || !s.contains(y)) {
                    System.out.println(0);
                    return;
                }
            }
        }
        System.out.println(1);
    }
    
}
