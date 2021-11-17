import java.util.StringTokenizer;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.*;

class Solution2 {
	
	static int N, E;
	static int u, v, w;
	static PriorityQueue<Edge> maxHeap = new PriorityQueue<Edge>();
	static int Answer;
	static int[] root;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new FileReader("input2.txt"));
		StringTokenizer stk;
		PrintWriter pw = new PrintWriter("output2.txt");

		for (int test_case = 1; test_case <= 10; test_case++) {

			stk = new StringTokenizer(br.readLine());
			N = Integer.parseInt(stk.nextToken());
			E = Integer.parseInt(stk.nextToken());
      stk = new StringTokenizer(br.readLine());
			Answer = 0;
			root = new int [N+1];

			for (int i = 0; i < E; i++) {
				u = Integer.parseInt(stk.nextToken());
				v = Integer.parseInt(stk.nextToken());
				w = Integer.parseInt(stk.nextToken());
				Edge curr = new Edge(u, v, w);
				maxHeap.add(curr);
			}

			// union-find로 mst 찾아낼 것
			for (int i = 1; i <= N; i++) {
				root[i] = i;
			}

			int selected_edges = 1;
			while(selected_edges < N) {
				Edge max = maxHeap.poll();
				if (find(max.u) != find(max.v)) {
					union(max.u, max.v);
					Answer += max.w;
					selected_edges++;
				}
			}

			maxHeap.clear();

      pw.println("#" + test_case + " " + Answer);
			pw.flush();
		}

		br.close();
		pw.close();
	}

	public static int find(int i) {
		if (root[i] == i) return i;
		else return root[i] = find(root[i]);
	}

	public static void union(int i, int j) {
		int root_i = find(i);
		int root_j = find(j);
		root[root_i] = root_j;
	}

	public static class Edge implements Comparable<Edge> {
		int u;
		int v;
		int w;

		public Edge(int u, int v, int w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Edge compared) {
			return this.w <= compared.w ? 1 : - 1;
		}
	}
}