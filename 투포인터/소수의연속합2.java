import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class 소수의연속합2 {
    public static void main(String[] args) throws Exception {
        new 소수의연속합2().solution();
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

        int cnt = 0;
        int sum = 0;
        int left = 0;

        for(int right = 0; right <= primes.size(); right++){ // 소수의 개수만큼 돌면서
            sum += primes.get(right);

            while(sum > N){ // 합이 목표 값보다 클 경우 합이 작거나 같아질 때까지 왼쪽 요소들을 하나씩 뺀다.
                sum -= primes.get(left++);
            }
            if(sum == N) cnt++;
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
