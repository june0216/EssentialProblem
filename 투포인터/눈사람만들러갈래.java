import java.util.*;
import java.io.*;
public class 눈사람만들러갈래{
    public static void main(String[] args) throws Exception{
        new 눈사람만들러갈래().solution();
    }

    int N;
    int[] snow;
    public void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        snow = new int[N];
        for(int i = 0; i < N; i++){
            snow[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(snow);
        //3 5 2 5 9
        // 2 3 5 5 9

        // 2 3 5 5 6 9
        // 언니거 확정 후 동생거  탐색



        int selectedSnowOld;
        int selectedSnowYoung;
        int minGap= Integer.MAX_VALUE;
        for(int i = 0; i < N; i++){
            for(int j = i+1; j < N; j++){
                selectedSnowOld = snow[i] + snow[j];
                // 정렬되어 있음 

                int start = 0;
                int end = N-1;
                while(start < end){
                    // 언니랑 겹치는 경우 
                    if(start == i || start == j){
                        start++;
                        continue;
                    }
                    if(end== i || end == j){
                        end--;
                        continue;
                    }
                    selectedSnowYoung = snow[start] + snow[end];
                    if(selectedSnowYoung == selectedSnowOld){ // 차이가 없음 
                        System.out.println(0);
                        return;
                    }
                    if(selectedSnowYoung > selectedSnowOld){
                        // 동생이 더 크면 줄여야함
                        end--;
                    }else{
                        start++; // 동생이 더 작으면 둘의 차이를 줄이기 위해서 좀 더 키워야함 
                    }

                    minGap = Math.min(minGap, Math.abs(selectedSnowYoung-selectedSnowOld));

                }
            }
        }
        System.out.println(minGap);
    }
}