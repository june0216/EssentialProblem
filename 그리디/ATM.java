import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class ATM {


    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int total = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] numList = new int[total];
        for(int i = 0; i < total; i++){
            numList[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(numList); // 정렬한다. -> 작은 시간부터 해야 최소 시간이 걸린다.
        List<Integer> sumList = new ArrayList<>();
        int sum = 0;
        for(int num : numList){
            sum += num;
            sumList.add(sum);
        }
        int result = 0;
        for(int num : sumList){
            result += num;
        }
        System.out.println(result);
    }
    public static void main(String[] args) throws Exception{
        new ATM().solution();
    }
}
