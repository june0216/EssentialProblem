import java.util.*;
public class 도넛과막대그래프 {
    int H;
    int maxVertex;
    int W;
    List<Integer>[] info;

    int[] incommingVertex;
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];

        H = edges.length;

        W = edges[0].length;

        for(int[] num : edges){
            maxVertex = Math.max(maxVertex, Math.max(num[0], num[1]));
        }
        info = new List[maxVertex+1]; // 주는 방향
        incommingVertex = new int[maxVertex+1];// 받는 방향

        for(int i = 0 ;i < maxVertex+1; i++){
            info[i] = new ArrayList<>(); // 배열 초기화
        }
        for(int i = 0; i < H; i++){
            info[edges[i][0]].add(edges[i][1]); // 정점의 정보 (오는 정점만 저장)
            incommingVertex[edges[i][1]]++; // 오는 정점의 개수
        }


        for(int i = 1 ; i <maxVertex+1 ; i++){
            if(info[i].size() >=2 && incommingVertex[i] >= 2){ // 오는 정점이 2개 , 가는 정점이 2개이면 8자 패턴이다.
                answer[3]++;
            }else if(info[i].size() == 0 && incommingVertex[i] > 0){ // 받는 정점이 1개이고 가는 정점이 0개이면 막대 패턴 (여기서 가는 정점1개, 받는 정점 0개로 해도 되지만 시작 정점과 겹친다
                answer[2]++;
            }else if(info[i].size() >= 2 && incommingVertex[i] == 0){ // 시작 정점은 2개 이상 가는 정점이다.
                answer[0] = i;
            }
        }

        answer[1] = info[answer[0]].size() - answer[2] - answer[3]; // 도넛 패턴은 2가지 패턴의 나머지이다


        return answer;
    }

    public static void main(String[] args) throws Exception{
        new 도넛과막대그래프().solution(new int[][]{{2, 3}, {4, 3}, {1, 1}, {2, 1}});
    }

}
