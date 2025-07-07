import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class 색종이3 {
    public static void main(String[] args) throws Exception{
        new 색종이3().solution();
    }

    int N;
    public void solution() throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int map[][] = new int[101][101];
        int arr[][] = new int[101][101];
        int[][] sum = new int[101][101];
        for(int c = 0; c < N; c++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            // 10크기 만큼 칠하기 
            for(int i = y; i < y+10; i++){
                for(int j = x; j < x+10; j++){
                    map[i][j] = 1;
                }
            }

            
        }


        // 배열을 다 돌리면서
                    /*
             sum[i][j] = arr[i][j]
            + sum[i - 1][j]
            + sum[i][j - 1]
            - sum[i - 1][j - 1];
            */
        int[][] presum = new int[101][101];
        
        for(int i = 1; i < 101; i++){
            for(int j = 1; j < 101; j++){
                // 바뀌면 바꾸기 
                sum[i][j] = (map[i][j] 
                                + sum[i-1][j] + sum[i][j-1] - sum[i-1][j-1]);
                
            }
        }


        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 101; i++){
            for(int j = 0; j < 101; j++){
                sb.append(sum[i][j]).append(" ");
            }
        }
       // System.out.println(sb.toString());
        

        /*
        직사각형 구하기 (2차원 누적합으로 해결 가능 )
        */


        int max = 0;
        // 시작점 
        for(int i = 1; i < 101; i++){
            for(int j = 1; j < 101; j++){


                // 사각형 

                 /*
             sum[i][j] = arr[i][j]
            + sum[i - 1][j]
            + sum[i][j - 1]
            - sum[i - 1][j - 1];
            */
                for(int r = i; r < 101; r++){
                    for(int c = j ; c < 101; c++){
                        int count = (r-i+1)*(c-j+1);
                        /*
                        int total = sum[r2][c2]
                          - sum[r1 - 1][c2]
                          - sum[r2][c1 - 1]
                          + sum[r1 - 1][c1 - 1];
                        */
                        // 이 공식을 생각해내기가 어려웠다
                        int total = sum[r][c] - sum[i-1][c] - sum[r][j-1] + sum[i-1][j-1];

                        

                        if(total == count){
                            max = Math.max(total, max);
                        }
                        
                        
                    }
                }
            }
        }

        System.out.println(max);


        
        
    }
}
