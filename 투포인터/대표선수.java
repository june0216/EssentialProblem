import java.util.*;
import java.io.*;

public class 대표선수 {
    private int M;
    private int N;
    private int[][] classScores;

    public static void main(String[] args) throws Exception {
        new 대표선수().findMinimumGap();
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

        // 우선순위 큐 (최소 힙) - [값, 반 인덱스, 학생 인덱스]
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
            int[] current = pq.poll(); // 최소 점수 추출
            int minScore = current[0]; // 현재 최소 점수
            int classIndex = current[1]; // 반 인덱스
            int studentIndex = current[2]; // 학생 인덱스

            // 현재 범위 최소값과 최대값의 차이 갱신
            minGap = Math.min(minGap, maxScore - minScore);

            // 다음 학생 존재 여부 확인
            if (studentIndex + 1 >= M) {
                break; // 해당 반에서 더 이상 학생이 없으면 종료
            }

            // 다음 학생을 우선순위 큐에 추가
            int nextScore = classScores[classIndex][studentIndex + 1];
            pq.offer(new int[]{nextScore, classIndex, studentIndex + 1});

            // 최대값 업데이트
            maxScore = Math.max(maxScore, nextScore);
        }

        // 최소 격차 출력
        System.out.println(minGap);
    }
}
