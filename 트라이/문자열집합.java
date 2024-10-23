import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
public class 문자열집합 {
    public static void main(String[] args) throws Exception{
        new 문자열집합().solution();
    }

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Set<String> set = new HashSet<>();
        while(N--> 0){
            String target = br.readLine();
            set.add(target);
        }


        int cnt = 0;

        while(M-- > 0){
            String compare = br.readLine();
            if(set.contains(compare)){
                cnt++;
            }
        }

        System.out.println(cnt);


    }


// 트라이처럼 보였지만 시간초과
    public void solutionTrie() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        //List<String> target = new ArrayList<>();

        Trie trie = new Trie();
        while(N--> 0){
            String target = br.readLine();
            trie.insert(target);
        }


        int cnt = 0;

        while(M-- > 0){
            String compare = br.readLine();
            if(trie.search(compare)){
                cnt++;
            }
        }

        System.out.println(cnt);
    }


    class Trie{
        TrieNode root;
        public Trie(){
            root = new TrieNode();
        }


        public void insert(String word) {
            TrieNode trieNode = root;
            for(int i=0; i<word.length(); i++) {
                char c = word.charAt(i);

                // tmp childNode에 c없으면 추가
                trieNode.child.putIfAbsent(c, new TrieNode());
                trieNode = trieNode.child.get(c);

                // 마지막 문자 terminal
                if(i== word.length()-1) {
                    trieNode.setLast();
                    return;
                }
            }
        }

        public boolean search(String word){
            TrieNode parent = root;
            char[] wordToChar = word.toCharArray();
            for(char c : word.toCharArray()){
                if(parent.child.size() == 0) return false;
                if(!parent.child.containsKey(c)){
                    return false;
                }
                parent = parent.child.get(c);

            }
            return parent.isLast;
        }
    }



    class TrieNode{
        Map<Character, TrieNode> child = new HashMap<>();

        char c;
        boolean isLast = false;

        public TrieNode(char c){
            this.c = c;
        }

        public TrieNode(){

        }

        public void setLast() {
            this.isLast = true;
        }

    }
}
