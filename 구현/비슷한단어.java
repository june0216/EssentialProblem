import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 비슷한단어 {
    public static void main(String[] args) throws Exception{
        new 비슷한단어().solution();
    }

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<String> wordList = new ArrayList<>();
        int maxCnt = 0;
        String answerWord1 = "";
        String answerWord2 = "";
        for(int i = 0 ; i < N;i++) {
            String word = br.readLine();
            if(wordList.contains(word)){
                continue;
            }
            wordList.add(word);
        }


        for(int i = 0; i < N;i++){ // 하나씩 꺼내서 각각 비교하기
            String word = wordList.get(i);
            for(int j = i+1; j < N; j++){
                String compareWord = wordList.get(j);
                if(compareWord.equals(word)) continue;
                int cnt = 0;
                int compareLen = Math.min(word.length(), compareWord.length()); // 글자 수가 작은 단어의 길이까지만 비교
                for(int k = 0; k < compareLen; k++){
                    if(word.charAt(k) != compareWord.charAt(k)){
                        break;
                    }
                    cnt++;
                }
                if (maxCnt < cnt) { // 만약 최장길이라면 업데이트
                    answerWord2 = compareWord;
                    answerWord1 = word;
                    maxCnt = cnt;
                }
            }
        }
        System.out.println(answerWord1);
        System.out.println(answerWord2);


    }
}
