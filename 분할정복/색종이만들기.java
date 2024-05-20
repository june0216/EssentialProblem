import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 색종이만들기 {
    public static void main(String[] args) throws Exception{
        new 색종이만들기().solution();
    }

    int total;
    int[][] info;
    int blue;
    int white;
    public void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        total = Integer.parseInt(br.readLine());
        StringTokenizer st;
        info = new int[total][total];
        for(int i = 0 ; i < total; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < total; j++){
                info[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        blue = 0;
        white = 0;

        countColor(total, 0, 0);
        System.out.println(white + "\n" + blue);
        
    }

    // 분할 정복 -> 크기를 줄여나가면서 색 확인

    public void countColor(int size, int startX, int startY){
        int cnt = 0;
        for(int i = startY; i < startY+size; i++){
            for(int j = startX; j < startX + size; j++){
                cnt += info[i][j];
            }
        }
        if(cnt == 0){
            white++;
        }else if(cnt == size * size){
            blue++;
        }else{
            int nextSize = size/2;
            if(nextSize> 0){
                countColor(nextSize, startX, startY);
                countColor(nextSize, startX+nextSize, startY);
                countColor(nextSize, startX, startY+nextSize);
                countColor(nextSize, startX+nextSize, startY+nextSize);

            }

        }
    }
}
