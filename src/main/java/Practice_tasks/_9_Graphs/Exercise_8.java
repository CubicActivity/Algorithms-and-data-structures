/*
Потребно е дадени материјали да се трансформираат во други материјали. Дадени се можните директни трансформации и цената за секоја од нив. Да се одреди најмалата цена за да се транформираат почетните материјали во целните.


Влез: Во првиот ред е даден бројот на материјали N во секоја група (почетни и целни). Потоа во следните N редови се дадени парови на материјали: почетниот материјал и материјалот во кој треба да се трансформира. Во следниот ред е даден бројот на можни трансформации M, а потоа во следните M редови се дадени трансформациите и нивните цени.

Излез:  Минималната цена да се трансформираат сите материјали.

Пример:

Влез:

4
Wood Wood
Iron Steel
Steel Iron
Glass Mirror
6
Wood Iron 2
Iron Steel 5
Steel Iron 5
Steel Mirror 1
Mirror Iron 2
Glass Mirror 20

Излез: 28

(Објаснување:

транформација wood->wood 0

транформација iron->steel 5

транформација steel->iron 3

транформација glass->mirror 20

вкупно: 28

)


/

Given materials need to be transformed into other materials. You're given the possible direct transformations and the price of each transformation. Determine the minimum price to transform the starting materials into the goal materials.


Input: The first line contains the number of materials in each set (starting/goal) N. The next N lines contain the pairs of materials: starting material and the material it needs to be transformed into. The next line contains the number of possible transformations M, followed by M lines that contain the transformations and their prices.

Output: The minimum cost to transform all materials.

Example:

Input:

4
Wood Wood
Iron Steel
Steel Iron
Glass Mirror
6
Wood Iron 2
Iron Steel 5
Steel Iron 5
Steel Mirror 1
Mirror Iron 2
Glass Mirror 20

Output: 28

(Explanation:

transformation wood->wood 0

transformation iron->steel 5

transformation steel->iron 3

transformation glass->mirror 20

total: 28

)


For example:
Input 	Result

4
Wood Wood
Iron Steel
Steel Iron
Glass Mirror
6
Wood Iron 2
Iron Steel 5
Steel Iron 5
Steel Mirror 1
Mirror Iron 2
Glass Mirror 20



28

2
Iron Steel
Wood Plank
4
Iron Steel 5
Wood Plank 3
Iron Wood 10
Plank Steel 8



8
 */
package Practice_tasks._9_Graphs;

import java.util.Scanner;

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
//        adjacencyList.get(destination).put(source, weight); // for undirected graph
    }

    // Remove an edge from the graph
    public void removeEdge(T source, T destination) {
        if (adjacencyList.containsKey(source)) {
            adjacencyList.get(source).remove(destination);
        }
        if (adjacencyList.containsKey(destination)) {
//            adjacencyList.get(destination).remove(source); // for undirected graph
        }
    }

    // Get all neighbors of a vertex
    public Map<T, Integer> getNeighbors(T vertex) {
        return adjacencyList.getOrDefault(vertex, new HashMap<>());
    }

    public void DFS(T startVertex) {
        Set<T> visited = new HashSet<>();
        DFSUtil(startVertex, visited);
    }

    private void DFSUtil(T vertex, Set<T> visited) {
        // Mark the current node as visited and print it
        visited.add(vertex);
        System.out.print(vertex + " ");

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

    public int shortestPath(T startVertex, T endVertex) {
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

        return distances.get(endVertex);
    }

    @Override
    public String toString() {
        String ret = new String();
        for (Map.Entry<T, Map<T, Integer>> vertex : adjacencyList.entrySet())
            ret += vertex.getKey() + ": " + vertex.getValue() + "\n";
        return ret;
    }



}

class prog{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();

        AdjacencyListGraph<String> graph = new AdjacencyListGraph<>();


        HashMap<String, String> transformations  = new HashMap<>();

        for(int i=0;i<n;i++){
            String [] inputs = sc.nextLine().split(" ");
            transformations.put(inputs[0],inputs[1]);
        }

        int k = sc.nextInt();
        sc.nextLine();
        for(int i=0;i<k;i++){
            String [] parts = sc.nextLine().split(" ");
            graph.addEdge(parts[0],parts[1],Integer.parseInt(parts[2]));
        }
        int total=0;
        for(String input : transformations.keySet()){
            total+= graph.shortestPath(input, transformations.get(input));

        }
        System.out.println(total);

    }
}
