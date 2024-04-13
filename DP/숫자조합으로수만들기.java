import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 숫자조합으로수만들기 {

	public static void main(String[] args) throws Exception{
		new 숫자조합으로수만들기().solution();
	}
	int N;
	int count;
	int[] res;
	static final int[] num = new int[]{1, 2, 3};
	public void solution() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for(int i = 0; i < N; i++){
			int num = Integer.parseInt(br.readLine());
			count = 0;
			for(int j = 1 ; i <= num; j++){
				res = new int[i];
				getPermutation(0);
			}
			sb.append(count).append("\n");
		}
		System.out.println(sb);

	}

	public void getPermutation(int index){
		if(index == res.length){

			int sum = 0;
			for(int i = 0 ;i < res.length; i++){
				sum+= res[i];
			}
			if(sum == N){ // 합이 일치한다면
				count++;
			}
			return;
		}

		for(int i = 0 ; i < num.length; i++){ // 1, 2, 3을 순서대로 넣는다.
			res[index] = num[i];
			getPermutation(index +1);
		}

	}


}
