import java.util.*;

public class Solution_1225_박도윤 {
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T;

		for (int test_case = 1; test_case <= 10; test_case++) {
			T = sc.nextInt();
			Deque<Integer> q = new ArrayDeque<>();
			int now = 0;
			for (int i = 0; i < 8; i++) {
				int num = sc.nextInt();
				q.offer(num);
			}
			while(true) {
				int n = q.pollFirst() - (now++ % 5) - 1;
				if(n <= 0) {
					q.offerLast(0);
					break;
				}
				q.offerLast(n);
			}
			System.out.print("#"+test_case+" ");
			while(!q.isEmpty()) {
				System.out.print(q.pollFirst()+" ");
			}
			System.out.println();
		}
	}
}
