import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 단지번호붙이기 {

    int total;
    char[][] mapInfo;

    int count;
    List<Integer> result;
    boolean[][] visited;

    int[] dx = new int[]{-1, 1, 0, 0};
    int[] dy = new int[]{0, 0, -1, 1};

    public void solution() throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        total = Integer.parseInt(br.readLine());
        result = new ArrayList<>(); // 결과를 저장
        mapInfo = new char[total][total]; // 정보
        visited = new boolean[total][total]; // 방문 여부
        for(int i = 0 ; i < total; i++){
            mapInfo[i] = br.readLine().toCharArray(); // char 타입을 하나씩 저장
        }
        count =1; // 각 그룹의 개수를 저장할 전역 변수
        for(int i = 0 ; i < total; i++){ // 모든 정점을 탐색하며 각각 그룹들을 탐색한다.
            for(int j = 0; j < total; j++){
                if(!visited[i][j] && mapInfo[i][j] == '1'){
                    dfs(i, j);
                    result.add(count); // 탐색한 결과를 저장한다.
                    count = 1;
                }
            }
        }

        System.out.println(result.size());
        result.stream() // 리스트를 스트림으로 변환
                .sorted() // 정렬
                .forEach(System.out::println);

    }

    public void dfs(int startX, int startY){
        visited[startX][startY] = true;
        for(int i = 0 ; i < 4 ; i++){ // 4방으로 검사한다.
            int nx = dx[i] + startX;
            int ny = dy[i] + startY;
            // 표를 넘지 않고 + 방문하지 않았고 + 1인 경우에만 방문
            if(nx >= 0 && ny >= 0 && nx < total && ny < total &&!visited[nx][ny] &&mapInfo[nx][ny]== '1'){
                dfs(nx, ny);
                count++;
            }
        }
    }

    public static void main(String[] args) throws Exception{
        new 단지번호붙이기().solution();
    }
}
