import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.*;

public class 가희와은행 {
    public static void main(String[] args) throws Exception{
        new 가희와은행().solution();
    }


    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        PriorityQueue<Node> pq = new PriorityQueue<>(); // 은행 오픈 후 들어오는 사람의 정보
        Deque<Node> preWait = new ArrayDeque<>(); // 은행에서 실제 대기줄

        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());


        for(int i = 0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int id = Integer.parseInt(st.nextToken()); // 고객
            int wasteTime = Integer.parseInt(st.nextToken()); // 필요 시간
            preWait.offer(new Node(id, wasteTime, 0)); // 영업 시간 전에 들어온 손님
        }

        int M = Integer.parseInt(br.readLine());

        for(int i = 0 ; i< M; i++){
            st = new StringTokenizer(br.readLine());
            int id = Integer.parseInt(st.nextToken());
            int wasteTime =Integer.parseInt(st.nextToken());
            int enter = Integer.parseInt(st.nextToken());
            pq.offer(new Node(id, wasteTime, enter)); // 영업 시간 이후 들어온 손님
        }


        StringBuilder sb  = new StringBuilder();
        int time = 0;


        int job = 0;

        while(time < W){ // 1초씩 증가하면서 조건에 따라 큐에 넣거나 꺼내거나 작업을 한다.
            Node cur = preWait.peek(); // 기다리는 줄에서 먼저 온 한 명을 확인한다.


            // 은행에 들어오는 사람 관리
            if(!pq.isEmpty() && pq.peek().enter == time){ // 만약 지금 타임이 입장한 사람이 있으면 실제 줄에 옮겨준다.
                preWait.offer(pq.poll());
            }


            // 사람 교체 작업
            if(cur.wasteTime - job == 0){ //지금 사람이 할 일이 끝났다면
                preWait.poll(); // 대기 줄에서 꺼낸다.
                job = 0; // 작업 초기화
            }else if(job >= T){ // 작업이 끝나지 않았지만 남아있다면
                preWait.poll(); // 대기 줄에서 꺼낸다.
                preWait.add(new Node(cur.id, cur.wasteTime - job, 0)); // 맨 뒤로 보낸다.
                job = 0; // 작업 초기화
            }

            //실제 일하는 부분
            sb.append(preWait.peek().id).append("\n"); // 현재 작업하는 사람의 id를 출력
            time++; // 시간 증가
            job++; // 작업 수 증가


        }
        System.out.println(sb);

    }

    public class Node implements Comparable<Node>{
        int id;
        int wasteTime;
        int enter;

        public Node(int id, int wasteTime, int enter){
            this.id = id;
            this.wasteTime = wasteTime;
            this.enter = enter;
        }
        @Override
        public int compareTo(Node node){
            return this.enter - node.enter;
        }
    }




}
