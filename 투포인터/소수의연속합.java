import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class 소수의연속합 {
    public static void main(String[] args) throws Exception {
        new 소수의연속합().solution();
    }

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());


        List<Integer> primes = new ArrayList<>(); // 소수만 저장하는 리스트
        for (int i = 2; i <= N; i++) {
            if (isPrime(i)) { // 소수이면 추가
                primes.add(i);
            }
        }

        primes.add(0); // right 인덱스가 마지막까지 도달해도 left 인덱스를 추가적으로 옮기는 케이스가 발생할 수도 있으므로 반복문 종료 조건을 위해 여유롭게 1개 추가(합산에 영향이 없는 0으로 추가)

        int cnt = 0;
        int sum = 0;
        int left = 0;
        int right = 0;

        while(right < primes.size() && left <= right){
            if(sum >= N){ // 합이 더 크거나 같다면 왼쪽 부분 요소를 뺀다.
                sum -= primes.get(left);
                left++;
                if(sum == N){
                    cnt++;
                }

            }else{ // 합이 작다면 오른쪽 부분을 추가한다.
                sum += primes.get(right);
                right++;
            }


        }

        System.out.println(cnt);
    }

    public boolean isPrime(int num) {
        for (int i = 2; i <= (int) Math.sqrt(num); i++) {
            if (num % i == 0) { // 소수가 아닐 경우 false
                return false;
            }
        }
        return true;
    }
}