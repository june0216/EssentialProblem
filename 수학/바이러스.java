import java.io.*;
import java.util.*;

public class 바이러스 {

    public static void main(String[] args) throws Exception{
        new 바이러스().solution();
    }

    int K;
    int P;
    int N;

    // 기하급수적으로 증가 -> dp의 냄새 or 재귀 라고 생각했지만 정말 간단한 수학공식으로 풀렸다.

    long res;
    public void solution () throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        res = K;
        for(int i = 0; i < N; i++){
            res = (res*P) % 1000000007;
        }
        System.out.println(res);

    }

}

