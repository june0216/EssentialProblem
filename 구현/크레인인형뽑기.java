import java.util.*;
class 크레인인형뽑기 {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        //게임 사용자는 크레인을 좌우로 움직여서 멈춘 위치에서 가장 위에 있는 인형을 집어 올릴 수 있습니다.
        
        // 바구니는 무한대 
        PriorityQueue<Node> info[] = new PriorityQueue[board.length];
        for(int j = 0; j < board[0].length; j++){
            info[j] = new PriorityQueue<>();
            for(int i = 0; i < board.length; i++){
                if(board[i][j] != 0){
                    info[j].offer(new Node(i,board[i][j] ));
                }
            }
        }
        
      
        
        Deque<Integer> stack = new ArrayDeque<>();
        
        for(int i = 0; i < moves.length; i++){
            if(info[moves[i]-1].isEmpty()) continue;
            int num = info[moves[i]-1].poll().num;
            if(!stack.isEmpty() && stack.peekLast() == num){
                stack.pollLast();
                answer+=2;
                continue;
            }else{
                stack.offerLast(num);
            }
        }
        
        
        
        
        return answer;
    }
    
    class Node implements Comparable<Node>{
        int idx;
        int num;
        
        public Node(int idx, int num){
            this.num = num;
            this.idx = idx;
        }
        
        @Override
        public int compareTo(Node node){
            return this.idx- node.idx;
        }
    }
}
