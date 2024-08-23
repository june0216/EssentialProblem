import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 시계 {
	public static void main(String[] args) throws Exception{
		new 시계().solution();
	}

	public void solution() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int i = 0 ;i < testCase; i++){
			st = new StringTokenizer(br.readLine());
			/*
			시간 -> 360/12 -> 30도
			분 -> 360/60 -> 6도
			초 -> 360/60 -> 6도
			 */

			int H = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int S = Integer.parseInt(st.nextToken());
			 // 시침 = (시 / 12 * 360) + ((분 / 60 * 360) / 12) + ((초 / 60 * 360) / 12 / 12 / 5)

			double hourAngle = H * 30.0 + (M * 6.0) / 12 + (S * 6.0) / 60 / 12;
			double minuteAngle = M * 6.0 + (S * 6.0) / 60;
			double secondAngle = S * 6.0;

			double[] sorted = new double[]{hourAngle, minuteAngle, secondAngle};


			// 각각 각도 구하기
			double diff1 = getDiff(hourAngle, minuteAngle);
			double diff2 = getDiff(minuteAngle, secondAngle);
			double diff3 = getDiff(secondAngle, hourAngle);

			double result =Math.min(diff1, Math.min(diff3, diff2));

			System.out.println(Math.round(result * 1000000) / 1000000.0);


		}


	}

	public double getDiff(double n1, double n2){
		double diff = Math.abs(n2 - n1);
		return Math.min(Math.abs(360-diff), diff);
	}
}
