import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 회의실배정 {
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int total = Integer.parseInt(br.readLine());
        int[][] meetings = new int[total][2];
        for(int i = 0 ; i < total; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            meetings[i][0] = start;
            meetings[i][1] = end;

        }
        Arrays.sort(meetings, (s1, s2) -> {
            if(s1[1] == s2[1]){
                return s1[0] - s2[0]; // 시작 시간으로 정렬하기
            }
            return s1[1] -s2[1]; // 끝나는 시간으로 정렬
        });


        int cnt = 1;
        int finish = meetings[0][1];
        for(int i = 1 ; i < total; i++){
            if(meetings[i][0] >= finish){ // 이전 끝나는 시간보다 회의 시작 시간이 크거나 같으면 체크한다.
                cnt++;
                finish = meetings[i][1];
            }
        }

        System.out.println(cnt);
    }
    public static void main(String[] args) throws Exception{
        new 회의실배정().solution();
    }
}
