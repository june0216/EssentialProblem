public class 이진탐색코드 {

    public boolean binarySearch(int[] arr, int start, int end, int target){
        if(start > end) return false;
        int mid = start + ((end-start)/2); // 오버플로우 방지
        if(arr[mid] == target){
            return true;
        }else if(arr[mid] > target){
            return binarySearch(arr, start, mid-1, target);
        }else if(arr[mid] < target){
            return binarySearch(arr, mid+1, end, target);
        }else{
            return false;
        }
    }
}
