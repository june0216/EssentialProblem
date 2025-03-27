import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class 단축키 {
	public static void main(String[] args) throws Exception{
		new 단축키().solution();
	}

	public void solution() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Set<Character> keys = new HashSet<>();

		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		boolean flag = false;
		for(int i = 0; i < N; i++){
			flag = false;
			st =  new StringTokenizer(br.readLine());
			HashSet<Character> wordSet = new LinkedHashSet<>();
			List<String> wordsList = new ArrayList<>();
			sb=new StringBuilder();

			// 한 줄 별
			while(st.hasMoreTokens()){
				String word = st.nextToken();

				wordsList.add(word);
				// 첫단어가
				if(flag){

					sb.append(" ").append(word);
					continue;
				}
				if(!keys.contains(word.charAt(0)) && !flag){
					keys.add(Character.toUpperCase(word.charAt(0)));
					keys.add(Character.toLowerCase(word.charAt(0)));

					flag = true;
					//System.out.println(word + " " + flag + " " + i);
					sb.append("[").append(word.charAt(0)).append("]").append(word.substring(1, word.length()));
					continue;

				}
				if(flag){

				}
				sb.append(word).append(" ");



				// 중복없이 순서대로 저장
				for(char c : word.toCharArray()){
					if(wordSet.contains(c) || keys.contains(c)){
						continue;
					}
					wordSet.add(Character.toUpperCase(c));
					wordSet.add(Character.toLowerCase(c));


				}
			}

			if(flag){
				System.out.println(sb);
			}
            
                

                /* 만약 모든 단어의 첫 글자가 이미 지정이 되어있다면 왼쪽에서부터 차례대로 알파벳을 보면서 단축키로 지정 안 된 것이 있다면 단축키로 지정한다.
                for(Character c : wordSet.keySet() ){
                    if(!keys.contains(c)){
                        keys.set(word.charAt(0));
                        sb.append("[").append(word).append("]");
                        flag = true;
                        break;
                    }
                }*/

			if(flag) continue;
			boolean f = false;
			sb = new StringBuilder();

			for(String str : wordsList){
				for(char c : str.toCharArray()){
					if(wordSet.contains(c) && !keys.contains(c) && !f){
						sb.append("[").append(c).append("]");
						wordSet.add(Character.toUpperCase(c));
						wordSet.add(Character.toLowerCase(c));
						keys.add(Character.toUpperCase(c));
						keys.add(Character.toLowerCase(c));
						f =true;
						continue;
					}
					sb.append(c);
				}
				sb.append(" ");


			}
			System.out.println(sb);



		}






		//System.out.println(sb);
	}
}