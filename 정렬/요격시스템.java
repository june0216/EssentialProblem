import java.util.*;
public class 요격시스템 {
    public int solution(int[][] targets) {
        int answer = 0;
        //두 개의 int[] 배열을 나타내
        Arrays.sort(targets, (insert, compare) -> insert[1] - compare[1]); // target을 어디에 넣어야 할지

        int before = 0;
        for(int i = 0; i < targets.length; i++){
            if(before <= targets[i][0]){
                answer++;
                before = targets[i][1];
            }

        }

        return answer;
    }
}
