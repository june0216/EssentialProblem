import java.util.*;
public class LIS_binarySearch {
    public static void main(String[] args) throws Exception{
        new LIS_binarySearch().solution(new int[]{1, 5, 6, 2});
    }

    int total;
    public void solution(int[] arr){
        List<Integer> sorted = new ArrayList<>();
        sorted.add(0);
        int index = 0;
        for(int i = 0 ; i< arr.length; i++){
            if(arr[i] > sorted.get(index)){ // 크다면 배열을 추가한다.
                sorted.add(arr[i]); // 배열을 추가한다. -> 배열에 추가하는 과정 중 가장 큰 길이일때가 정답이다.
                index++;
            }else{
                int idx = binarySearch(sorted, arr[i]); // 해당 index가 들어갈 위치를 찾는다/
                sorted.set(idx, arr[i]); // 업데이트를 해준다.
            }
        }
        System.out.println(index);
    }

    public int binarySearch(List<Integer> sorted, int target){
        int start = 0;
        int end = sorted.size();
        int result = 0;
        while(start <= end){
            int mid = start + ((end-start)/2);
            if(sorted.get(mid) >= target){
                end = mid-1;
                result = mid;
            }else{
                start = mid+1;
            }
        }
        return result;
    }

}
