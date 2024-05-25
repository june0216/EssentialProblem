import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 정보장인호석 {
    public static void main(String[] args) throws Exception{
        new 정보장인호석().solution();
    }


    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int query = Integer.parseInt(br.readLine());

        Map<String, PriorityQueue<Integer>> info = new HashMap<>();
        long result = 0;
        while(query -- > 0){ // 쿼리순서대로
            StringTokenizer st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            if(command == 1){ // 정보 얻기
                String name = st.nextToken();
                if(!info.containsKey(name)){
                    info.put(name, new PriorityQueue<>(Collections.reverseOrder()));
                }
                int num = Integer.parseInt(st.nextToken());
                while(num-- >0){
                    info.get(name).offer(Integer.parseInt(st.nextToken()));
                }

            }else{ // 구매하기
                String name = st.nextToken();
                int num = Integer.parseInt(st.nextToken());
                if(!info.containsKey(name))continue;
                while(num-- >0 && !info.get(name).isEmpty()){
                    result += info.get(name).poll();
                }


            }

        }
        System.out.println(result);

    }
}
