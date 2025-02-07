import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 부분수열 {
    public static void main(String[] args ) throws Exception {
        new 부분수열().solution();
    }

    int N;
    StringBuilder sb;
    int[] num;
    boolean[] visited;
    List<Integer> selected;
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        sb = new StringBuilder();
        num = new int[N];
        visited = new boolean[N];
        selected = new ArrayList<>();
        perm(0);
        String[] str = sb.toString().split("\n");
        Arrays.sort(str);
        StringBuilder sb1 = new StringBuilder();
        for(String s : str){
            sb1.append(s).append("\n");
        }

        System.out.println(sb1);

    }

    public void perm(int start){
      if(start == N){
          if(selected.size() == 0){
              return;
          }
          for(int n : selected){
              if(n == 0) continue;
              sb.append(n).append(" ");


          }

          sb.append("\n");
          return;
      }
        selected.add(start+1);
        perm(start+1);
        selected.remove(selected.size()-1);
        perm(start+1);



    }
}
