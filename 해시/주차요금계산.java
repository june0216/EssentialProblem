import java.util.*;

class 주차요금계산 {

	int time;
	int fee;
	int extraPer;
	int extraFee;
	Map<Integer, Integer> temp;
	public int[] solution(int[] fees, String[] records) {

		time = fees[0];
		fee = fees[1];
		extraPer = fees[2];
		extraFee = fees[3];
		Map<Integer, String> inInfo = new HashMap<>();
		temp = new HashMap<>();
		Map<Integer, Integer> sortedResult = new TreeMap<>();
		StringTokenizer st;
		int cnt = 0;
		List<Integer> carNumList = new ArrayList<>();
		for(int i = 0; i < records.length; i++){
			st = new StringTokenizer(records[i]);
			String time = st.nextToken();
			Integer carNum = Integer.parseInt(st.nextToken());
			String info = st.nextToken();
			if(info.equals("IN")){
				inInfo.put(carNum, time);
				carNumList.add(carNum);
				cnt++;
			}else{
				// 계산하기
				String outTime = time;
				int feeResult = 0;
				if(temp.containsKey(carNum)){
					// 합치기
					feeResult = duplicate(inInfo.get(carNum), outTime,temp.get(carNum), carNum);
					//System.out.println(feeResult);
				}else{
					feeResult = cal(carNum, inInfo.get(carNum), outTime);
				}



				sortedResult.put(carNum, feeResult );
				inInfo.put(carNum, "NO");


			}


		}
		// 같은 번호인데 있다면
		// 남아있다면
		if(inInfo.size() != 0){
			for(Integer key : inInfo.keySet()){
				if(inInfo.get(key).equals("NO")) continue;

				int feeResult = 0;

				if(temp.containsKey(key)){

					// 합치기
					feeResult = duplicate(inInfo.get(key), "23:59",temp.get(key), key);
					//System.out.println(feeResult);

				}else{
					feeResult = cal(key, inInfo.get(key), "23:59");
				}

				//System.out.println(sortedResult.getOrDefault(key, 0));
				sortedResult.put(key, feeResult );
				//inInfo.remove(key);
				//System.out.println(key);
			}
		}

		int[] answer = new int[sortedResult.size()];
		int idx = 0;

		for(Integer key : sortedResult.keySet()){
			answer[idx] = sortedResult.get(key);
			idx++;
		}

		return answer;
	}


	public int duplicate(String start, String end, int tempTime, int carNum){
		//System.out.println(start);
		StringTokenizer st = new StringTokenizer(start, ":");
		int hour = Integer.parseInt(st.nextToken());
		int min = Integer.parseInt(st.nextToken());
		int startMin = hour*60 + min;

		st = new StringTokenizer(end, ":");
		int hour2 = Integer.parseInt(st.nextToken());
		int min2 = Integer.parseInt(st.nextToken());
		int endMin = hour2*60 + min2;
		//System.out.println((endMin - startMin)+tempTime);
		temp.put(carNum, (endMin - startMin)+tempTime);
		int leftTime = time - ((endMin - startMin)+tempTime);

		if(leftTime >= 0){
			return fee;
		}

		return fee + (int)Math.ceil(((double)(((endMin - startMin)+tempTime) - time)/extraPer))*extraFee;
	}

	// 계산하기
	public int cal(Integer carNum, String start, String end){
		StringTokenizer st = new StringTokenizer(start, ":");
		int hour = Integer.parseInt(st.nextToken());
		int min = Integer.parseInt(st.nextToken());
		int startMin = hour*60 + min;

		st = new StringTokenizer(end, ":");
		int hour2 = Integer.parseInt(st.nextToken());
		int min2 = Integer.parseInt(st.nextToken());
		int endMin = hour2*60 + min2;
		//System.out.println(endMin- startMin);
		temp.put(carNum, endMin - startMin);
		int leftTime = time - (endMin - startMin);
		if(leftTime >= 0){
			return fee;
		}
		// System.out.println(fee + (int)Math.ceil((((endMin - startMin) - time)/extraPer))*extraFee);
		return fee + (int)Math.ceil(((double)((endMin - startMin) - time)/extraPer))*extraFee;


	}
}