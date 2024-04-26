import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class 최솟값찾기 {

    // 우선순위 큐를 사용해도 되지만 Java로는 해당 문제의 시간 제한에 통과하기가 어렵다.
    public static void main(String[] args) throws Exception{
        new 최솟값찾기().solution();
    }

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int total = Integer.parseInt(st.nextToken());
        int[] num = new int[total];
        int L = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i= 0;i < total; i++){
            num[i] = Integer.parseInt(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();
        Deque<Integer> que = new ArrayDeque<>(); // 단조 큐

        for(int i = 0 ; i < total; i++){
            while(!que.isEmpty() && num[que.peekLast()] > num[i]){ // 넣을 값이 오름차순으로 정렬된 큐의 마지막 값보다 작으면 오름차순이 유지되지 않으므로 제거한다.
                que.pollLast();
            }
            while(!que.isEmpty() && i-L+1 >= 0 && que.peek() < i-L+1){ // 해당 최솟값이 유효하지 않는 값이라면 (범위에 포함되지 않는다면)
                que.poll(); // 앞에서부터 없앤다.
            }
            que.offer(i);
            sb.append(num[que.peek()]).append(" ");
        }
        System.out.println(sb);
    }
}
