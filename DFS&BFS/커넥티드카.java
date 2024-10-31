import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.StringTokenizer;

public class 커넥티드카 {
    public static void main(String[] args) throws Exception {
        new 커넥티드카().solution();
    }


    int N;
    boolean[] visited;


    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int startCar = Integer.parseInt(st.nextToken());
        visited = new boolean[N + 1];

        st = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        Deque<Node> que = new ArrayDeque<>();
        Node[] car = new Node[N+1];
        car[0] = new Node(0, Integer.MAX_VALUE, Integer.MAX_VALUE);
        for (int i = 1; i <= N; i++) {
            int position = Integer.parseInt(st.nextToken());
            int energy = Integer.parseInt(st2. nextToken());
            car[i] = new Node(i, position, energy);

        }
        que.offer(car[startCar]);

        visited[startCar] = true;
        while(!que.isEmpty()) {
            Node cur = que.poll();
            // 선택한 차 기준으로 앞의 차들을 확인하기
            for(int i = cur.idx-1; i > 0; i--){
                // 핵심 -> 탐색하다가 불가능한 것이 있으면 바로 종료 (이거 처리 안하고 다 for문 돌리면 시간초과)
                // 어차피 정렬된 상태로 입력이 주어지기 때문에 뒤에 인덱스들을 확인 다 안해도 앞의 인덱스에서 불가능하다고 판단이 들면 뒤에도 다 불가능함
                if(cur.position - cur.energy > car[i].position) break;
                if(visited[car[i].idx]) continue;
                que.offer(car[i]);
                visited[car[i].idx] = true;

            }

            // 선택된 차 기준 뒤의 차들을 확인
            for(int i = cur.idx+1; i <= N; i++){
                if(cur.position + cur.energy < car[i].position) break;
                if(visited[car[i].idx]) continue;
                que.offer(car[i]);
                visited[car[i].idx] = true;

            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= N;i++){
            if(visited[i]) sb.append(i).append(" ");
        }

        System.out.println(sb);
    }


    class Node {
        int idx;
        int energy;
        int position;
        public Node(int idx, int position, int energy){
            this.idx = idx;
            this.energy = energy;
            this.position = position;
        }
    }

}
