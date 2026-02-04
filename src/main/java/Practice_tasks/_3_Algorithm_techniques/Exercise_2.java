//Дадени се N возови со време на пристигање и поаѓање. Да се најде минималниот број на платформи потребен за да се сместат тие возови.
//
//
//Влез: Првиот број од влезот е бројот на возови N, а потоа во следниот ред минутата на пристигнување и минутата на поаѓање на возот.
//
//
//Излез: Најмалиот потребен број на платформи за да се сместат сите возови
//
///
//
//We are given N trains. Write an algorithm that finds the minimum number of platforms needed to schedule those trains.
//
//Input: The first number in the input is the number of trains N, then in the next N lines are the arrival and departure minutes for each of the trains.
//
//Output: The minimum number of needed platforms to accommodate the trains.
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



public class Exercise_2 {
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