//Даден е неориентиран нетежински граф чии темиња се позитивни цели броеви. Да се најде бројот на патишта во графот, почнувајќи од фиксно теме V, кај кои сумата на темињата има вредност N. Темињата во патот може да се повторуваат.
//
//
//Влез: Во првиот ред е даден бројот на рабови во графот M. Потоа во следните M редови се дадени рабовите во графот во формат теме1 теме2. Во претпоследниот ред е дадено почетното теме V, a во последниот ред е дадена бараната сума N.
//
//Излез:  Бројот на патишта во графот кај кои сумата на темињата изнесува N.
//
//Пример:
//
//Влез:
//
//6
//2 5
//2 3
//5 3
//5 1
//3 1
//3 4
//5
//10
//
//Излез: 3 (Објаснување: патеките се 5-2-3, 5-3-2, 5-1-3-1)
//
//
///
//
//Given an undirected unweighted graph whose vertices are positive integers, find the number of paths in the graph, starting from a fixed vertex V, such that the sum of the vertices is N. The vertices in the path can be repeated.
//
//
//Input: The first line contains the number of edges in the graph M, followed by the edges in the next M lines. The edges are given in the format vertex1 vertex2. The second-to-last line contains the start vertex V and the last line contains the wanted sum N.
//
//Output: The number of paths where the sum of the vertices is N.
//
//Example:
//
//Input:
//
//6
//2 5
//2 3
//5 3
//5 1
//3 1
//3 4
//5
//10
//
//Output: 3 (Explanation: the paths are 5-2-3, 5-3-2, 5-1-3-1)
//
//For example:
//Input 	Result
//
//6
//2 5
//2 3
//5 3
//5 1
//3 1
//3 4
//5
//10
//
//
//
//3
//
//4
//1 5
//4 5
//3 1
//5 3
//1
//2
//
//
//
//0

package Labs.Lab9_Introduction_to_graphs;
import java.util.*;

public class Lab9_home {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int edges = scanner.nextInt();
        AdjacencyListGraph<Integer> graph = new AdjacencyListGraph<>();

        //pocetnoto i krajnoto teme na rebroto gi dodavame vo grafot
        for (int i = 0; i < edges; i++) {
            int source = scanner.nextInt();
            int destination = scanner.nextInt();
            graph.addEdge(source, destination);
        }

        // pocetno teme i baranata suma za nekoj pat
        int startVertex = scanner.nextInt();
        int targetSum = scanner.nextInt();

        //brojot na validni patishta
        int result = findPaths(graph, startVertex, targetSum);
        System.out.println(result);
    }

    private static int findPaths(AdjacencyListGraph<Integer> graph, int startVertex, int targetSum) {
        return dfs(graph, startVertex, targetSum, 0);
    }

    private static int dfs(AdjacencyListGraph<Integer> graph, int currentVertex, int targetSum, int currentSum) {
        //current sum plus segashnoto teme
        currentSum += currentVertex;

        //ako zbirot e pogolem od baranoto, vrati 0 za celata pateka
        if (currentSum > targetSum) {
            return 0;
        }

        // ako sumata e ista so baranata suma, dodaj go patot vo vkupniot broj
        int pathCount = 0;
        if (currentSum == targetSum) {
            pathCount++;
        }

        //proveri gi patistata za site sosedi, i ako e validen pat vrati 1 za sekoj sho e validen, i 0 obratno
        for (int neighbor : graph.getNeighbors(currentVertex)) {
            pathCount += dfs(graph, neighbor, targetSum, currentSum);
        }

        return pathCount;
    }
}

class AdjacencyListGraph<T> {
    private Map<T, Set<T>> adjacencyList;

    public AdjacencyListGraph() {
        this.adjacencyList = new HashMap<>();
    }

    // Add a vertex to the graph
    public void addVertex(T vertex) {
        if (!adjacencyList.containsKey(vertex)) {
            adjacencyList.put(vertex, new HashSet<>());
        }
    }

    // Add an edge to the graph
    public void addEdge(T source, T destination) {
        addVertex(source);
        addVertex(destination);

        adjacencyList.get(source).add(destination);
        adjacencyList.get(destination).add(source); // for undirected graph
    }

    // Get all neighbors of a vertex
    public Set<T> getNeighbors(T vertex) {
        return adjacencyList.getOrDefault(vertex, new HashSet<>());
    }
}