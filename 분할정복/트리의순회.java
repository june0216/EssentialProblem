import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 트리의순회 {
    public static void main(String[] args) throws Exception{
        new 트리의순회().solution();
    }

    int total;
    int[] inOrder;
    int[] postOrder;

    int[] preOrder;

    int idx;
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        total = Integer.parseInt(br.readLine());
        StringTokenizer st;
        idx = 0;


        inOrder = new int[total];
        preOrder = new int[total];
        postOrder = new int [total];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < total; i++){
            inOrder[i] =Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < total; i++){
            postOrder[i] =Integer.parseInt(st.nextToken());
        }

        preOrder(0, total-1, 0, total-1);

        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < total; i++){
            int num = preOrder[i];
            sb.append(num).append(" ");
        }
        System.out.println(sb);

    }

    public void preOrder(int is, int ie, int ps, int pe) {

        // is는 인오더 범위의 시작 위치, ie는 인오더의 끝 위치
        // ps는 포스트오더 범위의 시작 위치, pe는 끝위치

        if (is <= ie && ps <= pe) {

            preOrder[idx++] = postOrder[pe];
            int pos = is;
            for (int i = is; i <= ie; i++) { // 인오더에서 루트 노드의 위치를 찾음
                if (inOrder[i] == postOrder[pe]) {
                    pos = i;
                    break;
                }
            }

            // 왼쪽 자식 트리
            preOrder(is, pos - 1, ps, ps + pos - is - 1);
            // 오른쪽 자식 트리
            preOrder(pos + 1, ie, ps + pos - is, pe - 1);
        }
    }

}
