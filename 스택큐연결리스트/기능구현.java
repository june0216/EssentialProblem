import java.util.*;

class 기능구현 {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};
        int N = progresses.length;
        Deque<Integer> finish = new ArrayDeque<>();
        
        for(int i = 0; i < N; i++){
            int rest = 100-progresses[i];
            if(rest%speeds[i] == 0){
                finish.offerLast(rest/speeds[i]);
            }else{
                finish.offerLast((rest/speeds[i])+1);
            }
        }
        
        
        List<Integer> result = new ArrayList<>();
        int time = 0;
        int idx = 0;
        int curIdx = 0;
        
       
        int count = 0;
        boolean flag = false;
        while(!finish.isEmpty()){
            time++;
            flag = false;
            
            while(!finish.isEmpty() && finish.peekFirst() <= time){
                finish.pollFirst();
                count++;
                flag = true;
               
            }
            
            if(flag){
                result.add(count);
                count = 0;
                
            }
            
         
    
           
            
        }
        
        answer = new int[result.size()];
        for(int i = 0; i < result.size() ; i++){
            answer[i] = result.get(i);
        }
        
        return answer;
    }
}
