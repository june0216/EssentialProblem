import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 나무자르기2 {


	public static void main(String[] args) throws Exception{
		new 나무자르기2().solution();
	}

	long[] tree;
	long max;

	public void solution() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		tree= new long[N];
		max = 0;
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++){
			tree[i] = Long.parseLong(st.nextToken());
			max = Math.max(max, tree[i]);
		}

		 long start = 0;
		long end = max;
		long result = 0;
		long answer = 0;
		while(start < end ){
			result = 0;
			long mid = (end - start) / 2 + start;
			for(int i = 0; i < N; i++){
				if(tree[i] > mid){
					result += tree[i] - mid;
				}
			}
			//System.out.println(mid);
			if(result >= M) {
				start = mid + 1;
				answer= mid;
			}else {
				end = mid;
			}
		}

		System.out.println(answer);


	}

}
