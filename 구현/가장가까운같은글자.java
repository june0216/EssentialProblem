import java.util.*;

class 가장가까운같은글자 {
	public int[] solution(String s) {
		int[] answer = new int[s.length()];
		answer[0] = -1;
		char[] sToc = s.toCharArray();
		int[] idx = new int[s.length()];
		List<Character> cList = new ArrayList<>();


		cList.add(sToc[0]);
		for(int i = 1; i < s.length(); i++){
			if(cList.contains(s.charAt(i))){
				int dd = cList.size()-1;
				while(dd >=0){
					if(cList.get(dd) == s.charAt(i)){
						answer[i] = i - dd;
						cList.add(s.charAt(i));
						break;
					}
					dd--;
				}
			}else{
				answer[i] = -1;
				cList.add(s.charAt(i));
			}

		}
		return answer;
	}
}