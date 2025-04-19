import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class 휴게소세우기 {
    public static void main(String[] args) throws Exception{
       new 휴게소세우기().solution();
        
    }

    public void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        List<Integer> info = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            info.add(Integer.parseInt(st.nextToken()));
        }


        info.add(L);
        info.add(0);
        Collections.sort(info);

        // 간격을 기준으로 
        int start = 1;
        int end = L-1;
        int result = 0;
        while(start <= end){
            int mid = (start+end)/2;

            // 그 간격으로 나눠도 돠는지 
            int divide = 0;
            for(int i = 1; i < info.size(); i++){
  -
                int diff = info.get(i) - info.get(i-1)-1;
                if(diff >= mid){
                    divide += (diff/mid);
                }
                
            }
            //System.out.println(divide + " " + mid);
            if(divide >M){
                start = mid+1;
                
            }else{ // 적어도 됨 
                end = mid-1;
                result = mid;
            }
            
        }

        System.out.println(result);

    }
}
