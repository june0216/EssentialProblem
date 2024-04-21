import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.*;

public class 상자넣기 {
    public static void main(String[] args) throws Exception{
        new 상자넣기().solution();
    }

    int total;
    int[] boxes;
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        total = Integer.parseInt(br.readLine());
        boxes = new int[total];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i =0 ; i < total; i++){
            boxes[i] = Integer.parseInt(st.nextToken());
        }
        List<Integer> sorted = new ArrayList<>();
        sorted.add(0);
        int index = 0;
        for(int i = 0; i < total;i++){
            if(sorted.get(index) < boxes[i]){
                sorted.add(boxes[i]);
                index++;
            }else{
                int idx= binarySearch(sorted, boxes[i]);
                sorted.set(idx, boxes[i]);
            }
        }
        System.out.println(index);

    }

    public int binarySearch(List<Integer> sorted, int target){
        int start = 0;
        int end = sorted.size();
        int res = 0;
        while(start <= end){
            int mid = (start+end)/2;

            if(sorted.get(mid) >= target){
                end = mid-1;
                res = mid;
            }else{
                start = mid +1;
            }
        }
        return res;
    }

}
