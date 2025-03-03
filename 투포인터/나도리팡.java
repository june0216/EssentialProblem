import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class 나도리팡 {
	public static void main(String[] args) throws Exception{
		new 나도리팡().solution();
	}

	public void solution() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int bombLimit = Integer.parseInt(st.nextToken());
		int bombCnt = Integer.parseInt(st.nextToken());

		int[] basket = new int[N];
		st = new StringTokenizer(br.readLine());
		int startCnt = 0;
		for(int i = 0; i < N; i++){
			basket[i] = Integer.parseInt(st.nextToken());
			if(basket[i] == 0){
				startCnt++;
			}

		}

		//이거 없으면 시간초과
		if(startCnt == N){
			System.out.println("YES");
			return;
		}

		// 정렬
		Arrays.sort(basket);

		int start = 0;
		if(startCnt >0){
			start =startCnt-1;
		}
		int end = N-1;
		while(start < end && bombCnt > 0){
			//if(basket[start] == 0) continue;
			int diff = bombLimit - basket[end];
			if(diff > basket[start] ){
				basket[end] += basket[start];
				bombCnt-= basket[start];
				basket[start] = 0;
				start++;

			}else{
				basket[end] += diff;
				basket[start] -= diff;
				end--;
				bombCnt -= diff;
				if(basket[start] == 0){
					start++;
				}

			}
		}

		// 다 돌았는데 bombCnt를 넘지 않으면 된다.

		if(start > end && bombCnt>=0 ){
			System.out.println("YES");

		}
		else{
			System.out.println("NO");
		}




	}
}