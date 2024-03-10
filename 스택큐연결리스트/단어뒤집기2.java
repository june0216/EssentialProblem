import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 단어뒤집기2 {
    public static void main(String[] args) throws Exception{
        new 단어뒤집기2().solution();
    }
    public static void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();

        int total = Integer.parseInt(br.readLine());

        while(total--> 0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            while(st.hasMoreTokens()){
                result.append(reverseWord(st.nextToken())).append(" ");
            }
            result.append("\n");
        }
        System.out.println(result);

    }

    public static String reverseWord(String word){
        if(word.isEmpty()){
            return word;
        }
        return reverseWord(word.substring(1)) + word.charAt(0);
    }
}
