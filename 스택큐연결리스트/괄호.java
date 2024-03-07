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
                if(c == '('){ // 여는 괄호라면 스택에 넣는다.
                    openStack.push(c);
                }else{ // 닫는 괄호라면
                   if(openStack.isEmpty()){ // 1) 닫는 괄호인데 stack이 비어있다면 괄호 문자열 조건에 맞지 않으므로 틀린 것이다.
                       flag = false; // 괄호 문자열 조건이 아니라는 것을 표시
                       break;
                   }else{
                       openStack.pop(); // 2) 닫는 괄호인데 스택에 여는 괄호가 있다면 즉, 앞서 짝꿍인 여는 괄호가 있다는 의미이므로 조건 통과
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
