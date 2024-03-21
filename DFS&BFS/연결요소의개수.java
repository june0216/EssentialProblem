import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 연결요소의개수 {
    //유니온 파인드 -> 두 노드를 하나의 집합으로 합치는 연산을 지원하는 알고리즘


    int totalNode;
    int connected;
    int[] parent;
    int[] rank;
    public void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        totalNode = Integer.parseInt(st.nextToken());
        connected = Integer.parseInt(st.nextToken());
        parent = new int[totalNode+1];


        // 먼저 각 노드들은 자기 자신이 부모이다.
        for(int i= 1 ; i < totalNode+1; i++){
            parent[i] = i;
        }

        rank = new int[totalNode+1];
        Arrays.fill(rank, 1);
        while(connected-- >0){
            st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());
            union(num1, num2);
        }

        int groupNum = 0;
        for(int i = 1; i < totalNode+1; i++){
            if(parent[i] == i){
                groupNum++;
            }
        }
        System.out.println(groupNum);

    }

    public void union(int num1, int num2){
        int parent1 = find(num1);
        int parent2 = find(num2);
        if(parent2 != parent1){
            if(rank[parent2] > rank[parent1]){
                parent[parent1] = parent2;
                rank[parent2]++;
            }else{
                parent[parent2] = parent1;
                rank[parent1]++;
            }
        }
    }


    public int find(int num){
        if(parent[num] == num) return  num; // 자기 자신
        else{
            return parent[num] = find(parent[num]);
            // 경로 압축을 하여 루트 노드를 찾는 과정을 효율적으로 한다.
        }

    }
    public static void main(String[] args) throws Exception{
        new 연결요소의개수().solution();
    }
}
