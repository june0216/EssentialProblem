
import java.util.*;
import java.io.*;


class 줄기세포
{

    /*
    지나간 시간과 주어진 시간 관리가 어려웠음
    */

    int dx[] = {-1, 1, 0, 0};
    int dy[] = {0, 0, -1, 1};
    int C;
    int R;

    public void solution() throws Exception{

        /*
		   표준입력 System.in 으로부터 스캐너를 만들어 데이터를 읽어옵니다.
		 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T;
		T=Integer.parseInt(br.readLine());
		/*
		   여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		*/

        StringTokenizer st;
		for(int test_case = 1; test_case <= T; test_case++)
		{
            
    		st = new StringTokenizer(br.readLine());
            C = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            int time =  Integer.parseInt(st.nextToken());
            int arr[][] = new int[C][R];
            Set<String> visited = new HashSet<>();
            PriorityQueue<Node> pq = new PriorityQueue<>((c1, c2) -> c2.life - c1.life);
            for(int i = 0; i < C; i++){
                st= new StringTokenizer(br.readLine());
                for(int j = 0;j < R; j++){
                    arr[i][j] = Integer.parseInt(st.nextToken());
                    if(arr[i][j] <= 0) continue;
                    String key = j+ " " + i;
                    visited.add(key);
                    pq.offer(new Node(j, i, arr[i][j], arr[i][j]));
                }
            }


            // 얼마 남지 않은 값부터 꺼내오기
            // 생명력 수치가 높은 줄기 세포가 해당 그리드 셀을 혼자서 차지
            
            
            for(int i = 0; i < time; i++){

                // 살아남은 애들 
                Deque<Node> que = new ArrayDeque<>();
                while(!pq.isEmpty()){
                    Node cur = pq.poll();
                    // 시간 줄이기 
                    cur.time--;
                    //if(-cur.time > cur.life) continue;

                    // 활성화된 상태라면 
                    if(cur.time < 0){
                        // 번식 
                        for(int d = 0; d < 4; d++){
                            int nx = cur.x + dx[d];
                            int ny = cur.y + dy[d];
                            String key = nx + " " + ny;
                            if(visited.contains(key)){
                                continue;
                            }

                            visited.add(key);

                            que.offer(new Node(nx, ny, cur.life, cur.life));
                        }
                        
                        
                    }


                    // 아직 살아있다면 다시 넣기 
                    if(-cur.time < cur.life){
                        que.offer(new Node(cur.x, cur.y, cur.time,  cur.life));
                    }

                    

                    
                    
                    
                }

                pq.addAll(que);

                
            }

            System.out.println("#" + test_case + " " + pq.size());
			

		}

        

        
        
    }



    class Node{
        int x;
        int y;
        int life; // 주어진 값
        int time; // 얼마나 지났는지
        
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }

        public Node(int x, int y, int time){
            this.x = x;
            this.y = y;
            this.time =time;
        
        }

        public Node(int x, int y, int time, int life){
            this.x = x;
            this.y = y;
            this.time =time;
            this.life= life;
      
        }
    }
	public static void main(String args[]) throws Exception
	{

        new 줄기세포().solution();
		/*
		   아래의 메소드 호출은 앞으로 표준 입력(키보드) 대신 input.txt 파일로부터 읽어오겠다는 의미의 코드입니다.
		   여러분이 작성한 코드를 테스트 할 때, 편의를 위해서 input.txt에 입력을 저장한 후,
		   이 코드를 프로그램의 처음 부분에 추가하면 이후 입력을 수행할 때 표준 입력 대신 파일로부터 입력을 받아올 수 있습니다.
		   따라서 테스트를 수행할 때에는 아래 주석을 지우고 이 메소드를 사용하셔도 좋습니다.
		   단, 채점을 위해 코드를 제출하실 때에는 반드시 이 메소드를 지우거나 주석 처리 하셔야 합니다.
		 */
		//System.setIn(new FileInputStream("res/input.txt"));

		
	}
}
