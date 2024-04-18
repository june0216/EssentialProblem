public class 등굣길 {
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;


        int[][] streets = new int[n][m];
        // 장애물 처리
        for(int i = 0; i < puddles.length;i++){
            streets[puddles[i][1]-1][puddles[i][0]-1] = -1;
        }

        streets[0][0] = 1;

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(streets[i][j] == -1){ //웅덩이라면 패스
                    streets[i][j]=0; // 위아래에 웅덩이가 있으면 경우의 수가 0이므로 업데이트
                    continue;
                }
                if(j != 0){ // 아래로 갈 수 있다면
                    streets[i][j] += streets[i][j-1] % 1000000007;
                }
                if(i != 0){ // 오른쪽으로 갈 수 있다면
                    streets[i][j] += streets[i-1][j]% 1000000007;
                }

            }
        }
        return streets[n-1][m-1] % 1000000007;
    }
}
