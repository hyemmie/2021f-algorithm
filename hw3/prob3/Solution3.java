import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.PrintWriter;


class Solution3 {

	static int N, E;
	static int u, v, w;
	static int N_MAX = 1000;
	static int E_MAX= 100000;
	static int[] ver1 = new int[N_MAX+1];
	static int[] ver2 = new int[N_MAX+1];
	static Edge[] edges;
	static LinkedList<Edge>[] adjacencyList;
	static int[] visited;


	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new FileReader("input3.txt"));
		StringTokenizer stk;
		PrintWriter pw = new PrintWriter("output3.txt");

		for (int test_case = 1; test_case <= 10; test_case++) {

			stk = new StringTokenizer(br.readLine());
			N = Integer.parseInt(stk.nextToken());
			E = Integer.parseInt(stk.nextToken());
      stk = new StringTokenizer(br.readLine());
 
			edges = new Edge[E+1];
			adjacencyList = new LinkedList[N+1];
			visited = new int[N+1];

			for (int i = 0; i < E; i++) {
				u = Integer.parseInt(stk.nextToken());
				v = Integer.parseInt(stk.nextToken());
				w = Integer.parseInt(stk.nextToken());
				Edge currEdge = new Edge(u, v, w);
				edges[i] = currEdge;
				if (adjacencyList[u] == null) {
					adjacencyList[u] = new LinkedList<Edge>();
				}
				adjacencyList[u].add(currEdge);
			}

			long ver1Start = System.currentTimeMillis();

			for (int i = 1; i <= N; i++) {
				ver1[i] = Integer.MAX_VALUE;
      }

			ver1[1] = 0;
			for (int i = 1; i <= N-1; i++) { 
				for (int j = 0; j < E; j++) {
					Edge currEdge = edges[j];
					if (ver1[currEdge.u] != Integer.MAX_VALUE && ver1[currEdge.u] + currEdge.w < ver1[currEdge.v]) {
						ver1[currEdge.v] = ver1[currEdge.u] + currEdge.w;
					}
				}
			}
			long ver1End = System.currentTimeMillis();

			long ver2Start = System.currentTimeMillis();

      for (int i = 1; i <= N; i++) {
				ver2[i] = Integer.MAX_VALUE;
      }
			ver2[1] = 0;	
			visited[1] = 1;

			for (int i = 1; i <= N-1; i++) { 
				boolean relexation = false; 
				for (int v = 1; v <= N; v++) { 
					if (visited[v] == 1) {
						Iterator itr = adjacencyList[v].iterator();
						while (itr.hasNext()) { 
							Edge currEdge = (Edge)itr.next();
							if (ver2[currEdge.u] != Integer.MAX_VALUE && ver2[currEdge.u] + currEdge.w < ver2[currEdge.v]) { 
								ver2[currEdge.v] = ver2[currEdge.u] + currEdge.w;
								visited[currEdge.v] = 1;
								relexation = true;
							}
						} 
					} 
				}
				if (relexation == false) break; 
			}	

			long ver2End = System.currentTimeMillis();

			pw.println("#" + test_case);
			for (int i = 1; i <= N; i++) {
				pw.print(ver1[i]);
				if (i != N) pw.print(" ");
				else pw.print("\n");
			}
			long ver1Time = ver1End - ver1Start;
			pw.println(ver1Time);

			for (int i = 1; i <= N; i++) {
				pw.print(ver2[i]);
				if (i != N) pw.print(" ");
				else pw.print("\n");
			}
			long ver2Time = ver2End - ver2Start;
			pw.println(ver2Time);

			pw.flush();
		}

		br.close();
		pw.close();
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