import java.util.*;

public class 주식가격 {
    class Info implements Comparable<Info>{
        int idx;
        int price;
        public Info(int idx, int price){
            this.idx = idx;
            this.price = price;
        }

        @Override
        public int compareTo(Info info){
            return info.price - this.price;
        }
    }
    public int[] solution(int[] prices) {

        PriorityQueue<Info> que = new PriorityQueue<>(); // 이전의 가격 중 가장 높은 가격이 먼저 나오도록 한다.

        que.offer(new Info(0, prices[0]));
        for(int time = 1; time < prices.length; time++){
            while(!que.isEmpty()){ // time을 기준으로 이전에 넣었던 가격 중 가격이 떨어진 경우가 있다면 큐에서 꺼내고 유지한 시간을 업데이트 한다.
                Info cur = que.poll();
                if(cur.idx < time && cur.price > prices[time]){ // 가격이 떨어졌다면
                    prices[cur.idx] = time - cur.idx; // 유지한 시간을 확정하기
                }else{
                    que.offer(cur);
                    break;
                }
            }
            que.offer(new Info(time, prices[time])); // 현재 가격 큐에 넣기

        }
        while(!que.isEmpty()){ // 가격이 변동하지 않는 경우 큐에 남아 있으므로 꺼내서 시간 업데이트
            Info cur = que.poll();
            prices[cur.idx] = prices.length - cur.idx -1;
        }
        return prices;
    }

    public static void main(String[] args) throws Exception{
        System.out.println(new 주식가격().solution(new int[]{1, 2, 3, 4}));
    }
}
