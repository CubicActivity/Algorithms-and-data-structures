/*
Даден е стринг. Треба да се најде најчестиот под-стринг кој е дел од него и да се испечати. Доколку два под-стринга се исто фреквентни, тогаш се печати подолгиот. Доколку и овој услов го исполнуваат тогаш се печати лексикографски помалиот.

Пример: За стрингот "abc" под-стрингови се "a", "b", "c", "ab", "bc", "abc". Сите имаат иста честота па затоа се печати најдолгиот "abc".
/

Given a string, you need to find the most frequent sub-string that is a part of the original and print it. If two sub-strings are equally frequent, you should print the longer one. If they are with the same length as well, then you should print the one that is lexicographically smaller.

Example: Sub-strings of the string "abc" are "a", "b", "c", "ab", "bc", "abc". They all have the same frequency, so the longest one is printed - "abc".

For example:
Input 	Result

bab



b

 */
package Practice_tasks._11_Additional_practice_tasks_before_the_exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class MapEntry<K extends Comparable<K>,E> implements Comparable<K> {

    K key;
    E value;

    public MapEntry (K key, E val) {
        this.key = key;
        this.value = val;
    }

    public int compareTo (K that) {
        @SuppressWarnings("unchecked")
        MapEntry<K,E> other = (MapEntry<K,E>) that;
        return this.key.compareTo(other.key);
    }

    public String toString () {
        return "(" + key + "," + value + ")";
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

class CBHT<K extends Comparable<K>, E> {

    private SLLNode<MapEntry<K,E>>[] buckets;

    @SuppressWarnings("unchecked")
    public CBHT(int m) {
        buckets = (SLLNode<MapEntry<K,E>>[]) new SLLNode[m];
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % buckets.length;
    }

    public SLLNode<MapEntry<K,E>> search(K targetKey) {
        int b = hash(targetKey);
        for (SLLNode<MapEntry<K,E>> curr = buckets[b]; curr != null; curr = curr.succ) {
            if (targetKey.equals(((MapEntry<K, E>) curr.element).key))
                return curr;
        }
        return null;
    }

    public void insert(K key, E val) {		// Insert the entry <key, val> into this CBHT.
        MapEntry<K, E> newEntry = new MapEntry<K, E>(key, val);
        int b = hash(key);
        for (SLLNode<MapEntry<K,E>> curr = buckets[b]; curr != null; curr = curr.succ) {
            if (key.equals(((MapEntry<K, E>) curr.element).key)) {
                curr.element = newEntry;
                return;
            }
        }
        buckets[b] = new SLLNode<MapEntry<K,E>>(newEntry, buckets[b]);
    }

    public void delete(K key) {
        int b = hash(key);
        for (SLLNode<MapEntry<K,E>> pred = null, curr = buckets[b]; curr != null; pred = curr, curr = curr.succ) {
            if (key.equals(((MapEntry<K,E>) curr.element).key)) {
                if (pred == null)
                    buckets[b] = curr.succ;
                else
                    pred.succ = curr.succ;
                return;
            }
        }
    }

    public String toString() {
        String temp = "";
        for (int i = 0; i < buckets.length; i++) {
            temp += i + ":";
            for (SLLNode<MapEntry<K,E>> curr = buckets[i]; curr != null; curr = curr.succ) {
                temp += curr.element.toString() + " ";
            }
            temp += "\n";
        }
        return temp;
    }

}

class prog {

    static String printSubstrings(String s, CBHT<String,Integer> table) {
        //get the most common substring and the longest substring
        int largest=0;
        String element="";




        int n = s.length();
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j <= n; ++j) {

                // Print the substring from index i to j
//                System.out.println(s.substring(i, j));
                if(table.search(s.substring(i,j))==null){
                    table.insert(s.substring(i,j),1);
                    if(largest<1)largest=1;
                }else{

                    table.search(s.substring(i,j)).element.value++;

                    if(table.search(s.substring(i,j)).element.value>largest){
                        largest=table.search(s.substring(i,j)).element.value;
                        element=table.search(s.substring(i,j)).element.key;


                    }

                    if(table.search(s.substring(i,j)).element.value==largest){
                        if(table.search(s.substring(i,j)).element.key.length()>element.length()){
                            element=table.search(s.substring(i,j)).element.key;
                        }else if(table.search(s.substring(i,j)).element.key.length()==element.length()){
                            //check
                            if(table.search(s.substring(i,j)).element.key.compareTo(element)<0){
                                element=table.search(s.substring(i,j)).element.key;

                            }
                        }
                    }

                }
            }
        }

        return element;
    }


    public static void main (String[] args) throws IOException {
        CBHT<String,Integer> tabela = new CBHT<String,Integer>(3000);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String word = br.readLine().trim();

        /*
         *
         * Vashiot kod tuka....
         *
         */

        String res = printSubstrings(word,tabela);

        //count the highest frequency from the hashtable
        System.out.println(res);


    }
}
