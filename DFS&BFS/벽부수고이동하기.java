import java.util.*;
import java.io.*;
/*
부분 집합
 */
public class 벽부수고이동하기 {
    public static void main(String[] args) throws Exception{
        new 벽부수고이동하기().solution();
    }



    int[][] map;
    int[][] group;

    Deque<Node> zeroQue;


    int[] dx = new int[]{-1, 1, 0, 0};
    int[] dy = new int[]{0, 0, -1, 1};
    int R;
    int C;

    HashMap<Integer, Integer> groupMap = new HashMap<>();
    public void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        group = new int[R][C];

        zeroQue = new ArrayDeque<>();
        int groupNum = 2; // 벽의 의미인 1과 겹치는 것을 고려하여 2부터 시작
        for(int i = 0 ; i < R; i++){
            String input= br.readLine();
            for(int j = 0; j < C; j++){
                map[i][j] = input.charAt(j) - '0';
                group[i][j] = 0;
                if(map[i][j] == 0){
                    zeroQue.offer(new Node(j, i));
                }


            }
        }

        while(!zeroQue.isEmpty()){ // 0의 위치들을 돌면서 그룹화한다.
            Node cur = zeroQue.poll();
            if(group[cur.y][cur.x] == 0){
                groupMap.put(groupNum, bfs(new Node(cur.x, cur.y), groupNum)); // 해당 0의 그룹 번호 및 0의 개수 저장
                groupNum++;
            }
        }

        StringBuilder sb = new StringBuilder();

        for(int k = 0; k < R; k++){
            for(int j = 0; j < C; j++){
                int res = 1;

                Set<Integer> groupSet = new HashSet<>(); // 벽을 기준으로 위쪽에 group1을 카운트해줬는데 아래에도 group1이 있을 수 있으므로 중복을 제거해야한다.
                for(int i = 0; i < 4; i++){
                    int nx = dx[i] + j;
                    int ny = dy[i] + k;
                    if(nx >= 0 && ny >= 0 && nx < C && ny < R){
                        if(map[ny][nx] == 0 && group[ny][nx] != 0){ // 0이면서 그룹이 있는 0이라면
                            groupSet.add(group[ny][nx]); // 해당 그룹을 set에 넣는다.

                        }
                    }
                }

                for(int num : groupSet){
                    res += groupMap.get(num); // 각 그룹들의 0의 개수를 합친다.
                }

                if(map[k][j] == 0){ // 이 부분에서 주변에 0이 하나도 없으면 0으로 처리하려고 했는데 그래도 자기 자신이 있으므로 1로 처리해야한다.
                    sb.append(0);
                    continue;
                }
                sb.append(res%10);
            }
            sb.append("\n");

        }

        System.out.print(sb);

    }

    public int bfs(Node node, int groupNum){

        Deque<Node> que = new ArrayDeque<>();
        que.offer(node);
        group[node.y][node.x] = groupNum;
        int result = 1;
        while(!que.isEmpty()){
            Node cur = que.poll();
            for(int i = 0; i < 4; i++){
                int nx = dx[i] + cur.x;
                int ny = dy[i] + cur.y;
                if(nx >= 0 && ny >= 0 && nx < C && ny < R){
                    if(map[ny][nx] == 0 && group[ny][nx] == 0){ // 아직 방문하지 않았고 0이라면 그룹을 카운트 함
                        que.offer(new Node(nx, ny));
                        group[ny][nx] = groupNum; // 방문 표시 + 해당 그룹 표시
                        result++;
                    }
                }
            }
        }

        return result;
    }

    class Node{
        int x;
        int y;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
