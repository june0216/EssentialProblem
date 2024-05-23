import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 쿼드트리 {

    public static void main(String[] args) throws Exception{
        new 쿼드트리().solution();
    }

    int total;
    int[][] info;
    StringBuilder sb;
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        total = Integer.parseInt(br.readLine());
        info = new int[total][total];
        sb=new StringBuilder();
        for(int i = 0 ; i < total; i++){
            String input = br.readLine();
            for(int j = 0 ; j < total; j++){
                info[i][j] = input.charAt(j) - '0';

            }
        }
        count(0, 0, total);
        System.out.println(sb);

    }

    public void count(int x, int y, int size){
        int sum = 0;
        for(int i = y; i < y+size; i++){
            for(int j = x; j < x+size; j++){
                sum += info[i][j];
            }
        }

        if(sum == 0){
            sb.append("0");
        }else if(sum == size * size){
            sb.append("1");
        }else{
            sb.append("(");
            int nextSize = size/2;
            count(x, y, nextSize);
            count(x + nextSize, y, nextSize);
            count(x, y+nextSize, nextSize);
            count(x+nextSize, y+nextSize, nextSize);
            sb.append(")");
        }

    }
}
