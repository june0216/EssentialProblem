import java.io.*;
import java.util.*;

public class 팔단변속기 {

    public static void main(String[] args) throws Exception{
        new 팔단변속기().solution();
    }

    public void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cnt = 8;
        int[] number = new int[8];
        StringTokenizer st = new StringTokenizer(br.readLine());
        boolean asc = false;
        boolean desc = false;
        number[0] = Integer.parseInt(st.nextToken());
        for(int i = 1 ; i< 8; i++){
            number[i] = Integer.parseInt(st.nextToken());
            if(number[i-1] > number[i]){
                desc = true;
            }else if(number[i-1] < number[i]){
                asc = true;
            }
        }

        if(asc && desc){
            System.out.println("mixed");
        }else if(asc){
            System.out.println("ascending");
        }else{
            System.out.println("descending");
        }
    }
}

