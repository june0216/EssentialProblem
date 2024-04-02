import java.util.*;
public class 다단계칫솔만들기 {
    public Map<String, String> parentMap = new HashMap<>(); // 자신의 부모를 저장하는 map
    public Map<String, Integer> result = new HashMap<>(); // 자신과 정산 금액을 저장하는 map
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length]; // 결과를 저장하는 리스트
        result.put("-", 0);
        for(int i = 0 ; i < enroll.length; i++){
            result.put(enroll[i], 0);
            parentMap.put(enroll[i],referral[i]);
        }
        for(int i = 0; i < seller.length; i++){
            String sell = seller[i];
            int price = amount[i] * 100; // 수익
            while(!sell.equals("-")){

                result.put(sell, result.get(sell) + (price -(price/10))); // 10퍼센트 제외하고 수익 저장
                price = price/10;
                if(price < 1) break; // 1원 미만이면 부모에게 주지 않으므로 부모를 거슬러 올라가는 거 그만하기
                String parent = parentMap.get(sell); // 자기 자신의 부모 정보 꺼내기
                sell = parent; // 부모로 거슬러 올라가기

            }

        }
        for(int i = 0 ; i < enroll.length; i++){
            answer[i] = result.get(enroll[i]);
        }
        return answer;
    }
}
