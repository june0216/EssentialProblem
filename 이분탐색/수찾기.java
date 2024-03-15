import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import java.util.*;

public class 수찾기 {
    public static void main(String[] args) throws Exception{
        new 수찾기().solution();
    }
    public static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int total = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        long[] arr = new long[total];
        int i = 0;
        while(st.hasMoreTokens()){
            arr[i++] = Integer.parseInt(st.nextToken());
        }

        int searchTotal =  Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        long[] searchArr = new long[searchTotal];
        int j = 0;
        while(st.hasMoreTokens()){
            searchArr[j++] = Integer.parseInt(st.nextToken());
        }
        binarySearch(arr, searchArr); // 이분탐색하여 해당 숫자가 배열에 있는지 확인

    }

    public static void binarySearch(long[] arr, long[] searchArr){
        StringBuilder sb = new StringBuilder();
        Arrays.sort(arr); // 이분탐색을 하기 위해 정렬되어 있어야 한다.

        for(long target : searchArr){ // 찾고자하는 숫자가 들어있는 배열을 하나씩 꺼내서 이분탐색
            int start = 0;
            int end = arr.length-1;
            int flag = 0;
            while(start <= end){
                int mid = (start + end)/2;
                if(arr[mid] == target){
                    flag = 1; // 같으면 1을 출력
                    break;
                }else if(arr[mid] > target){ // 찾고자하는 숫자가 작으면 인덱스를 줄인다.
                    end = mid-1;
                }else{
                    start = mid+1; // 찾고자하는 숫자가 크면 인덱스를 줄인다.
                }
            }
            sb.append(flag).append("\n");

        }
        System.out.println(sb);

    }
}
