import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class 괄호문제 {

	public static void main(String[] args) throws Exception{
		new 괄호문제().solution();
	}

	/*
	(1) 해시 테이블 방법
	(2) 스택 방법 (닫힌 괄호를 넣는 방법)
	 */
	int N;
	public void solution() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		
	}
	public boolean isValid(String s) {
		Stack<Character> st = new Stack<>();
		for(char c : s.toCharArray()){
			if(c == '('){
				st.add(')');
				continue;
			}
			else if(c == '{'){
				st.add('}');
				continue;

			}else if(c == '['){
				st.add(']');
				continue;
			}
			else if(st.size() != 0 && c == st.peek()){
				st.pop();
				continue;
			}else{
				return false;
			}

		}

		if(st.isEmpty()){
			return true;
		}

		return false;





	}


}
