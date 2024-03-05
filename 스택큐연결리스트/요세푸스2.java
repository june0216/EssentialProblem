
import java.io.*;
import java.util.*;
public class 요세푸스2 {
    public static void main(String[] args) throws Exception{
        new 요세푸스2().solution();
    }

    public static void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        StringTokenizer st = new StringTokenizer(br.readLine());

        int total = Integer.parseInt(st.nextToken()); // 전체 번호 개수
        int deleteIndex = Integer.parseInt(st.nextToken()); // 삭제할 index

        List<Integer> linkedList = new LinkedList<>(); // 번호를 저장할 연결리스트 -> 삭제연산이 빈번하므로 배열보다는 연결리스트로 선택
        for(int i = 1; i <= total; i++) {
            linkedList.add(i); // 전체 번호를 세팅
        }
        int targetIndex = 0; // 삭제할 index 저장 변수
        int cnt = 0; // 삭제횟수
        while(!linkedList.isEmpty()){ // 연결리스트에 값이 없어질 때까지 삭제 연산을 반복
            targetIndex = (targetIndex+deleteIndex-1)%(total-cnt);
            int num = linkedList.get(targetIndex); // 삭제할 index에 있는 값을 가져옴
            linkedList.remove(targetIndex); // 삭제함
            if(linkedList.size()==0){ // 만약 마지막 삭제연산이라면 >으로 출력하고 끝
                sb.append(num+">");
                break;
            }else{
                sb.append(num+ ", "); // 마지막 연산이 아니라면 ,을 출력하고 삭제 연산 계속한다.
            }
            cnt++;//삭제 횟수 저장

        }
        System.out.println(sb);
    }
}
