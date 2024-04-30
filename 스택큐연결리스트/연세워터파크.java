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
        int[] bridge = new int[total+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i < total+1;i++){
            bridge[i] = Integer.parseInt(st.nextToken());

        }

        Deque<Node> que = new ArrayDeque<>();

        int step =  0;
        long res = Long.MIN_VALUE;
        for(int i = 1; i < total+1; i++){

            if(que.isEmpty()){ // 비어있으면 일단 넣기
                que.offerLast(new Node(bridge[i], i));
                continue;
            }else{
                if(!que.isEmpty() && que.peekLast().idx < i+D){ // 최댓값 중 범위를 넘어서는 값들을 제거하기
                    que.pollLast();
                }
                if(bridge[i] >= 0){ // 양수이면 무조건 더하기
                    int data = bridge[i];
                    if(!que.isEmpty() && bridge[i] <= que.peekLast().value){
                        data += que.peekLast().value;
                    }
                    que.offerLast(new Node(data,i));
                }else { // 음수라면 일단 오름차순으로 큐 정렬
                    while(que.size() > 1 && bridge[i] + que.peekLast().value >= que.peekFirst().value){
                        que.pollFirst();
                    }
                    int data = bridge[i];
                    if(!que.isEmpty()){
                        data += que.peekLast().value;
                    }
                    que.offerFirst(new Node(data, i));


                }

            }
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
