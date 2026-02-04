//Дадени се N состаноци со време на почеток и крај. Да се најде минималниот број на сали за состаноци потребен за да се одржат сите состаноци.
//
//
//Влез: Првиот број од влезот е бројот на состаноци N, а потоа во следниот ред минутата на почнување и минутата на завршување на состанокот.
//
//
//Излез: Најмалиот потребен број на соби за состаноци за да можат да се одржат сите состаноци
//
///
//
//We are given N scheduled meetings. Write an algorithm that finds the minimum number of meeting rooms needed to schedule those meetings.
//
//Input: The first number in the input is the number of meetings N, then in the next N lines are the start and end time for each of the meetings.
//
//Output: The minimum number of needed meeting rooms to schedule the meetings.
//
//For example:
//Input 	Result
//
//5
//1 2
//1 2
//5 10
//11 14
//15 20
//
//
//
//2
//
//5
//1 2
//2 3
//3 4
//4 5
//5 6
//
//
//
//2

package Practice_tasks._3_Algorithm_techniques;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;



public class Exercise_4 {
    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();

        //we need to check each train with every other one

        Integer[] arrivals = new Integer[n];
        Integer[] departures = new Integer[n];
        int minimumNum=1;

        for(int i=0;i<n;i++) {
            arrivals[i] = sc.nextInt();
            departures[i] = sc.nextInt();
        }

        Arrays.sort(arrivals);
        Arrays.sort(departures);

        int platformsNeeded = 0;
        int maxPlatforms = 0;

        // Initialize pointers for arrival and departure
        int i = 0;  // Pointer for arrivals
        int j = 0;  // Pointer for departures

        // Process the events
        while (i < n) {
            // If the next event is an arrival
            if (arrivals[i] <= departures[j]) {
                platformsNeeded++;  // We need a platform
                i++;  // Move to the next arrival
            } else {
                platformsNeeded--;  // A train departs and frees up a platform
                j++;  // Move to the next departure
            }

            // Update the maximum platforms required
            maxPlatforms = Math.max(maxPlatforms, platformsNeeded);
        }

        System.out.println(maxPlatforms);




    }
}