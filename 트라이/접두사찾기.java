import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.*;
import java.util.StringTokenizer;

public class 접두사찾기 {
    public static void main(String[] args) throws Exception{
        new 접두사찾기().solution();
    }

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Set<String> set = new HashSet<>();
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
        TrieNode root = new TrieNode();
        public void insert(String word){
            TrieNode parent = this.root;
            for(char w : word.toCharArray()){
                parent.child.putIfAbsent(w, new TrieNode());
                parent = parent.child.get(w);
            }
            parent.setLast();

        }

        public boolean search(String word){
            TrieNode parent = this.root;
            for(char c: word.toCharArray()){
                if(parent.child.containsKey(c)){
                    parent = parent.child.get(c);
                }else{
                    return false;
                }
            }
            return true;
        }


    }

    class TrieNode{
        Map<Character, TrieNode> child = new HashMap<>();
        char c;

        boolean isLast;

        public TrieNode(char c ){
            this.c = c;
        }

        public TrieNode(){

        }

        public void setLast(){
            isLast = true;
        }

    }
}
