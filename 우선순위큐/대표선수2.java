import java.util.*;
import java.io.*;

public class 대표선수2 {
	private int M;
	private int N;
	private int[][] classScores;

	public static void main(String[] args) throws Exception {
		new 대표선수2().findMinimumGap();
	}

	public void findMinimumGap() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 반의 수
		M = Integer.parseInt(st.nextToken()); // 각 반의 학생 수
		classScores = new int[N][M];

		// 반별 점수 입력
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				classScores[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 각 반의 학생 점수를 정렬
		for (int i = 0; i < N; i++) {
			Arrays.sort(classScores[i]);
		}


		PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
		int maxScore = 0;

		// 초기 최소값 및 최대값 설정
		for (int i = 0; i < N; i++) {
			pq.offer(new int[]{classScores[i][0], i, 0});
			maxScore = Math.max(maxScore, classScores[i][0]);
		}

		int minGap = Integer.MAX_VALUE;

		// 최소 격차 탐색
		while (!pq.isEmpty()) {
			int[] current = pq.poll();
			minGap = Math.min(minGap, maxScore - current[0]);
			if(current[2] + 1 >= M) {
				break;
			}
			pq.offer(new int[]{classScores[current[1]][current[2] + 1], current[1], current[2] + 1});
			maxScore = Math.max(maxScore, classScores[current[1]][current[2] + 1]);


		}

		// 최소 격차 출력
		System.out.println(minGap);
	}
}
