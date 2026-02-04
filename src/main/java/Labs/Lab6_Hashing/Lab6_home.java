//Во оваа задача ќе работите со два објекти „Вработени“ и „Проекти“.
//
//Еден вработен е дефиниран со 2 вредности: Име (String), Возраст (цел број).
//
//Еден проект е дефиниран со 2 вредности: Работно Време (цел број), Плата по час (цел број).
//
//За даден проект вкупната плата се пресметува како производ на работното време и платата по час.
//
//Вработените ќе добиваат понудени проекти, и треба да го изберат проектот од кој ќе добијат најголема плата (доколку имаат избор од повеќе проекти со иста најголема плата, тогаш првиот таков).
//
//Ваша задача ќе биде да им помогнете на вработените со тоа што ќе искористите CBHT со 10 кофички каде што за секој вработен ќе ја чувате неговата најдобра понуда.
//
//За хеш функција користете производ од возраста на вработениот и ASCII вредноста на првата буква од името на вработениот.
//
//Влез:
//Во првиот ред е даден еден цел број N кој го означува бројот на понуди. Потоа во следните N редови се дадени по 4 вредности, информациите за понудите, името и возраста на вработениот и работното време и платата од час на проектот соодветно:
//
//N
//name_1 age_1 time_1 rate_1
//name_2 age_2 time_2 rate_2
//...
//name_N age_N time_N rate_N
//
//Излез:
//
//Отпечатете ја целата табела (со готовиот toString метод).
//
//Секој вработен да се печати во формат "<name, age>" каде на местото на name и age треба да стои името и возраста на вработениот соодветно (имплементирајте го ова во toString метод)
//
//Секој проект да се печати во формат "<time, rate>" каде на местото на time и rate треба да стои работното време и платата по час на проектот соодветно (имплементирајте го ова во toString метод)
//
//-----------------------------------
//
//In this task, you will work with two objects "Employees" and "Projects".
//
//An employee is defined with 2 values: Name (String), Age (integer).
//
//A project is defined with 2 values: Working Time (integer), Hourly Salary (integer).
//
//For a given project, the total salary is calculated as the product of the working time and the hourly salary.
//
//Employees will be offered projects, and they should choose the project from which they will receive the highest salary (if they have a choice of multiple projects with the same highest salary, then the first one).
//
//Your task will be to help employees by using CBHT with 10 buckets where for each employee you will store their best offer.
//
//For the hash function, use the product of the employee's age and the ASCII value of the first letter of the employee's name.
//
//Input:
//The first line contains an integer N that indicates the number of offers. Then, in the next N rows, 4 values ​​are given, the information about the offers, the name and age of the employee and the working hours and hourly wage of the project respectively:
//
//N
//name_1 age_1 time_1 rate_1
//name_2 age_2 time_2 rate_2
//...
//name_N age_N time_N rate_N
//
//Output:
//
//Print the entire table (with the ready-made toString method).
//
//Each employee should be printed in the format "<name, age>" where in place of name and age should be the name and age of the employee respectively (implement this in the toString method)
//
//Each project should be printed in the format "<time, rate>" where in place of time and rate should be the working hours and hourly wage of the project respectively (implement this in the toString method)
//
//
//Пример/Еxample:
//Влез/Input:
//
//5
//Martin 25 3 100
//Jana 26 4 90
//Martin 25 5 120
//Jana 26 2 95
//Nenad 20 6 80
//Излез/Output:
//0:<<Nenad, 20>,<6, 80>>
//1:
//2:
//3:
//4:<<Jana, 26>,<4, 90>>
//5:<<Martin, 25>,<5, 120>>
//6:
//7:
//8:
//9:
//
//
//
//
//For example:
//Input 	Result
//
//5
//Martin 25 3 100
//Jana 26 4 90
//Martin 25 5 120
//Jana 26 2 95
//Nenad 20 6 80
//
//
//
//0:<<Nenad, 20>,<6, 80>>
//1:
//2:
//3:
//4:<<Jana, 26>,<4, 90>>
//5:<<Martin, 25>,<5, 120>>
//6:
//7:
//8:
//9:
//
//1
//Toni 45 10 500
//Toni 42 30 600
//Ana 20 10 300
//Jana 20 15 350
//
//
//
//0:<<Toni, 45>,<10, 500>>
//1:
//2:
//3:
//4:
//5:
//6:
//7:
//8:
//9:

package Labs.Lab6_Hashing;
import java.util.Scanner;

public class Lab6_home {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine(); // Consume the newline character after the integer input

        // Create a Chained Bucket Hash Table with 10 buckets
        CBHT<Employee, ProjectOffer> cbht = new CBHT<>(10);

        // Read all offers and process
        for (int j = 0; j < n; j++) {
            String[] input = sc.nextLine().split(" ");  // Read each line and split it into parts
            String name = input[0];
            int age = Integer.parseInt(input[1]);
            int time = Integer.parseInt(input[2]);
            int rate = Integer.parseInt(input[3]);

            Employee employee = new Employee(name, age);
            ProjectOffer projectOffer = new ProjectOffer(time, rate);

            // Calculate the total salary of the project
            int totalSalary = projectOffer.getSalary();

            // Check if the employee already exists in the hash table
            SLLNode<MapEntry<Employee, ProjectOffer>> node = cbht.search(employee);
            if (node != null) {
                // Compare and update the best offer if necessary
                ProjectOffer currentOffer = node.element.value;
                if (currentOffer.getSalary() < totalSalary) {
                    node.element.value = projectOffer;  // Update with the new offer
                }
            } else {
                // If employee doesn't exist, insert the new offer
                cbht.insert(employee, projectOffer);
            }
        }

        // Output the result in the required format
        System.out.println(cbht);
    }

    // Employee class to hold employee details (name and age)
    static class Employee {
        String name;
        int age;

        public Employee(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "<" + name + ", " + age + ">";
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Employee employee = (Employee) obj;
            return age == employee.age && name.equals(employee.name);
        }

        @Override
        public int hashCode() {
            // Hash code based on the first character of the name and the age
            return name.charAt(0) * age;
        }
    }

    // ProjectOffer class to hold project details (working time and hourly rate)
    static class ProjectOffer {
        int time;  // Working time
        int rate;  // Hourly salary

        public ProjectOffer(int time, int rate) {
            this.time = time;
            this.rate = rate;
        }

        public int getSalary() {
            return time * rate;  // Calculate total salary (time * rate)
        }

        @Override
        public String toString() {
            return "<" + time + ", " + rate + ">";
        }
    }

    // Chained Bucket Hash Table (CBHT)
    static class CBHT<K, V> {
        private SLLNode<MapEntry<K, V>>[] buckets;

        @SuppressWarnings("unchecked")
        public CBHT(int m) {
            buckets = (SLLNode<MapEntry<K, V>>[]) new SLLNode[m];
        }

        // Hash function to get the index for the key
        private int hash(K key) {
            return Math.abs(key.hashCode()) % buckets.length;
        }

        // Search for an entry by key
        public SLLNode<MapEntry<K, V>> search(K targetKey) {
            int b = hash(targetKey);
            SLLNode<MapEntry<K, V>> currNode = buckets[b];
            while (currNode != null) {
                MapEntry<K, V> currEntry = currNode.element;
                if (currEntry.key.equals(targetKey)) return currNode;
                else currNode = currNode.succ;
            }
            return null;
        }

        // Insert a key-value pair into the hash table
        public void insert(K key, V val) {
            MapEntry<K, V> newEntry = new MapEntry<>(key, val);
            int b = hash(key);
            SLLNode<MapEntry<K, V>> currNode = buckets[b];
            while (currNode != null) {
                MapEntry<K, V> currEntry = currNode.element;
                if (currEntry.key.equals(key)) {
                    currNode.element = newEntry;
                    return;
                } else currNode = currNode.succ;
            }
            buckets[b] = new SLLNode<>(newEntry, buckets[b]);
        }

        @Override
        public String toString() {
            StringBuilder temp = new StringBuilder();
            for (int i = 0; i < buckets.length; i++) {
                temp.append(i).append(":");
                SLLNode<MapEntry<K, V>> curr = buckets[i];
                while (curr != null) {
                    temp.append(curr.element.toString()).append(" ");
                    curr = curr.succ;
                }
                temp.append("\n");
            }
            return temp.toString();
        }
    }

    // Singly Linked List Node class for storing elements
    static class SLLNode<T> {
        T element;
        SLLNode<T> succ;

        public SLLNode(T element, SLLNode<T> succ) {
            this.element = element;
            this.succ = succ;
        }

        @Override
        public String toString() {
            return element.toString();
        }
    }

    // MapEntry class to hold key-value pairs in the hash table
    static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "<" + key + "," + value + ">";
        }
    }
}
