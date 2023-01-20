import java.util.*;

public class MaxBandwidth {
    

//figure out the max index
int maxBandwidth(int weight[], Boolean visited[], int n) {
    int maxValue = Integer.MIN_VALUE, max_index = -1;

    for(int i = 0; i < n; i++){
        if (weight[i] >= maxValue && visited[i] == false){
            maxValue = weight[i];
            max_index = i;
        }
    }
    return max_index;
}

int min (int x, int y){
    if (x == 0 || y == 0){
        return x+y;
    }
    if(x < y){
        return x;
    } else {
        return y;
    }
}

//dijkstra's algorithm flipped from min to max
void dijkstraMod(int graph[][], int n, int x, int y) {
    //initialize
    int bandwidth[] = new int[n];
    Boolean visited[] = new Boolean[n];

    //iterate through graph
    for(int i = 0; i<n; i++){
        bandwidth[i] = Integer.MIN_VALUE;
        visited[i]= false;
    }
    //set starting vertex point to 0 as the source vertex
    bandwidth[x] = 0;
    //find the max bandwidth loop after visiting each vertex-edge
    for(int i = 0; i<n; i++){
        int index = maxBandwidth(bandwidth, visited, n);
        visited[index] = true;

        for(int j = 0; j<n; j++){
            if(bandwidth[index] !=  Integer.MAX_VALUE && min(bandwidth[index], graph[index][j]) > bandwidth[j] && !visited[j] && graph[index][j] !=0) {
                bandwidth[j] = min(bandwidth[index], graph[index][j]);
            }
        }
    }
    
    print(bandwidth, n, x, y);
}

//print function
void print(int bandwidth[], int n, int x, int y){
    System.out.println("The max bandwidth is " + bandwidth[y]);
}

 
//main function
public static void main(String[] args){
    Scanner input = new Scanner(System.in);

    //number of nodes in the graph
    System.out.println("Please enter number of centers:");
    int n = input.nextInt();

    //starting node
    System.out.println("Please enter the index of the starting center between 0 and " + (n-1));
    int a = input.nextInt();
    
    //ending node
    System.out.println("Please enter the index of the goal center between 0 and " + (n-1));
    int b = input.nextInt();

    //Edge weights entry
    System.out.println("Please enter the edge weights for the vertex graph.\nExample: \n  [0 10 14 12] = a:a:0, a:b:10, a:c:14, a:d:12");
    int graph[][] = new int[n][n];
    for(int i = 0; i < n; i++){
        for(int j = 0; j < n; j++){
            graph[i][j] = input.nextInt();
        } 
    } 
    /*further examplaination:
        A;B;10
        B;D;15
        A;C;14
        C;B;15
        D;A;12
    input as:
        0 10 14 12
        10 0 15 15
        14 15 0 0
        12 15 0 0
    */

    MaxBandwidth maxBand = new MaxBandwidth();
    maxBand.dijkstraMod(graph, n, a, b);
    //spit out the result



}



}
