import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class 조합 {
	public static void main(String[] args) {
		new 조합().solution();
	}

	public void solution(){
		arr = new int[10];
		for(int i = 0; i < 10; i++){
			arr[i] = i;
		}
		selected = new int[end];
		combination(0, 0);
	}
	int end = 3;
	int[] arr;
	int[] selected;
	public void combination(int start, int cnt){
		if(cnt  == 3){
			for(int i= 0; i < end; i++){
				System.out.println(selected[i]);

			}
			System.out.println("=================");
			return;
		}

		for(int i = start; i < 10; i++){
			selected[cnt] = arr[i];
			//System.out.println(i);
			combination(i, cnt+1);
		}
		System.out.println("======");
	}
}