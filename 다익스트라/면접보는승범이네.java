import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 면접보는승범이네 {

    public static void main(String[] args) throws Exception {
        new 면접보는승범이네().solution();
    }

    int N, M, K;
    List<Node>[] map;
    long[] dp;
    int[] interview;
    boolean[] visited;


    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new List[N+1];
        dp = new long[N+1];
        interview = new int[K];
        visited = new boolean[N+1];

        Arrays.fill(dp,Long.MAX_VALUE); //  다익스트라 초기화

        for (int i = 0; i <= N; i++) {
            map[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            map[to].add(new Node(from, cost)); // 단방향
        }

        // 면접장 입력
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            interview[i] = Integer.parseInt(st.nextToken());
            dp[interview[i]] = 0l;
        }

        dijkstra();

        // 최댓값 찾기
        long max = 0l, maxIndex = -1;
        for (int i = 1; i <= N; i++) {
            if (dp[i] != Long.MAX_VALUE && dp[i] > max) {
                max = dp[i];
                maxIndex = i;
            }
        }

        // 결과 출력
        StringBuilder sb = new StringBuilder();
        sb.append(maxIndex).append("\n").append(max);
        System.out.println(sb);
    }

    public void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();

        //  다익스트라 시작 지점 초기화
        //
        for (int i = 0; i < K; i++) {
            dp[interview[i]] = 0;
            visited[i] = true;
            pq.offer(new Node(interview[i], 0));
        }

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if(dp[cur.to] < cur.cost) continue;

            for (Node next : map[cur.to]) {
                //if(visited[next.to]) continue;
                if (dp[next.to] > dp[cur.to] + next.cost) { //  최단 거리 갱신 조건 수정

                    dp[next.to] = dp[cur.to] + next.cost;
                    // System.out.println(dp[next.to]);

                    pq.offer(new Node(next.to, dp[next.to]));
                }
            }
        }
    }

    class Node implements Comparable<Node> {
        int to;
        long cost;

        public Node(int to, long cost) {
            this.to = to;
            this.cost = cost;
        }

        public int compareTo(Node node) {
            return Long.compare(this.cost, node.cost);
        }
    }
}
