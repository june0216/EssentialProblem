import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.StringTokenizer;

public class 모두의마블 {
    public static void main(String[] args) throws Exception{
        new 모두의마블().solution();
    }
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        //레벨은 그대로 골드는 레벨을 합치기
        // 인접한 카드끼리 가능

        int total = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        int[] cardList = new int[total];
        int sum = 0;
        int maxLevel = 0;

        for(int i = 0 ; i < total;i++){
            cardList[i] = Integer.parseInt(st.nextToken());
            sum += cardList[i]; // 누적해서 레벨의 합을 구하기
            if(maxLevel < cardList[i]){ // 제일 큰 레벨 구하기
                maxLevel = cardList[i];
            }

        }

        int result = sum + maxLevel * (total-2); // 누적해서 레벨을 다 더한 값에 제일 큰 레벨을 n-2번 더한다. ( 처음 2개의 요소를 각가 더한 거 빼기)


        System.out.println(result);


    }

}
