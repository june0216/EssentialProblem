import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 최댓값과최솟값 {
    public static void main(String[] args) throws Exception{
        new 최댓값과최솟값().solution();
    }

    int[] maxTree;
    int[] minTree;
    int [] num;
    int N;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        num = new int[N+1];
        int queryNum = Integer.parseInt(st.nextToken());

        for(int i = 1; i <= N; i++){
            num[i] = Integer.parseInt(br.readLine());
        }
        int h = (int) Math.ceil(Math.log(N)/Math.log(2));
        int treeSize = (int) Math.pow(2, h+1);
        maxTree = new int[treeSize];
        minTree = new int[treeSize];
        makeMinTree(1, 1, N);
        makeMaxTree(1, 1, N);
        StringBuilder sb = new StringBuilder();
        while(queryNum-- > 0){
            st = new StringTokenizer(br.readLine());
            int queryStart = Integer.parseInt(st.nextToken());
            int queryEnd = Integer.parseInt(st.nextToken());
            int max = findMax(1, 1, N, queryStart, queryEnd);
            int min = findMin(1, 1, N, queryStart, queryEnd);
            sb.append(min + " " + max).append("\n");
        }
        System.out.println(sb);
    }

    public int makeMinTree(int node, int start, int end){
        if(start == end){
            return minTree[node] = num[start];
        }
        int mid = (start + end) /2;
        return minTree[node] = Math.min(makeMinTree(node*2, start, mid), makeMinTree(node*2+1, mid+1, end));
    }

    public int makeMaxTree(int node, int start, int end){
        if(start == end){
            return maxTree[node] = num[start];
        }
        int mid = (start+ end) /2;
        return maxTree[node] = Math.max(makeMaxTree(node*2, start, mid), makeMaxTree(node*2+1, mid+1, end));
    }

    public int findMin(int node, int start, int end, int queryStart, int queryEnd){
        if(queryStart > end || queryEnd < start){
            return Integer.MAX_VALUE;
        }
        if(queryStart <= start && queryEnd >= end){
            return minTree[node];
        }

        int mid = (start+ end)/2;
        return Math.min(findMin(node*2, start, mid, queryStart, queryEnd), findMin(node*2+1, mid+1, end, queryStart, queryEnd));
    }

    public int findMax(int node, int start, int end, int queryStart, int queryEnd){
        if(queryStart > end || queryEnd < start){
            return Integer.MIN_VALUE;
        }
        if(queryStart <= start && queryEnd >= end){ // 범위 안에 딱 들어온다면
            return maxTree[node];
        }
        int mid = (start+end)/2;
        return Math.max(findMax(node*2, start, mid, queryStart, queryEnd), findMax(node*2+1, mid+1, end, queryStart, queryEnd));
    }
}
