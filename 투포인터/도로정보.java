import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class 도로정보 {
    public static void main(String[] args) throws Exception{
        new 도로정보().solution();
    }
    int N;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        String input = br.readLine();
        char[] info = new char[input.length()+1];
        info[0] = ' ';
        for(int i = 1; i < input.length()+1; i++){
            info[i] = input.charAt(i-1);
        }

        Map<Character, Integer> map = new HashMap<>();
        map.put('F', 0);
        map.put('T', 0);
        map.put('G', 0);
        map.put('P', 0);

        char[] st = {'F', 'T', 'G', 'P'};
        // 처음에는 투포인터로 생각함, 하지만 숫자는 범위가 크면 크다 정도의 그런 게 있는데 이것은 모든 경우를 다 보아야 한다.

        int result =0;

        int idx = 0;

        int[][] presum= new int[4][input.length()];
        for(int i = 0; i < input.length(); i++){
            for(int j= 0 ;j < 4; j++){
                if(st[j] == info[i]){
                    presum[j][i] = presum[0][i-1]+1;
                    continue;
                }
                presum[j][i] = presum[0][i-1];
            }
        }



    }
}
