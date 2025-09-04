import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class 스타트와링크 {
    public static void main(String[] args) throws Exception{
        new 스타트와링크().solution();
    }

    int total;
    int min;
    int[][] arr;
    int N;
    public void solution() throws Exception{
        //스타트 팀의 능력치와 링크 팀의 능력치의 차이를 최소
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        total = Integer.parseInt(br.readLine());
        min= Integer.MAX_VALUE;
        N = total;
        arr = new int[total][total];
        StringTokenizer st;
        for(int i = 0; i < total; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < total; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[total+1];
        permute(0, 0, new ArrayList<>());
        System.out.println(min);

        // 순열 4개 뽑기 
    }


    // visited를 어떻게 할 것인가? 
    boolean[] visited;
    public void permute(int start, int len, List<Integer> arr){
        if(len == N/2){
            find();
            return; // 이거도 신경쓰기
        }

        for(int i = start; i < total; i++){
            if(visited[i]) continue;
            visited[i] = true;
            arr.add(i);
            permute(i+1, len+1, arr); // 이 부분 때문에 틀렸음 
            visited[i] = false;
            arr.remove(arr.size()-1);
        }
    }


    public void find(){
        int start = 0;
        int link = 0;
        for(int i = 0; i < N-1; i++){
            for(int j = i+1; j < N; j++){
                if(visited[i] && visited[j]){
                    start+= arr[i][j] + arr[j][i];
                }
                else if(!visited[j] && !visited[i]){
                    link+= arr[i][j] + arr[j][i];
                }
            }
        }

        min = Math.min(Math.abs(start - link) , min);
        if(min == 0) {
            System.out.println(0);
            System.exit(0);
        }
        
        
    }
}
