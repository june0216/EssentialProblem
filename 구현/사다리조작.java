import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 사다리조작 {
    public static void main(String[] args) throws Exception{
        new 사다리조작().solution();
    }

    int W;
    int H;

    int line;

    //i번째가 뭔데
    Map<Integer, List<Node>> nodeList;
    boolean[] match;

    int result;

    List<Integer> notMathList;
    boolean[][] bridge;
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        line = Integer.parseInt(st.nextToken());
        nodeList = new HashMap<>();
        result = 0;
        notMathList = new ArrayList<>();
        //if(H == 0) result = 0;

        bridge = new boolean[H+1][W+1];

        match = new boolean[H+1];
        for(int i = 0; i < H; i++){
            nodeList.put(i+1, new ArrayList<>());
        }

        for(int i = 0; i < H; i++){ // 사다리 정보 만들기
            st = new StringTokenizer(br.readLine());
            int colLine = Integer.parseInt(st.nextToken());
            int rowLine = Integer.parseInt(st.nextToken());
            //bridge[colLine][rowLine] = true;
            Node node = new Node(new Pos(colLine, rowLine), new Pos(colLine+1, rowLine));
            nodeList.get(i+1).add(node);
        }

        for(int i = 0 ; i < H; i++){
            if(checkMatch(i)){
                match[i+1] = true;
            }else{
                notMathList.add(i+1);
            }
        }
        int idx = 0;

        while(true){ // 사다리 놓기
            if(idx >= notMathList.size()){
                result = -1;
                break;
            }
            // 연속하지 않는 경우
            if(!extra(idx)){ // 모든 곳에 넣었는데 들어갈 곳이 없다면 -1
                result = -1;
                break;
            }
            for(int i = 0; i < notMathList.size(); i++){
                if(checkMatch(i)) idx++;
            }

        }



    }

    public boolean extra(int index){
        for(int i = 0 ; i <= H; i++){
            List<Node> nodes = nodeList.get(i);
            for(int j = 0 ; j <= W; j++){


            }
        }
        return false;
    }
    // 재귀로 조합




    public boolean checkMatch(int index){
        List<Node> nodeInfo = nodeList.get(index);
        Collections.sort(nodeInfo, (s1, s2) ->  s1.from.y - s2.from.y);
        int i = 0;
        int cnt = 0;
        Node node = nodeInfo.get(0);
        while(true){
            if(i == nodeInfo.size()) break;

            // node.from과 연결된 다른 거를 찾을 때까지 i증가
            while(i <= nodeInfo.size()){
                Node next = nodeInfo.get(i++);
                if(next.from.x == node.to.x || next.to.x == node.to.x){
                    node = next;
                    break;
                }
            }

        }
        if(node.to.x == index) return true;
        else return false;
    }

    public class Node {
        Pos from;
        Pos to;
        public Node(Pos from, Pos to){
            this.from = from;
            this.to = to;

        }

    }

    public class Pos{
        int x;
        int y;

        public Pos(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
