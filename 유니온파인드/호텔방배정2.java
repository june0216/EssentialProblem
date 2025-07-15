import java.util.*;
import java.io.*;
class 호텔방배정2 {
    
    Map<Long, Long> map;
    List<Long> result;
    public long[] solution(long k, long[] room_number) {
        long[] answer = {};
        result = new ArrayList<>();
        // 유니온파인드
        
        map = new HashMap<>();
        for(int i = 0; i < room_number.length ;i++){
            // 이미 배정되어 있다면
            find(room_number[i] );
        }
        
        int idx = 0;
        
        answer = new long[room_number.length];
        for(long n : result){
            answer[idx] = n;
            idx++;
        }
        
        
        return answer;
    }
    
    public long find(long num){
        
        if(map.containsKey(num)){ // 이미 방이 차 있으면 
            
            long next = map.get(num);
        long nextRoom = find(next);
        map.put(num, nextRoom+1);
        return nextRoom;
            
                
            
        }else{ 
            // 빈방이라면 바로 넣기 
            map.put(num, num+1);
            result.add(num);
            return num;
        }
        
        
        
        
        
    }
    
    
}
