import java.util.*;
import java.lang.*;
import java.io.*;


class 단어수학 {
	public static void main(String[] args) throws Exception{
		new 단어수학().solution();
	}


	List<String> num;
	List<Character> key;
	int N;
	public void solution() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N= Integer.parseInt(br.readLine());
		// 모든 경우를 다 해봐야한다.
		num = new ArrayList<>();
		key = new ArrayList<>();
		// 많이 나오면서 맨 앞에 있는 거 -> 조건이 2가지

		for(int i = 0; i < N; i++){
			String n = br.readLine();
			num.add(n);
			for(char c : n.toCharArray()){
				if(!key.contains(c)){
					key.add(c);
				}
			}

		}
		seletected = new char[key.size()];
		max = 0;

		visited = new boolean[key.size()];
		permutaion(0, 0);
		System.out.println(max);





	}



	boolean[] visited;
	char seletected[];
	int max;
	// 시간초과 : 알파벳과 가능한 숫자 조합 만들기 -> 알파벳매칭해서 또 String으로 만들기 -> String을 int로 변환하여 풀기
	// 통과 : 알파벳과 가능한 숫자 조합 만들기 -> (과정을 줄임 -> 알파벳매칭해서 또 String으로 만들기) -> String을 int로 변환하여 풀기
	public void permutaion(int start, int cnt){
		if(start == key.size()){
			Map<Character, Integer> info = new HashMap<>();
			List<String> result = new ArrayList<>();
			for(int l  = 0; l < seletected.length; l++){
				info.put(seletected[l], 9-l);
			}

			int score = 0;
			for(String s : num){

				int n = 0;
				for(char c : s.toCharArray()){
					n*=10; // 자리 위치를 옮긴다.
					n +=info.get(c);
					//System.out.println(info.get(c));
				}
				score+= n;

			}
			max = Math.max(score, max);

			return;
		}

		for(int i =0; i < key.size(); i++){
			if(visited[i]) continue;
			//
			visited[i] = true;
			seletected[start] = key.get(i);
			permutaion(start+1, cnt+1);
			// 초기화
			seletected[start] = '0';
			visited[i] = false;

		}

	}



}

