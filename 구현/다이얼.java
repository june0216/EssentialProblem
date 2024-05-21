import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 다이얼 {
    public static void main(String[] args) throws Exception{
        new 다이얼().solution();
    }


    Map<Character, Integer> dial;
    public void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String word = br.readLine();
        dial = new HashMap<>(); // 알파벳 마다 각 숫자를 더한다.
        int num = 3;
        for(int i = 1; i <= 18 ;i++){
            dial.put((char) ('A' + (i-1)), num);
            if(i%3 == 0 && i != 18){ // 3개씩 떨어지는 경우
                num++;
            }
        }
        dial.put('S', num);
        num++;
        for(int i = 20; i <26; i++){ // 나머지 알파벳 카운팅
            dial.put((char) ('A' + (i-1)), num);
            if(i == 22){
                num++;
            }

        }
        dial.put('Z', num);

        int result = 0;
        for(char c : word.toCharArray()){
            result +=  dial.get(c);
        }
        System.out.println(result);
    }
}
