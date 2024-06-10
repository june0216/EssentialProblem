import java.util.*;
import java.io.*;

public class 열쇠 {
    public static void main(String[] args) throws Exception{
        new 열쇠().solution();
    }

    int R;
    int C;

    char[][] map;

    Deque<Node>[] door; // key가 없어 열지 못한 경우 일단 임시 저장소에 넣었다가 키를 얻게 되면 그떄 다시 꺼내서 이동

    boolean[] keyList;
    Deque<Node> que;
    Deque<Node> startPoint;

    boolean[][] visited; // 최단 거리가 아니라 단순히 갈 수 있는지 확인하는 것이므로 키를 얻은 상태에 따라 방문 여부 처리하지 않아도 된다.
    static int[] dx = new int[]{-1, 1, 0, 0};
    static int[] dy = new int[]{0, 0, -1, 1};
    // 여기서 문제는 같은 종류의 key가 여러 개 있을 수 있다는 것임 -> 어차피 열쇠를 얻으면 그 종류의 열쇠는 다시 안얻어도 되므로 그냥 길로 표시
    // 시작지점이 여러 개, 도착 지점도 여러 개 임
    int res;
    public void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while(T-->0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            map = new char[R+2][C+2];
            visited = new boolean[R+2][C+2];
            res = 0;
            que = new ArrayDeque<>();
            startPoint = new ArrayDeque<>();


            for(int r = 0; r < R+2; r++){ // 테두리 빈칸으로 채우기
                for(int c = 0 ; c < C+2; c++){
                    map[r][c] = '.';
                }
            }

            for(int r = 0; r < R; r++){
                String input = br.readLine();
                for(int c = 0 ; c < C; c++){
                    map[r+1][c+1] = input.charAt(c);
                }

            }


            char[] key = br.readLine().toCharArray();
            keyList =new boolean[26];
            if(key[0] != '0'){
                for(char c : key){
                    keyList[c-'a'] = true;
                }
            }
            door = new ArrayDeque[26];
            for(int k = 0 ; k < 26; k++){
                door[k] = new ArrayDeque<>(); // 각 알파벳 별로 리스트를 만든다.
            }

            bfs();
            sb.append(res).append('\n'); // 결과 출력


        }

        System.out.println(sb);

    }
    public void bfs(){

        que.offer(new Node(0, 0)); // 처음 시작
        visited[0][0] = true;
        while(!que.isEmpty()){
            Node cur = que.poll();
            for(int i = 0; i < 4; i++){
                int nx = dx[i] + cur.x;
                int ny = dy[i] + cur.y;
                    if(nx >= 0 && ny >= 0 && nx < C+2 && ny < R+2){
                        if(map[ny][nx] != '*' && !visited[ny][nx]){
                            if('a' <= map[ny][nx] && 'z' >= map[ny][nx]){ // 키라면
                                keyList[map[ny][nx] - 'a'] = true; // 키 저장

                                while(!door[map[ny][nx] - 'a'].isEmpty()){ // 이전에 방문했을 때 키가 없어서 못들어갔던 경우들을 다시 꺼내서 방문처리한다.
                                    Node next = door[map[ny][nx] - 'a'].poll();
                                    que.offer(next);
                                    visited[next.y][next.x] = true;
                                }
                            }
                            else if('A' <= map[ny][nx] && 'Z' >= map[ny][nx]){ // 문이라면
                                if(!keyList[map[ny][nx] - 'A']){ // 키가 없다면 해당 알파벳에 대해 문의 위치를 임시 보관하고 키를 찾을 때 꺼내서 방문처리한다.
                                    door[map[ny][nx] - 'A'].add(new Node(nx, ny));
                                    continue;
                                }

                            }else {
                                if(map[ny][nx] == '$'){ // 복도인 경우
                                    res++;
                                }

                            }
                            que.offer(new Node(nx, ny));
                            visited[ny][nx] = true;
                        }


                    }

            }

        }
    }

    class Node{
        int x;
        int y;

        int key;

        public Node(int x, int y){
            this.x = x;
            this.y = y;


        }



    }
}
/*
3
5 17
*****************
.............**$*
*B*A*P*C**X*Y*.X.
*y*x*a*p**$*$**$*
*****************
cz
5 11
*.*********
*...*...*x*
*X*.*.*.*.*
*$*...*...*
***********
0
7 7
*ABCDE*
X.....F
W.$$$.G
V.$$$.H
U.$$$.J
T.....K
*SQPML*
irony
 */

