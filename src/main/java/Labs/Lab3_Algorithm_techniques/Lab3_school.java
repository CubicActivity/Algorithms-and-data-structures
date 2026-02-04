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

package Labs.Lab3_Algorithm_techniques;

import java.util.Scanner;

public class Lab3_school {
    public static void main(String[] args) {

        int lightPositions;
        Scanner sc = new Scanner(System.in);
        lightPositions = sc.nextInt();

        int streetLength = sc.nextInt();


        Integer [] arr = new Integer[lightPositions];


        //the possible positions for the lightBulbs
        for(int i=0;i<lightPositions;i++){
            arr[i] = sc.nextInt();
        }

        int init=3;

        boolean contains=false;
        for(int j=3;j<streetLength-1;j+=5) {
            contains=false;

            for (int i = 0; i < lightPositions; i++) {
                if (arr[i] == init) {
                    contains = true;
                }
            }
            if(!contains) break;


        }

//        3,8,13,18
//                if atleast 1 k%5==3 and isnt in the array then increment min light bulbs

        //5/5 = min light bulbs
        int minLightBulbs=0;

        if(streetLength%5==0){
            minLightBulbs=streetLength/5;
        }else{
            minLightBulbs=streetLength/5;
            minLightBulbs++;
        }

        if(!contains) minLightBulbs++;


        System.out.println(minLightBulbs);


    }
}

