import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws Exception{
        new Main().solution();
    }

    /*
    틀린 이유
    A[i][j] = B[i][j] - A[i-X][j-Y] 입니다.
    즉, A의 현재 값을 구하려면 이전에 이미 복원된 A의 다른 값이 필요
    복원한 값을 어딘가에 저장해서 그거에서 빼야했지만 복원된 값을 어디다 저장하지 않고 계산하려고 해서 틀림 
    */

    public void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int A[][] = new int[H][W];

        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());

        int B[][] = new int[X][Y];

        int result[][]  = new int[X+H][Y+W];
        
        for(int i = 0; i < X+H; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < Y+W; j++){
                result[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        StringBuilder sb = new StringBuilder();
                
        for(int i = 0; i < H; i++){
            
            for(int j = 0; j < W; j++){
                
                
            

                if(j >= Y && i >= X  ){
                    // 겹친다
                    A[i][j] = result[i][j] - A[i-X][j-Y];
                     sb.append(A[i][j]).append(" ");
                    continue;
                    
                    
                }
                A[i][j] = result[i][j];
                sb.append(A[i][j]).append(" ");
            }
            sb.append("\n");
        }

       System.out.println(sb.toString());
        
    }
}
