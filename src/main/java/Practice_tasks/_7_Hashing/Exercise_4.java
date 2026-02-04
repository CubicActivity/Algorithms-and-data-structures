/*
На влез во оваа задача ќе ви бидат дадени редови како следниот формат:
Ime Prezime budzhet ip_adresa vreme grad cena
Пример
Jovan Todorov 1000 10.73.112.200 15:30 Bitola 760
Кое што значи дека лицето со Име и Презиме Jovan Todorov, има буџет од 1000 денари, има IP адреса со мрежа 10.73.112 и домаќин (host number) 200, и се приклучил во 15:30 часот за да купи билет кон Bitola кој што чини 760 денари.

Ќе ви бидат дадени N такви редови, по што ќе следи празен ред па уште M редови од истиот формат, кои ќе ги користиме за тестирање.

Од редот за тестирање треба да ја извадите IP адресата на мрежата и потоа да го одговорите следното прашање со оваа мрежа:

Од сите N лица на влез, кои што се поврзуваат со истата мрежа (од било кој домаќин во мрежата)
колку од нив се вклучиле подоцна од 11:59; и
од овие, кој од нив се вклучил најрано?

(погледнете го тест примерот!)

(доколку има повеќе со исто најмало време тогаш кој е првиот таков во влезот?) (секогаш ќе постои барем еден таков)

Ова ќе треба да го направите за сите M редови за тестирање!

Препорака, користете OBHT и/или CBHT.

----------------------
At the input of this task you will be given rows with the following format:
First_name Last_name budget ip_address time city price
Example
Jovan Todorov 1000 10.73.112.200 15:30 Bitola 760
Which means that the person with First name and Last name Jovan Todorov, has a budget of 1000 denars, has an IP address with network 10.73.112 and host number 200, and joined at 15:30 to buy a ticked to Bitola which costs 760 denars.

You will be given N such rows, followed by an empty row and M more rows of the same format, which we will use for testing.

From the test line you need to extract the IP address of the network and then answer the following question with this network:

Of the N people at the input, from the ones who connect from the same network (from any host on the network)
how many of them logged in later than 11:59; and
of these, which one logged in the earliest?

(if there are more with the same minimum time then which one is the first one in the input?) (there will always be at least one of them)

You will need to do this for all M rows for testing!

Recommendation, use OBHT and/or CBHT.

For example:
Input 	Result

5
Jovan Todorov    1000    10.73.112.200     16:30   Bitola     760
Mitko Janev      4350    132.28.112.200    12:15   Krusevo    4000
Sara Dobreva     2700    10.73.60.29       14:35   Bitola     2500
Mence Trajanova  4000    10.73.112.112     11:25   Bitola     4200
Viktor Jovev     2200    10.73.112.79      15:15   Strumica   1800

1
Jovan Todorov    1000    10.73.112.200     16:30   Bitola     760



IP network: 10.73.112 has the following number of users:
2
The user who logged on earliest after noon from that network is:
Viktor Jovev with salary 2200 from address 10.73.112.79 who logged in at 15:15

25
Marko Stankovic    1200       192.168.0.10     10:45  Skopje     1500
Ana Petrovska      800        192.168.0.20     15:00  Bitola     760
Stefan Jovanovic   1500       192.168.0.30     11:30  Ohrid      1100
Elena Pavlova      600        192.168.0.40     14:15  Tetovo     750
Ivan Mitrevski     1300       192.168.0.50     16:45  Veles      900
Mila Nikolova      900        10.0.1.10        10:00  Skopje     700
Kristina Ristevska 500        10.0.1.20        15:30  Bitola     1200
Darko Nikolic      1500       10.0.1.30        12:00  Ohrid      1500
Marija Filipova    850        10.0.1.40        17:45  Tetovo     600
Aleksandar Kosta   1000       10.0.1.50        13:15  Veles      1400
Petar Jovanovski   2000       172.16.5.10      08:45  Skopje     1000
Ivana Velickova    800        172.16.5.20      14:45  Bitola     700
Dejan Stojanov     900        172.16.5.30      13:30  Ohrid      1300
Tamara Ilijevska   1500       172.16.5.40      16:00  Tetovo     1700
Filip Atanasov     2100       172.16.5.50      09:00  Veles      2050
Simona Milanova    600        192.168.2.10     15:00  Skopje     650
Aleksandra Petrova 1200       192.168.2.20     14:30  Bitola     800
Goran Kostadinov   850        192.168.2.30     11:15  Ohrid      700
Maja Georgieva     1600       192.168.2.40     18:00  Tetovo     1200
Nikola Nikolovski  1400       192.168.2.50     08:30  Veles      1450
Lazar Angelov      700        10.1.2.10         09:45  Skopje     1200
Milena Stojanovska 1300       10.1.2.20         16:30  Bitola     900
Aleksandar Dimeski 800        10.1.2.30         11:15  Ohrid      850
Katerina Trajkoska 1500       10.1.2.40         14:45  Tetovo     1400
Viktor Pejkovski   950        10.1.2.50         13:00  Veles      800

5
Marko Stankovic    1200       192.168.0.10     10:45  Skopje     1500
Kristina Ristevska 500        10.0.1.20        15:30  Bitola     1200
Dejan Stojanov     900        172.16.5.30      13:30  Ohrid      1300
Maja Georgieva     1600       192.168.2.40     18:00  Tetovo     1200
Viktor Pejkovski   950        10.1.2.50         13:00  Veles      800



IP network: 192.168.0 has the following number of users:
3
The user who logged on earliest after noon from that network is:
Elena Pavlova with salary 600 from address 192.168.0.40 who logged in at 14:15

IP network: 10.0.1 has the following number of users:
4
The user who logged on earliest after noon from that network is:
Darko Nikolic with salary 1500 from address 10.0.1.30 who logged in at 12:00

IP network: 172.16.5 has the following number of users:
3
The user who logged on earliest after noon from that network is:
Dejan Stojanov with salary 900 from address 172.16.5.30 who logged in at 13:30

IP network: 192.168.2 has the following number of users:
3
The user who logged on earliest after noon from that network is:
Aleksandra Petrova with salary 1200 from address 192.168.2.20 who logged in at 14:30

IP network: 10.1.2 has the following number of users:
3
The user who logged on earliest after noon from that network is:
Viktor Pejkovski with salary 950 from address 10.1.2.50 who logged in at 13:00

 */

package Practice_tasks._7_Hashing;

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


class MapEntry<K extends Comparable<K>, E> {
    // Each MapEntry object is a pair consisting of a key (a Comparable object)
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

class OBHT<K extends Comparable<K>, E> {

    // An object of class OBHT is an open-bucket hash table, containing entries
    // of class MapEntry.
    private MapEntry<K, E>[] buckets;

    // buckets[b] is null if bucket b has never been occupied.
    // buckets[b] is former if bucket b is formerly-occupied
    // by an entry that has since been deleted (and not yet replaced).

    static final int NONE = -1; // ... distinct from any bucket index.

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static final MapEntry former = new MapEntry(null, null);
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
            else if (currEntry.key.equals(targetKey)) return b;
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
            } else if (key.equals(currEntry.key)) {
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
            else if (currEntry.key.equals(key)) {
                buckets[b] = former;
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
                copy.buckets[i] = new MapEntry<K, E>(e.key, e.value);
            else
                copy.buckets[i] = e;
        }
        return copy;
    }
}

class CBHT<K extends Comparable<K>, E> {

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


class Ticket{
    String name;
    String surname;
    int budget;
    String ipAddr;
    String time;
    String city;
    int price;

    public Ticket(String name, String surname, String ipAddr, int budget, String time, String city, int price) {
        this.name = name;
        this.surname = surname;
        this.ipAddr = ipAddr;
        this.budget = budget;
        this.time = time;
        this.city = city;
        this.price = price;
    }

    @Override
    public String toString(){
        return name + " "+surname+" with salary "+budget+" from address "+ipAddr+ " who logged in at "+time;
    }


}





public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n= sc.nextInt();

        OBHT<String, Integer> usersOnNetwork = new OBHT<>(n+1);
        CBHT<String, Ticket> latest = new CBHT<>(n+1);



        for(int i=0;i<n;i++){
            String name = sc.next();
            String surname = sc.next();
            int budget = sc.nextInt();
            String ipaddr = sc.next();
            String time = sc.next();
            String city = sc.next();
            int price = sc.nextInt();
            sc.nextLine();

            Ticket ticket = new Ticket(name,surname, ipaddr, budget,time,city,price);


            String [] parts = ipaddr.split("\\.");

            String ip = parts[0]+"."+parts[1]+"."+parts[2];



            String [] timeParts1 = time.split(":");
            if((Integer.parseInt(timeParts1[0])==11 && Integer.parseInt(timeParts1[1])>=59) || (Integer.parseInt(timeParts1[0])>11)){



                if(usersOnNetwork.search(ip)==-1){
                    usersOnNetwork.insert(ip,1);
                }
                else {
                    usersOnNetwork.getBucket(usersOnNetwork.search(ip)).value++;
                }

                if(latest.search(ip)==null){
                    latest.insert(ip,ticket);
                }else{


                    String [] timeParts2 = latest.search(ip).element.value.time.split(":");

                    if(Integer.parseInt(timeParts1[0])<Integer.parseInt(timeParts2[0]) || (Integer.parseInt(timeParts1[0])==Integer.parseInt(timeParts2[0]) && Integer.parseInt(timeParts1[1])<Integer.parseInt(timeParts2[1]))){
                        latest.search(ip).element.value=ticket;
                    }

                }


            }



        }

        int k =sc.nextInt();

        for(int i=0;i<k;i++){
            String name = sc.next();
            String surname = sc.next();
            int budget = sc.nextInt();
            String ipaddr = sc.next();
            String time = sc.next();
            String city = sc.next();
            int price = sc.nextInt();
            sc.nextLine();

            String [] parts = ipaddr.split("\\.");
            String ip = parts[0]+"."+parts[1]+"."+parts[2];


            System.out.println("IP network: "+ ip +" has the following number of users:");
            System.out.println(usersOnNetwork.getBucket(usersOnNetwork.search(ip)).value);
            System.out.println("The user who logged on earliest after noon from that network is:");
            System.out.println(latest.search(ip).element.value.toString());
            System.out.println();

        }




    }
}
