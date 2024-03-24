public class 회전시키기 {
    int[][] arr; // 이 배열은 외부에서 정의되어야 하며, 여기서는 예시로 추가했습니다.

    public void Rotate(int[] query) {
        int si = query[0] - 1;
        int sj = query[1] - 1;
        int fi = query[2] - 1;
        int fj = query[3] - 1;
        int qrows = fi - si;
        int qcolumns = fj - sj;

        // 방향을 나타내는 배열
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int[] movecounts = {qcolumns, qrows, qcolumns, qrows - 1};

        int temp = arr[si][sj]; // 첫 번째 값을 임시 저장
        int prevValue = temp;

        for (int i = 0; i < directions.length; ++i) {
            int[] dir = directions[i];
            int moves = movecounts[i];

            for (int j = 0; j < moves; ++j) {
                int nexti = si + dir[0];
                int nextj = sj + dir[1];
                // swap 대신에 순차적으로 이전 값을 다음 위치로 이동
                temp = arr[nexti][nextj];
                arr[nexti][nextj] = prevValue;
                prevValue = temp;

                si = nexti;
                sj = nextj;
            }
        }

        // 마지막으로 첫 번째 값으로 되돌림
        arr[query[0] - 1][query[1]] = prevValue;
    }
}
