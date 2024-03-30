import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 대회or인턴 {
    public static void main(String[] args) throws Exception{
        new 대회or인턴2().solution();
    }
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int girl = Integer.parseInt(st.nextToken());
        int boy = Integer.parseInt(st.nextToken());
        int intern = Integer.parseInt(st.nextToken());
        int team = girl/2;
        if(boy < girl/2){
            team = boy;
        }
        while(true){
            if(team == 0){ // 팀이 생기지 않는다면 0 출력
                System.out.println(team);
                break;
            }
            int remain = girl- team*2 + boy - team; // 남는 인원 체크
            if(remain >= intern){
                System.out.println(team);
                break;
            }else{
                team--;
            }
        }
    }
}
