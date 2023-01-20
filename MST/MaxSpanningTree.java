package MST;

/* Create a maximum spanning tree in G, which would maximize the bandwidth between two switching centers using Java */

import java.util.*;
import java.io.*;

public class MaxSpanningTree {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		int n = in.nextInt();
		int m = in.nextInt();
		int[][] graph = new int[n][n];
		for (int i = 0; i < m; i++) {
			int a = in.nextInt();
			int b = in.nextInt();
			int c = in.nextInt();
			graph[a][b] = c;
			graph[b][a] = c;
		}
		in.close();
		
		int[] parent = new int[n];
		int[] key = new int[n];
		boolean[] mstSet = new boolean[n];
		for (int i = 0; i < n; i++) {
			key[i] = Integer.MAX_VALUE;
			mstSet[i] = false;
		}
		key[0] = 0;
		parent[0] = -1;
		
		for (int i = 0; i < n - 1; i++) {
			int u = minKey(key, mstSet);
			mstSet[u] = true;
			for (int v = 0; v < n; v++) {
				if (graph[u][v] != 0 && mstSet[v] == false && graph[u][v] < key[v]) {
					parent[v] = u;
					key[v] = graph[u][v];
				}
			}
		}
		
		int max = 0;
		for (int i = 1; i < n; i++) {
			if (graph[i][parent[i]] > max) {
				max = graph[i][parent[i]];
			}
		}
		System.out.println(max);
	}
	
	public static int minKey(int[] key, boolean[] mstSet) {
		int min = Integer.MAX_VALUE;
		int minIndex = -1;
		for (int i = 0; i < key.length; i++) {
			if (mstSet[i] == false && key[i] < min) {
				min = key[i];
				minIndex = i;
			}
		}
		return minIndex;
	}
}