class 이모티콘할인행사 {

	double sales[][];
	int maxPlus;
	int maxS;
	int[][] user;

	public int[] solution(int[][] users, int[] emoticons) {
		int[] answer = new int[2];
		user = new int[users.length][2];
		// 전역으로 변경
		for(int i = 0; i < users.length; i++){
			user[i][0] = users[i][0];
			user[i][1] = users[i][1];

		}
		//결과 -> 이모티콘 플러스 가입자 수 , 매출액
		// 플러스 가입자 늘리기 -> 이모티콘 판매액 늘리기
		// 이모티콘 별 할인율을 유동적
		maxPlus = 0;
		maxS = 0;
		//비율% 이상의 할인이 있는 이모티콘을 모두 구매한다는 의미입니다

		// 10, 20, 30, 40
		sales = new double[emoticons.length][4];
		selected = new int[emoticons.length];
		for(int i = 0 ; i < emoticons.length; i++){

			sales[i][0] = emoticons[i] * 0.9;
			sales[i][1] = emoticons[i] * 0.8;
			sales[i][2] = emoticons[i] * 0.7;
			sales[i][3] = emoticons[i] * 0.6;

			//System.out.println(sales[i][3]);
		}


		combi(0,0, emoticons.length, users.length);


		answer[0] = maxPlus;
		answer[1] = maxS;
		return answer;
	}


	int[] selected;

	public void combi(int start, int cnt, int N, int people){
		if(cnt== N){

			cal(people, N);
			return;
		}

		for(int i = 0; i < 4; i++){
			selected[start] = i;
			combi(start+1, cnt+1, N, people);

		}
	}

	public void cal(int people, int N){

		int plus = 0;
		int s = 0;

		//System.out.println(people + " " + N);
		for(int p = 0; p < people; p++){
			int sum = 0;

			for(int i = 0;  i < N; i++){
				// 더 커야지 합칠 수 있음

				if((selected[i] +1) *10 >= user[p][0]){
					//System.out.println(sales[i][selected[i]]);
					sum+= sales[i][selected[i]];
					//System.out.println((selected[i] +1) *10);
				}
				//System.out.println((selected[i] +1) *10);

			}

			//System.out.println(sum);
			if(user[p][1] <= sum){
				//System.out.println(user[p][1]);
				plus++;
			}else{
				s+= sum;
			}
		}

		if(maxPlus < plus){

			maxPlus = plus;
			maxS = s;
			return;
		}else if(maxPlus <= plus && s > maxS){
			maxPlus = plus;
			maxS = s;
			return;
		}
		return;
	}
}



