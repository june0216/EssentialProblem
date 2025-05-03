import java.util.*;
class 방금그곡 {
    public String solution(String m, String[] musicinfos) {
        String answer = "(None)";
        int time =0;
        String newM = replaceSharp(m);
        List<Node> res = new ArrayList<>();
        // 원형으로 관리 
        // 조건 여러개 -> 재생된 시간이 가장 긴  , 먼저 입력된 음악 제목
        for(int idx = 0; idx < musicinfos.length; idx++){
            String mu = musicinfos[idx];
            
             StringTokenizer st = new StringTokenizer(mu, ",");
            String startTime = st.nextToken();
            String endTime = st.nextToken();
            String title = st.nextToken();
            String code = st.nextToken();
            String newCode = replaceSharp(code);
            
            // 재생만큼 코드 만들기 
            int total = cal(startTime, endTime);
           //ystem.out.println(total);
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < total; i++){
                // # 관리 
                sb.append(newCode.charAt((i)%newCode.length()));
                //stem.out.println(sb.toString());
            }
            
            if(sb.toString().contains(newM) && time <total){ // 시간이 적어야 한다
                time= total;
                answer = title;
                //s.add(new Node(title, total, idx));
            }
            
            // 일치하면 재생 시간, 제목, 순서 넣기 
        }

        
       // 리스트에 담는 형식은 시간초과 
        
        
        return answer;
    }
    
    public String replaceSharp(String str){
        
        str = str.replaceAll("C#", "1");
        str = str.replaceAll("D#", "2");
        str = str.replaceAll("F#", "3");
        str = str.replaceAll("G#", "4");
        str = str.replaceAll("A#", "5");
        str = str.replaceAll("B#", "6"); // 이거다!
        String res = str;
        return res;
    }
    
    public int cal(String startTime, String endTime){
        // 
        StringTokenizer st = new StringTokenizer(startTime, ":");
        int sh = Integer.parseInt(st.nextToken());
        int sm =  Integer.parseInt(st.nextToken());
        st = new StringTokenizer(endTime, ":");
        
        int eh = Integer.parseInt(st.nextToken());
        int em = Integer.parseInt(st.nextToken());
        
        return eh*60 +em - sh*60 - sm;
        /*
        int total = (eh-sh)*60;
        // 23시에서 넘어가는 걸 못함 
        if(sh > eh){
            total = (24-sh+eh);
        }
        
        //stem.out.println("시"+""+ total);
        if(sm <= em){
            total+= (em - sm);
        }
        else{
            total += (60-(sm-em));
        }
        //stem.out.println("분" + " " + total);
        return total;
        */
        
    }
    class Node implements Comparable<Node>{
        String title;
        int total;
        int index;
        
        public Node(String title, int total, int index){
            this.title = title;
            this.total = total;
            this.index = index;
        }
        
        @Override
        public int compareTo(Node node){
            if(this.total == node.total){
                // 먼저 입력된 음악
                return this.index - node.index;
            }
            return node.total - this.total;
        }
    }
}
