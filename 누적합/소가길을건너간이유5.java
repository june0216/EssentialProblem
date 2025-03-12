import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class 소가길을건너간이유5 {
	public static void main(String[] args) throws Exception{
		new 소가길을건너간이유5().solution();
	}


	int N;
	int K;
	int B;
	int[] light;
	public void solution() throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		light = new int[N+2];
		for(int i = 0; i < B; i++){
			int num = Integer.parseInt(br.readLine());
			light[num] = -1;

		}


		//연속한 K개의 신호등이 존재하도록


		int[] presum = new int[N+1];

		presum[1] = (light[1]) + 1;


		for(int i = 2; i < N+1; i++){
			if(light[i] == 0){
				light[i] = 1;
			}else{
				light[i] = 0;
			}
			//System.out.println(light[i]);
			presum[i] = presum[i-1] + light[i];
		}



		for(int i = 2; i < N+1; i++){
			// System.out.println(presum[i]);
		}

		// 1 2 3 4
		// 1 3 6 10
		// 10 - 3 =

		// 4- 1 = 3

		int max = 0;
		int end = K;
		// 인덱스 관리 못해줌
		for(int i = 1; i <= N; i++){
			//System.out.println(presum[end] - presum[i-1]);
			max = Math.max(presum[end] - presum[i-1], max);
			end++;
			if(end == N+1){
				break;
			}

		}

		System.out.println(K - max);
	}
}