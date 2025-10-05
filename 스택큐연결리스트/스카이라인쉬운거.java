import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class 스카이라인쉬운거 {
    public static void main(String[] args) throws Exception{
        new 스카이라인쉬운거().solution();
    }

    public void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int total = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int[] arr = new int[total];
        Set<Integer> set = new HashSet<>();
        for(int i= 0; i < total; i++){
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int height = Integer.parseInt(st.nextToken());
            arr[i]= height;
            set.add(arr[i]);
        }

        // dfs?

        // 단조 스택
        Deque<Integer> stack = new ArrayDeque<>();
        int result = 0;
        
        for(int i= 0; i < total; i++){
            while(!stack.isEmpty() && stack.peekLast() > arr[i]){
                stack.pollLast();
                result++;
                
            }
            if(!stack.isEmpty() && stack.peekLast() == arr[i]){
                continue;
                
                
               
            }
            
            
            
            
            stack.offer(arr[i]);
             
        }

        while(!stack.isEmpty()){
            if(stack.peekLast() > 0){
                result++;
            }
            stack.pollLast();
        }

        

        System.out.println(result);
        


        
    }
}
