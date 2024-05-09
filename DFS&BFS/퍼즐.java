import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


/*처음에는 2차원 배열을 매번 상태가 변할 때마다 저장하고 비교해야하나 생각했었다.
 하지만 2차원 배열을 문자열로 관리하면 보다 편리하게 관리할 수 있다.
 */
public class 퍼즐 {

    int[] dx = new int[]{1, -1, 0, 0};
    int[] dy = new int[]{0, 0, -1, 1};

    String correct  = "123456780";
    HashMap<String, Integer> map = new HashMap<>();


    Deque<Node> que;
    String start = "";

    public void solution() throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        que = new ArrayDeque<>();


        for(int i = 0 ;i < 3; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < 3; j++){
                start += st.nextToken();
            }
        }


        map.put(start, 0);
        System.out.println(dfs());


    }


    public int dfs(){
        int idx = start.indexOf('0'); // 처음 위치에서 0이 위치하는 인덱스를 가져온다.
        que.add(new Node(idx%3, idx/3, 0, start));
        while(!que.isEmpty()){
            Node cur =que.poll();

            if(map.containsKey(correct)){ // 정답과 같은 키가 존재한다는 것은 정답 위치가 만들어졌다는 것이므로 바로 정답 도출한다.
                return map.get(correct);
            }

            for(int i = 0; i< 4; i++){
                int nx = dx[i] + cur.x;
                int ny = dy[i] + cur.y;
                if(nx >= 0 && ny >= 0 && nx < 3 && ny < 3){
                    int nPos = ny*3 + nx; // 새로운 0 위치
                    int prePos = cur.y*3 + cur.x; // 현재 0 위치
                    StringBuilder sb = new StringBuilder(cur.change); // 그냥 String의 replace()를 사용하는 것은 교체할 때마다 새로운 객체를 만들기 때문에 효율적이지 않다.  StringBuilder로 한다면 가변 객체이므로 비교적 효율적이다.
                    char ch = cur.change.charAt(nPos); // 새로운 위치의 값을 꺼내옴
                    sb.setCharAt(prePos, ch); // 이전 0값에 새로운 위치에 있었던 값을 넣는다.
                    sb.setCharAt(nPos, '0'); // 새로운 위치에 0을 넣음
                    String next = sb.toString();

                    if(!map.containsKey(next)){ // map에 있다는 것은 이전에 한 번 이 위치가 되었으므로 큐에 넣지 않는다.
                        que.offer(new Node(nx, ny, cur.time+1, next));
                        map.put(next, cur.time+1); // 이 위치는 몇번만에 만들어졌는지 저장
                    }

                }

            }


        }
        return -1;

    }



    class Node  {
        int x;
        int y;
        int time;

        String change;

        public Node(int x, int y, int time, String c) {
            this.x = x;
            this.y = y;
            this.time = time;
            this.change = c;


        }
    }
    public static void main(String[] args) throws Exception{
        new 퍼즐().solution();
    }
}
