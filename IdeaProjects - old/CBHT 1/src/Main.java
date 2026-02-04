import java.util.Scanner;

class SLLNode<E> {
    protected E element;
    protected SLLNode<E> succ;

    public SLLNode(E elem, SLLNode<E> succ) {
        this.element = elem;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}


class CBHT<K, E> {
    // An object of class CBHT is a closed-bucket hash table, containing
    // entries of class MapEntry.
    private SLLNode<MapEntry<K, E>>[] buckets;

    @SuppressWarnings("unchecked")
    public CBHT(int m) {
        // Construct an empty CBHT with m buckets.
        buckets = (SLLNode<MapEntry<K, E>>[]) new SLLNode[m];
    }


     private int hash(K key) {
        // Translate key to an index of the array buckets.
        return Math.abs(key.hashCode()) % buckets.length;
    }

    public SLLNode<MapEntry<K, E>> search(K targetKey) {
        // Find which if any node of this CBHT contains an entry whose key is equal to targetKey.
        // Return a link to that node (or null if there is none).
        int b = hash(targetKey);

        SLLNode<MapEntry<K, E>> currNode = buckets[b];
        while (currNode != null) {
            MapEntry<K, E> currEntry = currNode.element;
            if (currEntry.key.equals(targetKey)) return currNode;
            else currNode = currNode.succ;
        }
        return null;
    }

    public void insert(K key, E val) {
        // Insert the entry <key, val> into this CBHT.
        // If entry with same key exists, overwrite it.
        MapEntry<K, E> newEntry = new MapEntry<>(key, val);
        int b = hash(key);
        SLLNode<MapEntry<K, E>> currNode = buckets[b];
        while (currNode != null) {
            MapEntry<K, E> currEntry = currNode.element;
            if (currEntry.key.equals(key)) {
                // Make newEntry replace the existing entry ...
                currNode.element = newEntry;
                return;
            } else currNode = currNode.succ;
        }
        // Insert newEntry at the front of the SLL in bucket b ...
        buckets[b] = new SLLNode<>(newEntry, buckets[b]);
    }

    public void delete(K key) {
        // Delete the entry (if any) whose key is equal to key from this CBHT.
        int b = hash(key);
        SLLNode<MapEntry<K, E>> predNode = null, currNode = buckets[b];
        while (currNode != null) {
            MapEntry<K, E> currEntry = currNode.element;
            if (currEntry.key.equals(key)) {
                if (predNode == null) buckets[b] = currNode.succ;
                else predNode.succ = currNode.succ;
                return;
            } else {
                predNode = currNode;
                currNode = currNode.succ;
            }
        }
    }

    public String toString() {
        String temp = "";
        for (int i = 0; i < buckets.length; i++) {
            temp += i + ":";
            SLLNode<MapEntry<K, E>> curr = buckets[i];
            while (curr != null) {
                temp += curr.element.toString() + " ";
                curr = curr.succ;
            }
            temp += "\n";
        }
        return temp;
    }
}


class MapEntry<K, E> {
    // Each MapEntry object is a pair consisting of a key
    // and a value (an arbitrary object).
    K key;
    E value;

    public MapEntry(K key, E val) {
        this.key = key;
        this.value = val;
    }

    public String toString() {
        return "<" + key + "," + value + ">";
    }
}


public class Main {
    public static void main(String[] args) {

        int n;
        Scanner sc =new Scanner(System.in);
        n=sc.nextInt();
        sc.nextLine();

        CBHT<employee,project> buckets = new CBHT<>(10);



        for(int i=0;i<n;i++){
            String[] parts = sc.nextLine().split(" ");

            employee employee = new employee(parts[0],parts[1]);
            project project = new project(Integer.parseInt(parts[2]),Integer.parseInt(parts[3]));

            if(buckets.search(employee)==null){
                buckets.insert(employee,project);
            }else{
                if(buckets.search(employee).element.value.totalSalary() <= project.totalSalary()){
                    buckets.delete(employee);
                    buckets.insert(employee,project);
                }
            }
        }

        System.out.println(buckets.toString());

    }
}

class employee{
    String name;
    String age;

    public employee(String name, String age){
        this.name=name;
        this.age=age;
    }

    @Override public int hashCode(){
        return name.charAt(0)*Integer.parseInt(age);
    }

    @Override public String toString(){
        return "<"+name+","+age+">";
    }

    @Override public boolean equals(Object obj){
        employee other = (employee) obj; // Cast to employee
        return name.equals(other.name) && age.equals(other.age); // Check field equality

    }


}

class project{
    int hours;
    int wage;

    public project(int hours, int wage){
        this.hours=hours;
        this.wage=wage;


    }

    public int totalSalary(){
        return hours*wage;
    }

    @Override public String toString(){
        return "<"+hours+","+wage+">";
    }

}