import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class 쇠막대기 {
    public static void main(String[] args) throws Exception{
        new 쇠막대기().solution();
    }

    public void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        // 괄호 문제 + 레이저는 그 사이에 있다. 
        /*
            규칙이 없어보였는데 아니었던 것 같다. 규칙이 있었음 
            단순 구현으로 풀려고 했는데 실패함
            
            */

        Deque<Character> stack = new ArrayDeque<>();

          long total = 0;
        boolean preOpen = false;
        for(int i = 0; i < input.length();i++){
            if(input.charAt(i) == '('){
                stack.offer('(');
                preOpen = true;
                
            }else{
                char last = stack.peekLast();
                if(last == '('){
                    stack.pollLast();
                    if(preOpen){
                        
                        total+=stack.size();
                        
                    }else{
                        total++;
                        
                    }
                    
                    
                }
              
                preOpen = false;
            }
        }
      


         System.out.println(total);
    }

   
}
