import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class 앵무새 {
    public static void main(String[] args) throws Exception{
        new 앵무새().solution();
    }

    public void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Deque<String>[] li  = new Deque[N];
        StringTokenizer st;
        int total = 0;
        for(int i = 0; i < N; i++){
            String input = br.readLine();
            li[i] = new ArrayDeque<>();
            st = new StringTokenizer(input);
            while(st.hasMoreTokens()){
                li[i].offer(st.nextToken());
                total++;
            }
        }

        String input = br.readLine();
       st = new StringTokenizer(input);
        
        boolean totalFlag = false;
       while(st.hasMoreTokens()){
           String sentence = st.nextToken();
           boolean flag = false;
            for(int i = 0; i < N ;i++){
                if(li[i].size() == 0){
                    continue;
                }
                String next =li[i].peekFirst();
                if(next.equals(sentence)){
                    //System.out.println(next);
                    li[i].pollFirst();
                    flag = true;
                    break;
                    
                }
            }

           if(!flag){
               totalFlag = true;
               //System.out.println(sentence);
               break;
           }
       }


        /*
        오답 이유 1) 중간에 정답을 만나도 break가 없어 다른 큐의 같은 문장을 꺼내는 현상
        오답 이유 2) 앵무새가 가진 단어의 크기가 전체 단어의 개수보다 작을 경우를 생각하지 못함
        */
        if(totalFlag){
            System.out.println("Impossible");
            return;
        }

        int cnt = 0;
        for(int i = 0 ; i < N; i++){
            if(li[i].size() == 0){
                        cnt++;
            }
        }
    
        if(cnt == N){
            System.out.println("Possible");
        }else{
             System.out.println("Impossible");
        }
      
        
    }
}
