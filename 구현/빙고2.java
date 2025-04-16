import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class 빙고2 {
    public static void main(String[] args) throws Exception{
        new 빙고2().solution();
    }

   

    Set<String> result;
    int[][] map;
    public void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        map = new int[5][5];
        Map<Integer, String> bingo = new HashMap<>();
        for(int i = 0; i < 5; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 5; j++){
                int num = Integer.parseInt(st.nextToken());
                bingo.put(num, i+ " " + j);
                map[i][j] = num;
            }
        }


        int time = 1;

        /*
        가로, 세로 , 대각선 
        
        */

        Map<Integer, Integer> xMap = new HashMap<>();
        Map<Integer, Integer> yMap = new HashMap<>();
            
        result = new HashSet<>();
        int cnt = 0;
        List<Integer> number = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 5; j++){
                
                number.add(Integer.parseInt(st.nextToken()));
                
            }
        }

        for(int i = 0; i < number.size(); i++){
            int num = number.get(i);
                            if(bingo.containsKey(num)){
                    String position = bingo.get(num);
                    st = new StringTokenizer(position);
                    int y = Integer.parseInt(st.nextToken());
                    int x= Integer.parseInt(st.nextToken());
                    map[y][x]= 1;
                    cnt += checkBingo();
                   if(cnt >= 3){
                            System.out.println(time);
                            return;
                    }
                    
                }
                time++;
        }

        System.out.println(0);
        
        
    }

    public int checkBingo(){
        int res = 0;
        for(int i = 0; i < 5 ; i++){
            int xSum = 0; 
            for(int j = 0; j < 5; j++){
                xSum+= map[i][j];
            }

            if(xSum == 5){
                String k = i + " " + "0";
                if(!result.contains(k)){
                    result.add(k);
                    res++;
                    break;
                }
                
            }
            
        }


        for(int i = 0; i < 5; i++){
            int ySum = 0;
            for(int j = 0; j < 5; j++){
                ySum +=map[j][i];
            }
            if(ySum == 5){
                String kk = "0" + " " + i;
                if(!result.contains(kk)){
                    result.add(kk);
                    res++;
                    break;
                }
            }
        }


        // 대각선
        int s = 0;
        for(int i = 0; i < 5; i++){
            s+=map[i][i];
        }
        
        if(s == 5){
            String kk = "0" + "0";
            if(!result.contains(kk)){
                    result.add(kk);
                    res++;
                  
                }
            
        }

        s = 0;

        for(int i = 0; i < 5; i++){
            s+=map[i][4-i];
        }
        if(s == 5){
            String kk = "1" + "0";
            if(!result.contains(kk)){
                    result.add(kk);
                    res++;
                }
            
        }

        
        return res;
    }
}
