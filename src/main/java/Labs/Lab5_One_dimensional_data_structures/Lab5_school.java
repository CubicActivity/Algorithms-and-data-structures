//Студентска служба е посетена од студентите со цел да приложат/земат документи. Студентот може да приложи документи, да побара да си го земе индексот или пак да побара да си ги земе документите од средно. Кога студентската служба ќе започне со работа се услужуваат студенти од сите три типа паралелно, но исто така сите три шалтера не одат со иста брзина па услужувањето е со следниот редослед (два студента што ги приложуваат документите, па три студенти што сакаат да си го земат индексот, па еден студент што сака да си ги земе документите од средно).
//
//Доколку студент чека ред за повеќе услуги кај студентската служба, тој чека ред првин во редицата за приложување на документи, потоа во редицата за земање на индекс и на крај во редицата за земање на документи од средно.
//
//Влез: Во првата линија е даден број на студенти кои имаат дојдено за да бидат услужени од студентската служба. Потоа 4 редици се внесуваат за секој студент, каде првата линија е име на студент, а во останатите 3 редици се внесува дали има потреба од дадена услуга (приложување документи, земање на индекс, земање на документи од средно соодветно), каде 1 значи дека има потреба од услуга од тој тип, 0 значи дека нема потреба од услуга од тој тип.
//
//Пример:
//
//Иван Ивановски
//
//1
//
//1
//
//0
//
//значи дека студентот Иван Ивановски има за цел да приложи документи и да си го земе индексот.
//
//Излез: Испечати го редоследот на студентите по редослед како завршуваат со сите услуги од студенстката служба.
//
/// //
//
//Student Administration is visited by students in order to submit/receive documents. The student can submit documents, request to receive his/her index card, or request to receive his/her high school documents. When the student services start working, students of all three types are served in parallel, but it is important to mention that all 3 counters go with different speeds, so the serving of students is in this order (two students submitting documents, then three students who want to receive their index card, then one student who wants to receive his/her high school documents).
//
//If a student is waiting in line for more than one service at the student services, he/she waits in line first for submitting documents, then in line for receiving his/her index card, and finally in line for receiving his/her high school documents.
//
//Input: The first line contains the number of students who have come to be served by the student service. Then 4 lines are entered for each student, where the first line is the name of the student, and the remaining 3 lines indicate whether there is a need for a given service (submitting documents, taking an index, taking documents from high school, respectively), where 1 means that there is a need for a service of that type, 0 means that there is no need for a service of that type.
//
//Example:
//
//Ivan Ivanovski
//
//1
//
//1
//
//0
//
//means that student Ivan Ivanovski aims to submit documents and get his index.
//
//Output: Print the order of students in the order they complete all services from the student administration.
//
//For example:
//Input 	Result
//
//2
//Иван Ивановски
//1
//1
//0
//Марија Маркова
//1
//0
//1
//
//
//
//Иван Ивановски
//Марија Маркова
//
//3
//Иван Ивановски
//1
//0
//1
//Марија Маркова
//0
//1
//1
//Петар Петров
//1
//1
//0
//
//
//
//Петар Петров
//Иван Ивановски
//Марија Маркова

package Labs.Lab5_One_dimensional_data_structures;

import java.util.ArrayList;
import java.util.Scanner;


public class Lab5_school {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();

        ArrayList<String> names = new ArrayList<String>();
        ArrayList<Integer> submitDocs = new ArrayList<Integer>();
        ArrayList<Integer> takingIndex = new ArrayList<Integer>();
        ArrayList<Integer> docsFromHs = new ArrayList<Integer>();



        for(int i=0;i<n;i++) {
            names.add(sc.nextLine());

            submitDocs.add(sc.nextInt());
            takingIndex.add(sc.nextInt());

            docsFromHs.add(sc.nextInt());
            sc.nextLine();


        }

        //first for docs then for index then for hsdocs back to the line

        boolean emptyQueue=false;

        while(!emptyQueue){


            if(names.isEmpty()){
                emptyQueue=true;
                break;
            }

            for(int i=0;i<n;){
                if(!names.isEmpty()){
                    if(submitDocs.get(0).equals(0)){
                        if(takingIndex.get(0).equals(0)){
                            if(docsFromHs.get(0).equals(0)){
                                System.out.println(names.get(0));
                                names.remove(0);
                                submitDocs.remove(0);
                                takingIndex.remove(0);
                                docsFromHs.remove(0);
                                continue;
                            }
                        }
                    }
                    i++;} else break;
            }

            if(names.isEmpty()){
                emptyQueue=true;
                break;
            }



            int docsGive=0,indexDocs=0;

            while(docsGive<2){
                if (submitDocs.get(indexDocs).equals(1)) {
                    submitDocs.set(indexDocs, 0);
//                    System.out.println("setting 0 for docs for "+names.get(indexDocs));
                    docsGive++;
                    if(docsFromHs.get(indexDocs).equals(0) && takingIndex.get(indexDocs).equals(0)){
                        System.out.println(names.get(indexDocs));
                        names.remove(indexDocs);
                        submitDocs.remove(indexDocs);
                        takingIndex.remove(indexDocs);
                        docsFromHs.remove(indexDocs);
                    }
                }
                if(indexDocs<submitDocs.size()-1)
                    indexDocs++;
                else {
                    break;
                }
            }

            if(names.isEmpty()){
                emptyQueue=true;
                break;
            }

            int indexTake=0,indexindex=0;

            while(indexTake<3){
                if (takingIndex.get(indexindex).equals(1)) {
                    takingIndex.set(indexindex, 0);
                    indexTake++;
                    if(docsFromHs.get(indexindex).equals(0) && submitDocs.get(indexindex).equals(0)){
                        System.out.println(names.get(indexindex));
                        names.remove(indexindex);
                        submitDocs.remove(indexindex);
                        takingIndex.remove(indexindex);
                        docsFromHs.remove(indexindex);
                    }
//                    System.out.println(docsFromHs.get(indexindex).equals(0)+" "+submitDocs.get(indexindex).equals(0)+ "setting 0 for takingIndex for "+names.get(indexindex));
                }
                if(indexindex<takingIndex.size()-1)
                    indexindex++;
                else {
                    break;
                }
            }

            if(names.isEmpty()){
                emptyQueue=true;
                break;
            }

            int hstake=0,indexhs=0;
            while(hstake<1){
                if (docsFromHs.get(indexhs).equals(1)) {
                    docsFromHs.set(indexhs, 0);
                    hstake++;
//                    System.out.println("setting 0 for hsDocs for "+names.get(indexhs));

                    if(takingIndex.get(indexhs).equals(0) && submitDocs.get(indexhs).equals(0)){
                        System.out.println(names.get(indexhs));
                        names.remove(indexhs);
                        submitDocs.remove(indexhs);
                        takingIndex.remove(indexhs);
                        docsFromHs.remove(indexhs);
                    }

                }
                if(indexhs<docsFromHs.size()-1)
                    indexhs++;
                else {
                    break;
                }
            }

            if(names.isEmpty()){
                emptyQueue=true;
                break;
            }

        }



    }
}