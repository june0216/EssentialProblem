import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 구간합구하기1 {
    public static void main(String[] args) throws Exception{
        new 구간합구하기1().solution();

    }

    long[] tree;

    static int PRINT = 2;
    static int UPDATE = 1;
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int printTime = Integer.parseInt(st.nextToken());
        int updateTime = Integer.parseInt(st.nextToken());
        int totalTime = printTime + updateTime;


        long[] num = new long[N+1];
        int k = (int) Math.ceil(Math.log(num.length) / Math.log(2));
        int height = k + 1;
        int treeSize =(int) Math.pow(2, height);
        tree = new long[treeSize];
        for(int i =1; i <= N; i++){
            num[i] = Long.parseLong(br.readLine());
        }

        makeTree(1, 1, N, num);

        while(totalTime-- > 0){
            st = new StringTokenizer(br.readLine());
            int query = Integer.parseInt(st.nextToken());
            if(query==UPDATE){
                int index = Integer.parseInt(st.nextToken());
                long value =  Long.parseLong(st.nextToken());

                updateTree(1, 1, N, index, value - num[index]); // 차이를 트리에 반영한다.
                num[index] = value; //num 배열 수정함
            }else if(query == PRINT){
                int queryStart = Integer.parseInt(st.nextToken());
                int queryEnd = Integer.parseInt(st.nextToken());
                sb.append((sumTree(1, 1, N,queryStart, queryEnd))).append("\n");

            }
        }
        System.out.println(sb);


    }

    public long makeTree(int node, int start, int end, long[] number){ // 트리 초기화
        if(start == end){
            //System.out.println(node + " " + start);
            return tree[node] = number[start];

        }
        int mid= (start+end)/2;
        return tree[node]  =  makeTree(node*2, start, mid, number) + makeTree(node*2+1, mid+1, end, number);
    }

    public void updateTree(int node, int start, int end , int index, long diff){
        if(start > index || end < index){ //바꾸고자 하는 인덱스밖의 범위라면
            return;
        }

        tree[node] += diff; // 차를 업데이트 함
        if(start == index && end == index){ // 리프노드라면 아래를 탐색하지 않음
            return;
        }

        int mid = (start + end) /2;
        updateTree(node*2, start, mid, index, diff);
        updateTree(node*2+1, mid+1, end, index, diff);

    }

    public long sumTree(int node, int start, int end, int queryStart, int queryEnd){
        if(queryStart > end || queryEnd < start){ // 범위 밖이라면 합에 영향을 미치지 않는 0을 반환
            return 0;
        }

        if(queryStart <= start && end <= queryEnd){ // 지금 구간이 쿼리 구간에 완전히 속한다면 바로 그 위치의 값을 반환한다.
            return tree[node];
        }
        // 구간에 속하지 않는다면 자식들을 탐색한다.
        int mid = (start + end) /2;
        return sumTree(node*2, start, mid, queryStart, queryEnd)
                + sumTree(node*2+1, mid+1, end, queryStart, queryEnd);

    }
}
