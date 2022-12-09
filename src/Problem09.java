import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @author kuang
 * @description 最大整数 贪心+大根堆
 * @date 2022/12/5  14:22
 */
public class Problem09 {
    private static final Scanner scanner = new Scanner(System.in);
    private static PriorityQueue<String> pq = new PriorityQueue<>(Problem09::compare);
    
    private static void preProcess() {
        String str = scanner.nextLine();
        String[] nums = str.split("[,，]");
        scanner.close();
        pq.addAll(Arrays.asList(nums));
    }
    
    private static int compare(String a, String b) {
        int len1 = a.length();
        int len2 = b.length();
        if (len1 > len2) {
            return -compare(b, a);
        }
        char[] aa = a.toCharArray();
        char[] bb = b.toCharArray();
        int k = 0;
        while (k < len1) {
            char c1 = aa[k];
            char c2 = bb[k];
            if (c1 != c2) {
                return c2 - c1;
            }
            k++;
        }
        return compare(a, b.substring(len1));
    }
    
    public static void main(String[] args) {
        preProcess();
        StringBuffer sb = new StringBuffer();
        while (!pq.isEmpty()) {
            String str = pq.poll();
            sb.append(str);
        }
        System.out.println(sb);
    }
}
