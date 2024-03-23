import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.StringTokenizer;

public class 숨바꼭질 {

    static final int MAX = 100000;
    int[] road = new int[MAX];
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int from = Integer.parseInt(st.nextToken());
        int to = Integer.parseInt(st.nextToken());
        System.out.println(bfs(from, to));
    }

    public int bfs(int from, int to){
        Deque<Node> que = new ArrayDeque<>();
        boolean[] visited = new boolean[MAX + 1]; // 방문 여부 배열
        que.offer(new Node(from, 0));
        visited[from] = true; // 시작 지점 방문 처리

        while(!que.isEmpty()){
            Node current = que.poll();
            if(current.num == to){
                return current.cnt;
            }

            if(current.num + 1 <= MAX && !visited[current.num + 1]){ // +1 방향으로 이동 가능한 경우
                que.offer(new Node(current.num + 1, current.cnt + 1));
                visited[current.num + 1] = true;
            }

            if(current.num - 1 >= 0 && !visited[current.num - 1]){ // -1 방향으로 이동 가능한 경우
                que.offer(new Node(current.num - 1, current.cnt + 1));
                visited[current.num - 1] = true;
            }

            if(current.num * 2 <= MAX && !visited[current.num * 2]){ // 2배로 이동 가능한 경우
                que.offer(new Node(current.num * 2, current.cnt + 1));
                visited[current.num * 2] = true;
            }
        }

        return -1; // 도달할 수 없을 때
    }

    public static void main(String[] args) throws Exception{
        new 숨바꼭질().solution();
    }

    class Node{
        int num;
        int cnt;

        public Node(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }
}
