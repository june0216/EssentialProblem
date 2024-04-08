import java.util.*;
public class 푸드파이트대회 {
    public String solution(int[] food) {
        String answer = "";
        StringBuilder sb = new StringBuilder();
        Deque<Integer> que = new ArrayDeque<>();
        Deque<Integer> stack = new ArrayDeque<>();
        for(int i =1 ; i < food.length; i++){
            int num = food[i] / 2;
            while(num-- > 0){
                que.offer(i);
                stack.add(i);
            }

        }
        while(!que.isEmpty()){
            sb.append(que.poll());
        }
        sb.append(0);
        while(!stack.isEmpty()){
            sb.append(stack.removeLast());
        }
        return sb.toString();
    }
}
