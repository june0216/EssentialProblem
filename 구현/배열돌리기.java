/////////////////////////////////////////////////////////////////////////////////////////////
// 기본 제공코드는 임의 수정해도 관계 없습니다. 단, 입출력 포맷 주의
// 아래 표준 입출력 예제 필요시 참고하세요.
// 표준 입력 예제
// int a;
// double b;
// char g;
// String var;
// long AB;
// a = sc.nextInt();                           // int 변수 1개 입력받는 예제
// b = sc.nextDouble();                        // double 변수 1개 입력받는 예제
// g = sc.nextByte();                          // char 변수 1개 입력받는 예제
// var = sc.next();                            // 문자열 1개 입력받는 예제
// AB = sc.nextLong();                         // long 변수 1개 입력받는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
// 표준 출력 예제
// int a = 0;                            
// double b = 1.0;               
// char g = 'b';
// String var = "ABCDEFG";
// long AB = 12345678901234567L;
//System.out.println(a);                       // int 변수 1개 출력하는 예제
//System.out.println(b); 		       						 // double 변수 1개 출력하는 예제
//System.out.println(g);		       						 // char 변수 1개 출력하는 예제
//System.out.println(var);		       				   // 문자열 1개 출력하는 예제
//System.out.println(AB);		       				     // long 변수 1개 출력하는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
import java.util.*;
import java.io.*;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class 배열돌리기
{


    
	public static void main(String args[]) throws Exception
	{
        new 배열돌리기().solution();
	}


    int max;
    int N;
    int[][] map;
    String[][] arr;
    public void solution() throws Exception{
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        
        
		for(int test_case = 1; test_case <= T; test_case++)
		{
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());

            arr = new String[N][N];
         
            map = new int[N][N];
            for(int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            max = 0; 

            //+또는 x 중 하나로 분사된다. 뿌려진 일부가 영역을 벗어나도 상관없다.
            // removeCnt-1개씩 나가고 중간 위치 빼기 

            // 모든 경우의 수 (시작점 )
            // 90도 회전 
            //90도, 180도, 270도 회전한 모양
            int[][] map90 = new int[N][N];
            int[][] map180 = new int[N][N];
            int[][] map270 = new int[N][N];

            spin90(0);
            spin90(1);
            spin90(2);
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < N; i++){
                for(int j = 0; j < 3; j++){
                    sb.append(arr[j][i]).append(" ");
                }
                sb.append("\n");
            }

            System.out.println("#" + test_case);
            System.out.print(sb.toString());

		
			
		}
        
    }

    public void spin90(int iter){
        StringBuilder sb = new StringBuilder();

        int[][] map90= new int[N][N];
        // 스택으로 
        Deque<Integer> stack = new ArrayDeque<>();
        for(int i = 0; i < N; i++){
            sb = new StringBuilder();
            for(int j = 0; j < N; j++){
                stack.offer(map[j][i]);
                
            }

            // stack에서 빼기 
            int cnt = 0;
            while(!stack.isEmpty()){
                int n = stack.pollLast();
                sb.append(n);
                //System.out.println(n);
                map90[i][cnt] = n;
                cnt++;
                
            }

            arr[iter][i] = sb.toString();
            
        }

        map = map90;
        
    }
}
