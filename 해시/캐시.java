import java.util.*;
class 캐시 {
	public int solution(int cacheSize, String[] cities) {
		int answer = 0;

		Set<String >cache = new LinkedHashSet<>();

		int cnt = 0;

		if(cacheSize ==0){
			return cities.length*5;
		}
		// List<String> useInfo = new LinkedList<>();

		for(String c : cities){
			String city = c.toLowerCase();
			//System.out.println(city);
			if(cache.contains(city)){
				cache.remove(city);
				cache.add(city);

				answer++;
			}
			else{
				if(cnt < cacheSize){
					cache.add(city);
					answer += 5;
					cnt++;

				}else{
					Iterator<String> it = cache.iterator();
					String r = it.next();
					cache.remove(r);
					cache.add(city);
					answer+=5;

				}

			}
		}
		return answer;

	}


}
