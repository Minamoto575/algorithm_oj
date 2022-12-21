import java.util.Arrays;
import java.util.Scanner;

/**
 * @author kuang
 * @description 平衡字符串 计数+滑动窗口
 * @date 2022/12/21  10:15
 */
public class BalanceString {
    private static final Scanner scanner = new Scanner(System.in);
    
    private static String str;
    private static int[] charCnt = new int[26];
    private static int[] searchCnt = new int[26];
    
    private static int len;
    
    private static void preProcess() {
        str = scanner.nextLine().replace(" ", "");
        scanner.close();
        len = str.length();
        Arrays.fill(charCnt, -len / 4);
        for (char c : str.toCharArray()) {
            charCnt[c - 'A']++;
        }
        
    }
    
    public static void main(String[] args) {
        preProcess();
        int ret = Integer.MAX_VALUE;
        if (canReplace()) {
            System.out.println(0);
            return;
        }
        int i = 0, j = 0;
        searchCnt[str.charAt(j) - 'A']++;
        while (j < len) {
            while (canReplace()) {
                ret = Math.min(ret, j - i + 1);
                searchCnt[str.charAt(i) - 'A']--;
                i++;
            }
            j++;
            if (j < len) {
                searchCnt[str.charAt(j) - 'A']++;
            }
            
        }
        System.out.println(ret);
    }
    
    public static boolean canReplace() {
        return charIsEnough('Q') && charIsEnough('W') && charIsEnough('E') && charIsEnough('R');
    }
    
    public static boolean charIsEnough(char c) {
        return searchCnt[c - 'A'] >= charCnt[c - 'A'];
    }
}
