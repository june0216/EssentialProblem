import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class 회전초밥3 {
    public static void main(String[] args) throws Exception{
        new 회전초밥3().solution();
    }


    int N;
    int typeCnt;
    int windowCnt;
    int coupNum;

    List<Integer> belt;
    public void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //회전 초밥 벨트에 놓인 접시의 수 N, 초밥의 가짓수 d, 연속해서 먹는 접시의 수 k, 쿠폰 번호 c
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        typeCnt = Integer.parseInt(st.nextToken());
        windowCnt = Integer.parseInt(st.nextToken());
        coupNum = Integer.parseInt(st.nextToken());

        belt = new ArrayList<>();


       // st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            belt.add(Integer.parseInt(br.readLine()));
        }

        // 원형 회전 초밥이므로 
        
        for(int j = 0; j < windowCnt; j++){
            belt.add(belt.get(j));
        }

        
        List<Integer> res = new ArrayList<>();
        res.add(coupNum);

        int start = 0; 
        int end = windowCnt;

        Map<Integer, Integer> map = new HashMap<>();

        

        for(int i = 0; i < windowCnt; i++){
            if(map.containsKey(belt.get(i))){
                map.put(belt.get(i), map.get(belt.get(i))+1);
            }else{
                map.put(belt.get(i), 1);
            }


    
            //end++;
            
        }

        //System.out.println(start + " " + end);


        int answer = map.size();
        int tmp = map.size();

        if(!map.containsKey(coupNum)){
                      answer = Math.max(answer, tmp+1);
                 }else{
                      answer = Math.max(answer, tmp);
                 }
        //System.out.println(answer);
      
         while(start <N){

             // 앞에 삭제 
                if(map.containsKey(belt.get(start))){
                    // 하나 줄이는데 0이라면 아예 키를 없앰 
                    if(map.get(belt.get(start))== 1){
                        map.remove(belt.get(start));
                         tmp--;
                        
                    }else{
                        map.put(belt.get(start), map.get(belt.get(start))-1);
                    }
                   
                     
                }

             // 뒤에 넣기 
             if(map.containsKey(belt.get(end))){
                    // 하나 줄이는데 0이라면 아예 키를 없앰 
                    
                        map.put(belt.get(end), map.get(belt.get(end))+1);
                    
                     
                }else{
                 map.put(belt.get(end),1);
                    tmp++;
                }
                start++;
                end++;
                 if(!map.containsKey(coupNum)){
                     //System.out.println("dd" + start);
                      answer = Math.max(answer, tmp+1);
                 }else{
                      answer = Math.max(answer, tmp);
                 }
               
             
             //System.out.println(start + " " +map.size());
            }


        System.out.println(answer);

        

        

        
    }
}
