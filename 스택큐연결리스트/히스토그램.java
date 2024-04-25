import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
풀이 방법 3가지
1) 스택
2) 세그먼트 트리
3) 분할 정복
 */

public class 히스토그램 {

    public static void main(String[] args) throws Exception{
        new 히스토그램().solution();
    }

    int[] heights;
    int total;
    int max;
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        total = Integer.parseInt(br.readLine());
        heights = new int[total];
        for(int i = 0; i < total; i++){
            heights[i] = Integer.parseInt(br.readLine());
        }


        max = 0;
        int result = 0;
        Deque<Integer> stack = new ArrayDeque<>();

        for(int i = 0; i < total;i++){
            while(!stack.isEmpty() && heights[i] <= heights[stack.peek()]){ // 넣을 값이 스택의 top에 있는 값보다 작으면 pop 필요 + 넓이 계싼

                int h = stack.pop(); // 새로 들어오는 사각형보다 top이 더 크니까 앞으로 들어오는 것은 top 넓이보다 작게 계산되므로 넓이를 구하고 pop한다.
                int w = stack.isEmpty() ? i: i - 1 - stack.peek();; // 스택이 비어있다는 경우까지 핸들링해야한다. 여기서 스택이 비어있다는 것은 i-1번째 전체를 대상으로 계산해야한다.

                result = Math.max(result,  heights[h] * w);


            }
            stack.push(i);
        }

        // 위 과정이 끝나고 Stack에 남아있는 체인들이 존재할 수 있으므로 나머지도 위와같은 과정을 거친다.
        while(!stack.isEmpty()) {
            int height = heights[stack.pop()];

            /*
             * 만약 pop하고 난 뒤 스택이 비어있다면 가장 작은 값이라는 것
             * 배열 전체 폭이 width가 되는 것이다.
             * 스택이 남아있다면  stack에 남아있는 값 이전까지 넓이를 구한다.
             */
            int width = stack.isEmpty() ? total: total - 1 - stack.peek();
            result = Math.max(result, width * height);

        }

        System.out.println(result);

    }



}
