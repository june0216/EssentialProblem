import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class 퇴사2 {
	public static void main(String[] args) throws Exception{
		new 퇴사2().solution();

	}
	int[] dp;
	int N;
	List<Node> nodeList;
	public void solution() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new int[N+1];
		StringTokenizer st;
		nodeList = new ArrayList<>();
		for(int i = 0 ; i < N; i++){
			st = new StringTokenizer(br.readLine());
			int days = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			nodeList.add(new Node(i+1, days, cost));
		}
		update(1);
		int max = 0;
		for(int i = 1 ; i <= N; i++){
			max = Math.max(dp[i], max);
		}
		System.out.println(max);

	}

	public void update(int start){

		for(int i = start; i <= N; i++){

			int finish = i + nodeList.get(i-1).days-1;
			if(finish <= N){
				dp[finish] = Math.max(dp[finish], dp[i-1] + nodeList.get(i-1).cost);
			}
			dp[i] = Math.max(dp[i], dp[i-1]); // 전날과 오늘 중 큰 값으로 업데이트 -> 그 상담으로 인해 얻는 이익을 더한 것과, i일에 상담을 하지 않을 경우 i-1일까지의 최대 이익(dp[i-1]) 중 더 큰 값을 dp[i]로 업데이트
			//각 일자에 대해서, 그 일자에 아무런 상담을 하지 않았을 경우에도 이전 일자까지의 최대 이익을 현재 일자의 최대 이익으로 반영합니다. 이는 상담을 선택하지 않는 날이 있을 수 있기 때문에 필요한 로직입니다.
		}
	}

	public class Node{
		int id;
		int days;
		int cost;
		public Node(int id, int days, int cost){
			this.id = id;
			this.days = days;
			this.cost = cost;
		}
	}
}
