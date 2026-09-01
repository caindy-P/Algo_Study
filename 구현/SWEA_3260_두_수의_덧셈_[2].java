import java.util.*;

public class Solution_두수의덧셈_박도윤 {

	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {
			String A = sc.next();
			String B = sc.next();

			int numLen = Math.max(A.length(), B.length());

			String answer = "";
			int roundUp = 0;

			for (int i = 0; i < numLen; i++) {

				int numA = 0;
				int numB = 0;

				if (i < A.length()) {
					numA = A.charAt(A.length() - 1 - i) - '0';
				}

				if (i < B.length()) {
					numB = B.charAt(B.length() - 1 - i) - '0';
				}

				int sum = numA + numB + roundUp;

				answer = (sum % 10) + answer;

				if (sum >= 10) {
					roundUp = 1;
				} else {
					roundUp = 0;
				}
			}

			// 마지막 자리에서 올림이 남은 경우
			if (roundUp == 1) {
				answer = "1" + answer;
			}

			System.out.println("#" + test_case + " " + answer);
		}
	}
}