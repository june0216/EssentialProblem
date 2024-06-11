import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 복제로봇 {
    //열쇠가 있는 곳들과 로봇이 출발하는 위치에 로봇이 복제할 수 있는 장치를 장착

    public static void main(String[] args) throws Exception{
        new 복제로봇().solution();
    }

    int S;
    int K;
    char[][] map;

    Node start;


    Deque<Node> que;

    static int[] dx = new int[]{-1, 1, 0, 0};
    static int[] dy = new int[]{0, 0, -1, 1};

    int res;
    Map<String, Node> keyMap;
    PriorityQueue<WeightNode> pq;
    int index;

    int[] parent;
    List<Node> keyList;
    public void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new char[S][S];

        que = new ArrayDeque<>();
        res = 0;

        keyMap = new HashMap<>();
        index = 0;
        for(int r = 0 ; r < S; r++){
            String input = br.readLine();
            for(int c = 0 ; c < S ; c++){
                map[r][c] = input.charAt(c);
                if(map[r][c] == 'S' || map[r][c] == 'K'){
                    keyMap.put(intToString(c, r), new Node(c, r, index));

                    index++;
                }
            }
        }

        parent = new int[K+1];
        for(int i= 0; i < K+1; i++){
            parent[i] = i;
        }

        pq = new PriorityQueue<>();

       for( Map.Entry<String, Node> keyNode : keyMap.entrySet()){ // 각 노드에서 갈 수 있는 경로들을 탐색해서 가중치 그래프를 만든다.

            bfs(keyNode.getValue());
        }


        System.out.println(kruskal());


    }


    public int kruskal(){
        int connected = 0;
        int answer = 0;
        while(!pq.isEmpty()){
            WeightNode cur = pq.poll();
            int fromParent = find(cur.from);
            int toParent = find(cur.to);
            if(toParent != fromParent){ //사이클이 만들어지지 않는다면
                answer += cur.cost; // 결과에 추가
                parent[fromParent] = toParent; // union
                connected++;
            }

        }
        if(connected != K) return -1; // 모든 요소들이 연결되지 않는다면 -1을 반환
        return answer;
    }

    public int find(int node){ // 부모 노드 찾기
        if(parent[node] == node) return node;
        else return parent[node] = find(parent[node]);

    }

    public void bfs(Node s){
        boolean[][] visited = new boolean[S][S];
        que.offer(new Node(s.x, s.y, 0));
        visited[s.y][s.x] = true;
        while(!que.isEmpty()){
            Node cur = que.poll();
            for(int i = 0; i < 4; i++){
                int nx = dx[i] + cur.x;
                int ny = dy[i] + cur.y;
                if(nx >= 0 && ny >= 0 && nx < S && ny < S) { // 범위 안에 있는 경우
                    if(map[ny][nx] == '1' || visited[ny][nx]){ // 벽이나 한 번 방문한 곳이라면
                        continue;
                    }
                    if(map[ny][nx] == 'S' || map[ny][nx] == 'K'){ // 노드라면
                        pq.offer(new WeightNode(s.cnt,  keyMap.get(intToString(nx, ny)).cnt ,cur.cnt+1));

                    }
                    visited[ny][nx] = true;
                    que.offer(new Node(nx, ny, cur.cnt+1));

                }
            }
        }
    }

    public String intToString(int x, int y){ // 좌표가 최대 (50, 50)이므로 X + Y를 인덱스로 사용하였다.
        String result = "";
        if(x >= 10){ // 10 이상 즉 2자리라면
            result+=x;
        }else{ // 1자리라면 앞에 0을 붙임
            result+= ("0" + x);
        }
        if(y >= 10){
            result += y;
        }else{
            result += ("0" + y);
        }
        return result;
    }



    class Node{
        int x;
        int y;

        int cnt;

        public Node(int x, int y, int cnt){
            this.x = x;
            this.y = y;
            this.cnt = cnt;


        }

    }

    class WeightNode implements Comparable<WeightNode>{
        int cost;

        int from;
        int to;

        public WeightNode(int from, int to, int cost){
            this.from = from;
            this.to = to;
            this.cost = cost;

        }

        public int compareTo(WeightNode node){
            return this.cost - node.cost;
        }

    }

}
