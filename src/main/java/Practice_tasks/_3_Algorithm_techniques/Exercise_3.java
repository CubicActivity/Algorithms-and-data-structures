//Дадени се N задачи кои треба да се изработат. За секоја од задачите го знаеме времето за изработка (во часови) и заработката која ја носи. Да се најде максималната заработка во рок од една 40 часовна работна недела. Напомена дека и делумно сработени задачи носат делумна заработка, во зависност од процентот на завршеност.
//
//Влез: Првиот број од влезот е бројот на задачи N, а потоа во следниот ред времетраењето на задачата во часови и заработката која ја носи.
//
//
//Излез: Максимална заработка која можеме да ја направиме за 40 часа.
//
///
//
//We are given N tasks with estimated completion time and the amount we can earn from that task. Write an algorithm that finds the maximum earnings we can have for 40 hours. Note that we can have a partial earning from a partially completed task.
//
//Input: The first number in the input is the number of tasks N, then in the next N lines are the time needed for the task and the amount of money we can earn from it.
//
//Output: The maximum amount we can earn in 40 hours
//
//
//For example:
//Input 	Result
//
//3
//10 60
//20 100
//30 120
//
//
//
//200
//
//1
//10 60
//
//
//
//60

package Practice_tasks._3_Algorithm_techniques;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Exercise_3 {

    static void sortiraj(int p[], int t[], int n) {
        int i, j, tmpP, tmpT;
        for (i = 0; i < n; i++) {
            for (j = i + 1; j < n; j++) {
                if ((p[i]/(float)t[i])<(p[j]/(float) t[j])) {
                    tmpP = p[i];
                    tmpT = t[i];
                    p[i] = p[j];
                    t[i] = t[j];
                    p[j] = tmpP;
                    t[j] = tmpT;
                }
            }
        }
    }


    // p i t gi sodrzat profitot i tezinata na objektite
// C e kapacitet na paketot, x e vektor na resenieto
    static float grFractKnp(int p[], int t[], float C, int n, float x[]) {
        sortiraj(p, t, n);
// objektite se podredeni taka da bide zadovolen
// p[i]/t[i] >= p[i+1]/t[i+1]
        int i;
        float profit = 0;
        for (i = 0; i < n; i++) {
            x[i] = 0;
        }

        for (i = 0; i < n; i++) {
            if (C > t[i]) { // objektot go stavame celosno
                x[i] = 1;
                C -= t[i];
                profit += p[i];
            } else { // objektot go stavame delumno
                x[i] = (C / (float) t[i]);
                profit += x[i] * (float) p[i];
                C = 0;
                break;
            }
        }
        return profit;
    }


    public static void main(String[] args)  {

        Scanner sc = new Scanner(System.in);

        int n=sc.nextInt();

        int [] Time = new int[n];
        int [] Money = new int[n];
        float [] averages = new float[n];

        for(int i=0;i<n;i++){
            Time[i]=sc.nextInt();
            Money[i]=sc.nextInt();
        }

        int maxTime=40;


        System.out.println((int)grFractKnp(Money, Time, maxTime, n, averages));

    }
}
