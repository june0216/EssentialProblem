import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 시간이겹칠까 {
    public static void main(String[] args) throws Exception{
        new 시간이겹칠까().solution();

    }


    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st;

        int time[] = new int[1000002]; // 최고 시간이 1000000이고 인덱스가 0부터 시작한다 + 여기에 범위를 1 더 추가한 이유는 끝 범위를 end+1값으로 저장해야하기 때문이다.
        for(int i = 0 ;i < N; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            time[start]++;

            time[end+1]--; // 종료 시간도 자리가 차있다고 해야하므로 범위를 1개 늘려서 저장하기
        }


        // 스위핑
        for(int i = 1;i < 1000002; i++){
            time[i] = time[i] + time[i-1];

        }

        int queyrNum = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i< queyrNum; i++){
            int num = Integer.parseInt(st.nextToken());
            sb.append(time[num]).append("\n");

        }

        System.out.println(sb);




    }

}

