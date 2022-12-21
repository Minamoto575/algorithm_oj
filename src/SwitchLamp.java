import java.util.Arrays;
import java.util.Scanner;

/**
 * @author kuang
 * @description 开关灯 模拟暴力
 * @date 2022/12/9  15:22
 */
public class SwitchLamp {
    private static final Scanner scanner = new Scanner(System.in);
    private static int lamp, people;
    
    private static void preProcess() {
        String str = scanner.next();
        scanner.close();
        String[] strs = str.split("[,，]");
        lamp = Integer.parseInt(strs[0]);
        people = Integer.parseInt(strs[1]);
        
    }
    
    public static void main(String[] args) {
        preProcess();
        int[] lamps = new int[lamp + 1];
        for (int i = 1; i <= people; i++) {
            for (int j = 1; j <= lamp; j++) {
                if (j % i == 0) {
                    lamps[j] = lamps[j] == 0 ? 1 : 0;
                }
            }
        }
        System.out.println(Arrays.stream(lamps).sum());
    }
}
