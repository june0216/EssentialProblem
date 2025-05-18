import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class 연결요소의개수 {
    public static void main(String[] args) throws Exception{
       new 연결요소의개수().solution();
    }

 
    int[] parent;
    public void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int edgeCnt = Integer.parseInt(st.nextToken());

       parent = new int[N+1];
        for(int i = 0; i < N+1; i++){
            parent[i] = i;
        }

        
        for(int i = 0; i < edgeCnt; i++){
             st = new StringTokenizer(br.readLine());
            int to = Integer.parseInt(st.nextToken());
            int from =Integer.parseInt(st.nextToken());
            union(to, from);

        }

        
        int cnt = 0;
        for(int i = 1; i <= N; i++){
            // parent[i] == i인 것만 세고 있는데, 이 방식은 find()를 한 번도 호출하지 않은 경우 잘못된 루트 값이 남아 있을 수 있음
           if(find(parent[i]) == i) cnt++;
            
        }
        System.out.println(cnt);
    }


    public void union(int to, int from){
        int parent1= find(to);
        int parent2 = find(from);


        if(parent1 != parent2){ 
            
            if(parent1 < parent2){ // 이거로 인해 91퍼까지 올라갈 수 있었음 
                 parent[parent2] = parent[parent1];
            }else{
                 parent[parent1] = parent[parent2];
            }
            
        }

    }


    public int find(int node){
        if(parent[node]== node){
            return node;
        }
        return parent[node] = find(parent[node]);
    }
}
