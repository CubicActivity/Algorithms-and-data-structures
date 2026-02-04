/*
Дадени се градови и патишта кои постојат помеѓу нив. Дел од патиштата се поплавени и не може да се користат, што може да доведе до тоа градовите да се поделат на групи што не се меѓусебно поврзани. Секој град од една група е поврзан со барем еден друг град од групата преку достапни (непоплавени) патишта, а не е поврзан со градови надвор од групата. Да се одреди колку вакви групи ќе има во случај на поплава.


Влез: Во првиот ред е даден бројот на патишта M. Во следните M редови се дадени градовите кои ги поврзува секој пат. Потоа е даден бројот k на поплавени патишта, а во следните k редови се дадени поплавените патишта.

Излез:  Бројот на групи на градови кои ќе останат меѓусебно поврзани.

Пример:

Влез:

6
London Paris
Paris Brussels
Brussels Amsterdam
London Brussels
Paris Berlin
Berlin Warsaw
2
Paris Berlin
London Brussels

Излез: 2 (Објаснување: Ако не може да се користат патиштата Paris-Berlin и London-Brussels, градовите ќе се поделат во две групи: {London, Paris, Brussels, Amsterdam} и {Berlin, Warsaw})


/

Given cities and roads that exist between them, some roads are flooded and cannot be used. This may cause the cities to split into groups that are no longer connected. Each city in a group is connected to at least one other city in the same group via accessible (non-flooded) roads and is not connected to cities outside the group. Determine how many such groups will exist in the event of a flood.


Input: The first line contains the number of roads M. The next M lines specify the cities connected with each road. In the next line, you're given the number of flooded roads k, followed by k lines which give the flooded roads.

Output: The number of groups of still connected cities.

Example:

Input:

6
London Paris
Paris Brussels
Brussels Amsterdam
London Brussels
Paris Berlin
Berlin Warsaw
2
Paris Berlin
London Brussels


Output: 2 (Explanation: If the roads Paris-Berlin and London-Brussels can't be used, the cities will be split into two groups: {London, Paris, Brussels, Amsterdam} and {Berlin, Warsaw}.)

For example:
Input 	Result

6
London Paris
Paris Brussels
Brussels Amsterdam
London Brussels
Paris Berlin
Berlin Warsaw
2
Paris Berlin
London Brussels



2

5
Chicago Boston
Boston Philadelphia
Philadelphia Washington
Washington Atlanta
Atlanta Miami
2
Boston Philadelphia
Atlanta Miami



3
 */
package Practice_tasks._9_Graphs;

import java.util.Scanner;
import java.util.*;

import java.util.*;

class AdjacencyListGraph<T> {
    private Map<T, Map<T, Integer>> adjacencyList;

    public AdjacencyListGraph() {
        this.adjacencyList = new HashMap<>();
    }

    // Add a vertex to the graph
    public void addVertex(T vertex) {
        if (!adjacencyList.containsKey(vertex)) {
            adjacencyList.put(vertex, new HashMap<>());
        }
    }

    // Remove a vertex from the graph
    public void removeVertex(T vertex) {
        // Remove the vertex from all adjacency lists
        for (Map<T, Integer> neighbors : adjacencyList.values()) {
            neighbors.remove(vertex);
        }
        // Remove the vertex's own entry in the adjacency list
        adjacencyList.remove(vertex);
    }

    // Add an edge to the graph
    public void addEdge(T source, T destination, int weight) {
        addVertex(source);
        addVertex(destination);

        adjacencyList.get(source).put(destination, weight);
        adjacencyList.get(destination).put(source, weight); // for undirected graph
    }

    // Remove an edge from the graph
    public void removeEdge(T source, T destination) {
        if (adjacencyList.containsKey(source)) {
            adjacencyList.get(source).remove(destination);
        }
        if (adjacencyList.containsKey(destination)) {
            adjacencyList.get(destination).remove(source); // for undirected graph
        }
    }

    // Get all neighbors of a vertex
    public Map<T, Integer> getNeighbors(T vertex) {
        return adjacencyList.getOrDefault(vertex, new HashMap<>());
    }

    public int components() {
        Set<T> visited = new HashSet<>();
        int counter =0;

        for(T node : adjacencyList.keySet()){
            if(!visited.contains(node)){
                counter++;
                DFSUtil(node,visited);
            }

        }
        return counter;
    }

    private void DFSUtil(T vertex, Set<T> visited) {
        // Mark the current node as visited and print it
        visited.add(vertex);

        // Recur for all the vertices adjacent to this vertex
        for (T neighbor : getNeighbors(vertex).keySet()) {
            if (!visited.contains(neighbor)) {
                DFSUtil(neighbor, visited);
            }
        }
    }


    public void DFSNonRecursive(T startVertex) {
        Set<T> visited = new HashSet<>();
        Stack<T> stack = new Stack<>();

        stack.push(startVertex);
        while (!stack.isEmpty()) {
            T vertex = stack.pop();
            if (!visited.contains(vertex)) {
                visited.add(vertex);
                System.out.print(vertex + " ");
                for (T neighbor : getNeighbors(vertex).keySet()) {
                    if (!visited.contains(neighbor)) {
                        stack.push(neighbor);
                    }
                }
            }
        }
    }

    public void printPath(T source, T destination) {
        Set<T> visited = new HashSet<>();
        Stack<T> stack = new Stack<>();

        stack.push(source);
        visited.add(source);
        while (!stack.isEmpty() && !stack.peek().equals(destination)) {
            T vertex = stack.peek();

            boolean f = true;
            for(T neighbor: getNeighbors(vertex).keySet()) {
                if(!visited.contains(neighbor)) {
                    stack.push(neighbor);
                    visited.add(neighbor);
                    f = false;
                    break;
                }
            }

            if(f) {
                stack.pop();
            }
        }

        Stack<T> path = new Stack<>();

        while(!stack.isEmpty()) {
            path.push(stack.pop());
        }

        while(!path.isEmpty()) {
            System.out.print(path.pop() + " ");
        }
    }

    public void BFS(T startVertex) {
        Set<T> visited = new HashSet<>();
        Queue<T> queue = new LinkedList<>();

        visited.add(startVertex);
        queue.add(startVertex);

        while (!queue.isEmpty()) {
            T vertex = queue.poll();
            System.out.print(vertex + " ");

            for (T neighbor : getNeighbors(vertex).keySet()) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
    }

    public Map<T, Integer> shortestPath(T startVertex) {
        Map<T, Integer> distances = new HashMap<>();
        PriorityQueue<T> queue = new PriorityQueue<>(Comparator.comparingInt(distances::get));
        Set<T> explored = new HashSet<>();

        // Initialize distances
        for (T vertex : adjacencyList.keySet()) {
            distances.put(vertex, Integer.MAX_VALUE);
        }
        distances.put(startVertex, 0);

        queue.add(startVertex);

        while (!queue.isEmpty()) {
            T current = queue.poll();
            explored.add(current);

            for (Map.Entry<T, Integer> neighborEntry : adjacencyList.get(current).entrySet()) {
                T neighbor = neighborEntry.getKey();
                int newDist = distances.get(current) + neighborEntry.getValue();

                if (newDist < distances.get(neighbor)) {
                    distances.put(neighbor, newDist);

                    // Update priority queue
                    if (!explored.contains(neighbor)) {
                        queue.add(neighbor);
                    }
                }
            }
        }

        return distances;
    }

    @Override
    public String toString() {
        String ret = new String();
        for (Map.Entry<T, Map<T, Integer>> vertex : adjacencyList.entrySet())
            ret += vertex.getKey() + ": " + vertex.getValue() + "\n";
        return ret;
    }



}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        AdjacencyListGraph<String> graph = new AdjacencyListGraph<>();


        int m = sc.nextInt();
        sc.nextLine();

        for(int i=0;i<m;i++){

            String [] parts = sc.nextLine().split(" ");
            String v1 = parts[0];
            String v2 = parts[1];

            graph.addEdge(v1,v2,0);

        }

        int k = sc.nextInt();
        sc.nextLine();
        for(int i=0;i<k;i++){

            String [] parts = sc.nextLine().split(" ");
            String v1 = parts[0];
            String v2 = parts[1];

            graph.removeEdge(v1,v2);
        }

        System.out.println(graph.components());

    }
}
