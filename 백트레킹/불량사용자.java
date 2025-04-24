import java.util.*;

class 불량사용자 {
    Set<Set<String>> res;

    public int solution(String[] user_id, String[] banned_id) {
        res = new HashSet<>();
        boolean[] visited = new boolean[user_id.length];
        dfs(0, new ArrayList<>(), visited, user_id, banned_id);

        // 출력
        StringBuilder sb = new StringBuilder();
        for (Set<String> innerSet : res) {
            sb.append("{ ");
            for (String s : innerSet) {
                sb.append(s).append(" ");
            }
            sb.append("}\n");
        }
       // System.out.println(sb.toString());

        return res.size();
    }

    public void dfs(int depth, List<String> path, boolean[] visited, String[] user_id, String[] banned_id) {
        if (depth == banned_id.length) {
            Set<String> set = new HashSet<>(path);
            res.add(set); // 중복 방지용
            return;
        }

        for (int i = 0; i < user_id.length; i++) {
            if (visited[i]) continue; // 놓친 부분 1) "frodo"를 fr*d*에 썼다면, "frodo"는 다시 다른 금지 ID에 쓰면 안 된다. 

            if (isMatch(user_id[i], banned_id[depth])) { // 놓친부분 2) 조건에 맞아야 하는데 무작정 함수를 호출했다. 
                visited[i] = true;
                path.add(user_id[i]);
                dfs(depth + 1, path, visited, user_id, banned_id);
                path.remove(path.size() - 1);
                visited[i] = false;
            }
        }
    }

    public boolean isMatch(String user, String banned) {
        if (user.length() != banned.length()) return false;
        for (int i = 0; i < user.length(); i++) {
            if (banned.charAt(i) != '*' && user.charAt(i) != banned.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}
