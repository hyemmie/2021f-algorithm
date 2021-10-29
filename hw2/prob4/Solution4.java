import java.util.StringTokenizer;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.PrintWriter;

/*
  1. Compile the file with the following command. The class file named Solution4 would be created when you compile the source code.
      javac Solution4.java -encoding UTF8

  2. Run the compiled program with the following command. Output file(output.txt) should be created after the program is finished
      java Solution4

  - The encoding of the source code should be UTF8
  - You can use the 'time' command to measure your algorithm.
    time java Solution4
  - You can also halt the program with the 'timeout' command.
      timeout 0.5 java Solution4
      timeout 1 java Solution4
 */

class Solution4 {
	static final int max_n = 1000;

	static int n, H;
	static int[] h = new int[max_n], d = new int[max_n-1];
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
			/*
				n is the number of blocks, and H is the max tower height
				read each height of the block to h[0], h[1], ... , h[n-1]
				read the heights of the holes to d[0], d[1], ... , d[n-2]
			 */
			stk = new StringTokenizer(br.readLine());
			n = Integer.parseInt(stk.nextToken()); H = Integer.parseInt(stk.nextToken());
			stk = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				h[i] = Integer.parseInt(stk.nextToken());
			}
			stk = new StringTokenizer(br.readLine());
			for (int i = 0; i < n-1; i++) {
				d[i] = Integer.parseInt(stk.nextToken());
			}


			/////////////////////////////////////////////////////////////////////////////////////////////
			/*
				YOUR CODE HERE
				Save your answer to the variable Answer
			 */
			/////////////////////////////////////////////////////////////////////////////////////////////
			// Answer = 0;
			build();


			// Print the answer to output.txt
			pw.println("#" + test_case + " " + Answer);
			// To ensure that your answer is printed safely, please flush the string buffer while running the program
			pw.flush();
		}

		br.close();
		pw.close();
	}

	public static void build() {
		Answer = 0;
		int [] temp_block = new int[H+1];
		int [] confirmed_block = new int[H+1];

		temp_block[h[0]]++;

		for(int block = 1; block < n ; block++){
			for(int curr = H; curr > 0; curr--){
				temp_block[h[block]]++;
				int continous = curr + h[block];
				int discrete = curr + h[block] - d[block-1];
				if(discrete <= H) {
					if(continous <= H){
						temp_block[continous] += confirmed_block[curr];
						temp_block[continous] %= 1000000;
					}
					temp_block[discrete] += temp_block[curr];
					temp_block[discrete] %= 1000000;
					confirmed_block[curr] += temp_block[curr];
					confirmed_block[curr] %= 1000000;
					temp_block[curr] = 0;
				} else {
					confirmed_block[curr] += temp_block[curr];
					confirmed_block[curr] %= 1000000;
					temp_block[curr] = 0;
				}
			}
		}

		for(int sub = 1; sub <= H; sub++){
			Answer += (temp_block[sub] + confirmed_block[sub]) % 1000000;
			Answer %= 1000000;
		}
	}
}

