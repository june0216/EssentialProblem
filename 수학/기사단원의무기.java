import java.util.*;
public class 기사단원의무기 {
    public int solution(int number, int limit, int power) {
        //번호에 따라 -> 공격력 결정
        int result = 0;
        for(int i = 1; i <= number; i++){
            int calResult = cal(i);
            if(calResult > limit){ // 제한보다 크다면 power값으로 대체
                result+=power;
                continue;
            }
            result+=calResult;

        }
        return result;

    }
    // 약수의 개수를 구하는 함수
    public int cal(int num){
        if(num == 1) return 1;
        if(num == 2) return 2;
        int result = 0;
        int target = 1;
        while(target*target<= num){ // 절반까지 약수를 구한다. (나머지는 2개씩 짝이 있으니까 직접 안구해도 된다.)
            if(num%target == 0){
                if(target * target == num){ // 짝이 같은 숫자가면 1번만 카운트
                    result++;
                    break;
                }
                result+=2;
            }
            target++;
        }
        return result;
    }
}
