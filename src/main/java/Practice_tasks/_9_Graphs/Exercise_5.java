/*
Даден е неориентиран нетежински граф чии темиња се позитивни цели броеви. Да се најде бројот на патишта во графот, почнувајќи од фиксно теме V, кај кои сумата на темињата има вредност N. Темињата во патот може да се повторуваат.


Влез: Во првиот ред е даден бројот на рабови во графот M. Потоа во следните M редови се дадени рабовите во графот во формат теме1 теме2. Во претпоследниот ред е дадено почетното теме V, a во последниот ред е дадена бараната сума N.

Излез:  Бројот на патишта во графот кај кои сумата на темињата изнесува N.

Пример:

Влез:

6
2 5
2 3
5 3
5 1
3 1
3 4
5
10

Излез: 3 (Објаснување: патеките се 5-2-3, 5-3-2, 5-1-3-1)


/

Given an undirected unweighted graph whose vertices are positive integers, find the number of paths in the graph, starting from a fixed vertex V, such that the sum of the vertices is N. The vertices in the path can be repeated.


Input: The first line contains the number of edges in the graph M, followed by the edges in the next M lines. The edges are given in the format vertex1 vertex2. The second-to-last line contains the start vertex V and the last line contains the wanted sum N.

Output: The number of paths where the sum of the vertices is N.

Example:

Input:

6
2 5
2 3
5 3
5 1
3 1
3 4
5
10

Output: 3 (Explanation: the paths are 5-2-3, 5-3-2, 5-1-3-1)

For example:
Input 	Result

6
2 5
2 3
5 3
5 1
3 1
3 4
5
10



3

4
1 5
4 5
3 1
5 3
1
2



0
 */
package Practice_tasks._9_Graphs;

import java.util.*;

class AdjacencyListGraph<T extends Number> {
    private Map<T, Set<T>> adjacencyList;

    public AdjacencyListGraph() {
        this.adjacencyList = new HashMap<>();
    }

    public void addVertex(T vertex) {
        adjacencyList.putIfAbsent(vertex, new HashSet<>());
    }

    public void addEdge(T source, T destination) {
        addVertex(source);
        addVertex(destination);
        adjacencyList.get(source).add(destination);
        adjacencyList.get(destination).add(source); // Undirected graph
    }

    public Set<T> getNeighbors(T vertex) {
        return adjacencyList.getOrDefault(vertex, new HashSet<>());
    }

    public int countPathsWithSum(T startVertex, int targetSum) {
        return dfs(startVertex, targetSum, 0);
    }

    private int dfs(T currentVertex, int targetSum, int currentSum) {
        currentSum += ((Number) currentVertex).intValue();

        if (currentSum > targetSum) {
            return 0;
        }

        int count = (currentSum == targetSum) ? 1 : 0;

        for (T neighbor : getNeighbors(currentVertex)) {
            count += dfs(neighbor, targetSum, currentSum);
        }

        return count;
    }
}

class prog {
    public static void main(String[] args) {
        AdjacencyListGraph<Integer> graph = new AdjacencyListGraph<>();

        Scanner sc = new Scanner(System.in);
        int edges = sc.nextInt();

        for (int i = 0; i < edges; i++) {
            int vertex1 = sc.nextInt();
            int vertex2 = sc.nextInt();
            graph.addEdge(vertex1, vertex2);
        }

        int startVertex = sc.nextInt();
        int targetSum = sc.nextInt();

        int result = graph.countPathsWithSum(startVertex, targetSum);
        System.out.println(result);
    }
}
