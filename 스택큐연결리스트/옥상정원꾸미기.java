import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 옥상정원꾸미기 {
    public static void main(String[] args) throws Exception{
        new 옥상정원꾸미기().solution();
    }

    int[] buildings;
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int total = Integer.parseInt(br.readLine());
        buildings = new int[total];
        for(int i = 0 ; i < total; i++){
            buildings[i] = Integer.parseInt(br.readLine());
        }
        long result = 0;
        Deque<Integer> stack = new ArrayDeque<>();

        for(int i =0; i < buildings.length;i++){ // 빌딩 하나씩 검사 대상이 된다.
            while(!stack.isEmpty() && buildings[i]>=  stack.peek()){ // 뒤로 가면서 비교(들어있는 최신 값 순서대로)
                stack.pop(); // 해당 빌딩 기준으로 크기가 작거나 같다면 1) 지금 비교 대상 빌딩이 볼 수 없으며 + 2) 앞에 있는 빌딩들을 볼 수 없다(즉 앞에 등장할 빌딩들이 자기가 보이는지 비교할 때 지금 기준이 되는 빌딩이 더 커서 가려지기 때문에 보이지 않는 빌딩임)
            }
            result += stack.size(); // stack에 남아있는 것들은 다른 빌딩들을 볼 수 있는 빌딩들임
            stack.push(buildings[i]); // 지금 비교했던 빌딩 추가
        }
        System.out.print(result);
    }
}
