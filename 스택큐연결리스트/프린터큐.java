import java.util.*;
import java.io.*;
public class 프린터큐 {

    public static void main(String[] args) throws Exception{
        new 프린터큐().solution();
    }

    public static class Task {
        int id; // 일을 구분하기 위해 들어온 순서대로 id 부여
        int weight; // 우선순위

        public Task(int id, int weight){
            this.id = id;
            this.weight = weight;
        }
    }
    public static void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        while(T-- > 0){
            int answer = 0;
            st = new StringTokenizer(br.readLine());
            int totalSize = Integer.parseInt(st.nextToken());

            int pos = Integer.parseInt(st.nextToken());

            Deque<Task> que = new  ArrayDeque<>(); // 일들을 큐로 저장
            List<Integer> weightInfo = new ArrayList<>(); // 우선순위를 정렬하여 저장하기

            st = new StringTokenizer(br.readLine());
            int cnt = 0; // 입력 순서
            int targetId = 0; // 순서를 알고 싶은 id
            while(st.hasMoreTokens()){
                int num = Integer.parseInt(st.nextToken());
                if(cnt == pos) targetId = cnt; // 순서를 알고 싶은 id 저장
                que.offer(new Task(cnt, num)); // 큐에 정보 다 넣기

                weightInfo.add(num); // 우선순위를 순서대로 관리하기 위해 따로 저장
                cnt++;
            }
            Collections.sort(weightInfo, Collections.reverseOrder()); // 제일 우선순위 낮은 것부터 차례대로 뽑아야하므로 정렬

            int weightIdx = 0; // 뽑아야할 우선순위를 뽑기 위한 인덱스
            while(!que.isEmpty()){
                Task checkTarget = que.poll(); //큐에서 일단 뽑아서 확인 후 조건에 맞지 않으면 맨 뒤로 보냄

                // 뽑아야할 우선순위 순서와 큐에서 꺼낸 우선순위가 같다면 큐에서 출력한다.
                if(weightInfo.get(weightIdx) == checkTarget.weight){
                    if(checkTarget.id == targetId){ // 그게 순서를 알고싶었던 대상이라면
                        sb.append(answer+1).append("\n"); // 출력하고 반복문 종료
                        break;
                    }
                    answer++; // 순서 업데이트
                    weightIdx++; // 출력을 완료했으니 다음 우선순위를 출력하기 위해 인덱스 업데이트
                }
                else{ // 큐에서 꺼낸 일의 우선순위와 출력해야할 우선순위가 다르다면 다시 큐에 넣는다.
                    que.offer(checkTarget);
                }
            }

        }
        System.out.println(sb);

    }
}
