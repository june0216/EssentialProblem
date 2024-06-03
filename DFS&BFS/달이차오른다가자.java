import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
visited 처리하기
키 비트로 관리하기
 */
public class 달이차오른다가자 {
    public static void main(String[] args) throws Exception{
        new 달이차오른다가자().solution();
    }

    int R;
    int C;
    static int[] dx = new int[]{-1, 1, 0, 0};
    static int[] dy = new int[]{0, 0, -1, 1};

    char[][] miro;
    Deque<Node> que;
    boolean visited[][][];
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        miro = new char[R][C];
        que = new ArrayDeque<>();
        visited = new boolean[R][C][64]; // 키를 가지고 방문했는지 여부 -> 6개의 알파벳 조합을 000000 ~ 111111 으로 나타냄(2의 6승)
        for(int i = 0; i < R; i++){
            String input = br.readLine();
            for(int j = 0 ; j < C; j++){
                miro[i][j] = input.charAt(j);
                if(miro[i][j] == '0'){
                    que.offer(new Node(j, i, 0, 0));
                }
            }
        }
        dfs();


    }

    public void dfs(){

        while(!que.isEmpty()){
            Node cur = que.poll();
            if(miro[cur.y][cur.x] == '1'){
                System.out.println(cur.cnt);
                return;
            }
            for(int i = 0; i < 4; i++){
                int nx = dx[i] + cur.x;
                int ny = dy[i] + cur.y;
                if(nx >=  0 &&  ny >= 0 && nx < C && ny < R ) {
                    if(miro[ny][nx] != '#' && !visited[ny][nx][cur.key]){ // 벽이 아니면서 방문한 적이 없는 경우
                        // 넣기
                        if(miro[ny][nx] >= 'a' && miro[ny][nx] <= 'f'){ // 열쇠일 경우
                            // 열쇠 넣기
                            int getKey = 1 << (miro[ny][nx] - 'a'); // a라면 000001 , b라면 000010 이런식으로 a는 1을 0번밀고, b는 1을 1번 왼쪽으로 민다.
                            getKey = getKey | cur.key; // 기본 키에 얻은 키를 합친다.
                            if(!visited[ny][nx][getKey]){
                                que.offer(new Node(nx, ny, cur.cnt+1, getKey));
                                visited[ny][nx][getKey] = true; // 새로 얻은 키로 해당 지점을 지나갔다고 방문 처리
                                visited[ny][nx][cur.key] = true; // 기존 키를 가지고 해당 지점을 지나갔다고 방문 처리
                            }

                        }else if(miro[ny][nx] >= 'A' && miro[ny][nx] <= 'F'){ // 문일 경우
                            int door = 1 << (miro[ny][nx] - 'A');
                            if((cur.key & door) > 0 ){ // 키와 and연산을 통해 1보다 크다면 해당 열쇠가 있는 것이므로 지나갈 수 있다.
                                que.offer(new Node(nx, ny, cur.cnt+1, cur.key));
                                visited[ny][nx][cur.key] = true;
                            }

                        }else{ // 갈 수 있는 통로라면 방문 처리 후 큐에 담는다.
                            que.offer(new Node(nx, ny, cur.cnt+1, cur.key));
                            visited[ny][nx][cur.key] = true;
                        }

                    }
                }
            }
        }
        System.out.println(-1);

    }
    class Node{
        int x;
        int y;
        int cnt;
        int key;
        public Node(int x, int y, int cnt, int key){
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.key = key;
        }



    }
}
