import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
	public static void main(String[] args) throws Exception{
		new Main().solution();
	}

	public void solution() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		int aLen = 0;
		int bLen = 0;


		for(int i = 0;  i< input.length(); i++){
			if(input.charAt(i) == 'a'){
				aLen++;
				continue;
			}
			if(input.charAt(i) == 'b'){
				bLen++;
				continue;

			}
		}



		int change = 0;
		int tempALen = 0;
		for(int i = 0; i < aLen; i++){
			if(input.charAt(i%input.length()) == 'b'){
				change++;
			}
		}

		// a를 연속으로 만들기
		// 모든 경우들을 탐색
		// 순서대로 있어야함
		// a 안에
		// 3가지 경우
		// aaba
		// baaa
		// aaab

		// 맨 앞 맨 끝은 a이거나 b

		int min = change;
		int start = 0;
		for(int i = aLen; start < input.length(); i++){
			if(start >i) break;
			if(input.charAt(i%input.length()) == 'b'){ // 끝이 b이면서
				if(input.charAt(start) == 'a'){ // 앞이 a라면
					change++;
					start++;
					continue;
				}else{

					start++;
				}


			}else{
				if(input.charAt(start) == 'a'){ // 끝이 a이면서

					start++;
					continue;
				}else{
					// bb
					change--;
					start++;
				}


			}
			min = Math.min(min, change);


		}
		System.out.println(min);




	}
}