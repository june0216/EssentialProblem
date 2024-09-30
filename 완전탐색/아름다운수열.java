import java.util.*;
import java.io.*;
public class 아름다운수열 {
    public static void main(String[] args) throws Exception{
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine().trim());
        List<Integer> aList = new ArrayList<>();
        List<Integer> bList = new ArrayList<>();

        for(int i = 0; i < K; i++){
            aList.add(Integer.parseInt(br.readLine().trim()));
        }

        int M = Integer.parseInt(br.readLine());
        for(int i = 0; i < M; i++){
            bList.add(Integer.parseInt(br.readLine().trim()));
        }
        Collections.sort(bList);
        List<Integer> result = new ArrayList<>();
        //조건이 너무 많은데 ..
        
        boolean isBeautiful = true;
        for(int i = 0 ; i <= K-M; i+=1){
           int temp[] = new int[M];
            for(int j = 0; j < M; j++){
                temp[j] = aList.get(j+i);
            }
            Arrays.sort(temp);
            int gap = temp[0] - bList.get(0);
            isBeautiful = true;
            for(int k = 1 ; k < M; k++){
                if((temp[k] - bList.get(k)) != gap){
                    isBeautiful = false;
                    break;
                }
            }
            if(isBeautiful){
                result.add(i+1);
            }
            
            
        }
        System.out.println(result.size());
        for(int n : result){
            System.out.println(n);
        }
        

    }


}
