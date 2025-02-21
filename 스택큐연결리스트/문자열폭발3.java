
import java.util.*;
import java.io.*;
public class 문자열폭발3 {

	public static void main(String[] args) throws Exception{
		new 문자열폭발3().solution();
	}


    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String bomb = br.readLine();
        int bombLength = bomb.length();
        Deque<Character> stack = new ArrayDeque<>();
		StringBuilder sb = new StringBuilder();
		boolean flag = false;
		for(int i = 0; i < input.length(); i++){
			sb.append(input.charAt(i));
			flag = false;
			if(sb.length() >= bombLength){

				//flag = false;
				for(int j = 0; j < bomb.length(); j++){
					//System.out.println(sb.length()-j-1);
					char c = sb.charAt(sb.length()-j-1);
					if(c != bomb.charAt(bomb.length()-j-1)) {
						break;
					}
					if(j == bomb.length()-1){
						flag = true;
					}

				}



			}

			if(flag){
				//System.out.println(sb.charAt(sb.length()-bomb.length()));
				sb.delete(sb.length()-bomb.length(), sb.length());

			}


		}

		if(sb.length() == 0) {
			System.out.println("FRULA");
		}
		else{
			System.out.println(sb);
		}




	}

	//mirkovniz

}
