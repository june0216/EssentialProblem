import java.util.*;
public class 디펜스게임 {
    public static void main(String[] args) throws Exception{
        new 디펜스게임().solution(7, 3,new int[]{4, 2, 4, 5, 3, 3, 1});
    }
    public int solution(int n, int k, int[] enemy) {
        int answer = enemy.length;
        if(k >= enemy.length){
            return k;
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int card = k;
        int total = n;
        for(int i = 0; i < enemy.length; i++){
            total -= enemy[i];
            pq.offer(enemy[i]);
            if(total <0){ // 만약 주어진 수가 없어져서 라운드를 진행할 수 없다면
                if(card > 0 && !pq.isEmpty()){ // 카드가 있고 pq가 주어져 있다면
                    int max = pq.poll(); // 앞서 싸운 병사 수 중 가장 큰 병사 수를 카드로 대체한다.
                    total += max;
                    card--;
                }
                else {
                    answer = i;
                    break;
                }
            }

        }
        return answer;
    }
}
