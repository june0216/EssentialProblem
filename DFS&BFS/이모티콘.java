import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class 이모티콘 {
    public static void main(String[] args) throws Exception {
        new 이모티콘().solution();

    }

    boolean visited[][];
    public void solution() throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        visited = new boolean[1001][1001]; // 화면에 나타난 숫자와 클립보드 상태 방문 표시
        bfs(N); // 최솟값을 구하기 위해 bfs사용

    }

    public void bfs(int N){
        PriorityQueue<Node> que = new PriorityQueue<>();
        que.offer(new Node(0, 1, 0)); // 처음 상태 넣기
        visited[0][1] = true; // 이미 화면에 1개 표시 되어 있음
        while(!que.isEmpty()){
            Node current = que.poll();
            if(current.total == N){ // 화면에 표시된 값과 구하고자 하는 값이 같다면 정답 출력하고 종료
                System.out.println(current.time);
                return;
            }

            // 1번 - 복사하기 참고로, 이때는 화면에 표시되는 건 바뀌지 않으므로 visited 처리 안해도 된다.
            que.offer(new Node(current.total, current.total, current.time+1));

            //2번 - 클립보드에 있는 것들을 붙여넣기
            if(current.clipboard != 0 && (current.total + current.clipboard) <= 1000&&!visited[current.clipboard][current.total+current.clipboard] ){
                //조건 1 = 클립보드에 0이상의 값이 있어야 붙여넣기로 상태를 바꿀 수 있음
                // 조건 2 = visited 배열의 범위를 넘어서지 않는 경우
                //조건 3 = 붙여넣기 한 결과를 한 번 방문한 적 있으면 다시 방문하지 않음
                que.offer(new Node(current.total+ current.clipboard, current.clipboard, current.time+1));
                visited[current.clipboard][current.clipboard+ current.total] = true;
            }

            //3번 - 화면에서 하나 삭제하기
            if(current.total >= 1 && !visited[current.clipboard][current.total-1]){ // 1개를 지워야하기 때문에 현재 화면에 표시된 개수사 1보다 커야한다. 또한, 삭제한 결과를 한 번 방문한 적이 있으면 다시 방문하지 않는다.
                que.offer(new Node(current.total-1, current.clipboard, current.time+1));
                visited[current.clipboard][current.total-1] = true;
            }
        }

    }

    class Node implements Comparable<Node>{
        int total; // 화면에 표시된 개수
        int clipboard; // 클립보드에 저장된 개수
        int time;
        public Node(int total, int clipboard, int time){
            this.total = total;
            this.clipboard = clipboard;
            this.time = time;
        }
        @Override
        public int compareTo(Node node){
            return this.time-node.time; // 최솟값 시간을 구하기 위해 시간을 기준으로 우선순위 큐 설정한다.
        }

    }
}
