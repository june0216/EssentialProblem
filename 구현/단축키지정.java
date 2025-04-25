import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class 단축키지정 {
    public static void main(String[] args) throws Exception{
        new 단축키지정().solution();
    }


    int N;
    public void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N= Integer.parseInt(br.readLine());
        Set<Character> set = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        List<Integer> arr = new ArrayList<>();
        for(int i = 0; i < N; i++){
            String str = br.readLine();
            String toUpperStr = str.toUpperCase();
            boolean flag = false;
            int first = 0;
            
            StringTokenizer st = new StringTokenizer(str);
            arr.clear();
            set.add(' ');
            //1) 단어의 첫 글자가 이미 단축키로 지정되었는지 살펴본다
            while(st.hasMoreTokens()){
                String next = st.nextToken().toUpperCase();
                if(!set.contains(next.charAt(0))){
                    arr.add(first); // 몇번째 단어인지 
                    
                    set.add(next.charAt(0));
                    break;
                }
                first++;
               
            }




            // 2) 만약 모든 단어의 첫 글자가 이미 지정이 되어있다면 왼쪽에서부터 차례대로 알파벳을 보면서 단축키로 지정 안 된 것이 있다면 단축키로 지정한다.
            int second = 0;
            for(int j = 0 ; j  < str.length(); j++){
                // 단어 앞글자에 단축키 설정 못한다면 
                if(arr.size() == 0){
                    if(!flag && !set.contains(toUpperStr.charAt(j))){
                    sb.append("[").append(str.charAt(j)).append("]");
                    set.add(toUpperStr.charAt(j));
                    flag = true;
                    continue;
                    }
                }else{
                    if(!flag &&arr.get(0) == second){
                        sb.append("[").append(str.charAt(j)).append("]");
                        flag = true;
                        continue;// 이부분
                    }
                }
                
                if(str.charAt(j) == ' '){
                    second++;
                }
                
                sb.append(str.charAt(j));
            }
            sb.append("\n");
        }
        System.out.println(sb);
        }
}
