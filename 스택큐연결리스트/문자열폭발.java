import java.util.*;
import java.io.*;
public class 문자열폭발 {
    public static void main(String[] args) throws Exception{
        new 문자열폭발().solution();
    }

    public void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String bomb = br.readLine();

        Deque<Integer> stack = new ArrayDeque<>();


        //일치하면 빼고 일치하지 않으면 다시 넣기
        // 근데 뺀 것 중간에 있으면?
        Deque<Integer> temp = new ArrayDeque<>();
        for(int i = 0 ; i < input.length(); i++){
            boolean isPush = true;
            boolean match = true;
            stack.addLast(i);

            // 스택에 들어있는 문자열의 개수와 비교 문자열의 개수가 일치하거나 크면서 + 현재 문자열이 비교 문자열의 마지막 문자와 같다면
            // 스택에서 하나씩 꺼내 비교해본다.
            if(bomb.length()<= stack.size() && bomb.charAt(bomb.length()-1) == input.charAt(i)){
                temp.clear();

                    for(int b = 0 ; b < bomb.length(); b++){ // 문자열을 차례대로 검사한다.
                        if(bomb.charAt(bomb.length()- b-1) == input.charAt(stack.peekLast())){
                            int idx = stack.pollLast();

                            temp.offer(idx); // 혹시나 스택에서 꺼냈지만 중간에 다른 문자열이 있다면 복구하기 위해 임시로 넣어둔다.
                        }else{  // 문자열이 일치하지 않는 부분이 있다면 표시하고 비교를 종료한다.
                            match = false;
                            break;

                        }
                    }
                    if(!match){ // 중간에 안맞는 부분이 발견되었더라면 복구한다.
                        while(!temp.isEmpty()){
                            stack.addLast(temp.pollLast()); //넣은 순서대로 넣는다.
                        }
                    }



            }

        }


        StringBuilder sb = new StringBuilder();
        if(stack.isEmpty()){ // 스택이 비어있다면 아래의 문자를 출력
            sb.append("FRULA");
        }else{
            while(!stack.isEmpty()){
                sb.append(input.charAt(stack.pollFirst()));
            }
        }


        System.out.println(sb);
    }
}
