import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 스카이라인 {
    public static void main(String[] args) throws Exception{
        //long startTime = System.currentTimeMillis();
        new 스카이라인().solution();
        //long endTime = System.currentTimeMillis();
        //System.out.println(endTime - startTime);
    }

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        List<Node> position = new ArrayList<>();
       int maxEnd = 0;
        while(num-- > 0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int height = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            maxEnd = Math.max(maxEnd, end);
            position.add(new Node(start, end, height));
        }

        int[] h = new int[maxEnd+1];

        PriorityQueue<Integer> que = new PriorityQueue<>();
        List<Node> result = new ArrayList<>();
        int idx = 0;
        for(Node cur : position){
            // 포개진 경우
            if(result.size() == 0){
                result.add(cur);
                continue;
            }
            if(cur.start <= result.get(idx).start && result.get(idx).end > cur.end)
            {
                continue;
            }
            // 튀어 나온 경우
            if(cur.start < result.get(idx).start && result.get(idx).end > cur.end){
                // 튀어나왔는데 더 큰 경우
                if(result.get(idx).height >= cur.height){
                    result.add(result.get(idx));
                    continue;
                }else{
                    // 튀어나왔는데 큰 경우
                    result.add(new Node(cur.end, result.get(idx).end, result.get(idx).height));
                    continue;
                }
            }
            result.add(cur);
            idx++;
        }
        int pre = 1;
        StringBuilder sb = new StringBuilder();
        for(Node node : result){
            if(pre != node.start){
                sb.append(node.height).append(" ");
            }
            pre = node.height;
        }
        System.out.println(sb);
    }

    class Node implements Comparable<Node>{
        int start;
        int end;
        int height;
        int width;
        Node(int start, int end, int height){
            this.start = start;
            this.end = end;
            this.height = height;
            this.width = end-start;
        }

        @Override
        public int compareTo(Node o) {
            if(this.start == o.start) return this.height -o.height;
            return this.start - o.start;
        }
    }
}
