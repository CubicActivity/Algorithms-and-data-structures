//За даден збор кој се внесува од стандарден влез, да се испечати истиот превртен. На влез во првиот ред се дава број на зборови кои ќе се внесуваат. Во наредните линии се внесуваат самите зборови.
//
///
//
//For a given word entered from standard input, print it reversed. On input in the first line, the number of words that will be entered is given. In the following lines, the words are entered.
//
//For example:
//Input 	Result
//
//3
//one
//two
//three
//
//
//
//eno
//owt
//eerht

package Practice_tasks._1_Intro_to_java;
import java.util.Scanner;

public class Exercise_2 {

    public static void printReversed(String word) {

    }

    public static void main(String[] args) {

        int n;
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();

        scanner.nextLine();

        for(int i=0;i<n;i++){
            String input=scanner.nextLine();
            String other="";
            for(int j=input.length()-1;j>=0;j--){
                other+=input.charAt(j);
            }

            System.out.println(other);
        }

    }
}

