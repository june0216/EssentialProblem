import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 타임머신 {
    public static void main(String[] args) throws Exception{
        new 타임머신().solution();
    }

    public static int dx[] = new int[]{0, 1, 0, -1};
    public static int dy[] = new int[]{1, 0, -1, 0};
    int direction;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int cityNum = Integer.parseInt(st.nextToken());
        int line = Integer.parseInt(st.nextToken());
        List<Edge> busLine = new ArrayList<>();


        for(int i = 0; i< line; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            busLine.add(new Edge(from, to, cost));
        }

        long[] dist = new long[cityNum+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        int result = 0;
        dist[1] = 0;

        // 정점 -1번 반복
        for(int i = 0; i < cityNum; i++){
            for(Edge edge : busLine){
                // 출발지점이 갱신되지 않은 경우 무시 (즉, 출발지점에서 도달할 수 없는 정점이라는 의미이므로 건너뛰어도 된다)
                if(dist[edge.from] != Integer.MAX_VALUE && dist[edge.to] > (dist[edge.from] + edge.cost)){
                    dist[edge.to] = dist[edge.from] + edge.cost;
                    if(i == cityNum-1){ // 음의 간선이 있는 경우
                        result = -1;
                    }
                }
            }
        }

        if(result == -1){
            System.out.println(result);
        }else{
            for(int i = 2; i < dist.length; i++){
                // 정점에 도달할 수 없는 경우 -1 출력
                if(dist[i] == Integer.MAX_VALUE){
                    System.out.println(-1);
                    continue;
                }
                System.out.println(dist[i]);
            }
        }

    }

    class Edge{
        int from;
        int to;
        int cost;
        public Edge(int from, int to, int cost){
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }
}
