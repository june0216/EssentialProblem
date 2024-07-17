import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class LIS_DP_배열요소_여러개 {
    public static void main(String[] args) throws Exception{
        new LIS_DP_배열요소_여러개().solution();
    }

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] num = new int[N];
        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N]; // LIS 길이 저장
        List<Integer>[] paths = new ArrayList[N]; // 각 인덱스에서의 경로 저장

        for (int i = 0; i < N; i++) {
            dp[i] = 1; // 기본 길이
            paths[i] = new ArrayList<>();
            paths[i].add(i); // 자기 자신을 기본 경로로 추가
        }

        // DP로 LIS 길이 및 경로 추적
        int maxLength = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (num[i] > num[j] && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1; // LIS 길이 갱신
                    paths[i] = new ArrayList<>(paths[j]); // 경로 갱신
                    paths[i].add(i);
                } else if (num[i] > num[j] && dp[i] == dp[j] + 1) {
                    // 길이가 같다면 경로 추가
                    List<Integer> newPath = new ArrayList<>(paths[j]);
                    newPath.add(i);
                    paths[i] = newPath; // 중복 허용 시 여러 경로 추가 가능
                }
            }
            maxLength = Math.max(maxLength, dp[i]); // 최대 길이 갱신
        }

        // 최장 길이의 모든 경로 추출
        List<List<Integer>> allPaths = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            if (dp[i] == maxLength) {
                List<Integer> lis = new ArrayList<>();
                for (int idx : paths[i]) {
                    lis.add(num[idx]);
                }
                allPaths.add(lis);
            }
        }

        // 출력
        System.out.println("최장 길이: " + maxLength);
        System.out.println("가능한 LIS:");
        for (List<Integer> lis : allPaths) {
            System.out.println(lis);
        }
    }
}
