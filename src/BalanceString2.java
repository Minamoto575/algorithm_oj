import java.util.Scanner;

/**
 * @author kuang
 * @description 平衡字符串2 贪心遍历
 * @date 2022/12/21  9:55
 */
public class BalanceString2 {
    private static final Scanner scanner = new Scanner(System.in);
    private static String str;
    
    private static void preProcess() {
        str = scanner.nextLine().replace(" ", "");
        scanner.close();
    }
    
    public static void main(String[] args) {
        preProcess();
        int ret = 0;
        // 计数变量 正数表示L多 负数表示R多 为0则ret加1
        int count = 0;
        for (char c : str.toCharArray()) {
            if (c == 'L') {
                count++;
            } else {
                count--;
            }
            if (count == 0) {
                ret++;
            }
        }
        System.out.println(ret);
    }
}
