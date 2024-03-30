import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class 배수30 {
    public static void main(String[] args) throws Exception{
        new 배수30().solution();
    }
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        List<Integer> number = new ArrayList<>();
        int sum = 0;
        for(int i = 0 ; i < input.length(); i++) {
            number.add(Character.getNumericValue(input.charAt(i)));
            sum += number.get(i);
        }
        List<Integer> sortedNumber = number.stream().sorted(Collections.reverseOrder()).collect(Collectors.toList());

        if(sum %3 != 0 ||  !input.contains("0")){ // 30의 배수가 아니라면 -1 출력
            System.out.println(-1);
        }else{ // 30배수 맞다면 내림차순으로 정렬한 숫자를 출력
            StringBuilder sb = new StringBuilder();
            for(int num : sortedNumber){
                sb.append(num);
            }
            System.out.println(sb);
        }

    }
}
