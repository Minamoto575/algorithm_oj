import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author kuang
 * @description 窗口 双端队列+mock
 * @date 2022/12/21  13:42
 */
public class WindowMock {
    private static final Scanner scanner = new Scanner(System.in);
    private static Deque<Window> deque = new LinkedList<>();
    private static int[] xNums, yNums;
    private static int M;
    
    private static void preProcess() {
        int N = scanner.nextInt();
        M = scanner.nextInt();
        xNums = new int[M];
        yNums = new int[M];
        for (int i = 1; i <= N; i++) {
            Window window = new Window(i, scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
            deque.push(window);
        }
        for (int i = 0; i < M; i++) {
            xNums[i] = scanner.nextInt();
            yNums[i] = scanner.nextInt();
        }
    }
    
    public static void main(String[] args) {
        preProcess();
        for (int i = 0; i < M; i++) {
            int x = xNums[i];
            int y = yNums[i];
            boolean flag = false;
            for (Object object : deque.toArray()) {
                Window window = (Window) object;
                if (window.inWindow(x, y)) {
                    System.out.println(window.getIndex());
                    deque.remove(object);
                    deque.push(window);
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                System.out.println("IGNORED");
            }
        }
    }
    
    public static class Window {
        private int index;
        private int x1, y1, x2, y2;
        
        public Window(int index, int x1, int y1, int x2, int y2) {
            this.index = index;
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
        
        public int getIndex() {
            return index;
        }
        
        public boolean inWindow(int x, int y) {
            return x >= x1 && x <= x2 && y >= y1 && y <= y2;
        }
    }
}


