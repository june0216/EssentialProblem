import java.util.*;
public class 광물캐기 {

    public static void main(String[] args) throws Exception{
        new 광물캐기().solution(new int[]{0, 1, 1},new String[]{"diamond", "diamond", "diamond", "diamond", "diamond", "iron", "iron", "iron", "iron", "iron", "diamond"});
    }
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[][] score = new int[][]{{1, 1, 1}, {5, 1, 1}, {25, 5, 1}};
        int avail = 0;
        for(int i = 0; i < minerals.length; i+=5){
            int iron = 0; // 도구 종류별로 다 계산한다. iron으로 캤을 때
            int dia = 0; // 다이아로 캤을 때 계산한다.
            int stone = 0; // 돌로 캤을 때를 계산한다.

            for(int j = i; j < i+5; j++){
                if(j >= minerals.length) break; // 만약 5단위로 안떨어질 경우 총 길이보다 더 반복문을 수행할 수 있기 때문에 종료 조건 추가
                if(minerals[j].startsWith("d")){
                    dia+=score[0][0]; // 다이아를 다이아로 캤을 경우
                    iron+=score[1][0]; // 다이아를 철로 캤을 경우
                    stone+= score[2][0]; // 다이아를 돌로 캤을 경우
                }else if(minerals[j].startsWith("i")){
                    dia +=score[0][1]; // 철을 다이아로 캤을 경우
                    iron+=score[1][1]; // 철을 철로 캤을 경우
                    stone += score[2][1]; // 철을 돌로 캤을 경우
                }else if(minerals[j].startsWith("s")){
                    dia +=score[0][2]; // 돌을 다이아로 캤을 경우
                    iron+=score[1][2]; // 돌을 철로 캤을 경우
                    stone += score[2][2]; //돌을 돌로 캤을 경우
                }

            }
            if(avail >= picks[0] + picks[1] + picks[2]) break; // 만약 도구로 다 커버가 안된다면 고려 대상이 아니며 정렬대상이 안된다.
            pq.offer(new Node(dia, iron, stone));
            avail++;


        }


        int index = 0;

        while(!pq.isEmpty()){
            Node cur = pq.poll();
            if(picks[0] >0){ // 0인덱스, 즉 다이아부터 차례대로 정렬된 그룹에 맞게 도구를 매칭한다.
                answer+= cur.dia;

                picks[0]--;

            }
            else if(picks[1] > 0){ // 그 다음 철로 캘 수 있게 한다.

                answer+= cur.iron;

                picks[1]--;

            }
            else if(picks[2] > 0){ // 마지막으로 남은 것들은 돌로 캐게 한다.
                answer += cur.stone;
                picks[2]--;

            }

        }


        return answer;
    }

    class Node implements Comparable<Node>{

        int dia;
        int iron;
        int stone;
        public Node( int dia, int iron, int stone){

            this.dia = dia;
            this.iron = iron;
            this.stone=stone;
        }

        @Override
        public int compareTo(Node node){
            if(node.stone == this.stone){
                return node.iron - this.iron;
            }
            return node.stone - this.stone;
        }
    }
}
