import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 연세워터파크세그먼트트리 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), D = Integer.parseInt(st.nextToken());
        SegmentTree seg = new SegmentTree(N);
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            long K = Long.parseLong(st.nextToken());
            seg.update(1, 1, N, i, Long.max(0, seg.query(1, 1, N, i - D, i - 1)) + K);
        }
        System.out.println(seg.tree[1]);
    }
}

class SegmentTree {
    long[] tree;

    SegmentTree(int N) {
        tree = new long[(N + 1) * 4];
    }

    long update(int idx, int s, int e, int i, long v) {
        if (i < s || e < i)
            return tree[idx];
        if (s == e)
            return tree[idx] = v;
        int m = (s + e) >> 1;
        return tree[idx] = Long.max(update(idx << 1, s, m, i, v), update(idx << 1 | 1, m + 1, e, i, v));
    }

    long query(int idx, int s, int e, int l, int r) {
        if (r < s || e < l)
            return 0;
        if (l <= s && e <= r)
            return tree[idx];
        int m = (s + e) >> 1;
        return Long.max(query(idx << 1, s, m, l, r), query(idx << 1 | 1, m + 1, e, l, r));
    }
}
