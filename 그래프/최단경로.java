import java.util.*;
import java.lang.*;
import java.io.*;

class 최단경로 {
    public static void main(String[] args) throws Exception{
        new 최단경로().solution();
    }

    public void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int cityNum = Integer.parseInt(st.nextToken()); // 도시의 수
        int roadCnt = Integer.parseInt(st.nextToken()); // 도로의 수
        int targetShortDis = Integer.parseInt(st.nextToken()); // 출력할 최단 거리
        int start = Integer.parseInt(st.nextToken()); // 시작점


        List<Integer>[] graph = new List[cityNum+1];
        for(int i = 1; i <= cityNum; i++){
            graph[i] = new ArrayList<>();
        }


        // 단방향 정보 저장
        for(int i = 0; i < roadCnt; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to  = Integer.parseInt(st.nextToken());
            graph[from].add(to);

        }



        int cost[] = new int[cityNum+1]; // 각 노드 별 최솟값 저장

        Deque<int []> queue = new ArrayDeque<>();
        queue.offer(new int[]{start, 0}); // 시작점 넣기
        boolean visited[] = new boolean[cityNum+1]; // 방문 여부
        visited[start] = true;

        // 최단 경로 찾기
        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            for(int next : graph[cur[0]]){ // 현재 연결된 노드들을 다 탐색
                if(!visited[next]){ // 방문하지 않았다면
                    visited[next] = true;
                    cost[next] = cur[1] +1; // 가중치가 없으므로 무조건 처음 만나는 노드 번호가 최솟값임
                    queue.offer(new int[]{next, cost[next]});
                }
            }
        }


        StringBuilder sb = new StringBuilder();
        List<Integer> result = new ArrayList<>();
        for(int i= 1; i <= cityNum; i++){ // 조건 만족하는 노드 번호를 오름차순으로 출력
            if(cost[i] == targetShortDis){
                sb.append(i + "\n");
            }
        }
        if(sb.length() ==0){ // 없으면 -1 처리
            sb.append(-1);
        }
        System.out.println(sb);




    }


}
