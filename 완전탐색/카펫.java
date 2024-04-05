public class 카펫 {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        for(int i = 1; i <= yellow; i++){
            if(yellow%i == 0){ // 나누어 떨어지면
                int h = yellow/i;
                int w = i;
                if((4+w*2 + h*2) == brown){  // brown의 개수와 동일하면 정답
                    if(h < w){
                        int temp = h;
                        h = w;
                        w = temp;
                    }
                    answer[0] = h+2;
                    answer[1] = w+2;
                    return answer;
                }
            }
        }
        return answer;
    }
}
