import java.util.*;

class 베스트앨범 {
    public int[] solution(String[] genres, int[] plays) {

        Map<String, Info> dic = new HashMap<>();
        PriorityQueue<Info> sortNum = new PriorityQueue<>();
        for(int i = 0; i < genres.length; i++){
            if(dic.containsKey(genres[i])){
                Info getInfo = dic.get(genres[i]);
                getInfo.total += plays[i];
                getInfo.pq.offer(new Node(i, plays[i]));
                dic.put(genres[i], getInfo);

            }
            else{
                Info info = new Info(plays[i]);
                info.pq.offer(new Node(i, plays[i]));
                dic.put(genres[i], info);
            }
        }
        for(String key : dic.keySet()){
            sortNum.offer(dic.get(key));
        }

        List<Integer> answer = new ArrayList<>();

        while(!sortNum.isEmpty()){
            Info target = sortNum.poll();

            if(target.pq.size() >= 2){
                answer.add(target.pq.poll().id);
                answer.add(target.pq.poll().id);
            }else{
                answer.add(target.pq.poll().id);
            }


        }
        return answer.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }
    class Info implements Comparable<Info>{
        int total;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        public Info(int total){
            this.total = total;
        }
        @Override
        public int compareTo(Info info){
            return info.total - this.total;
        }

    }

    static class Node implements Comparable<Node>{
        int id;
        int num;

        public Node(int id, int num){
            this.id = id;
            this.num = num;
        }

        @Override
        public int compareTo(Node node){
            if(this.num == node.num){
                return this.id - node.id; // id가 낮은 순으로
            }
            return node.num- this.num; // 재생 수가 많은 순으로
        }
    }
}
