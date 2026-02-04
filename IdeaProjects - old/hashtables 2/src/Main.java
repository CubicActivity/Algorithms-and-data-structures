import java.util.Scanner;

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


class OBHT<K, E> {

    // An object of class OBHT is an open-bucket hash table, containing entries
    // of class MapEntry.
    private MapEntry<K, E>[] buckets;

    // buckets[b] is null if bucket b has never been occupied.
    // buckets[b] is former if bucket b is formerly-occupied
    // by an entry that has since been deleted (and not yet replaced).

    static final int NONE = -1; // ... distinct from any bucket index.

    private final MapEntry<K, E> former = new MapEntry<>(null, null);
    // This guarantees that, for any genuine entry e,
    // e.key.equals(former.key) returns false.

    private int occupancy = 0;
    // ... number of occupied or formerly-occupied buckets in this OBHT.

    @SuppressWarnings("unchecked")
    public OBHT(int m) {
        // Construct an empty OBHT with m buckets.
        buckets = (MapEntry<K, E>[]) new MapEntry[m];
    }


    private int hash(K key) {
        // Translate key to an index of the array buckets.
        return Math.abs(key.hashCode()) % buckets.length;
    }


    public int search(K targetKey) {
        // Find which if any bucket of this OBHT is occupied by an entry whose key
        // is equal to targetKey. Return the index of that bucket.
        int b = hash(targetKey);

        for (int n_search = 0; n_search < buckets.length; n_search++) {
            MapEntry<K, E> currEntry = buckets[b];
            if (currEntry == null) return NONE;
            else if (currEntry != former && currEntry.key.equals(targetKey)) return b;
            else b = (b + 1) % buckets.length;
        }
        return NONE;
    }

    public MapEntry<K, E> getBucket(int bucket) {
        return buckets[bucket];
    }


    public void insert(K key, E val) {
        // Insert the entry <key, val> into this OBHT.
        MapEntry<K, E> newEntry = new MapEntry<K, E>(key, val);
        int b = hash(key);
        int n_search = 0;
        Integer free_position = null;
        Integer existing_position = null;
        while (n_search < buckets.length) {
            MapEntry<K, E> currEntry = buckets[b];
            if (currEntry == null) {
                if (free_position == null) free_position = b;
                break;
            } else if (currEntry != former && currEntry.key.equals(key)) {
                existing_position = b;
                break;
            } else {
                if (currEntry == former && free_position == null) {
                    free_position = b;
                }
                b = (b + 1) % buckets.length;
                n_search++;
            }
        }
        if (existing_position != null)
            buckets[existing_position] = newEntry;
        else if (free_position != null) {
            buckets[free_position] = newEntry;
            if (++occupancy == buckets.length) {
                System.out.println("Hash tabelata e polna!!!");
            }
        } else {
            // failed to insert, table was already full
        }
    }


    @SuppressWarnings("unchecked")
    public void delete(K key) {
        // Delete the entry (if any) whose key is equal to key from this OBHT.
        int b = hash(key);
        int n_search = 0;
        while (n_search < buckets.length) {
            MapEntry<K, E> currEntry = buckets[b];
            if (currEntry == null) return;
            else if (currEntry != former && currEntry.key.equals(key)) {
                buckets[b] = former;
                --occupancy;
                return;
            } else {
                b = (b + 1) % buckets.length;
                n_search++;
            }
        }
    }


    public String toString() {
        String temp = "";
        for (int i = 0; i < buckets.length; i++) {
            temp += i + ":";
            if (buckets[i] == null)
                temp += "\n";
            else if (buckets[i] == former)
                temp += "former\n";
            else
                temp += buckets[i] + "\n";
        }
        return temp;
    }


    public OBHT<K, E> clone() {
        OBHT<K, E> copy = new OBHT<K, E>(buckets.length);
        for (int i = 0; i < buckets.length; i++) {
            MapEntry<K, E> e = buckets[i];
            if (e != null && e != former)
                copy.buckets[i] = new MapEntry<>(e.key, e.value);
            else if(e == former)
                copy.buckets[i] = copy.former;
            else
                copy.buckets[i] = null;
        }
        return copy;
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

class Person{
    String name;
    String surname;
    String salary;
    String ip;
    String time;
    String city;
    String price;

    public Person(String name, String surname, String salary, String ip, String time, String city, String price){
        this.name=name;
        this.surname=surname;
        this.salary=salary;
        this.ip=ip;
        this.time=time;
        this.city=city;
        this.price=price;
    }


    public void print(){
        System.out.println(name+" "+ surname + " with salary "+salary+ " from address "+ ip+" who logged in at "+ time);
    }


}

public class Main {
    public static void main(String[] args) {

        int n;
        Scanner sc = new Scanner(System.in);
        n=sc.nextInt();
        sc.nextLine();

        OBHT<String, Integer> obht = new OBHT<String,Integer>(n);

        OBHT<String, Person> earliestPersons = new OBHT<String, Person>(n);
        // city person


        for(int i=0;i<n;i++){

            String name = sc.next();
            String surname = sc.next();
            String salary = sc.next();
            String ip = sc.next();
            String time = sc.next();
            String city = sc.next();
            String price = sc.next();
            sc.nextLine();

            String[] hours_mins = time.split(":");
            if(hours_mins[0].charAt(0)>='1' && hours_mins[0].charAt(1)>='2'){
//
//
//                System.out.println(obht.search(city));

                if(obht.search(city)==-1)
                obht.insert(city,1);
                else{
                    obht.insert(city,obht.getBucket(obht.search(city)).value+1);
                }

                Person p = new Person(name,surname,salary,ip,time,city,price);
                if(earliestPersons.search(city)==-1){
                    earliestPersons.insert(city,p);
                }else {
                    String[] vremeRazdelba= earliestPersons.getBucket(earliestPersons.search(city)).value.time.split(":");
                    String[] segaVreme = time.split(":");

                    int curHrs, curMins;
                    curHrs=Integer.parseInt(segaVreme[0]);
                    curMins=Integer.parseInt(segaVreme[1]);

                    int oldHrs, oldMins;
                    oldHrs=Integer.parseInt(vremeRazdelba[0]);
                    oldMins=Integer.parseInt(vremeRazdelba[1]);

                    if(curHrs<oldHrs || (curHrs==oldHrs && curMins<=oldMins)){
                        earliestPersons.insert(city,p);
                    }
                }

            }

            System.out.println("THE BUCKET IS: \n"+obht.toString());

        }

        int k= sc.nextInt();
        sc.nextLine();
        for(int i=0;i<k;i++){
            String name = sc.next();
            String surname = sc.next();
            String salary = sc.next();
            String ip = sc.next();
            String time = sc.next();
            String city = sc.next();
            String price = sc.next();
            sc.nextLine();

            System.out.println("City: "+ city +" has the following number of customers:");
            System.out.println(obht.getBucket(obht.search(city)).value);

            System.out.println("The user who logged on earliest after noon from that city is:");
            earliestPersons.getBucket(earliestPersons.search(city)).value.print();
        }






    }
}