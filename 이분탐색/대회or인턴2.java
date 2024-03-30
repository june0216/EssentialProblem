import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 대회or인턴2 {
    public static void main(String[] args) throws Exception{
        new 대회or인턴2().solution();
    }
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int girl = Integer.parseInt(st.nextToken());
        int boy = Integer.parseInt(st.nextToken());
        int intern = Integer.parseInt(st.nextToken());
        int start = 0;
        int end = 0;
        if(girl/2 > boy){ // 남자의 수가 더 작다면 최대로 팀을 만들 수 있는 경우가 남자의 수가 맥시멈이 된다.
            end = boy;
        }else{
            end = girl/2;
        }

        int result = 0;
        while(start <= end){
            int mid = start + ((end-start)/2);
            int remain = girl - mid*2 + boy -mid; // Mid 개의 팀을 만들고 남은 인원 체크
            if(remain >= intern){ // 인턴으로 보낼 수 있다면 범위를 좁혀서 다시 탐색
                start = mid +1;
                result = mid;
            }else{
                end = mid-1;
            }

        }
        System.out.println(result);
    }
}
