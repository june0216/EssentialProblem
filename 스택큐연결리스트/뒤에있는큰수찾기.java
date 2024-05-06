import java.util.*;
public class 뒤에있는큰수찾기 {
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        Deque<Integer> stack = new ArrayDeque<>(); // 자기보다 큰 값들을 못찾은 수들을 저장함 그리고 바로 자기보다 큰 수를 발견하면 다 나오게 함
        stack.addLast(0);
        Arrays.fill(answer, -1);
        for(int i= 1; i < numbers.length; i++){ // 자기보다 큰 수를 찾는 것이 아니라 자기보다 작은 값들을 찾아 업데이트 한다
            while(!stack.isEmpty() &&numbers[i] > numbers[stack.peekLast()]){ // 스택에 있는 값들 중 기준 값보다 작은 수들이 있다면 다 꺼내고 업데이트 함
                answer[stack.pollLast()] = numbers[i];
            }

            stack.addLast(i); // 현재 수 스택에 넣기
        }
        return answer;
    }
}
