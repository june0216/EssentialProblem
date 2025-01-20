import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class 문자열폭발2 {
    public static void main(String[] args) throws Exception{
        new 문자열폭발2().solution();
    }

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String bomb = br.readLine();
        int bombLength = bomb.length()-1;
        Deque<Character> stack = new ArrayDeque<>();
        for(int i = 0; i < input.length(); i++){
            stack.addLast(input.charAt(i));

            if (stack.size() >= bombLength) {
                boolean isBomb = true;
                int j = bombLength - 1;

                // Traverse stack to compare with bomb pattern
                for (char c : stack.subList(stack.size() - bombLength, stack.size())) {
                    if (c != bomb.charAt(j--)) {
                        isBomb = false;
                        break;
                    }
                }
        }

        StringBuilder sb = new StringBuilder();
        if (stack.isEmpty()) {
            System.out.println("FRULA");
        }else{
            while(!stack.isEmpty()){
                sb.append(stack.pollFirst());
            }
            System.out.println(sb);

        }

    }
}
