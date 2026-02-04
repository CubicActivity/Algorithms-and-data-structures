/*
Даден е проект што се состои од повеќе задачи. За секоја задача се потребни одреден број денови за да се заврши, а задчите може да се зависни од други задачи (не може да се почне со задача 2 пред да се зврши задача 1). Да се одреди колку најмалку денови ќе бидат потребни да се заврши целиот проект ако на независни задачи може да се работи паралелно.


Влез: Во првиот ред е даден бројот на задачи N. Потоа во следните N редови се дадени задачите и времето што е потребно за завршување на секоја задача. Во следниот ред е даден бројот на зависности M, а потоа во следните M редови се дадени зависностите. (Задача2 Задача1 значи дека Задача2 зависи од Задача1).

Излез:  Минималното време да се заврши целиот проект.

Пример:

Влез:

5
Task1 3
Task2 5
Task3 2
Task4 7
Task5 4
2
Task2 Task1
Task4 Task3

Излез: 9

(Објаснување: задачите 1, 3 и 5 може да се почнат паралелно. По завршување на задача 1, може да се почне задача 2, таа ќе се заврши за најмалку (5+3) 8 дена. Задача 4 може да почне по завршување на задача 3 и за неа ќе бидат потребни најмалку (2 + 7) 9 дена. Затоа што задача 1 и 2 и задача 3 и 4 може да се извршуваат паралелно, а паралелно се извршува и задача 5, потребни се 9 дена за да се заврши целиот проект.)


/

You're given a project that consists of multiple tasks. Each task takes a certain amount of days to complete, and tasks can be dependent on each other (can't start task 2 before task 1 is completed). Determine the minimum number of days to complete the entire project if it's possible to work on independent tasks in parallel.


Input: The first line contains the number of tasks N. The next N lines contain the names of the tasks and the time needed for each task. The next line contains the number of dependencies M, followed by M lines that contain the dependencies. (Task2 Task1 means that Task2 depends on Task1)

Output: The minimum number of days to complete the project.

Example:

Input:

5
Task1 3
Task2 5
Task3 2
Task4 7
Task5 4
2
Task2 Task1
Task4 Task3

Output: 9

(Explanation: tasks 1, 3 and 5 can be done in parallel. After task 1 is done, task 2 can be started, and will need at least (5+3) 8 days. Task 4 can start after task 3 and needs at least (2 + 7) 9 days. Because tasks 1 and 2 and tasks 3 and 4 can be done in parallel, and task 5 can also be done in parallel, it takes 9 days to complete the entire project.)

For example:
Input 	Result

5
Task1 3
Task2 5
Task3 2
Task4 7
Task5 4
2
Task2 Task1
Task4 Task3



9

5
TaskA 3
TaskB 2
TaskC 4
TaskD 5
TaskE 1
4
TaskC TaskA
TaskC TaskB
TaskD TaskC
TaskE TaskB



12

 */
package Practice_tasks._9_Graphs;

import java.util.*;

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

    // Remove a vertex from the graph
    public void removeVertex(T vertex) {
        // Remove the vertex from all adjacency lists
        for (Set<T> neighbors : adjacencyList.values()) {
            neighbors.remove(vertex);
        }
        // Remove the vertex's own entry in the adjacency list
        adjacencyList.remove(vertex);
    }

    // Add an edge to the graph
    public void addEdge(T source, T destination) {
        addVertex(source);
        addVertex(destination);

        adjacencyList.get(source).add(destination);
    }

    // Remove an edge from the graph
    public void removeEdge(T source, T destination) {
        if (adjacencyList.containsKey(source)) {
            adjacencyList.get(source).remove(destination);
        }
    }

    // Get all neighbors of a vertex
    public Set<T> getNeighbors(T vertex) {
        return adjacencyList.getOrDefault(vertex, new HashSet<>());
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
        for (T neighbor : getNeighbors(vertex)) {
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
                for (T neighbor : getNeighbors(vertex)) {
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
            for(T neighbor: getNeighbors(vertex)) {
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

            for (T neighbor : getNeighbors(vertex)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
    }

    // DFS utility function used for topological sorting
    private void topologicalSortUtil(T vertex, Set<T> visited, Stack<T> stack) {
        visited.add(vertex);
        for (T neighbor : getNeighbors(vertex)) {
            if (!visited.contains(neighbor)) {
                topologicalSortUtil(neighbor, visited, stack);
            }
        }
        stack.push(vertex);
    }

    public List<T> topologicalSort() {
        Stack<T> stack = new Stack<>();
        Set<T> visited = new HashSet<>();

        for (T vertex : adjacencyList.keySet()) {
            if (!visited.contains(vertex)) {
                topologicalSortUtil(vertex, visited, stack);
            }
        }

        List<T> order = new ArrayList<>();
        while (!stack.isEmpty()) {
            order.add(stack.pop());
        }
        return order;
    }


    @Override
    public String toString() {
        String ret = new String();
        for (Map.Entry<T, Set<T>> vertex : adjacencyList.entrySet())
            ret += vertex.getKey() + ": " + vertex.getValue() + "\n";
        return ret;
    }



}
class prog {

    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);

        int maximumVertices = sc.nextInt();
        sc.nextLine();

        AdjacencyListGraph<String> g = new AdjacencyListGraph<>();


        HashMap<String, Integer> times = new HashMap<>();

        int largest=0;

        for(int i=0;i<maximumVertices;i++){
            String vertex = sc.next();
            int time = sc.nextInt();
            if(time>largest)largest=time;
            sc.nextLine();


            g.addVertex(vertex);
            times.put(vertex,time);

        }

        int k= sc.nextInt();

        if(k==0) {
            System.out.println(largest);
        }else{

            HashMap<String, Integer> dp = new HashMap<>();
            //hashmap with destination, and total length to it?

            for(int i=0;i<k;i++){
                String dest = sc.next();
                String src = sc.next();
                g.addEdge(src,dest);


                if(dp.get(src)==null && dp.get(dest)==null){
//                    System.out.println("SUMATA 1 E "+(times.get(dest)+times.get(src)));
                    dp.put(dest,(times.get(dest)+times.get(src)));
                }else if(dp.get(dest)==null){
//                    System.out.println("SUMATA 2 E "+(times.get(dest)+dp.get(src)));
                    dp.put(dest,(times.get(dest)+dp.get(src)));
                }else if(dp.get(src)==null){
//                    System.out.println("SUMATA 3 E "+(times.get(dest) + times.get(src)));
                    if(dp.get(dest)<times.get(src)+times.get(dest)){
                        dp.put(dest, (times.get(dest) + times.get(src)));

                    }

                }else{
//                    System.out.println("SUMATA 4 E "+(times.get(dest)+dp.get(src)));
                    if(dp.get(dest)<times.get(dest)+dp.get(src))
                        dp.put(dest,(times.get(dest)+dp.get(src)));
                }

            }
            int max =0;
            for(String dest : dp.keySet()){
//            System.out.println(dp.get(dest)+" is iterated");
                if(dp.get(dest)>max)max=dp.get(dest);

            }

            System.out.println(max);

        }






    }

}
