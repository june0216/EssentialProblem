import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 배열돌리기4 {
    public static void main(String[] args) throws Exception{
        new 배열돌리기4().solution();
    }

    public static int dx[] = new int[]{0, 1, 0, -1};
    public static int dy[] = new int[]{1, 0, -1, 0};
    int direction;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        StringBuilder sb = new StringBuilder();
        int Y = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int spinCnt = Integer.parseInt(st.nextToken());

        int[][] original = new int[Y][X];
        for(int i = 0; i < Y; i++){
            st =new StringTokenizer(br.readLine());
            for(int j = 0; j < X; j++){
                original[i][j] = Integer.parseInt(st.nextToken());

            }
        }

        List<int[]> cal = new ArrayList<>();
        int[] caseNum = new int[spinCnt];
        while(spinCnt-- >0){
            st =new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c= Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            cal.add(new int[]{r, c, s});
            caseNum[spinCnt] = spinCnt;
        }


        List<List<Integer>> permute= new ArrayList<>();

        //순열
        permutation(caseNum, 0, permute);
        int result = Integer.MAX_VALUE;
        for(List<Integer> perList : permute){
            // origin으로 수정
            int[][] map = new int[Y][X];
            for(int i = 0; i < Y; i++){
                System.arraycopy(original[i], 0, map[i], 0, original[i].length);
            }
            for(int idx : perList){
                int startY = cal.get(idx)[0]-cal.get(idx)[2];
                int startX = cal.get(idx)[1]-cal.get(idx)[2];

                int endY = cal.get(idx)[0]+cal.get(idx)[2];
                int endX = cal.get(idx)[1]+cal.get(idx)[2];
                direction = 0;
                int nx = startX;
                int ny = startY;
                int beforeValue = map[startY][startX];
                int h = endY - startY;
                int w = endX - startX;
                while(true){
                    int tmp = map[ny][nx];
                    map[ny][nx] = beforeValue;
                    beforeValue = tmp;

                    // 다음
                    int nextX= dx[direction] + nx;
                    int nextY = dy[direction] + ny;
                    if(nx < startX || ny < startY || nx >= endX || ny >= endY){
                        direction = (direction+1)%4;
                    }

                    // 다음 좌표
                    nx= dx[direction] + nx;
                    ny = dy[direction] + ny;
                    if(nx == startX && ny == startY){
                        startX +=1;
                        startY +=1;
                        endX -= 1;
                        endY -= 1;
                        // 시작과 끝이 같아지거나, 차이가 1일 때 그만
                        if((startX == endX && startY == endY) || (endX - startX ) == 1 || (endY - startY) == 1) break;
                        beforeValue = map[startY][startX];
                    }

                }


            }
            int sum = 0;
            int min = Integer.MAX_VALUE;
            for(int i = 0; i< Y; i++){
                sum = 0;
                for(int j = 0; j < X; j++){
                    sum += map[i][j];
                }
                min = Math.min(min, sum);
            }
            result = Math.min(result, min);

        }





    }

    public void permutation(int[] num, int start, List<List<Integer>> result){
        if(num.length == start){
            List<Integer> cur = new ArrayList<>();
            for(int n : num){
                cur.add(n);
            }
            result.add(cur);
        }else{
            for(int i = start; i < num.length; i++){
                swap(start, num, i);

                permutation(num, start + 1, result);
                swap(start, num, i); // 원상태로
            }
        }
    }

    public void swap(int n1, int[] num , int n2){
        int tmp = num[n1];
        num[n1] = num[n2];
        num[n2] = tmp;
    }

}
