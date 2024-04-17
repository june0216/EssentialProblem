import java.util.*;
public class LIS_DP {
    public static void main(String[] args) throws Exception{
        new LIS_DP().solution(new int[]{1, 5, 6, 2});
    }
    int[] cache;

    int[] ar;
    public void solution(int[] arr){
        cache = new int[arr.length+1];
        Arrays.fill(cache, -1);

        ar = new int[arr.length];
        for(int i = 0 ; i < arr.length;i++){
            ar[i] = arr[i];
        }
        System.out.println(find(0));

    }

    int res;
    public int find(int start){
        if(cache[start] != -1){ // 이미 해당 위치에서
            return cache[start];
        }
        for(int i = start+1; i < ar.length; i++){
            if(ar[i-1] < ar[i]){
                 res = Math.max(res, find(i)+1);
            }
        }
        res = Math.max(res, cache[start]);
        return res;
    }

}
