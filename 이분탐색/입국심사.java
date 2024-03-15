
public class 입국심사 {

    public static void main(String[] args) throws Exception{
        new 입국심사().solution(6, new int[]{7, 10});
    }

    public static void solution(int n, int[] times) throws Exception {
        long answer = binarySearch(n, times);
        System.out.println(answer);
    }

    public static long binarySearch(int n, int[] times){
        long start = 1;
        long max = 0;
        long answer = 0;
        for(int t : times){
            if(t> max) max = t;
        }
        long end = n* max; // 최대는 최대로 오래걸리는 사람이 모든 사람을 심사하는 경우이다.

        while(start <= end){
            long mid = start + ((end-start)/2);
            long avaliable = cal(times, mid); // 각각 심사위원이 mid시간동안 몇명 사람을 검사할 수 있는지
            if(avaliable >= n){ // 검사할 수 있는 사람이 주어진 사람보다 크면 시간을 줄일 수 있음
                end = mid-1;
                answer = mid;
            }else{ // 주어진 사람보다 작다면 시간을 늘려서 주어진 사람들이 다 검사할 수 있도록 해야한다.
                start = mid+1;
            }
        }
        return answer;
    }

    public static long cal(int[] times, long time){
        long avaliable = 0;
        for(int examine : times){
            avaliable += time/examine; // 총 time시간이 주어진다면 심사하는 사람은 몇명 검사가 가능한지 누적 합 구하기
        }
        return avaliable;
    }
}
