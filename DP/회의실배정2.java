import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class 회의실배정2 {
    public static void main(String[] args) throws Exception{
        new 회의실배정2().solution();
    }

    List<Node> li;

    public void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        //시작 시간, 끝나는 시간, 회의 인원이
        //long[] dp = new long[]

        li = new ArrayList<>();
        for(int i= 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            long startTime = Long.parseLong(st.nextToken());
            long endTime =  Long.parseLong(st.nextToken());
            int peopleCnt =  Integer.parseInt(st.nextToken());
            li.add(new Node(startTime, endTime, peopleCnt));
            
        }

        //Collections.sort(li);

        /*
         어려웠던 부분 -> 계속 시간을 기준으로 배열을 만드려고 했다/ 
         다른 회의실 문제와 헷갈림 -> 그리디로 할 수 있는 회의실 문제는 끝점을 기준으로 최대한 많은 회의실이 가능하게 한 것이다. 하지만 이 문제는 사람 인원수가 있기 때문에 어려웠던 것 같다
            */
        long[] dp = new long[N]; // 선택 여부 
        
        dp[0] = li.get(0).peopleCnt;
        if(N>=2){
             dp[1] = Math.max(li.get(1).peopleCnt, dp[0]);
        }
       if(N>=3){
           dp[2] = Math.max(dp[1], dp[0] +li.get(2).peopleCnt );
       }
        if(N>=4){
            for(int i = 2; i < N; i++){
            Node cur = li.get(i);
            //dp[i][0] = dp[i-1][1];
            dp[i] = Math.max(dp[i-2] + cur.peopleCnt, dp[i-1]);
            //System.out.println(dp[i][0] + " " + dp[i][1]);
            }
        }
        

        System.out.println(dp[N-1]);

        
        
    }
    // 회의 중간인지 
    // 모든 경우의 수 

    public void combi(int cnt, List<Node> tmp, int goal){
        if(tmp.size() == cnt){
            // 중간에 없는지 확인 
            int sum = 0;
            for(int i = 0; i < tmp.size()-1; i++){
                if(tmp.get(i).endTime <= tmp.get(i+1).startTime){
                    // 가능 
                    sum+= tmp.get(i).peopleCnt;
                }else{
                    continue;
                }
            }
            return;
        }
        for(int i = cnt; i < goal; i++){
            tmp.add(li.get(i));
            combi(i+1, tmp, goal);
            tmp.remove(tmp.size()-1);
        }
    }

    class Node implements Comparable<Node>{
        long startTime;
        long endTime;
        long peopleCnt;
        public Node(long startTime, long endTime, long peopleCnt){
            this.startTime = startTime;
            this.endTime = endTime;
            this.peopleCnt = peopleCnt;
        }

        @Override
        public int compareTo(Node node){
            if(this.startTime>= node.startTime){
                return 1;
            }else{
                return -1;
            }
        }

        
    }
}
