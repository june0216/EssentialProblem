import java.util.*;
public class 디펜스게임2 {
    public int solution(int n, int k, int[] enemy) {
        int result = enemy.length;
        if(enemy.length <= k) return enemy.length;


        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        int total = n;
        int passCnt = k;
        for(int i = 0; i < enemy.length; i++){
            total-= enemy[i];
            pq.offer(enemy[i]);
            if(passCnt > 0){
                if(total < 0){ // 패스권을 써야하는 상황일 때
                    if(!pq.isEmpty()){
                        total += pq.poll();
                        passCnt--;

                    }
                }

            }else{ // 패스권이 남아 있지 않다면
                if(total < 0){ // 마이너스 값이라면 최근에 카운트 해놓은 것보다 1작은 값이 정답
                    return i;
                }else if(total == 0){
                    return i+1;
                }

            }


        }
        return result;
    }
}
