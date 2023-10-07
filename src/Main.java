import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {

    }
}

//optimized runtime editorial solution. O(n) time despite nested loop  since items can only
// be pushed/popped once
class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] answer = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int currDay = 0; currDay < n; currDay++) {
            int currentTemp = temperatures[currDay];
            // Pop until the current day's temperature is not
            // warmer than the temperature at the top of the stack
            while (!stack.isEmpty() && temperatures[stack.peek()] < currentTemp) {
                int prevDay = stack.pop();
                answer[prevDay] = currDay - prevDay;
            }
            stack.push(currDay);
        }
        // final indexes never assigned to 0. this is because when array initialized, all
        // values already default to 0, not 'null'*****

        return answer;
    }
}

// my solution
//class Solution {
//    public int[] dailyTemperatures(int[] temperatures) {
//        Stack<Integer> temp = new Stack<>();
//        int[] result = new int[temperatures.length];
//
//        for (int i = 0; i < temperatures.length; i++) {
//            if (temperatures[i] < temperatures[i - 1] || temp.isEmpty()) {
//                temp.push(i);
//            } else {
//                while (!temp.isEmpty() && temperatures[i] > temperatures[temp.peek()]) {
//                    int indx = temp.pop();
//                    result[indx] = i - indx;
//                }
//                temp.push(i);
//            }
//        }
//        while (!temp.isEmpty()) {
//            result[temp.pop()] = 0;
//        }
//        return result;
//    }
//}
// trouble when 75,71,69,72,76. 72>71. only push if element < element on stack at prior index
// if not then need to pop untl greater # found and then push current #

// [73,74,75,71,69,72,76,73]
// [1, 1, 4, 2, 1, 1, 0, 0]
// [0, 1, 2, 3, 4, 5, 6, 7]
//    [1, 1, -4, -2,3,4, -3] // by diff in val
//    [1, 2, -2, -4,-1,3,0] // total added difs

// L pointer to backfill for all answers wheen higheer found?
// 5,6,5,4,3,2,1,10 pointer is on index 0, then 6 found, backfill 0 anser, pointer on 6
// get to 10 and backfill indexes up to 10 thn 10. backfill behavioorr matchees a stack
// if val < curreent greatest, then push index and compute currnt index to that index annd
// assign the val in returrn array