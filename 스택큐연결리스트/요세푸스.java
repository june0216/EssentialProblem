import java.util.*;
import java.io.*;

public class 요세푸스 {
    public static void main(String[] args) throws Exception{
        new 요세푸스().solution();
    }

    public static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        StringTokenizer st = new StringTokenizer(br.readLine());

        int total = Integer.parseInt(st.nextToken()); // 전체 번호 개수
        int deleteIndex = Integer.parseInt(st.nextToken()); // 삭제할 index

        Queue<Integer> queue = new ArrayDeque<>();
        for(int i =1 ; i <= total; i++){
            queue.offer(i); // 순서대로 사람을 큐에 넣음
        }

        while(!queue.isEmpty()){ // 큐에 사람이 없을 때까지 반복
            for(int i = 0 ; i < deleteIndex; i++){ // 삭제할 순서대로 반복
                if(i == deleteIndex-1){ // 삭제할 순서가 되면 삭제
                    sb.append(queue.poll());
                }else{
                    queue.offer(queue.poll()); // 삭제할 순서가 아니면 나중에 삭제되기 위해 다시 큐에 넣음 (원모양을 유지)
                }

            }
            if(queue.size() ==0){ // 마지막 삭제라면 끝을 의미하는 >을 추가
                sb.append(">");
                break;
            }else{
                sb.append(", "); // 아직 삭제할 것이 있다면 , 을 추가하고 계속 삭제 진행
            }
        }
        System.out.println(sb);
    }
    }
