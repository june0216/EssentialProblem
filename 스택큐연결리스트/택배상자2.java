import java.util.*;

class 택배상자2 {
    public int solution(int[] order) {
        int answer = 0;
        Deque<Integer> que = new ArrayDeque<>();
        
        int next = 1;
       // que.offer(0);
        // 큐에 숫자가 아닌 index를 넣어야한다. 
        
        
        int[] newOrder = new int[order.length+1];
        for(int i =1; i < order.length+1; i++){
            newOrder[i] = order[i-1];
        }
        
        // 한 번 담고 그 다음에 빼면 된다. 
        for(int i = 1; i <= order.length; i++){
            
            que.offer(i);
            
            while(!que.isEmpty() && que.peekLast() == newOrder[next]){
              
                int num = que.pollLast();
                // System.out.println(num);
                
                next++;
                
            }
            
            
            
        }
        return next-1;
    }
}
