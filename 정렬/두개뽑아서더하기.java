import java.util.*;
public class 두개뽑아서더하기 {
    public int[] solution(int[] numbers) {
        int[] answer = {};
        // 조합으로 모든 합 만들기
        List<Integer> result = new ArrayList<>();
        Set<Integer> resultSet = new HashSet<>();
        int total = numbers.length;
        for(int i = 0; i < total-1; i++){
            for(int j = i+1; j < total;j++){
                resultSet.add(numbers[i] + numbers[j]);
            }
        }
        // 정렬
        for(int num : resultSet){
            result.add(num);
        }

        Collections.sort(result);
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}
