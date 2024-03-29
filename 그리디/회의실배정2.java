import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 회의실배정2 {
    static class Node implements Comparable<Node>{
        public int start;
        public int end;
        public Node(int start, int end){
            this.start = start;
            this.end = end;
        }
        @Override
        public int compareTo(Node node){
            // 끝나는 시간 순으로 정렬한다.
            if(this.end == node.end){ // 시간이 같으면 시작시간이 빠른 순으로 정렬한다. ->  최대 사용할 수 있는 회의의 최대 개수를 출력이므로 최대로 활용하기 위함
                return this.start - node.start;
            }
            return this.end - node.end;
        }
    }
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int total = Integer.parseInt(br.readLine());
        for(int i = 0 ; i < total; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            pq.offer(new Node(start, end));
        }

        int finish = pq.poll().end;
        int cnt = 1;
        while(!pq.isEmpty()){
            Node node = pq.poll(); // 회의가 빨리 끝나는 회의 순서대로 꺼낸다.
            if(finish <= node.start){ // 이전 회의가 끝난 이후에 시작하는 회의라면 회의가 가능하다 (회의가 끝난 동시에 시작 가능)
                cnt++;
                finish = node.end;
            }

        }
        System.out.println(cnt);
    }
    public static void main(String[] args) throws Exception{
        new 회의실배정2().solution();
    }
}
