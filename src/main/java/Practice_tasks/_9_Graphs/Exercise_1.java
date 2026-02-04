/*
Дадени се објекти кои се поврзани со улици и се групирани во градови. За група на објекти се смета дека припаѓаат на еден град ако секој објект е поврзан со барем еден друг објект од тој град, а не е поврзан со ниту еден објект од некој друг град. Да се одреди во колку градови се групирани дадените објекти.


Влез: Во првиот ред е даден бројот на објекти N. Потоа во следните N редови се дадени имињата на објектите. Во следниот ред е даден бројот на улици M, а во следните M редови се дадени објектите кои ги поврзува секоја улица.

Излез:  Бројот на градови во кои се групирани дадените објекти.

Пример:

Влез:

5
School1
ApartmentBuilding1
Park1
Supermarket1
Hospital1
2
School1 ApartmentBuilding1
Park1 Supermarket1

Излез: 3 (Објаснување: School1 и ApartmentBuilding1 се во еден град, Park1 и Supermarket1 во друг и Hospital1 во трет)

/

You're given objects connected by streets and grouped into cities. A group of objects is considered to belong to the same city if each object is connected to at least one other object in that city and is not connected to any object from another city. Determine how many cities the given objects are grouped into.


Input: The first line contains the number of objects N. The next N lines contain the names of the objects. The next line contains the number of streets M, followed by M lines that specify the objects connected by each street.

Output: The number of cities the given objects are grouped into.

Example:

Input:

5
School1
ApartmentBuilding1
Park1
Supermarket1
Hospital1
2
School1 ApartmentBuilding1
Park1 Supermarket1

Output: 3 (Explanation: School1 and ApartmentBuilding1 are in one city, Park1 and Supermarket1 in another, and Hospital1 in a third.)

For example:
Input 	Result

5
School1
ApartmentBuilding1
Park1
Supermarket1
Hospital1
2
School1 ApartmentBuilding1
Park1 Supermarket1



3

5
School1
ApartmentBuilding1
ApartmentBuilding2
Park1
Hospital1
4
School1 ApartmentBuilding1
ApartmentBuilding1 ApartmentBuilding2
ApartmentBuilding2 Park1
Park1 Hospital1



1
 */
package Practice_tasks._9_Graphs;


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

        sc.nextLine();

        HashMap<String, Integer> getNumber = new HashMap<>();
        int counter=0;

        for(int i=0;i<v;i++){
            String input = sc.nextLine();
            getNumber.put(input,counter);
            g.addVertex(counter);
            counter++;
        }

        int e = sc.nextInt();
        sc.nextLine();
        for(int i=0;i<e;i++){
            int v1,v2;
            String str1=sc.next();
            String str2=sc.next();

            g.addEdge(getNumber.get(str1),getNumber.get(str2));
        }

        System.out.println(g.BrojNaSvrzaniKomponenti());


    }
}