import java.util.*;
public class 경주로건설 {

    public static void main(String[] args) throws Exception{
        new 경주로건설().solution(new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}});
    }
    int[] dx= new int[]{0, 0, -1, 1};
    int[] dy = new int[]{-1, 1, 0, 0};
    public int solution(int[][] board) {
        //장애물도 피해야함
        // 최소비용으로 완주하기
        int answer = 0;
        int total = board[0].length;
        int[][][] visited = new int[total][total][4]; // 방향 정보가 포함된 좌표마다 최소의 비용을 저장
        for(int i = 0; i < total; i++){
            for(int j = 0 ; j < total; j++){
                Arrays.fill(visited[i][j], Integer.MAX_VALUE);
            }

        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        if(board[1][0] == 0){ // 장애물이 없다면
            pq.offer(new Node(0, 1, 100, 1)); // 처음에 직선으로 이동
        }
        if(board[0][1] == 0){
            pq.offer(new Node(1, 0, 100, 3)); // 오른쪽으로 이동
        }


        while(!pq.isEmpty()){
            Node cur = pq.poll();
            if(cur.x == total-1 && cur.y == total-1){ // 도착지점에 도착하면 반환
                return answer; // que에서 cost가 낮은 순으로 저장되어 있기 때문에 도착점에 먼저 도착한 케이스가 가장 최솟값이므로 바로 반환한다.
            }
            for(int i = 0; i < 4; i++){
                int nx = dx[i] + cur.x;
                int ny = dy[i] + cur.y;

                if(nx >= 0 && ny >= 0 && nx < total && ny < total){
                    if(board[ny][nx] != 1){ // 장애물 없으면

                        if(cur.dir == i){ // 같은 방향으로 이동할 경우
                            if(visited[ny][nx][i]>= cur.cost+100){ // 최초 방문이거나 이전의 값보다 작은 경우 큐에 넣음
                                pq.offer(new Node(nx, ny, cur.cost+100, i));
                                visited[ny][nx][i] = cur.cost+100;
                            }

                        }else{ // 다른 방향일 경우
                            if(visited[ny][nx][i]>= cur.cost+600){
                                pq.offer(new Node(nx, ny, cur.cost+600, i));
                                visited[ny][nx][i] = cur.cost+600;
                            }
                        }

                    }
                }
            }
        }
        return answer;
    }

    class Node implements Comparable<Node>{
        int cost;
        int x;
        int y;
        int dir;
        public Node(int x, int y, int cost, int dir){
            this.x = x;
            this.y = y;
            this.cost = cost;
            this.dir = dir;
        }

        public int compareTo(Node node){
            return this.cost - node.cost;
        }
    }
}
