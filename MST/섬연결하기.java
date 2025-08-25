import java.util.*;
class 섬연결하기 {
    int[] parent;
    public int solution(int n, int[][] costs) {
        int answer = 0;
        Arrays.sort(costs, (a, b) -> a[2] - b[2]);
        
        parent = new int[n];
        for(int i = 0; i < n; i++){
            parent[i] = i;
        }
        int total =0;
        
        /*
        크루스칼은 먼저 선점 (탐욕 가능) -> 증명되었음
        */
        for(int i = 0; i < costs.length; i++){
            int parentTo= find(costs[i][0]);
            int parentFrom = find(costs[i][1]);
            if(parentTo == parentFrom){
                continue;
            }
            total += costs[i][2];
            parent[parentTo] = parentFrom;
        }
            
        
            
        return total;
        
    }
    

    
    public int find(int start){
        if(parent[start]== start) return start;
        return parent[start] = find(parent[start]);
    }
}
