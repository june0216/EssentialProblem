import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;

public class 좌표압축 {


    public static void main(String[] args) throws Exception{
        new 좌표압축().solution();
    }
    public static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        long[] number = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            number[i] = Integer.parseInt(st.nextToken());
        }
        long[] origin = number.clone(); // 주어진 순서대로 배열에 저장
        Arrays.sort(number); // 정렬하여 압축 -> 오름차순으로 0부터 숫자가 붙음
        Map<Long, Long> mapping = new HashMap<>(); // 정렬된 순서대로 각각 숫자에 번호를 매핑
        long  cnt = 0;
        for(int i = 0; i < N;i++){
            if(!mapping.containsKey(number[i])){
                mapping.put(number[i], cnt++);
            }
        }

        for(long num : origin){
            sb.append(mapping.get(num)).append(" "); // 해당 숫자에 매핑된 번호 출력
        }

        System.out.println(sb);


    }
}
