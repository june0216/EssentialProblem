import java.util.*;

/*
틀린 이유 -> 종료 조건이 잘 안되어서 
조건을 잘못생각함
*/
class 괄호변환 {
    StringBuilder sb;
    public String solution(String p) {
        String answer = "";
        if(p.equals("")){
            return p;
        }
        
        sb = new StringBuilder();
        
        
        return convert(p);
    }
    
    public String convert(String str){
        //입력이 빈 문자열인 경우, 빈 문자열을 반환합니다. 
        if(str.equals("") || str.length() == 0){
            return str;
        }
        
        //문자열 w를 두 "균형잡힌 괄호 문자열" u, v로 분리합니다. 단, u는 "균형잡힌 괄호 문자열"로 더 이상 분리할 수 없어야 하며, v는 빈 문자열이 될 수 있습니다. 
        
        //'(' 의 개수와 ')' 의 개수가 같다면 
        Deque<Character> stack = new ArrayDeque<>();
        int splitIdx  = 0;
        int open = 0;
        int close = 0;
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) == '('){
                 open++;
            }else{
                close++;
            }
            if(open == close){
                splitIdx = i;
                break;
            }
           
        }
        
        String u = str.substring(0, splitIdx+1);
        String v = str.substring(splitIdx+1); //이 부분 때문에 stackoverflow남
   
        
        //문자열 u가 "올바른 괄호 문자열"이 아니라면 아래 과정을 수행합니다. 
        if(isRight(u)){
          
            return u + convert(v);
            
        }else{
            
            return "(" + convert(v) + ")" + reverse(u);
        }
        
        
    
        
       
        
        
        
    }
    
    public String reverse(String v){
        StringBuilder s = new StringBuilder();
        for(int i = 1; i < v.length()-1; i++){
            if(v.charAt(i) == '('){
                s.append(")");
            }else{
                 s.append("(");
            }
        }
        
        return s.toString();
    }
    
    
    public boolean isRight(String str){
    
    int open = 0;
    int close = 0;
    
    for (int i = 0; i < str.length(); i++) {
        if (str.charAt(i) == '(') open++;
        else close++;
        if(open < close){ // 중간에 검사하는 게 필요 
            return false;
        }
    }

        return open == close;
    }

}
