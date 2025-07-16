import java.util.*;
class 네트워크 {
    
    int[] parent;
    public int solution(int n, int[][] computers) {
        int answer = 0;
        // 연결 묶음 세기 
        parent = new int[n+1];
        for(int i = 0; i <= n; i++){
            parent[i] = i;
        }
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(computers[i][j] == 0) continue;
                if(i == j) continue;
                union(j+1, i+1);
            }
        }
        
        Set<Integer> s = new HashSet<>();
        
        for(int i = 1; i <= n; i++){
            // 여기서 원래 parent[i]만 봤는데 그게 아니라 find(parent[i])로 해야한다. 
            //parent[i] 는 중간 부모일 수 있으므로 그대로 Set에 넣으면 잘못된 네트워크 개수가 계산
            s.add(find(parent[i]));
        }
        return s.size();
    }
    
    public void union(int num1, int num2){
        int parent1 = find(num1);
        int parent2 = find(num2);
        
        if(parent1 != parent2){
            if(parent1 > parent2){
                // num1의 부모는 parent2이다. 
                // 이 부분이 상대의 parent에다가 연결해야했는데 num에 연결을 해버림 
                parent[parent1] = parent2;
                
            }else{
                parent[parent2] = parent1;
            }
        }
        
    }
    
    public int find(int num){
        if(parent[num] != num ){
            return parent[num]= find(parent[num]);
            
        }
        
        return parent[num];
    }
}
