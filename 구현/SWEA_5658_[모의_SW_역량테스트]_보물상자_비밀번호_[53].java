import java.util.*;
import java.io.*;

public class Solution_5658_박도윤 {
	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			Deque<Character> q = new ArrayDeque<>();
			Set<String> s = new HashSet<>();

			String input = br.readLine();
			for (int i = 0; i < input.length(); i++) {
				q.offer(input.charAt(i));
			}
			String str = "";
			for (int r = 0; r < N / 4; r++) {
				for (int i = 0; i < N; i++) {
					char c = q.poll();
					q.offer(c);
					str += c;
					if (str.length() == N / 4) {
						s.add(str);
						str = "";
					}
				}
				char temp = q.poll();
				q.offer(temp);
			}
			List<String> l = new ArrayList<>(s);
			l.sort(new Comparator<String>() {
				@Override
				public int compare(String o1, String o2) {
					return Integer.parseInt(o2, 16) - Integer.parseInt(o1, 16);
				}
			});
			System.out.println("#"+test_case+" "+Integer.parseInt((String) l.toArray()[K-1], 16));
		}
	}
}
