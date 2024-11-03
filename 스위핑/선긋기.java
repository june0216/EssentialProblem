import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 선긋기 {
    public static void main(String[] args) throws Exception{
        new 선긋기().solution();

    }


    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] pos = new int[N][2];
        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end= Integer.parseInt(st.nextToken());
            pos[i][0] = start;
            pos[i][1] = end;

        }

        Arrays.sort(pos, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] == o2[0]){
                    return o1[1] - o2[1];
                }
                return o1[0] - o2[0];
            }
        });


        int start = pos[0][0];
        int end = pos[0][1];
        int len = end - start;
        for(int i = 1; i < N; i++){
            // 완전 포함일 경우
            if(pos[i][0] >= start && pos[i][1] <= end){
                continue;
            }

            // 끝보다 시작점이 뒤에 있으면 (중간에 빈틈)
            if(pos[i][0] > end){
                len += (pos[i][1] - pos[i][0]); // 사이 값 빼기

            } // 사이에 있다면
            else if(pos[i][0] <= end && end < pos[i][1]){
                len += (pos[i][1] - end);
            }
            start = pos[i][0];
            end = pos[i][1];
        }

        System.out.println(len);
    }

}
