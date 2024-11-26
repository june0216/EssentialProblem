import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.*;
import java.util.StringTokenizer;

public class 사이클게임 {
    public static void main(String[] args) throws Exception{
        new 사이클게임().solution();
    }

    int[] parent;
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int dotCnt = Integer.parseInt(st.nextToken());
        int gameCnt =Integer.parseInt(st.nextToken());

        int res = -1;
        parent = new int[dotCnt];
        for(int i = 0; i < dotCnt; i++){
            parent[i] = i;
        }
        for(int i = 1; i <= gameCnt; i++){
            st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());

            if(!union(first, second)){  // 부모가 같은 숫자 2개가 입력되면 사이클이 형성된다.
                res = i;
                break;
            }

        }
        System.out.println(res == -1 ? 0 : res);


    }
    public int find(int start){
        if(parent[start] == start){
            return start;
        }
        return parent[start] = find(parent[start]);
    }

    public boolean union(int s1, int s2){
        int parent1 = find(s1);
        int parent2 = find(s2);
        // 합치면 true
        if(parent2 != parent1){
            parent[parent1] = parent2;
            return true;
        }
        // 이미 부모가 같으면 합치지 않았으므로 false
        return false;
    }
}
