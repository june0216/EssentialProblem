import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 집구하기 {
    public static void main(String[] args) throws Exception{
        new 집구하기().solution();
    }


    int roadCnt;
    int nodeCnt;
    List<Node>[] info;
    Set<Integer> macSet;
    Set<Integer> starbucksSet;
    int macDis;
    int macCost[];
    int starCost[];
    int starbucksDis;

    static int NOT_ROUTE = Integer.MAX_VALUE;
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        nodeCnt = Integer.parseInt(st.nextToken());
        roadCnt = Integer.parseInt(st.nextToken());
        info = new List[nodeCnt+1];
        for(int i = 0; i <= nodeCnt; i++){
            info[i] = new ArrayList<>();
        }
        for(int i =0; i < roadCnt; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());
            info[from].add(new Node(to, distance));
            info[to].add(new Node(from, distance));
        }
        // 맥도날드 입력
        st = new StringTokenizer(br.readLine());
        int mac = Integer.parseInt(st.nextToken());
        macDis = Integer.parseInt(st.nextToken());

        // 맥도날드 정점 입력
        st = new StringTokenizer(br.readLine());
        macSet = new HashSet<>();
        for(int i = 0; i < mac; i++){
            macSet.add(Integer.parseInt(st.nextToken()));
        }

        // 스타벅스
        st = new StringTokenizer(br.readLine());
        int starbucks = Integer.parseInt(st.nextToken());
        starbucksDis = Integer.parseInt(st.nextToken());

        // 스타벅스 정점 입력
        st = new StringTokenizer(br.readLine());
        starbucksSet = new HashSet<>();
        for(int i = 0; i < starbucks; i++){
            starbucksSet.add(Integer.parseInt(st.nextToken()));
        }

        // 거리 갱신
        int result = NOT_ROUTE;
        macCost = new int[nodeCnt+1];
        starCost = new int[nodeCnt+1];
        Arrays.fill(starCost, NOT_ROUTE);
        Arrays.fill(macCost, NOT_ROUTE);

        //맥도날드를 기준으로 반경 내 최단 거리 갱신
        dijkstra(macSet, macDis, macCost);
        // 스타벅스를 기준으로 반경 내 최단 거리 갱신
        dijkstra(starbucksSet,starbucksDis, starCost);

        // 집인 것들 중 스벅과 맥날까지의 거리 합이 최소인 것을 찾기
        for(int i = 1; i <= nodeCnt; i++){
            if (macCost[i] == NOT_ROUTE || starCost[i] == NOT_ROUTE ) { // 해당 집이 맥날, 스벅 하나라도 반경 안에 못드는 경우
                continue;
            }if (macCost[i] == 0 || starCost[i] == 0 ) { // 해당 노드가 집이 아닌 경우
                continue;
            }
            result = Math.min(result, macCost[i] + starCost[i]);
        }
// 아예 없으면 -1을 출력, 있으면 최솟값을 출력
        System.out.println(result == NOT_ROUTE ? -1: result);
    }

    public void dijkstra(Set<Integer> startPoint, int maxDis, int[] cost){

        PriorityQueue<Node> pq = new PriorityQueue<>();
        for(int start : startPoint){
            cost[start] = 0;
            pq.offer(new Node(start, 0));
        }
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            for(Node next : info[cur.point]){
                if(cost[next.point] > next.distance + cost[cur.point]){

                    // 여기서 맥도날드나 스벅이 아닌 경우 케이스도 걸러내도 틀리지는 않음
                    if(next.distance +  cost[cur.point] <= maxDis){
                        cost[next.point]  = next.distance +  cost[cur.point];
                        pq.offer(new Node(next.point, cost[next.point]));
                    }


                }
            }
        }

    }


    class Node implements Comparable<Node>{
        int point;
        int distance;

        public Node(int point, int distance){
            this.point = point;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node node){
            return this.distance - node.distance;
        }
    }
}


