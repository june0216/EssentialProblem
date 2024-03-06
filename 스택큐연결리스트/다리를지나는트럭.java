import java.util.*;
public class 다리를지나는트럭 {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int time = 0;
        Queue<Integer> queue = new ArrayDeque<>(); // 넣은 순서대로 나가기 때문에 queue를 사용하여 트럭 정보 관리
        for(int num : truck_weights){ // 트럭 정보 넣기
            queue.offer(num);
        }
        Queue<Integer[]> bridge = new ArrayDeque<>(); // 다리 현황 관리하기 위한 큐 -> 들어간 순서대로 나가기 때문에 큐를 사용
        int total = 0; // 현 다리 상태의 무게를 저장하기 위한 변수

        while(true){
            if(bridge.isEmpty() && queue.isEmpty()) break; // 트럭이 다 다리에 들어갔고 동시에 다리에 아무 트럭도 없으면 모두 다 지나간 것이므로 반복문 종료
            time+=1; //
            if(!bridge.isEmpty()){ // 다리가 비어있지 않으면 다리에 있는 트럭이 얼마나 왔는지 확인
                Integer[] truckInfo = bridge.peek(); // 임시로 맨 앞에 있는 (가장 오래된 )트럭 정보 꺼냄
                int truck = truckInfo[0]; // 트럭의 무게
                int startTime = truckInfo[1]; // 트럭이 들어간 시간
                //다리를 다 건넌 트럭이 있다면
                if(time- startTime >= bridge_length){ // (지금 시간 - 트럭이 들어간 시간) 다리를 지나기 위한 시간보다 크거나 같다면 다 다리를 다 지난 것이므로 다리에서 꺼내준다.
                    bridge.poll(); // 진짜로 다리에서 꺼낸다.
                    total -= truck; // 현 다리의 총 무게를 업데이트
                }
            }


            //트럭 넣기 -> 브릿지에 트럭이 가득 차지 않았거나, 무게가 넘지 않았다면
            if(!queue.isEmpty()) {
                int temp = queue.peek();
                if (bridge.size() + 1 <= bridge_length && total + temp <= weight) { // 트럭이 다리에 들어갈 수 있다면
                    int newTruck = queue.poll(); // 트럭을 다리에 넣는다.
                    bridge.offer(new Integer[]{newTruck, time});
                    total += newTruck;


                }
            }

        }

        return time;
    }

    public static void main(String[] arge)throws Exception{
        new 다리를지나는트럭().solution(2, 10, new int[]{7,4,5,6});
    }
}
