import java.util.*;
public class 징검다리 {

    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        Arrays.sort(rocks); // 순서대로 정렬한다.

        int start = 1;
        int end = distance;

        while(start <= end){
            int mid = ((end+start)/2);
            int value = 0;
            int before = 0;
            for(int i = 0 ; i < rocks.length; i++){
                if(rocks[i] - before < mid){ // 만약 차이가 mid보다 작다면 바위 제거
                    value++;
                    continue;
                }
                before = rocks[i]; // 차이가 mid보다 크다면 바위를 제거하지 않는다.
            }
            if(distance - before < mid) value++; // rock의 마지막 값이랑 도착 지점의 차이까지 확인한다.

            if(n < value){ // 제거할 수 있는 바위보다 크다면 값을 줄여야하함
                end = mid-1;

            }else{ // 제거할 수 있는 바위보다 작다면 범위를 조절한다.
                start = mid+1;
                answer = mid;
            }
        }
        return answer;
    }
}
