import java.util.*;

class 행렬테두리 {
    
    int[][] map;
    int R;
    int C;
    List<Integer> result;
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = {};
        R = rows; //Y
        C = columns;
        map = new int[R][C];
        result = new ArrayList<>();
        int num = 1;
        for(int i=0; i < R; i++){
            for(int j = 0; j < C; j++){
                map[i][j] = num++;
            }
        }
        for(int i = 0; i < queries.length; i++){
            int y1 = queries[i][0]-1; //세로 5
            int x1 = queries[i][1]-1; // 가로  4
            int y2 = queries[i][2]-1;
            int x2 = queries[i][3]-1;
            spin(y1, x1, y2, x2);
            
            
            
            
        }
        answer = new int[result.size()];
        int idx= 0;
        for(int n : result){
            answer[idx++] = n;
        }
        
        
        
        //원래 위치와의 비교 
        
        return answer;
    }
    
    
    // 진동 
    public void spin(int y, int x, int y2, int x2){
        int X = x2-x+1;
        int Y = y2 - y+1;
        
        int startX = x;
        int startY = y;
        
       
        // 세로 아래로 이동 
        
        int tmp = map[y][x];
        int min = tmp;
        //System.out.println(tmp);
        
        //print();
        
        for(int i = 0; i <Y-1; i++){
           min = Math.min(map[y+1][x], min);
            map[y][x] = map[y+1][x];
            y++;
           
            //System.out.println(y + " " + x);
            
        }
        //System.out.println(tmp);
        for(int i = 0; i < X-1; i++){
            min = Math.min(map[y][x+1], min);
            map[y][x] = map[y][x+1];
            x++;
            
             
        }
        
        // 위로 올라가기 
        for(int i = 0; i < Y-1; i++){
            min = Math.min(map[y-1][x], min);
            map[y][x] = map[y-1][x];
            y--;
              
        }
        
        
       // System.out.println(x + " " + y);
        for(int i = 0; i < X-1; i++){
           min = Math.min(map[y][x-1], min);
            map[y][x] = map[y][x-1];
            x--;
            
            
        }
        
        
       map[startY][startX+1] = tmp;
        //print();
        
        result.add(min);
        
        
        // 세로 이동
        // 가로 이동
        // 세로 이동
        
    }
    
    public void print(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }
        
        System.out.println(sb.toString());
    }
}
