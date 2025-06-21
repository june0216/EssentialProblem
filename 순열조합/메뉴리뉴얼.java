import java.util.*;

class 메뉴리뉴얼 {
    Map<String, Integer> map;
    int max;

    public String[] solution(String[] orders, int[] course) {
        List<String> result = new ArrayList<>();

        for (int i = 0; i < course.length; i++) {
            map = new HashMap<>();
            max = 0;

            for (String str : orders) {
                char[] strToChar = str.toCharArray();
                Arrays.sort(strToChar);

                if (strToChar.length < course[i]) continue;
                combi(strToChar, 0, new StringBuilder(), course[i]);
            }

            for (String key : map.keySet()) {
                if (map.get(key) > 1 && map.get(key) == max) {
                    result.add(key);
                }
            }
        }

        Collections.sort(result);
        return result.toArray(new String[0]);
    }

    public void combi(char[] arr, int start, StringBuilder sb, int targetLen) {
        if (sb.length() == targetLen) {
            String menu = sb.toString();
            map.put(menu, map.getOrDefault(menu, 0) + 1);
            max = Math.max(max, map.get(menu));
            return;
        }

        for (int i = start; i < arr.length; i++) {
            sb.append(arr[i]);
            combi(arr, i + 1, sb, targetLen);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
