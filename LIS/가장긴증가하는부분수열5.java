import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 가장긴증가하는부분수열5 {
    public static void main(String[] args) throws Exception{
        new 가장긴증가하는부분수열5().solution();
    }

    int[] arr;
    int[] acsArr;
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        arr = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        acsArr = new int[N+1]; // LIS 배열 유지
        acsArr[0] = -1000000001;
        int len = 0;
        int[] longestLength = new int[N+1];

        for(int i =1; i <= N; i++){
            if(acsArr[len] < arr[i]){ // 오름차순으로 정렬되어 있던 배열의 마지막 값보다 크면 무조건 추가
                acsArr[++len] = arr[i]; // 오름차순 배열에 요소 추가
                longestLength[i] = len; // 지금 위치에서 증가하는 부분 수열의 길이 저장
                continue;
            }
            // 증가하는 부분 수열의 큰 값보다 작거나 같으면 자기가 들어갈 위치를 찾아 들어가야함
            int changeIdx = binarySearch(arr[i], len);
            // 만약 10, 100 , 200 이 있었는데 15가 입력값으로 주어진다면 10, 15, 200이 되어야 하는 거 아닌가? 라고 했는데 생각해보니 어차피 15넣어도 최대가 아니므로 지나가면 된다.
            // 뒤에 지워줄 의무가 없어진다.
            if(changeIdx != -1){
                acsArr[changeIdx] = arr[i]; // 값 변경, 여기서 배열의 요소 하나하나가 곧 LIS를 의미하는 건 아니다. (최대 수를 찾기 위한 과정일 뿐)
                longestLength[i] = changeIdx; // 여기까지에서는 최대가 이거임
            }
        }

        // 최대 길이 출력
        StringBuilder sb = new StringBuilder();
        sb.append(len).append("\n");

        // 최대 길이의 배열 요소를 출력
        Deque<Integer> stack = new ArrayDeque<>(); // 뒤에서부터 넣고 최신순으로 출력할 것이기 때문에 스택 자료구조 선택
        for(int i = N; i > 0; i--){
            if(longestLength[i] == len){ // 지금 길이와 같은 인덱스를 만나면
                stack.addFirst(arr[i]); // 해당 인덱스의 숫자를 출력 후
                len--; //그 다음의 배열 요소를 추적
            }
        }
        for(int num : stack){
            sb.append(num).append(" ");
        }
        System.out.println(sb);

    }

    public int binarySearch(int num, int len) { // lower binarysearch
        int start = 0;
        int end = len;
        while (start < end) {
            int mid = start + ((end - start) / 2);
            if (acsArr[mid] < num) { // 작으면 옮기기
                start = mid + 1;
            } else { // 크거나 같으면 된다.
                end = mid;
            }
        }
        return start;
    }
}
/*
6
10 20 10 30 20 50
 */
