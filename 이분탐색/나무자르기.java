import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 나무자르기 {

    public static void main(String[] args) throws Exception{
        new 나무자르기().solution();
    }
    public static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int treeNum = Integer.parseInt(st.nextToken());
        long targetHeight = Integer.parseInt(st.nextToken());

        long[] tree = new long[treeNum];
        st = new StringTokenizer(br.readLine());
        long max = 0;
        int i = 0;
        while(st.hasMoreTokens()){
            tree[i] = Integer.parseInt(st.nextToken());
            if(max < tree[i]) max = tree[i]; // 주어진 트리 중 가장 큰 값이 자를 수 있는 최대 높이이다.
            i++;
        }

        long start = 1;
        long end = max;
        long answer = 0;
        while(start <= end){
            long mid = start + ((end-start)/2);
            long height = cal(tree, mid);
            if(height >= targetHeight){ // 자른 결과가 적어도 가져가야하는 것보다 크다면 일단 정답이고 그래도 더 쪼개기 위해 절단 높이를 키운다.
                start = mid+1;
                answer =  mid;
            }else{ // 자른 결과가 가져가야하는 길이보다 작으면 자르는 높이를 줄여서 잘라야 하므로 높이를 줄여서 탐색한다.
                end = mid-1;

            }
        }

        System.out.println(answer);
    }


    // 나무를 잘라서 집에 어느정도로 가져갈 수 있는지 계산
    public static long cal(long[] tree, long targetHeight){
        long result = 0;
        for(long num : tree){
            if(num >= targetHeight){ // 자를 수 있다면 집에 가져갈 수 있음
                result += (num - targetHeight);
            }
            // 길이보다 작으면 자를 수 없으므로 집에 가져갈 수 있는 토막이 없음


        }
        return result;
    }
}
