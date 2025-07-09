import java.util.*;
class 두큐합같게하기 {
    public int solution(int[] queue1, int[] queue2) {
        int answer = -2;
        
        // 하노이의 탑 같은 문제 
        // 작은 문제 : 1에서 빼기, 2에 넣기 
        Deque<Long> que1 = new ArrayDeque<>();
        Deque<Long> que2 = new ArrayDeque<>();
        
        long que1Sum = 0;
        long que2Sum = 0;
        
        long sum = 0;
        for(int i = 0; i < queue1.length; i++){
            que1.offer((long) queue1[i]);
            que2.offer((long) queue2[i]);
            que1Sum += queue1[i];
            que2Sum += queue2[i];
            sum+= (queue1[i] + queue2[i]);
         
        }
        
        if(sum%2 != 0){
            return -1;
        }
        
        int cnt = 0;
        
        while(true){
            if(cnt>= queue1.length*2*2) {
                cnt = -1;
                //System.out.println("dd");
                break;
            }
            if(sum/2 == que1Sum) break;
            if(que1Sum > sum/2){
                long num = que1.pollFirst();
                que2.offerLast(num);
                que1Sum -= num;
            }else{
                long num2 = que2.pollFirst();
                que1.offerLast(num2);
                que1Sum += num2;
                
                
            }
            
            cnt++;
        }
        
        System.out.println(cnt);
        
        
        
        
        
        
        return cnt;
    }
}
