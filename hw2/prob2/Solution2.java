import java.util.StringTokenizer;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.PrintWriter;

/*
  1. Compile the file with the following command. The class file named Solution2 would be created when you compile the source code.
      javac Solution2.java -encoding UTF8

  2. Run the compiled program with the following command. Output file(output.txt) should be created after the program is finished
      java Solution2

  - The encoding of the source code should be UTF8
  - You can use the 'time' command to measure your algorithm.
      time java Solution2
  - You can also halt the program with the 'timeout' command.
      timeout 0.5 java Solution2
      timeout 1 java Solution2
 */

class Solution2 {
	static final int max_n = 100000;

	static int n;
	static String s;
	static int Answer;
	static int length;
	static int[][] sub_length;


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
			s = br.readLine();

			/////////////////////////////////////////////////////////////////////////////////////////////
			/*
			YOUR CODE HERE
			Save your answer to the variable Answer
			 */
			length = longest_palindrome();
			/////////////////////////////////////////////////////////////////////////////////////////////
			Answer = length;

			// Print the answer to output.txt
			pw.println("#" + test_case + " " + Answer);
			// To ensure that your answer is printed safely, please flush the string buffer while running the program
			pw.flush();
		}

		br.close();
		pw.close();
	}

	public static int longest_palindrome() {
		sub_length = new int[n+1][n+1];
		// padding
		for(int i = 0; i <= n; i++) {
			sub_length[i][0] = 0;
			sub_length[0][i] = 0;
		} 

		for(int i = 1; i <= n; i++) {
			sub_length[i][i] = 1;
		} 

		for(int start = 1; start <= n; start++) { 
			for(int end = n; end > 0; end--) { 
				if (s.charAt(start - 1) == s.charAt(end - 1))
					sub_length[start][n-end+1] = sub_length[start-1][n-end]+1;
				else
					sub_length[start][n-end+1] = Math.max(sub_length[start-1][n-end+1] , sub_length[start][n-end]);
				}
		}

		return sub_length[n][n];
	}
}

