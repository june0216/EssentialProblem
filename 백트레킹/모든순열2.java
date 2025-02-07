import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 모든순열2 {
    public static void main(String[] args ) throws Exception {
        new 모든순열2().solution();
    }

    int N;
    StringBuilder sb;
    int[] num;
    boolean[] visited;
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        num = new int[N];
        visited = new boolean[N];
        perm(0, 0);

    }

    public void perm(int start, int cnt){
      if(cnt == N){
          for(int i = 0; i < N; i++){
              if(visited[i]){
                  sb.append(i+1).append("\n");
              }
          }
      }
      visited[start] = true;
      perm(start+1, cnt+1);
      visited[start] = false;
      perm(start+1, cnt);



    }
}
