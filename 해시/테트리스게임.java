import java.util.*;
class 테트리스게임 {
    public int solution(String dirs) {
        int answer = 0;
        int curX = 0;
        int curY = 0;
        Set<String> visited = new HashSet<>();
        for(int i = 0; i< dirs.length(); i++){
            char c = dirs.charAt(i);
            
            if(c == 'U'){
                // 위
                if(isValid(curX , curY+1)){
                    String key1 = curX + " " + curY + " " + curX + " " + (curY+1);
                    String key2 = curX + " " + (curY+1) + " " + curX + " " + (curY);
                    curY++;
                    
                     if(!visited.contains(key1) || !visited.contains(key2)){
                        answer++;
                    }
                   visited.add(key1);
                    visited.add(key2);
                    
                }
            }else if (c == 'D'){
                
                if(isValid(curX , curY-1)){
                    String key1 = curX + " " + curY + " " + curX + " " +(curY-1);
                    String key2 = curX + " " + (curY-1) + " " + curX + " " +(curY);
                    curY--;
                     if(!visited.contains(key1) || !visited.contains(key2)){
                        answer++;
                    }
                     visited.add(key1);
                    visited.add(key2);
                    
                }
                
            }else if( c== 'R'){
                
                if(isValid(curX+1 , curY)){
                    String key1 = curX + " " + curY + " " + (curX+1) + " " + curY;
                    String key2 = (curX+1)+ " " + curY + " " + (curX) + " " + curY;
                    curX++;
                    
                     if(!visited.contains(key1) || !visited.contains(key2)){
                        answer++;
                    }
                    visited.add(key1);
                    visited.add(key2);
                   
                }
                
            }else if(c == 'L'){
                
                if(isValid(curX-1 , curY)){
                    String key1= curX+ " " + curY + " " + (curX-1) + " " + curY;
                    String key2 =  (curX-1) + " " + curY + " "+ curX+ " " + curY;
                    curX--;
                    if(!visited.contains(key1) || !visited.contains(key2)){
                        //stem.out.println(i + " " + key);
                        answer++;
                    }
                     visited.add(key1);
                    visited.add(key2);
                    
                }
                
            }
        }
        return answer;
    }
    
    public boolean isValid( int x, int y){
        if( x >= -5 && x <= 5 && y >= -5 && y <= 5){
            return true;
        }
        return false;
    }
}
