import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 철도 {
    public static void main(String[] args) throws Exception{
        //long startTime = System.currentTimeMillis();
        new 철도().solution();
        //long endTime = System.currentTimeMillis();
        //System.out.println(endTime - startTime);
    }

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());

        List<Node> position = new ArrayList<>();
        for(int i = 0; i< num;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            if(start > end){
                int tmp = start;
                start = end;
                end = tmp;
            }
            position.add(new Node(start, end));

        }

        // 끝점을 기준으로 앞쪽에 있는 거부터 정렬
        Collections.sort(position);

        // 우선순위 큐 -> 시작점이 작은 순서대로 검사 -> 일단 큐 안에 있다는 것은 현재 위치의 끝점보다 더 작다는 것을 의미 -> 범위 내에 시작점과 끝점 둘다 있으려면
        // 시작점을 살펴보며 현재보다 작은 것들을 앞에서 다 쳐내면 그 뒤에는 검사하지 않아도 범위 안에 있으므로 다 안꺼내도 알 수 있다.
        PriorityQueue<Integer> que =new PriorityQueue<>();

        int limitRange = Integer.parseInt(br.readLine());
        int max = 0;
        for(Node pos : position){
            if(!que.isEmpty() && que.peek() < pos.end - limitRange){ // 범위에 안들어오는 경우
                que.poll(); // 나가리

            }
            if(pos.start >= pos.end - limitRange){ // 만약 현재 범위가 주어진 range 구간안에 쏙 들어오면 큐에 넣는다.
                que.offer(pos.start);
            }

            max = Math.max(max, que.size()); // 한 점씩 검사하면서 해당 범위에서 range에 들어오는 구간들을 저장
        }

        System.out.println(max);

    }

    class Node implements Comparable<Node> {
        int start;
        int end;
        public Node(int start, int end){
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Node node){
            if(this.end == node.end) return this.start - node.start;
            return this.end - node.end;
        }
    }
}
