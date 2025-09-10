import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class 키순회 {
    public static void main(String[] args) throws Exception{
        new 키순회().solution();
    }

    List<Integer>[] smaller;
    List<Integer>[] taller;
    Set<Integer> result;
    int s;
    int t;
    int cnt;
    int N;
    boolean[] visited;
    public void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
		T=Integer.parseInt(br.readLine());
		/*
		   여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		*/

		for(int test_case = 1; test_case <= T; test_case++)
		{
		
			/////////////////////////////////////////////////////////////////////////////////////////////
			/*
				 이 부분에 여러분의 알고리즘 구현이 들어갑니다.
			 */
			/////////////////////////////////////////////////////////////////////////////////////////////
            int total = Integer.parseInt(br.readLine());
            int cnt = Integer.parseInt(br.readLine());
            
            result = new HashSet<>();
            smaller = new List[total+1];
            taller = new List[total+1];
            N = total;
            for(int i = 0; i < total+1; i++){
                smaller[i] = new ArrayList<>();
                taller[i] = new ArrayList<>();
            }
            StringTokenizer st;
            for(int i = 0; i < cnt; i++){
                st = new StringTokenizer(br.readLine());
                int small  = Integer.parseInt(st.nextToken());
                int tall= Integer.parseInt(st.nextToken());
                smaller[tall].add(small);
                taller[small].add(tall);
                //System.out.println(small);
                
            }
            int res = 0;
            for(int i = 1; i < total+1; i++){
                visited = new boolean[total+1];
                s = dfs(i, smaller, i);
                visited = new boolean[total+1];
                t = dfs(i, taller, i);
                
                
                if(s + t-1 == total){
                    res++;
                }
                
            }

            System.out.println("#" + test_case + " " + res);

            

            

            
		}
    }

    public int dfs(int start, List<Integer>[] arr, int num){
        int count = 1;
        visited[num] = true;
        for(int next : arr[num]){
            if(visited[next]) continue;
            count += dfs(start,  arr, next);
            //System.out.println(next);
            
        }
        return count;
    }

    
}
