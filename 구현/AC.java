import java.util.*;
import java.io.*;

public class AC{
    public static void main(String[] args) throws Exception{
        new AC().solution();

    }

    public void solution() throws Exception{
        // 반례와 시간초과가 핵심인 문제!
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        StringBuilder sb= new StringBuilder();
        while(testCase-- >0){
            String command = br.readLine();
            int num = Integer.parseInt(br.readLine());

            String input= br.readLine().replace("[", "").replace("]", "").trim();


            StringTokenizer st = new StringTokenizer(input, ",");
            Deque<Integer> dq = new ArrayDeque<>();

            for(int i = 0; i < num;i++){
                int n = Integer.parseInt(st.nextToken());
                dq.add(n);
            }
            boolean isRight = true;
            boolean finish = false;
            for(int i = 0; i < command.length(); i++){

                if(command.charAt(i) == 'R'){
                    isRight = !isRight;

                }else if(command.charAt(i) == 'D'){ // 버리기
                    if(dq.isEmpty()){

                        finish = true;
                        break;
                    }
                    if(isRight){
                        dq.pollFirst();
                    }
                    else{
                        dq.pollLast();
                    }
                }
            }
            if(finish){
                sb.append("error");
            }else{
                sb.append("[");
                while(!dq.isEmpty()){


                    if(isRight){
                        sb.append(dq.pollFirst());
                    }else{
                        sb.append(dq.pollLast());
                    }
                    if(dq.size() == 0){
                        continue;
                    }else{
                        sb.append(",");
                    }
                }
                sb.append("]");
            }
            if(testCase == 0){
                continue;
            }
            sb.append("\n");
        }
        System.out.println(sb);

    }
}