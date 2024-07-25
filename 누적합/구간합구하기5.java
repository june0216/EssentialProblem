import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 구간합구하기5 {
    public static void main(String[] args) throws Exception {
        new 구간합구하기5().solution();
    }

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int sumCnt = Integer.parseInt(st.nextToken());

        int[][] map = new int[N+1][N+1];
        int[][] sum = new int[N+1][N+1];

        for(int i = 0; i< N; i++){
            st = new StringTokenizer(br.readLine());
            int total = 0;
            for(int j = 0 ; j < N; j++){
                int num = Integer.parseInt(st.nextToken());
                map[i][j] = num;
                sum[i][j] = map[i][j]+total;
                total = sum[i][j];
            }
        }

        StringBuilder sb = new StringBuilder();

        while(sumCnt-- > 0){
            st = new StringTokenizer(br.readLine());
            int startY = Integer.parseInt(st.nextToken());
            int startX = Integer.parseInt(st.nextToken());
            int endY = Integer.parseInt(st.nextToken());
            int endX = Integer.parseInt(st.nextToken());
            int result = 0;
            for(int i = startY-1; i <= endY-1; i++){
                // 전체
                //System.out.println(startX);
                if(startX == 1){
                    result += sum[i][endX-1];
                }else{
                    result+= (sum[i][endX-1] - sum[i][startX-2]);
                }
            }
            sb.append(result).append("\n");

        }
        System.out.println(sb);





    }
}
