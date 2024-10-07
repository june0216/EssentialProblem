import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.*;

public class 친구네트워크 {
    public static void main(String[] args) throws Exception{
        new 친구네트워크().solution();
    }

    public int find(int idx){
        // 부모가 자기 자신이 아니면 재귀적으로 부모를 찾아서 경로 압축
        if(parent.get(idx) != idx){
            parent.set(idx, find(parent.get(idx)));  // 경로 압축 적용
        }
        return parent.get(idx);  // 루트 노드 반환
    }

    public int merge(int idx1, int idx2){
        int p1 = find(idx1);  // idx1의 루트 노드 찾기
        int p2 = find(idx2);  // idx2의 루트 노드 찾기

        if(p1 != p2){  // 두 노드가 같은 집합에 있지 않으면
            if(rank.get(p1) >= rank.get(p2)){
                parent.set(p2, p1);  // p2를 p1에 합침
                rank.set(p1, rank.get(p1) + rank.get(p2));  // p1의 랭크를 갱신
            }else{
                parent.set(p1, p2);  // p1을 p2에 합침
                rank.set(p2, rank.get(p1) + rank.get(p2));  // p2의 랭크를 갱신
            }
        }
        // 병합된 집합의 크기 반환
        return Math.max(rank.get(p1), rank.get(p2));
    }


    public List<Integer> parent;
    public List<Integer> rank;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int testCase = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while(testCase-->0){
            int relation = Integer.parseInt(br.readLine());
            parent = new ArrayList<>();
            rank = new ArrayList<>();
            Map<String, Integer> nameIndex = new HashMap<>();

            int res = 0;
            while(relation-->0){
                st = new StringTokenizer(br.readLine());
                String friend1 = st.nextToken();
                String friend2 = st.nextToken();
                // 기존에 없는 거였으면 새로운 관계
                // 기존에 있는 아이라면 루트를 찾아서
                boolean isExist = false;
                int friend1Idx = 0;
                if(nameIndex.containsKey(friend1)){
                    friend1Idx = nameIndex.get(friend1);
                    isExist = true;
                }else{
                    friend1Idx = nameIndex.size();
                    nameIndex.put(friend1, friend1Idx);
                    rank.add(1);
                    parent.add(friend1Idx);
                }
                int friend2Idx = 0;
                if(nameIndex.containsKey(friend2)){
                    friend2Idx = nameIndex.get(friend2);


                    isExist = true;
                }else{
                    friend2Idx = nameIndex.size();
                    nameIndex.put(friend2, friend2Idx);
                    parent.add(friend2Idx);
                    rank.add(1);
                }
                int r = merge(friend1Idx, friend2Idx);

                sb.append(r).append("\n");
            }
        }
        System.out.println(sb);

    }
}
