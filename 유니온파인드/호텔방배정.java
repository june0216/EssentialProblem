import java.util.*;

/*
처음에는 이분탐색인줄알았다. -> 하지만 매번 가장 작은 방을 찾는 건 시간 복잡도 측면에서 걸릴 것 같아서 알고리즘을 떠올려야 했다. 
*/
class 호텔방배정 {
    Map<Long, Long> map;
    public long[] solution(long k, long[] room_number) {
        long[] answer = {};
        map = new HashMap<>();
        
        
        
        
        answer = new long[room_number.length];
        
        for(int idx = 0; idx < room_number.length; idx++){
            answer[idx] = find(room_number[idx]); 
            
        }
        return answer;
    }
    
    // 재귀적으로 자신의 다음을 찾는다. 
    public long find(long num){
        
        // 비어 있으면 
        if(!map.containsKey(num)){
            // num은 가득차있고 나 다음에 num+1라는 걸 의미
            map.put(num, num+1);
            return num;
            
        }
        
        // 비어 있지 않으면 
        long next = map.get(num); // 다음 방의 번호를 호출 
        long nextEmpty = find(next); // 빈 방의 번호까지 찾기 
        map.put( num, nextEmpty);
        return nextEmpty; // 자기가 넣은 방의 번호 
    }
    
    

}
