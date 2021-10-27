import java.util.StringTokenizer;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.PrintWriter;

/*
  1. Compile the file with the following command. The class file named Solution1 would be created when you compile the source code.
      javac Solution1.java -encoding UTF8

  2. Run the compiled program with the following command. Output file(output.txt) should be created after the program is finished
      java Solution1

  - The encoding of the source code should be UTF8
  - You can use the 'time' command to measure your algorithm.
      time java Solution1
  - You can also halt the program with the 'timeout' command.
      timeout 0.5 java Solution1
      timeout 1 java Solution1
 */

class Solution1 {

	/*
		Since the results cannot be covered with the boundary of int type, use long type for the variable of the result.
		Overflowed cases will be counted in zero points.
		We assume that Answer[0] is the number of "a", Answer[1] is the number of "b", Answer[2] is the number of "c".
	*/
	static int n;                           // string length
	static String s;                        // string sequence
	static long[] Answer = new long[3];
	static long a, b, c;

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
				You can massage string s whatever you want.
				Save your answer to the variable Answer(long type!)
			 */
			calculate_substring();		
			/////////////////////////////////////////////////////////////////////////////////////////////
			Answer[0] = a;  // the number of a
			Answer[1] = b;  // the number of b
			Answer[2] = c;  // the number of c


			// Print the answer to output.txt
			pw.println("#" + test_case + " " + Answer[0] + " " + Answer[1] + " " + Answer[2]);
			// To ensure that your answer is printed safely, please flush the string buffer while running the program
			pw.flush();
		}

		br.close();
		pw.close();
	}

	public static void calculate_substring() {
		CaseCounter[][] counter = new CaseCounter[n+1][n+1];

		// initialize matrix with every char of string
		for(int diag = 1; diag <= n; diag++) {
			counter[diag][diag] = new CaseCounter();
			String curr = s.substring(diag - 1, diag);
			if (curr.equals("a")) {
				counter[diag][diag].case_a += 1;
			} else if (curr.equals("b")) {
				counter[diag][diag].case_b += 1;
			} else {
				counter[diag][diag].case_c += 1;
			}
		}

		// build matrix with substring
		for(int i = 1; i < n; i++) {
			for(int start = 1; start <= n-i; start++) {
				int end = i + start;
				counter[start][end] = new CaseCounter();

				CaseCounter sub_start, sub_end;
				for(int k = start; k < end; k++) {
					sub_start = counter[start][k];
					sub_end = counter[k + 1][end];

					if (sub_start.case_a > 0) {
						if (sub_end.case_a > 0)
							counter[start][end].case_b += sub_start.case_a * sub_end.case_a;
						if (sub_end.case_b > 0)
							counter[start][end].case_b += sub_start.case_a * sub_end.case_b;
						if (sub_end.case_c > 0)
							counter[start][end].case_a += sub_start.case_a * sub_end.case_c;
					}
					if (sub_start.case_b > 0) {
						if (sub_end.case_a > 0)
							counter[start][end].case_c += sub_start.case_b * sub_end.case_a;
						if (sub_end.case_b > 0)
							counter[start][end].case_b += sub_start.case_b * sub_end.case_b;
						if (sub_end.case_c > 0)
							counter[start][end].case_a += sub_start.case_b * sub_end.case_c;
					}
					if (sub_start.case_c > 0) {
						if (sub_end.case_a > 0)
							counter[start][end].case_a += sub_start.case_c * sub_end.case_a;
						if (sub_end.case_b > 0)
							counter[start][end].case_c += sub_start.case_c * sub_end.case_b;
						if (sub_end.case_c > 0)
							counter[start][end].case_c += sub_start.case_c * sub_end.case_c;
					}
				}
			}
		}

		a = counter[1][n].case_a;
		b = counter[1][n].case_b;
		c = counter[1][n].case_c;
	}
}

class CaseCounter {
	public long case_a;
	public long case_b;
	public long case_c;

	public CaseCounter() {
		this.case_a = 0;
		this.case_b = 0;
		this.case_c = 0;
	}
}
