import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.*;

public class 괄호 {

    public static void main(String[] args) throws Exception{
        new 괄호().solution();
    }

    public static void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int total = Integer.parseInt(br.readLine());

        boolean flag = true;
        while(total-- > 0){
            Deque<Character> openStack = new ArrayDeque<>();
            char[] input = br.readLine().toCharArray();
            for(char c : input){
                if(c == '('){
                    openStack.push(c);
                }else{
                   if(openStack.isEmpty()){
                       flag = false;
                       break;
                   }else{
                       openStack.pop();
                   }
                }
            }
            if(flag && openStack.isEmpty()) sb.append("YES").append("\n");
            else sb.append("NO").append("\n");
            flag = true;

        }
        System.out.println(sb);


    }
}
