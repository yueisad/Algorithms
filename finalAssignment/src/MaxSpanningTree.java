import java.util.*;
import java.lang.*;
import java.io.*;

public class MaxSpanningTree {
    
int minm(int x, int y)
{
if(x == 0 || y == 0)
return x+y;
if(x < y)
return x;
return y;
}


int maxbandwidth(int bandwidth[], Boolean visited[], int n)
{
int max = Integer.MIN_VALUE, max_index = -1;

for (int i = 0; i < n; i++)
if (bandwidth[i] >= max && visited[i] == false)
{
max = bandwidth[i];
max_index = i;
}

return max_index;
}

void print(int bandwidth[], int n, int a, int b)
{
System.out.println("Max bandwidth between " + a + " and " + b + " = " + bandwidth[b]);
}


void reverse_dijkstra(int graph[][], int n, int a, int b)
{
int bandwidth[] = new int[n];

Boolean visited[] = new Boolean[n];

for (int i = 0; i < n; i++)
{
bandwidth[i] = Integer.MIN_VALUE;
visited[i] = false;
}

bandwidth[a] = 0;

for (int i = 0; i < n-1; i++)
{
int u = maxbandwidth(bandwidth, visited, n);

visited[u] = true;

for (int v = 0; v < n; v++)

if (bandwidth[u] != Integer.MAX_VALUE && minm(bandwidth[u], graph[u][v]) > bandwidth[v] && !visited[v] && graph[u][v] != 0)
bandwidth[v] = minm(bandwidth[u], graph[u][v]);
}

print(bandwidth, n, a, b);
}

public static void main(String[] args)
{
Scanner sc= new Scanner(System.in);
int n = sc.nextInt();
int a = sc.nextInt();
int b = sc.nextInt();
int graph[][] = new int[n][n];
for(int i = 0; i < n; i++)
for(int j = 0; j < n; j++)
graph[i][j] = sc.nextInt();
MaxSpanningTree t = new MaxSpanningTree();
t.reverse_dijkstra(graph, n, a, b);
}
}


