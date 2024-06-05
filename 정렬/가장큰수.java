import java.util.*;


class 가장큰수 {
    // 처음에는 우선순위큐로 해서 우선순위를 다 따지려고 했지만 우선순위가 너무 복잡했다. -> 단순하게 붙여보고 어떤 게 더 큰지만 비교해주면 간단
    public String solution(int[] numbers) {
        StringBuilder answer = new StringBuilder();
        List<String> numberToStr = new ArrayList<>();
        for(int i = 0; i < numbers.length; i++){
            numberToStr.add(String.valueOf(numbers[i]));
        }

        numberToStr.sort((a, b)-> (b+a).compareTo(a+b));

        // 에외 처리
        if(numberToStr.get(0).equals("0")){
            return "0";
        }
        for(String num : numberToStr){
            answer.append(num);
        }




        return answer.toString();
    }


}