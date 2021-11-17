import java.util.StringTokenizer;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.PrintWriter;

class Solution1 {

  static int N, E;
	static int u, v, w;
	static int Answer;
	static int[][] dp;
	static int[][] weight;

	public static void main(String[] args) throws Exception {
	
		BufferedReader br = new BufferedReader(new FileReader("input1.txt"));
		StringTokenizer stk;
		PrintWriter pw = new PrintWriter("output1.txt");

		for (int test_case = 1; test_case <= 10; test_case++) {

			stk = new StringTokenizer(br.readLine());
			N = Integer.parseInt(stk.nextToken());
			E = Integer.parseInt(stk.nextToken());
			stk = new StringTokenizer(br.readLine());

			weight = new int [N+1][N+1];
			dp = new int [N+1][N+1];
			Answer = 0;

			for (int i = 1; i<= N; i++) {
				for(int j = 1; j<= N; j++) {
					weight[i][j] = Integer.MAX_VALUE;
				}
			}

			for (int i = 0; i < E; i++) {
				u = Integer.parseInt(stk.nextToken());
				v = Integer.parseInt(stk.nextToken());
				w = Integer.parseInt(stk.nextToken());
				weight[u][v] = w;
			}

			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					dp[i][j] = weight[i][j];
				}
			}

			for (int k = 1; k <= N; k++) {
				for (int i = 1; i <= N; i++) {
					for (int j = 1; j <= N; j++) {
						if (i != j && dp[i][k] != Integer.MAX_VALUE && dp[k][j] != Integer.MAX_VALUE) {
							if (dp[i][k] + dp[k][j] < dp[i][j]) dp[i][j] = dp[i][k] + dp[k][j];
						}
					}
				}
			}

			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
					if(dp[i][j] != Integer.MAX_VALUE) Answer += dp[i][j];
				}
			}

			Answer %= 1000000000;

			pw.println("#" + test_case + " " + Answer);
			pw.flush();
		}

		br.close();
		pw.close();
	}
}
