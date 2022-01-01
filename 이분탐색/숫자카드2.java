import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.*;

public class 숫자카드2 {
    public static void main(String[] args) throws Exception{
        new 숫자카드2().solution();
    }
    public static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int total = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[total];
        int i = 0;
        while(st.hasMoreTokens()){
            arr[i++] = Integer.parseInt(st.nextToken());
        }

        int searchTotal =  Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        Map<Integer, Integer> mapping = new HashMap<>();
        int[] searchArr = new int[searchTotal];
        int j = 0;
        while(st.hasMoreTokens()){
            int num = Integer.parseInt(st.nextToken());
            searchArr[j++] = num;
            mapping.put(num, 0);
        }
        binarySearch(arr, mapping, searchArr); // 이분탐색하여 해당 숫자가 배열에 있는지 확인

    }


    public static void binarySearch(int[] arr, Map<Integer, Integer> mapping, int[] searchArr){
        StringBuilder sb = new StringBuilder();
        int[] origin = searchArr.clone(); // 출력할 떄 원래 순서대로 출력하기 위해 저장한다.
        Arrays.sort(searchArr); // 이분탐색을 하기 위해 정렬되어 있어야 한다.

        for(int target : arr){ // 찾고자하는 숫자가 들어있는 배열을 하나씩 꺼내서 이분탐색
            int start = 0;
            int end = arr.length-1;

            while(start <= end){
                int mid = (start + end)/2;
                if(searchArr[mid] == target){
                    mapping.put(target, mapping.get(target)+1);// 같으면 map에서 값 업데이트
                    break;
                }else if(searchArr[mid] > target){ // 찾고자하는 숫자가 작으면 인덱스를 줄인다.
                    end = mid-1;
                }else{
                    start = mid+1; // 찾고자하는 숫자가 크면 인덱스를 줄인다.
                }
            }

        }

        for(int key : origin){
            sb.append(mapping.get(key)).append("\n");
        }
        System.out.println(sb);

    }
}
