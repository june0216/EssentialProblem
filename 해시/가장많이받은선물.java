import java.util.*;

class 가장많이받은선물 {
	public int solution(String[] friends, String[] gifts) {
		int answer = 0;
		// 정보 보관
		Map<String, Integer> info = new HashMap<>();
		for(int i = 0; i < friends.length; i++){
			info.put(friends[i], i);
		}

		int giftscore[][] = new int[friends.length][2];
		int giftInfo[][] = new int[friends.length][friends.length];
		int calGiftScore[]= new int[friends.length];
		// 0-> 준 개수, 1 ->받은개수

		StringTokenizer st;
		for(int i =0; i < gifts.length; i++){
			st = new StringTokenizer(gifts[i]);
			int giver = info.get(st.nextToken());
			int given = info.get(st.nextToken());
			giftscore[giver][0]++;
			giftscore[given][1]++;
			giftInfo[giver][given]++;

		}
		for(int i = 0; i < friends.length; i++){
			// 준 - 받
			calGiftScore[i] = giftscore[i][0] - giftscore[i][1];
		}

		// 주는 입장

		boolean[][] visited = new boolean[friends.length][friends.length];
		int result[] = new int[friends.length];
		for(int i = 0; i < friends.length; i++){
			// 받는 입장
			for(int j = i+1; j < friends.length; j++){
				if(i == j) continue;
				if(visited[i][j] || visited[j][i]) continue;

				if((giftInfo[i][j] == 0 && giftInfo[j][i] == 0) || giftInfo[i][j] == giftInfo[j][i]){
					if(calGiftScore[i] < calGiftScore[j]){
						result[j]++;
					}else if(calGiftScore[i] > calGiftScore[j]){
						result[i]++;
					}
					continue;
				}

				// j가 더 많이 줬다면
				if(giftInfo[i][j] < giftInfo[j][i]){
					result[j]++;
				}else{
					result[i]++;
				}
				visited[i][j] = true;
				visited[j][i] = true;
			}


		}


		int max = 0;
		int maxIdx = 0;
		for(int i = 0; i < friends.length; i++){
			if(max < result[i]){
				max = result[i];
				maxIdx = i;
			}
		}


		//System.out.println(maxIdx);

		answer = max;

		return answer;
	}
}