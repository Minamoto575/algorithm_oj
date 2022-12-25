# 算法代码分析

匡瑞林 2022282110179

[代码仓库](https://github.com/Minamoto575/algorithm_oj)

## 1.聪明的小老鼠（一）

沿街有一排房屋，⼀个聪明的小老鼠，计划偷窃沿街的房屋里面的粮食，每间房内都藏有⼀定的粮食。相邻的房屋装有相互连通的防盗系统， 如果两间相邻的房屋在同⼀晚上被小老鼠闯⼊，系统会⾃动报警。 给定⼀个代表每个房屋存放数量的⾮负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最多粮食。

### 1.1思路分析

这是一个动态规划问题，如果只有一间房屋，那么就偷这一间，如果两间房屋，那么就偷两间的较大值，这也是dp的初始值。

设置dp[i]为考虑前i间房能偷取的最多粮食，对于第i间房屋，可以选择偷或者不偷。如果偷dp[i]=dp[i-2]+nums[i],如果不偷，dp[i]=dp[i-1]，最后dp[len-1]为结果。

状态转移方程为：

`dp[i]=max(dp[i-2]+nums[i],dp[i-1])`

初始条件：

`dp[0]=nums[0],dp[1]=max(nums[0],nums[1])`

### 1.2代码实现

```java
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author kuang
 * @description 聪明的小老鼠（一） dp
 * @date 2022/12/21  9:08
 */
public class SmartLittleMouse1 {
    private static final Scanner scanner = new Scanner(System.in);
    private static int[] nums;
    
    private static void preProcess() {
        String str = scanner.nextLine();
        scanner.close();
        nums = Arrays.stream(str.split("[,，]")).mapToInt(Integer::parseInt).toArray();
    }
    
    public static void main(String[] args) {
        preProcess();
        int len = nums.length;
        if (len == 1) {
            System.out.println(nums[0]);
            return;
        }
        if (len == 2) {
            System.out.println(Math.max(nums[0], nums[1]));
            return;
        }
        int[] dp = new int[len];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < len; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        System.out.println(dp[len - 1]);
    }
}

```

### 1.3复杂度分析

不考虑获取输入消耗的时空。

很显然时间复杂度为O(n)，空间复杂度为O(n)，n为房屋数目。

其中空间复杂度还可以优化到O(1)，对于dp[i]，只需记录前两个即可。

## 2.相反数

有 N 个非零且各不相同的整数。请你编一个程序求出它们中有多少对相反数(a 和 -a 为一对相反数)。

### 2.1思路分析

把N个数字进行排序，从小到大遍历，把小于0的数字加入集合set，对于大于0的数字num，判断-num是否在set中，在则计数+1。

### 2.2代码实现

```java
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
```

### 1.3复杂度分析

不考虑获取输入消耗的时空。

jdk底层使用快排，是O(NlogN)，Set的元素加入和元素判断的时间复杂度都是O(1)，一次遍历下来是O(N)，所以排序最耗时，总体时间复杂度是O(NlogN)。

使用了Set空间换时间，大小是数组中的负数的个数，空间复杂度是O(N)。

## 3.窗口

在某图形[操作系统](http://lib.csdn.net/base/operatingsystem)中,有 N 个窗口,每个窗口都是一个两边与坐标轴分别平行的矩形区域。窗口的边界上的点也属于该窗口。窗口之间有层次的区别,在多于一个窗口重叠的区域里,只会显示位于顶层的窗口里的内容。 
当你点击屏幕上一个点的时候,你就选择了处于被点击位置的最顶层窗口,并且这个窗口就会被移到所有窗口的最顶层,而剩余的窗口的层次顺序不变。如果你点击的位置不属于任何窗口,则系统会忽略你这次点击。 
现在我们希望你写一个程序模拟点击窗口的过程。

### 3.1思路分析

采用双端队列，首先将各个窗口从队首压入队列，此时是当做栈来使用。

然后从队首开始遍历，判断点击是否在当前窗口，不是则继续遍历，是则将这个窗口移到队首，终止遍历。一直没找到，就是IGNORED。

考虑到会有频繁的窗口移动删除，实现上使用LinkedList，删除添加的时间复杂度是O(1)。

### 3.2代码实现

```java
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
```

### 3.3复杂度分析

不考虑获取输入消耗的时空。

很明显空间复杂度是O(N)。

判断点击是否在窗口内部，窗口的删除和添加，都是O(1)，一次点击对应一次遍历，最好是O(1)，最坏是O(N)，一共有M次点击，空间复杂度是O(MN)。

## 4.雨水

从 x 轴开始，给出⼀个数组，数组⾥⾯的数字代表从 (0,0) 点开始，宽度为 1 个单位，⾼度为数组元素 的值。如果下⾬了，问这样⼀个容器能装多少单位的⽔？例如输入0,1,0,2,1,0,1,3,2,1,2,1下图中黑色代表高度，蓝色代表空白，可以装雨水，一共可以装6个单位的雨水。

### 4.1思路分析

木桶接水的上限取决于最短板，i处接水量取决于三个量，左边的最高点 leftMax，右边的最高点 rightMax，以及自己nums[i]。

i的短板就是`height=min(leftMax,rightMax)`

i的接水量就是`max(0,height-nums[i])`

nums已知，只需要知道leftMax、rightMax数组即可，两个数组都是单调的，状态转移方程如下：

`leftMax[i] = max(leftMax[i-1], nums[i-1])`

`rightMax[i] =max(rightMax[i+1], nums[i+1])`

初始条件:

`rightMax[len-1]=0`

`leftMax[0]=0`

### 4.2代码实现

```java
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author kuang
 * @description 雨水 dp
 * @date 2022/12/21  14:14
 */
public class RainWater {
    private static final Scanner scanner = new Scanner(System.in);
    private static int[] nums;
    
    private static void preProcess() {
        String str = scanner.nextLine();
        scanner.close();
        nums = Arrays.stream(str.split(",")).mapToInt(Integer::parseInt).toArray();
    }
    
    public static void main(String[] args) {
        preProcess();
        int len = nums.length;
        int[] leftMax = new int[len], rightMax = new int[len];
        leftMax[0] = 0;
        for (int i = 1; i < len; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], nums[i - 1]);
        }
        rightMax[len - 1] = 0;
        for (int i = len - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], nums[i + 1]);
        }
        int ret = 0;
        for (int i = 0; i < len; i++) {
            int height = Math.min(leftMax[i], rightMax[i]);
            ret += Math.max(height - nums[i], 0);
        }
        System.out.println(ret);
    }
}
```

### 4.3复杂度分析

不考虑获取输入消耗的时空。

用到了两个数组leftMax和rightMax，空间复杂度为O(n)。可以使用双指针优化到O(1)。

三次遍历，时间复杂度为O(n)。

## 5.快餐店仓库选址

在一个高速公路上（可以看成直线）给定n个餐馆位置，现要在公路上选择K个餐馆作为仓库，每一个餐馆到最近的仓库取货，请确定这K个仓库的位置，使得取货的总距离最小。

### 5.1思路分析

如果只有一个仓库，那么选址就是快餐店的中位数，如果餐馆个数是偶数，选取中间两个都可以，这里我们统一规定选取下标小的那个。

首先，我们定义任意两点之间的距离花费costs[i][j\]，即在i、j两点之间(包括i、j)选取一个仓库的最小距离花费，这个就是求中位数再遍历加法就行。

有多个仓库，就是划分成多段选取中位数，这是明显的dp问题，设置dp[i\][j\]表示i个仓库对前j个店的最小距离。当i>=j时，每家店都是仓库，dp=0；当i<j时，我们把新加的一个仓库依次尝试给后面几家店构成的片，状态转移方程：

`dp[i][j]=min(dp[i-1][m-1] + costs[m][j]) , i=<m<=j`

初始条件：

`dp[1][j]=costs[1][j]`

题目的要求出选址的值，这里我们保留dp数据来做倒序的遍历求解，根据状态转移方程，我们只要从dp[k-1\][n-1\]开始，找到下一层满足dp[k-2\][m-1\]+costs[m\][n-1]=dp[k-1\][n-1\]的m，那么（m+n-1）/2就是我们要找的快餐店的索引，再从dp[k-2\][m-1\]出发继续迭代即可。选址被压入栈，找到所有的店后再依次出栈输出，就是最终结果。

### 5.2代码实现

```java
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author kuang
 * @description 快餐店仓库选址 dp
 * @date 2022/12/9  15:08
 */
public class SelectWarehouseAddress {
    private static final Scanner scanner = new Scanner(System.in);
    private static int k;
    private static int[] nums;
    
    private static void preProcess() {
        String str = scanner.nextLine();
        nums = Arrays.stream(str.split(" ")).mapToInt(Integer::parseInt).toArray();
        k = scanner.nextInt();
        scanner.close();
    }
    
    public static void main(String[] args) {
        preProcess();
        Arrays.sort(nums);
        
        // 计算任意两点的距离花费
        int[][] costs = new int[nums.length + 1][nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int alias = nums[(i + j) / 2];
                int cost = 0;
                for (int k = i; k <= j; k++) {
                    cost += Math.abs(nums[k] - alias);
                }
                costs[i + 1][j + 1] = cost;
            }
        }
        
        // dp[i][j]  考虑i个仓库对前j个店的最小距离
        int[][] dp = new int[k + 1][nums.length + 1];
        for (int i = 1; i <= nums.length; i++) {
            dp[1][i] = costs[1][i];
        }
        
        for (int i = 2; i <= k; i++) {
            for (int j = i; j <= nums.length; j++) {
                dp[i][j] = dp[i - 1][j];
                // 求出中位数的餐馆地址
                for (int m = i; m <= j; m++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][m - 1] + costs[m][j]);
                }
            }
        }
        showResult(dp, costs);
    }
    
    /**
     * @param dp
     * @param costs
     * @description 倒序遍历求选址餐馆的地址
     * @author kuang
     * @date 2022/12/13
     */
    public static void showResult(int[][] dp, int[][] costs) {
        Deque<Integer> deque = new LinkedList<>();
        int m = dp.length - 1, n = dp[0].length - 1;
        int cur = dp[m][n];
        while (m > 0 && n > 0) {
            m--;
            int tempN = n--;
            while (dp[m][n] + costs[n + 1][tempN] != cur) {
                n--;
            }
            deque.push(nums[(n + 1 + tempN) / 2 - 1]);
            cur = dp[m][n];
        }
        while (!deque.isEmpty()) {
            System.out.print(deque.pop());
            if (!deque.isEmpty()) {
                System.out.print(" ");
            }
        }
    }
}

```

### 5.3复杂度分析

不考虑输入的时空复杂度。空间上，costs数组花费了O(n²)，dp数组花费了O(kn)，求结果压栈花费了O(k)，考虑到k大于n本题无意义，所以空间复杂度是O(n²)。

时间复杂度costs求解花费了O(n³)，dp求解花费了O(kn²)，结果求解花费了O(kn)，所以时间复杂度是O(n³)。

