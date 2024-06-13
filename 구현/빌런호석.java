import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 빌런호석 {
    public static void main(String[] args) throws Exception{
        new 빌런호석().solution();
    }

    static String ZERO = "1111110";
    static String ONE = "0110000";
    static String TWO = "1101101";
    static String THREE = "1111001";
    static String FOUR = "0110011";
    static String FIVE = "1011011";
    static String SIX = "1011111";
    static String SEVEN ="1110000";
    static String EIGHT = "1111111";
    static String NINE = "1111011";

    String[] digit = new String[]{ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE};


    int N;
    int K;
    int P;
    int X;
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 층수
        K = Integer.parseInt(st.nextToken()); // 자리 수
        P = Integer.parseInt(st.nextToken()); // 최대 반전의 수
        X = Integer.parseInt(st.nextToken()); // 현재 층


        // 모든 숫자 조합에 대해서 몇번 바뀌어야하는지 미리 계산하여 배열에 저장
        int[][] change = new int[10][10];
        for (int j = 0; j < 10; j++) {
            for (int c = 0; c < 10; c++) {
                if(j == c) continue;
                String cur = digit[j];
                String com = digit[c];
                int cnt = 0;
                for (int r = 0; r < 7; r++) {
                    if (cur.charAt(r) != com.charAt(r)) {
                        cnt++;
                    }
                }
                change[j][c] = cnt;
            }
        }
        String num = String.valueOf(X);
        if(num.length()< K){ // 최대 자리 수 보다 작으면 앞에 0을 붙인다.
            num = padding(num);
        }

        char[] current = String.valueOf(num).toCharArray();
        int result = 0;
        for(int i = 1 ; i <= N; i++){ // 0층~ 최대 층까지 반복하면서 변환 개수를 카운트 한다.
            int cnt = 0;
            if(i == X) continue; // 같은 값이면 패스
            String floor = String.valueOf(i);
            if(floor.length() < K){ // 최대 자리 수 보다 작으면 앞에 0을 붙인다.
                floor = padding(floor);
            }
            char[] charFloor = floor.toCharArray();

            for(int j = 0; j < charFloor.length; j++){

                int chan = change[current[j] - '0'][charFloor[j]  - '0']; // 해당 조합은 몇번 변경되는지 배열에서 가져옴


                cnt+=chan;

            }
            if(cnt <= P && cnt >= 1){ // 최소 1개 ~ 최대 P개 변경하면 조건에 만족하므로 결과에 반영
                result++;

            }

        }
        System.out.println(result);

    }

    public String padding(String num){ // 최대 자리 수보다 작은 경우 앞에 0을 만들어주는 함수
        StringBuilder zero = new StringBuilder(); // String이 아닌 StringBuilder를 사용하여 변경에 대해 메모리 절약
        for(int i = 0; i < K-num.length(); i++){
            zero.append("0");
        }
        zero.append(num);

        return zero.toString();
    }
}
