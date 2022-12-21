import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * @author kuang
 * @description 峰值数2 排序
 * @date 2022/12/6  11:01
 */
public class PeakNumber2 {
    private static final Scanner scanner = new Scanner(System.in);
    private static Point[] points;
    
    private static void preProcess() {
        String s = scanner.nextLine();
        s = s.replace("{", "").replace("}", "");
        int[] nums = Arrays.stream(s.split("[,，]")).mapToInt(Integer::parseInt).toArray();
        points = new Point[nums.length / 2];
        for (int i = 0; i < nums.length; i = i + 2) {
            Point point = new Point(nums[i], nums[i + 1]);
            points[i / 2] = point;
        }
    }
    
    private static boolean isPeek(int i) {
        if (i == 0) {
            return points[i].getY() > points[i + 1].getY();
        }
        if (i == points.length - 1) {
            return points[i].getY() > points[i - 1].getY();
        }
        return points[i].getY() > points[i + 1].getY() && points[i].getY() > points[i - 1].getY();
    }
    
    public static void main(String[] args) {
        preProcess();
        Arrays.sort(points, Comparator.comparingInt(Point::getX));
        int ret = 0;
        for (int i = 0; i < points.length; i++) {
            if (isPeek(i)) {
                ret++;
            }
        }
        System.out.println(ret);
    }
    
}

class Point {
    private int x, y;
    
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getY() {
        return y;
    }
    
    public int getX() {
        return x;
    }
}
