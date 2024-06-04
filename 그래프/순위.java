import java.util.*;

class 순위 {

    int info[][];
    int num;
    public int solution(int n, int[][] results) {
        int answer = 0;
        num=n;
        // 승패 결과가 4500개 이므로 적음
        //정확하게 순위를 매길 수 있는 선수의 수 출력
        //1은 n개와의 관계, 2는 n-1
        info = new int[n+1][n+1];

        for(int i = 0; i < results.length; i++){
            int winner = results[i][0];
            int loser = results[i][1];
            info[loser][winner] = -1;
            info[winner][loser] = 1;
        }

        int result = 0;
        for(int i = 1; i <= n; i++){
            int winCnt = dfs(new HashSet<>(), i, 1);
            int loseCnt = dfs(new HashSet<>(), i, -1);
            if((winCnt+loseCnt) == n-1){
                result++;
            }
        }
        return result;
    }

    public int dfs(Set<Integer> set, int idx, int winOrLose){
        for(int i = 1; i <= num; i++){
            if(info[idx][i] == winOrLose&& !set.contains(i)){
                set.add(i);
                dfs(set, i, winOrLose);
            }
        }
        return set.size();

    }

}