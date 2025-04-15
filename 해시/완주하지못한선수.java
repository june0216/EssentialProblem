import java.util.*;
class 완주하지못한선수 {
    public String solution(String[] participant, String[] completion) {
        
        
        Map<String , Integer> map = new HashMap<>();
        
        List<String> com = new LinkedList<>();
        
        for(int i = 0; i < completion.length; i++){
            map.put(completion[i], map.getOrDefault(completion[i], 0)+1);
        }
        
        List<String> result = new ArrayList<>();
        for(int i = 0;i < participant.length; i++){
            if(!map.containsKey(participant[i])){ // 이 부분 때문에?
               return participant[i];
            }else{
                int num = map.get(participant[i]);
                if(num -1 <=0 ){
                    map.remove(participant[i]);
                }else{
                    map.put(participant[i], num-1);
                }
            }
        }
        

        return result.get(0);
    }
}
