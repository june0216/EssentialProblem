    import java.io.BufferedReader;
    import java.io.InputStreamReader;
    import java.util.*;

    public class 특정한최단경로 {
        public static void main(String[] args) throws Exception{
            new 특정한최단경로().solution();
        }

        List<Node>[] info;
        int disCnt;
        int cost[];
        int nodeCnt;
        public void solution() throws Exception{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            nodeCnt = Integer.parseInt(st.nextToken());
            disCnt = Integer.parseInt(st.nextToken());

            info = new List[nodeCnt+1];
            for(int i = 0; i <= nodeCnt; i++){
                info[i] = new ArrayList<>();
            }

            for(int i = 0; i < disCnt; i++){
                st = new StringTokenizer(br.readLine());
                int node1 = Integer.parseInt(st.nextToken());
                int node2 = Integer.parseInt(st.nextToken());
                int dis = Integer.parseInt(st.nextToken());
                info[node1].add(new Node(node2, dis));
                info[node2].add(new Node(node1, dis));
            }

            st = new StringTokenizer(br.readLine());
            // 꼭 이 지점을 지나는 최단 경로 -> 간선이 많으므로 O(N^2)는 아니어야 한다.
            int nessaryOne = Integer.parseInt(st.nextToken());
            int nessaryTwo = Integer.parseInt(st.nextToken());

            // 출발 지점 -> 1번부터
            // 다시 또 지나갈 수 있다.
            // 꼭 지나야하는 지점 -> 한 번에 다 가는 것이 아니라 중간에 끊어준다.
            // 1~ nessaryOne까지의 최단 거리
            //  nessaryTwo~n까지의 최단거리


            // 1번째 경우 1 ~ nessaryOne ~ nessaryTwo ~ N

            // 한 번 만 다익스트라해야하나, 아니면 구간으로 나눠서 해야하나 -> 구간에 따라 달라질 수 있음
            int first1 = dijkstra(1, nessaryOne);
            int third1 = dijkstra(nessaryTwo, nodeCnt);
            int second = dijkstra(nessaryOne, nessaryTwo);

            // 2번째 경우 1 ~ nessaryTwo ~ nessaryOne ~ N
            int first2 = dijkstra(1, nessaryTwo);
            // 어차피 무방향이므로 중간은 똑같은 경로임
            int third2 = dijkstra(nessaryOne, nodeCnt);


            // 불가능한 경로가 있는지 확인
            boolean isPossibleOne = true;
            boolean isPossibleTwo = true;
            // 하나라도 불가능한 경로라면
            if (first1 == Integer.MAX_VALUE || second == Integer.MAX_VALUE ||third1 == Integer.MAX_VALUE) {
                isPossibleOne = false;
            }
            // 하나라도 불가능한 경로라면
            if(first2 == Integer.MAX_VALUE || second == Integer.MAX_VALUE ||third2 == Integer.MAX_VALUE){
                isPossibleTwo = false;
            }

            int result = Integer.MAX_VALUE;
            if(isPossibleOne){ // 1번 경로가 있는 경우
                result = first1 + second + third1;
            }if(isPossibleTwo){ // 2번 경로가 있는 경우
                result = Math.min(result,first2 + second + third2 ); // 최솟값 저장
            }else if(!isPossibleOne && !isPossibleTwo){ // 둘다 경로가 없는 경우
                result = -1;
            }
            System.out.println(result);


        }

        public int dijkstra(int start, int end){
            PriorityQueue<Node> pq = new PriorityQueue<>();
            // 최소 비용을 저장할 배열 초기화
            cost = new int[nodeCnt+1];
            Arrays.fill(cost, Integer.MAX_VALUE);
            cost[start] = 0; // 시작점은 비용이 들지 않음


            pq.offer(new Node(start, 0));
            while(!pq.isEmpty()){
                Node cur = pq.poll();

                for(Node next : info[cur.point]){
                    // 최단 거리 발견 시, 그리고 어차피 해당 조건을 만족하면 한 번도 방문하지 않았다는 것을 의미
                    if(cost[next.point] > cur.distance + next.distance){
                        cost[next.point] = cur.distance + next.distance;
                        pq.offer(new Node(next.point, cur.distance + next.distance));

                    }
                }
            }
            return cost[end];

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
