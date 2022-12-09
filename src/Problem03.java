import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author kuang
 * @description 油田数 并查集
 * @date 2022/12/9  11:58
 */
public class Problem03 {
    private static final Scanner scanner = new Scanner(System.in);
    private static boolean[][] arr;
    
    private static int m, n;
    ;
    
    private static void preProcess() {
        List<String> strings = new ArrayList<>();
        while (scanner.hasNextLine()) {
            strings.add(scanner.nextLine());
        }
        scanner.close();
        m = strings.size();
        n = strings.get(0).length();
        arr = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            String str = strings.get(i);
            char[] chars = str.toCharArray();
            for (int j = 0; j < n; j++) {
                arr[i][j] = chars[j] == '@';
            }
        }
    }
    
    public static void main(String[] args) {
        preProcess();
        UnionFind uf = new UnionFind(arr);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j]) {
                    if (i + 1 < m && arr[i + 1][j]) {
                        uf.union(i * n + j, (i + 1) * n + j);
                    }
                    if (j + 1 < n && arr[i][j + 1]) {
                        uf.union(i * n + j, i * n + j + 1);
                    }
                }
            }
        }
        System.out.println(uf.getSize());
    }
}


class UnionFind {
    private int size;
    private int[] nums;
    
    public UnionFind(boolean[][] arr) {
        size = 0;
        int m = arr.length;
        int n = arr[0].length;
        nums = new int[m * n];
        Arrays.fill(nums, -1);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j]) {
                    nums[i * n + j] = i * n + j;
                    size++;
                }
            }
        }
    }
    
    public void union(int a, int b) {
        int parentA = find(a);
        int parentB = find(b);
        if (parentB == parentA) {
            return;
        }
        nums[parentA] = parentB;
        size--;
    }
    
    public int find(int index) {
        if (nums[index] == index) {
            return index;
        }
        return find(nums[index]);
    }
    
    public int getSize() {
        return size;
    }
}
