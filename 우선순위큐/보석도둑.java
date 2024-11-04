import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 보석도둑 {
    public static void main(String[] args) throws Exception{
        new 보석도둑().solution();
    }

    int total;
    int[][] dp;
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int jewelCnt = Integer.parseInt(st.nextToken());
        int bagCnt = Integer.parseInt(st.nextToken());
        List<int[]> jewel = new ArrayList<>();
        while(jewelCnt-- >0){
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            jewel.add(new int[]{weight, cost});
        }
        Collections.sort(jewel, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) { // 무게가 같은 경우
                    return o2[1] - o1[1]; // 가격 내림차순
                }
                return o1[0] - o2[0]; // 무게 오름차순
            }
        });
        List<Integer> bagList = new ArrayList<>();
        while(bagCnt-->0){
            bagList.add(Integer.parseInt(br.readLine()));
        }
        // 무게가 작은 순서대로 -> 큰 거는 다 들어갈 수 있지만 작은 순서대로 하면 거기에 들어갈 수 있는 범위를 제한하여 탐색 가능
        Collections.sort(bagList);

        PriorityQueue<Integer> possibleJewel = new PriorityQueue<>(Collections.reverseOrder());
        int idx = 0;
        long answer =0;
        for(int bag : bagList){ // 가방 하나씩 살펴보면서 가능한 주얼리들을 살펴본다.

            // 현재 가방에 들어갈 수 있는 주얼리들을 큐에 넣는다. -> 현재 가방 이후에 나올 가방들을 이것보다 크기 때문에 다 들어갈 수 있는 주얼리이므로 큐에 넣는다.
            while(idx < jewel.size() && bag >= jewel.get(idx)[0]){
                possibleJewel.offer(jewel.get(idx)[1]);
                idx++;
            }

            if(!possibleJewel.isEmpty()){
                answer += possibleJewel.poll(); // 가장 큰 가격의 주얼리 꺼냄
            }


        }
        System.out.println(answer);

    }
    class Node implements Comparable<Node>{
        int cost;
        int weight;

        Node(int weight, int cost){
            this.weight = weight;
            this.cost = cost;
        }
        @Override
        public int compareTo(Node node){
            if(this.weight == node.weight) return node.cost - this.cost;
            return this.weight - node.weight;
        }

    }
}
