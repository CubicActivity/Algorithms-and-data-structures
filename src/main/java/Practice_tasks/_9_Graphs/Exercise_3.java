/*
Дадени се рутери означени со броеви од 0 до N-1 кои се поврзани во мрежа. Ако некој рутер R падне, мрежата може да се подели на групи на рутери што не се меѓусебно поврзани. Секој рутер од една група е поврзан со барем еден друг рутер од групата, а не е поврзан со рутери надвор од групата. Да се одреди колку вакви групи ќе настанат доколку рутерот R престане да работи.


Влез: Во првиот ред е даден бројот на рутери N. Во следниот ред е даден бројот на врски помеѓу рутерите M, а во следните M редови се дадени рутерите кои ги поврзува секоја врска. Во последниот ред е даден рутерот R што престанал да работи.

Излез:  Бројот на групи на рутери.

Пример:

Влез:

5
4
0 1
1 2
2 3
2 4
1

Излез: 2 (Објаснување: Ако падне рутерот 1 ќе се појават две групи: 0 и 2,3,4)


/

Given routers labeled from 0 to N−1 that are connected in a network, the failure of a specific router R may split the network into groups of routers that are no longer connected. Each router in a group is connected to at least one other router from that group, and isn't connected to any routers outside of that group. Determine the number of such groups that will form if router R fails.


Input: The first line contains the number of routers N. The next line contains the number of connections between the routers M, followed by M lines that specify which routers are connected with each connection. The last line contains the router R that failed.

Output: The number of groups of routers.

Example:

Input:

5
4
0 1
1 2
2 3
2 4
1


Output: 2 (Explanation: If router 1 fails, the network will split into two groups: 0 and 2,3,4.)

For example:
Input 	Result

5
4
0 1
1 2
2 3
2 4
1



2

4
3
0 1
1 2
2 3
0



1
 */
package Practice_tasks._9_Graphs;

/*
Given routers labeled from 0 to N−1 that are connected in a network, the failure of a specific router R may split the network into groups of routers that are no longer connected. Each router in a group is connected to at least one other router from that group, and isn't connected to any routers outside of that group. Determine the number of such groups that will form if router R fails.


Input: The first line contains the number of routers N. The next line contains the number of connections between the routers M, followed by M lines that specify which routers are connected with each connection. The last line contains the router R that failed.

Output: The number of groups of routers.

Example:

Input:

5
4
0 1
1 2
2 3
2 4
1

Output: 2 (Explanation: If router 1 fails, the network will split into two groups: 0 and 2,3,4.)

*/


import java.util.*;
import java.util.Map.Entry;

class AdjacencyListGraph{
    private Map<Integer, Set<Integer>> adjacencyList;

    public AdjacencyListGraph() {
        this.adjacencyList = new HashMap<>();
    }

    // Add a vertex to the graph
    public void addVertex(Integer vertex) {
        if (!adjacencyList.containsKey(vertex)) {
            adjacencyList.put(vertex, new HashSet<>());
        }
    }

    // Remove a vertex from the graph
    public void removeVertex(Integer vertex) {
        // Remove the vertex from all adjacency lists
        for (Set<Integer> neighbors : adjacencyList.values()) {
            neighbors.remove(vertex);
        }
        // Remove the vertex's own entry in the adjacency list
        adjacencyList.remove(vertex);
    }

    // Add an edge to the graph
    public void addEdge(Integer source, Integer destination) {
        addVertex(source);
        addVertex(destination);

        adjacencyList.get(source).add(destination);
        adjacencyList.get(destination).add(source); // for undirected graph
    }

    // Remove an edge from the graph
    public void removeEdge(Integer source, Integer destination) {
        if (adjacencyList.containsKey(source)) {
            adjacencyList.get(source).remove(destination);
        }
        if (adjacencyList.containsKey(destination)) {
            adjacencyList.get(destination).remove(source); // for undirected graph
        }
    }

    // Get all neighbors of a vertex
    public Set<Integer> getNeighbors(Integer vertex) {
        return adjacencyList.getOrDefault(vertex, new HashSet<>());
    }


    public int BrojNaSvrzaniKomponenti(){
        int brojNaKomponenti=0;
        Set<Integer> visited = new HashSet<>();

        for(int v: adjacencyList.keySet()){
            if(!visited.contains(v)){
                brojNaKomponenti++;
                DFS(v, visited);
            }

        }

        return brojNaKomponenti;

    }


    private void DFS(Integer vertex, Set<Integer> visited) {
        visited.add(vertex);

        // Recur for all the vertices adjacent to this vertex
        for (Integer neighbor : getNeighbors(vertex)) {
            if (!visited.contains(neighbor)) {
                DFS(neighbor, visited);
            }
        }
    }


    public void DFSNonRecursive(Integer startVertex) {
        Set<Integer> visited = new HashSet<>();
        Stack<Integer> stack = new Stack<>();

        stack.push(startVertex);
        while (!stack.isEmpty()) {
            Integer vertex = stack.pop();
            if (!visited.contains(vertex)) {
                visited.add(vertex);
                System.out.print(vertex + " ");
                for (Integer neighbor : getNeighbors(vertex)) {
                    if (!visited.contains(neighbor)) {
                        stack.push(neighbor);
                    }
                }
            }
        }
    }

    public void BFS(Integer startVertex) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        visited.add(startVertex);
        queue.add(startVertex);

        while (!queue.isEmpty()) {
            Integer vertex = queue.poll();
            System.out.print(vertex + " ");

            for (Integer neighbor : getNeighbors(vertex)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
    }

    public int shortestPath(Integer startVertex, Integer endVertex) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        visited.add(startVertex);
        queue.add(startVertex);
        int elementsAtLevel;
        int level = 0;

        while (!queue.isEmpty()) {
            elementsAtLevel = queue.size();
            while (elementsAtLevel > 0) {
                Integer vertex = queue.poll();
                if (vertex.equals(endVertex))
                    return level;

                for (Integer neighbor : getNeighbors(vertex)) {
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        queue.add(neighbor);
                    }
                }
                elementsAtLevel--;
            }
            level++;
        }
        return -1;
    }

    public void pathsOfLengthN(Integer startVertex, int goalLength) {
        Set<Integer> visited = new HashSet<>();
        ArrayList<Integer> startPath = new ArrayList<>();
        startPath.add(startVertex);
        pathsOfLengthNUtil(startVertex, goalLength, visited, startPath);
    }

    private void pathsOfLengthNUtil(Integer vertex, int goalLength, Set<Integer> visited, List<Integer> currentPath) {
        if (currentPath.size()==goalLength+1) {
            System.out.println(currentPath);
            return;
        }
        visited.add(vertex);
        for (Integer neighbor : getNeighbors(vertex)) {
            if (!visited.contains(neighbor)) {
                currentPath.add(neighbor);
                pathsOfLengthNUtil(neighbor, goalLength, visited, currentPath);
                currentPath.remove(neighbor);
            }
        }
        visited.remove(vertex);
    }


    public void findPath(Integer startVertex, Integer endVertex) {
        Set<Integer> visited = new HashSet<>();
        Stack<Integer> invertedPath = new Stack<>();
        visited.add(startVertex);
        invertedPath.push(startVertex);

        while(!invertedPath.isEmpty() && !invertedPath.peek().equals(endVertex)) {
            Integer currentVertex = invertedPath.peek();
            Integer tmp = currentVertex;

            for(Integer vertex : getNeighbors(currentVertex)) {
                tmp = vertex;
                if(!visited.contains(vertex)) {
                    break;
                }
            }

            if(!visited.contains(tmp)) {
                visited.add(tmp);
                invertedPath.push(tmp);
            }
            else {
                invertedPath.pop();
            }
        }

        Stack<Integer> path = new Stack<>();
        while(!invertedPath.isEmpty()) {
            path.push(invertedPath.pop());
        }
        while(!path.isEmpty()) {
            System.out.println(path.pop());
        }
    }

    @Override
    public String toString() {
        String ret = new String();
        for (Entry<Integer, Set<Integer>> vertex : adjacencyList.entrySet())
            ret += vertex.getKey() + ": " + vertex.getValue() + "\n";
        return ret;
    }



}

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        AdjacencyListGraph g = new AdjacencyListGraph();

        int v = sc.nextInt();
        int e = sc.nextInt();

        for(int i=0;i<e;i++){
            int v1,v2;
            v1=sc.nextInt();
            v2=sc.nextInt();
            g.addEdge(v1,v2);
        }
        int k = sc.nextInt();
        g.removeVertex(k);

        System.out.println(g.BrojNaSvrzaniKomponenti());


    }
}
