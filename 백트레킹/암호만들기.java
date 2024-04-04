import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.*;

public class 암호만들기 {
    public static void main(String[] args) throws Exception{
        new 암호만들기().solution();
    }

    public List<Character> totalAlphabet;

    public Set<String> result;
    public int totalLen;
    public int alphabetLen;
    StringBuilder sb = new StringBuilder();

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        totalLen = Integer.parseInt(st.nextToken());
        alphabetLen = Integer.parseInt(st.nextToken());
        result = new HashSet<>();

        totalAlphabet = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < alphabetLen; i++){
            char c = st.nextToken().charAt(0);

            totalAlphabet.add(c);

        }
        //1개모음+ 2개의 자음
        // 사전순으로 정렬
        Collections.sort(totalAlphabet);
        dfs(0, 0, new ArrayList<>());
        System.out.println(sb);

    }

    public void dfs(int depth, int idx ,List<Character> cipher){
        if(depth == totalLen){
            if(valid(cipher)){
                for (Character c : cipher) {
                    sb.append(c);
                }
                sb.append("\n");
            }
            return;

        }
        for(int i = idx; i < totalAlphabet.size(); i++){ // idx
            List<Character> currentCipher = new ArrayList<>(cipher);
            currentCipher.add(totalAlphabet.get(i)); // 항상 새 문자 추가
            dfs(depth + 1, i + 1, currentCipher); // 다음 깊이로 재귀 호출, idx는 순서를 지키기위해 다음 index부터 시작하도록 함
        }

    }

    public boolean valid(List<Character> cipher) {
        int gCount = 0;
        int aCount = 0;
        for(char c : cipher){
            if(c == 'a' || c == 'i' || c == 'e' || c == 'o' || c == 'u'){
                gCount++;
            }else{
                aCount++;
            }
        }
        if(gCount >= 1 && aCount>=2){
            return true;
        }
        return false;
    }
}
