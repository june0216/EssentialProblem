import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class 연세워터파크 {
    public static void main(String[] args) throws Exception{
        new 연세워터파크().solution();
    }
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int total = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());
        long[] bridge = new long[total];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < total;i++){
            bridge[i] = Long.parseLong(st.nextToken());
        }

        Deque<Node> que = new ArrayDeque<>();

        long res = Long.MIN_VALUE; // 결과를 저장하는 변수
        for(int i = 0; i < total; i++){
            while(!que.isEmpty() && que.peekLast().idx+D < i){ // 최댓값 중 범위(최대로 뛰어넘을 수 있는 값 이상인 경우)를 넘어서는 값들을 제거하기
                que.pollLast(); //
            }
            if(que.isEmpty()){ // 비어있으면 일단 넣기
                que.offerLast(new Node(bridge[i], i));
                continue;
            }

            // (1) 현재 값에다가 범위 내 최댓값을 더하여 최댓값을 갱신하기
            if(que.peekLast().value <= que.peekLast().value + bridge[i]){ // 해당 값과 더한 값이 큐 내에서 가장 크다면 바로 마지막 값에 저장
                que.offerLast(new Node(bridge[i] + que.peekLast().value,i));
            }else { // 합이 최대가 아니라면 앞에서부터(최솟값부터) 현재 계산된 값보다 작은 값들을 전부 삭제하기
                while(que.size() > 1 && que.peekFirst().value <=que.peekLast().value + bridge[i] ){ // 최댓값 1개 남을 때까지 제한두고 검사
                    que.pollFirst();
                }
                que.offerFirst(new Node(bridge[i] + que.peekLast().value, i)); // 현재 계산한 값이 최솟값 자리에 넣음

            }

            //(2) 최댓값과 현재 값을 더하여 최댓값을 갱신했는데도 현재값 그 자체가 최대가 될 수 있다. -> 큐에 이 경우를 추가한다.
            if(que.peekLast().value < bridge[i]){
                que.offerLast(new Node(bridge[i], i));
            }

            // (3) 해당 인덱스에 대한 값을 반영했으니 반영한 후 최댓값을 갱신한다.
            res = Math.max(res, que.peekLast().value);



        }
        System.out.println(res);
    }

    class Node {
        long value;
        int idx;


        public Node(long value, int idx){
            this.value = value;
            this.idx = idx;
        }

    }
}