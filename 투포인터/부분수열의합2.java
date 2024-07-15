import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 부분수열의합2 {
    public static void main(String[] args) throws Exception {
        new 부분수열의합2().solution();

    }


    int[] num;
    public void solution() throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        num = new int[N+1];

        for(int i = 0 ; i < N; i++){
            num[i] = Integer.parseInt(st.nextToken());
        }


        List<Long> leftList = new ArrayList<>();
        List<Long> rightList = new ArrayList<>();

        makeSum(0, N/2, 0, leftList); // 절반으로 나누어 부분합을 구한다.
        makeSum(N/2, N, 0, rightList); // 나머지에 대해 부분합을 구한다.

        Collections.sort(leftList); // 정렬한다.
        Collections.sort(rightList);




        int start = 0 ;
        int end = rightList.size()-1;
        long total = 0;

        while(start < leftList.size() && end >= 0){
            long sum = rightList.get(end) + leftList.get(start);
            if(sum == target){
                long rightVal = rightList.get(end);
                long leftVal = leftList.get(start);

                long leftCnt = 0; //2^20이므로 int를 넘어서는 경우가 있을 수 있다. -> long 타입
                while(start < leftList.size() && leftList.get(start) == leftVal){ // 같은 값이 있는 것들을 처리하기 위해 확인
                    start++;
                    leftCnt++;

                }

                long rightCnt = 0;
                while(end >= 0 && rightList.get(end) == rightVal){ // 연속적으로 같은 값이 있는지 확인하고 같은 값이면 인덱스 이동
                    end--;
                    rightCnt++; // 같은 값이므로 개수를 늘리낟.
                }

                total += leftCnt * rightCnt; // 가능한 경우의 수 업데이트
            }
            else if (sum < target){ // sum 보다 작으면 start를 증가시켜서 더 큰 값이 되도록함
                start++;
            }else{
                end--; // sum보다 크거나 작으면 end 줄여서 더 작은 값이 되도록함
            }
        }

        if(target==0) total--; // right 리스트에 있는 부분수열의 합만으로(혹은 left 리스트에 있는) S가 되는 경우가 있을 경우를 생각해서 각각 리스트에 0이 있다.  -> 하지만 목표값이 0일 경우 이 공집합을 더해서 0을 만족시키기 때문에 이 경우는 빼준다.
        System.out.println(total);

    }

    public void makeSum(int start, int end, long sum,List<Long> li){ // 모든 조합의 합을 구한다.
        if(start == end){
            li.add(sum);
            return;
        }
        makeSum(start+1, end, sum+num[start], li); // 더 하는 경우
        makeSum(start+1, end, sum, li); // 더하지 않는 경우
    }
}
