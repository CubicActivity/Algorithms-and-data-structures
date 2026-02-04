//Дадена е улица на која има поставено места за улични светилки. Улицата е со должина М и има N такви места на кои можат да се постават улични светилки и нивната позиција е дадена во една низа.
//
//Ако знаеме дека една светилка освен својата позиција осветлува и уште две позиции во лево и две во десно, да се најде најмалиот број на светилки со кои може да се осветли улицата. Не мора да поставуваме светлика на секоја можна позиција.
//
//
//Влез: Првиот број од влезот е бројот на позиции за светилки N и должината на улицата М, а потоа во следниот ред се дадени можните позиции за поставување на светилки.
//
//
//Излез: Минимален број на светилки за да се осветли улицата.
//
///
//
//We are given a street with N possible positions on which we can put a light. A single light will illuminate its own position and 2 more positions to the left and 2 more to the right of it. Our task is to illuminate the street with the minimum possible lights. Not all possible positions must contain light.
//
//Input: The first number in the input is the number of possible positions to put a light bulb N and the length of the street M, then in the next line are the possible positions on which we can put a light.
//
//Output: The minimum lights we need to illuminate the street.
//
//
//For example:
//Input 	Result
//
//5 5
//1 2 3 4 5
//
//
//
//1
//
//3 10
//3 8 9
//
//
//
//2

package Practice_tasks._3_Algorithm_techniques;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Exercise_5 {
    public static void main(String[] args)  {

        Scanner sc = new Scanner(System.in);

        int kLights = sc.nextInt();
        int n=sc.nextInt();

        int [] lights = new int[kLights];

        for(int i=0;i<kLights;i++){
            lights[i]=sc.nextInt();
        }

        boolean finished=false;

        int minimumLights=0;

        int idealLight=3;
        int maxIllumnation=0;

        int largestPrev=-1;

        while(!finished){

            boolean found=false;
            for(int i=0;i<kLights;i++){
                if(lights[i]==idealLight){
                    found=true;
                    maxIllumnation+=5;
                    minimumLights++;
//                    System.out.println("Found ideal"+maxIllumnation);
                    break;

                }
            }

            if(!found){

                for(int i=0;i<kLights;i++){
                    if(lights[i]<idealLight){
                        if(lights[i]>largestPrev){
                            largestPrev=lights[i];
                            found=true;
                        }

                    }
                }
                if(largestPrev+2>maxIllumnation){
                    maxIllumnation=largestPrev+2;

                    idealLight=largestPrev;
                    minimumLights++;
                }
            }

            idealLight+=5;

            if(maxIllumnation>=n){
                finished=true;
            }



        }

        System.out.println(minimumLights);

    }
}