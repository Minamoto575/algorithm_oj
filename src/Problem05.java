import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author kuang
 * @description 最大子矩阵和 前缀和
 * @date 2022/12/9  13:59
 */
public class Problem05 {
    private static final Scanner scanner = new Scanner(System.in);
    
    private static int[][] prefixes;
    private static int size;
    
    private static void preProcess() {
        List<Integer> list = new ArrayList<>();
        while (scanner.hasNext()) {
            list.add(scanner.nextInt());
        }
        scanner.close();
        size = (int) Math.sqrt(list.size());
        prefixes = new int[size + 1][size + 1];
        for (int i = 0; i < list.size(); i++) {
            prefixes[i / size + 1][i % size + 1] = prefixes[i / size + 1][i % size] + list.get(i);
        }
    }
    
    public static void main(String[] args) {
        preProcess();
        int ret = Integer.MIN_VALUE;
        // 先固定上下边界
        for (int i = 0; i <= size; i++) {
            int[] tempPrefix = new int[size + 1];
            for (int j = i + 1; j <= size; j++) {
                for (int q = 0; q <= size; q++) {
                    tempPrefix[q] += prefixes[j][q];
                }
                // 再固定左右边界
                for (int k = 0; k <= size; k++) {
                    for (int m = k + 1; m <= size; m++) {
                        ret = Math.max(ret, tempPrefix[m] - tempPrefix[k]);
                    }
                }
            }
        }
        System.out.println(ret);
    }
}
