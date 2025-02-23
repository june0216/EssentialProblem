import java.util.*;
import java.lang.*;
import java.io.*;

class CD {
	public static void main(String[] args) throws Exception {
		new CD().solution();
	}

	public void solution() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] num1= new int[N];
		int[] num2 = new int[M];
		int res = 0;
		Set<Integer> num1Set = new HashSet<>();
		Set<Integer> num2Set = new HashSet<>();
		for(int i = 0; i < N; i++){
			num1[i] = Integer.parseInt(br.readLine());
			num1Set.add(num1[i]);
		}
		for(int j = 0; j < M;j++){

			num2[j] = Integer.parseInt(br.readLine());
			num2Set.add(num2[j]);


		}

		int n = 0;
		int m = 0;


		while(n < N && m < M){
			if(num1[n] == num2[m]){
				n++;
				m++;
				res++;
			}else if(num1[n] > num2[m]){
				m++;
			}else{
				n++;
			}
		}




		System.out.println(res);



	}
}
