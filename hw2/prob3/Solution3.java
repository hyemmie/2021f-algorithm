import java.util.StringTokenizer;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.PrintWriter;

/*
  1. Compile the file with the following command. The class file named Solution3 would be created when you compile the source code.
      javac Solution3.java -encoding UTF8

  2. Run the compiled program with the following command. Output file(output.txt) should be created after the program is finished
      java Solution3

  - The encoding of the source code should be UTF8
  - You can use the 'time' command to measure your algorithm.
      time java Solution3
  - You can also halt the program with the 'timeout' command.
      timeout 0.5 java Solution3
      timeout 1 java Solution3
 */

class Solution3 {
	static final int max_n = 100000;

	static int n;
	static int[][] A = new int[3][max_n];
	static int Answer;

	public static void main(String[] args) throws Exception {
		/*
			Read the input from input.txt
			Write your answer to output.txt
		 */
		BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		StringTokenizer stk;
		PrintWriter pw = new PrintWriter("output.txt");

		for (int test_case = 1; test_case <= 10; test_case++) {
			
			stk = new StringTokenizer(br.readLine());
			n = Integer.parseInt(stk.nextToken());
			for (int i = 0; i < 3; i++) {
				stk = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					A[i][j] = Integer.parseInt(stk.nextToken());
				}
			}


			/////////////////////////////////////////////////////////////////////////////////////////////
			/*
				YOUR CODE HERE
				Save your answer to the variable Answer
			 */
			/////////////////////////////////////////////////////////////////////////////////////////////
			Answer = pebble();


			// Print the answer to output.txt
			pw.println("#" + test_case + " " + Answer);
			// To ensure that your answer is printed safely, please flush the string buffer while running the program
			pw.flush();
		}

		br.close();
		pw.close();
	}

	public static int pebble() {
		int[][] peb = new int[6][n];
		int[][] weight = new int[6][n];

		for (int i = 0; i < n; i++) {
			weight[0][i] = A[0][i] - A[2][i];
			weight[1][i] = A[0][i] - A[1][i];
			weight[2][i] = A[1][i] - A[2][i];
			weight[3][i] = A[2][i] - A[1][i];
			weight[4][i] = A[1][i] - A[0][i];
			weight[5][i] = A[2][i] - A[0][i];
		}

		for (int j = 0; j < 6; j++) {
			peb[j][0] = weight[j][0];
		}

		for (int i = 1; i < n; i++) { 
			for (int j = 0; j < 6; j++) { 
				int temp = 0;
				int[] comp = compatible(j);
				for (int q = 0; q < comp.length; q++) {
					int p = comp[q];
					if (peb[p][i - 1] > temp) { 
						temp = peb[p][i - 1];
					}
				}
				peb[j][i] = temp + weight[j][i];
				}
			}

		int selectedSum = 0;
		for (int p = 0; p < 6; p++) {
			if (peb[p][n-1] > selectedSum) selectedSum = peb[p][n-1];
		}
		return selectedSum;
	}

	public static int[] compatible(int p) {
		switch (p) {
			case (0): return new int[] {3,4};
			case (1): return new int[] {2,5};
			case (2): return new int[] {1,5};
			case (3): return new int[] {0,4};
			case (4): return new int[] {0,3};
			case (5): return new int[] {1,2};
			default: return  new int[] {0};
		}
	}
}

