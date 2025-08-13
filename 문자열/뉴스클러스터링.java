import java.util.*;
class 뉴스클러스터링 {
    /*
    합집합 -> Map에서 2개이면 출력하고 다시 되돌리기
    교집합 -> Map에서 2개 이상의 Set
    */
    public int solution(String str1, String str2) {
        int answer = 0;
        Map<String, Integer> map1 = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();
        List<String> multiSet1 = makeMultiSet(str1);
        List<String> multiSet2 = makeMultiSet(str2);
        
        for(String el : multiSet1){
            map1.put(el, map1.getOrDefault(el, 0) + 1);
        }
        
        for(String el : multiSet2){
            map2.put(el, map2.getOrDefault(el, 0) + 1);
        }
        
        
        Set<String> keySet = new HashSet<>(map1.keySet());
        keySet.addAll(map2.keySet());
        
        if(keySet.size() == 0){
            return 1* 65536;
        }
        
        int union = 0;
        int inter = 0;
        
        for(String key : keySet){
            inter += Math.min(map1.getOrDefault(key, 0), map2.getOrDefault(key, 0));
            union += Math.max(map1.getOrDefault(key, 0), map2.getOrDefault(key, 0));
            
        }
        
        
        return (int) ((double) inter/union * 65536);
    }
    
    public List<String> makeMultiSet(String str){
        List<String> li = new ArrayList<>();
        
        for(int i = 1; i < str.length(); i++){
            String sub = str.substring(i-1, i+1).toLowerCase();
            //System.out.println(sub);
            if(!isAlpha(sub)) continue;
            li.add(sub);
        }
        return li;
        
    }
    
    public boolean isAlpha(String str){
        if(str.charAt(0) >= 'a' && str.charAt(0) <= 'z'){
            if(str.charAt(1) >= 'a' && str.charAt(1) <= 'z'){
                return true;
            }
            return false;
        }
        return false;
    }
}
