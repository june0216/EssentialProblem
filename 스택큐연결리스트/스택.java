/**
 * https://www.acmicpc.net/problem/10828
 */

import java.util.*;
import java.io.*;

public class 스택 {
    public static void main(String[] args) throws Exception{
        new 스택().solution();
    }

    static void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int num = Integer.parseInt(st.nextToken());

        Deque<Integer> stack = new ArrayDeque<>(); // stack 생성

        for(int i = 0; i < num; i++){
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            switch (command){
                case "push":
                    int number = Integer.parseInt(st.nextToken());
                    stack.add(number); // 값 넣기
                    break;
                case "pop":
                    if(stack.isEmpty()) sb.append("-1").append("\n");
                    else // 비어있지 않으면 맨 위의 값을 꺼냄
                        sb.append(stack.pollLast()).append("\n");
                    break;
                case "size": // 스택의 사이즈를 반환
                    sb.append(stack.size()).append("\n");
                    break;
                case "empty":
                    if(stack.isEmpty()) sb.append("1").append("\n"); //스택이 비어있으면 -1
                    else sb.append("0").append("\n"); // 스택이 비어있지 않으면 00
                    break;
                case "top":
                    if(stack.isEmpty()) sb.append("-1").append("\n"); // 스택이 비어있으면 -1
                    else sb.append(stack.peekLast()).append("\n"); // 스택에 값이 있으면 값을 꺼내지 않고 값 정보만 출력
                    break;
            }

        }

        System.out.println(sb);
    }

}
