import java.util.*;
class 보석쇼핑 {
	public int[] solution(String[] gems) {
		int[] answer = new int[2];

		// 종류에 관한 누적합
		Set<String> s = new HashSet<>();
		Map<String, Integer> ls = new HashMap<>();
		for(int i = 0; i < gems.length; i++){
			s.add(gems[i]);


		}

        /*
        처음에는 MAP을 다 채우려고 했지만 하나씩 넣으면서 비교하는 게 더 맞음
        자기 기준으로 앞에 애들이 1
        */



		// 짧은 구간
		int start = 0;
		int end = 0;
		int idx = 0;
		int min = Integer.MAX_VALUE;
		// 1인 위치들의 인덱스가 가장 적은 거  + 가장 많은 거
		while(start < gems.length && end <gems.length){
			ls.put(gems[end], ls.getOrDefault(gems[end], 0) + 1);
			while(ls.getOrDefault(gems[start], 0) >1){
				ls.put(gems[start], ls.getOrDefault(gems[start], 0)-1);
				start++;
			}

			// 뒤에서 줄여야 함
			if(end-start < min && ls.size() == s.size()){
				min = Math.min(min, end-start);
				answer[0] = start +1;
				answer[1] = end+1;
			}
			end++;

		}


		return answer;
	}
}
