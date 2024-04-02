import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 경사로 {
    public static void main(String[] args) throws Exception{
        new 경사로().solution();
    }

    int slopeLen;
    int N;
    int[][] table;
    int[][] slope;
    int[][] route;

    boolean[] isSlope;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        slopeLen = Integer.parseInt(st.nextToken());
        table = new int[N][N];
        route = new int[2][N];
        slope = new int[N][N];
        for(int i = 0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());
            boolean flag = true;
            for(int j = 0; j < N; j++){
                table[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        int count = 0;

        for(int i =0; i < N; i++){
            if(calCol(i)) count++;
            if(calRow(i)) count++;
        }
        System.out.println(count);


    }

    public boolean calCol(int index){
        isSlope = new boolean[N];
        boolean allSame = true;
        for(int i = 0; i < N-1; i++){
            int current = table[i][index];
            int next = table[i+1][index];
            if(Math.abs(current-next) >1){
                return false;
            }
            if(current - next == 1){
                // 다음 칸이 더 작은 경우 -> 다음 칸 부터 앞으로 가면서
                for(int j = i + 1; j <= i + slopeLen; j++){
                    if(j >= N) return false; // 범위에 벗어나는 경우
                    if(table[j][index] != next || isSlope[j]) return false; // 칸의 높이가 같지 않거나 경사로가 있는 경우
                    isSlope[j] = true;
                }
                i += slopeLen - 1; // 경사로 길이만큼 건너뛰기
            }else if(current - next == -1) {
                // 다음 칸이 더 큰 경우 -> 현재 칸 부터 뒤로 가면서
                for(int j = i; j > i-slopeLen;j--){ // 뒤로 가기
                    if(j <0 ) return false;
                    if(table[j][index] != current || isSlope[j]) return false;
                    isSlope[j] = true;
                }
            }
        }
        return true;
    }

    public boolean calRow(int index){
        isSlope = new boolean[N];

        for(int i = 0; i < N-1; i++){
            int current = table[index][i];
            int next = table[index][i+1];
            if(Math.abs(current-next) >1){
                return false;
            }
            if(current - next == 1){
                // 다음 칸이 더 작은 경우 -> 다음 칸부터 앞으로 가면서 len만큼
                for(int j = i + 1; j <= i + slopeLen; j++){
                    if(j >= N) return false; // 범위에 벗어나는 경우
                    if(table[index][j] != next || isSlope[j]) return false;
                    isSlope[j] = true;
                }
                i += slopeLen - 1; // 경사로 길이만큼 건너뛰기
            }else if(current - next == -1) {
                // 다음 칸이 더 큰 경우 -> 현재 칸 포함 뒤로 가면서  len만큼
                for(int j = i; j > i-slopeLen;j--){ // 뒤로 가기
                    if(j <0 ) return false;
                    if(table[index][j] != current || isSlope[j]) return false;
                    isSlope[j] = true;
                }
            }
        }
        return true;
    }
}
