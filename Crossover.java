import java.util.LinkedList;
import java.util.Map;
import java.util.Random;
import java.util.Vector;


public class Crossover {

    //A - one-point crossover
    //A - A single crossover point on both parents' organism strings is selected. A
    //A - All data beyond that point in either organism string is swapped between the two parent organisms. The resulting organisms are the children:
    public static Vector<Vertex> onePoint(Map.Entry<Integer, Integer> parents, Vector<Vertex> chromosom) {
        Vertex parent1 = chromosom.get(parents.getValue());
        Vertex parent2 = chromosom.get(parents.getKey());

        int kolor1 = parent1.kolor;
        int kolor2 = parent2.kolor;

    /**A
     * TODO
     * A - napisać funkcję, która będzie zwracać liczbę wierzchołków w grafie, których ID jest różny od 150
     */
        int crosspoint = new Random().nextInt(23); //A - generowanie losowej liczby dla crosspoint z ilości wierzchołków w grafie

        int i = 0;
        while (i < 100) {
            Vertex temp = chromosom.get(i);
            int newId = temp.id;
            if (newId != 150) {
                if (newId < crosspoint) {
                    temp.kolor = kolor1;
                } else {
                    temp.kolor = kolor2;
                }
            }
            chromosom.set(i,temp);
            i++;
        }
        return chromosom;
    }

    //A - Two-point crossover
    //A - Two-point crossover calls for two points to be selected on the parent organism strings.
    //A - Everything between the two points is swapped between the parent organisms, rendering two child organisms:
    public static Vector<Vertex> twoPoint(Map.Entry<Integer, Integer> parents, Vector<Vertex> chromosom) {
        Vertex parent1 = chromosom.get(parents.getValue());
        Vertex parent2 = chromosom.get(parents.getKey());

        int kolor1 = parent1.kolor;
        int kolor2 = parent2.kolor;
        /**
         * TODO
         * A - napisać funkcję, która będzie zwracać liczbę wierzchołków w grafie, których ID jest różny od 150
         */
        int crosspoint1 = new Random().nextInt(23);
        int crosspoint2;
        do{
            crosspoint2 = new Random().nextInt(23);
        }while (crosspoint1 >= crosspoint2); //A - ustawianie crosspoint1 na mniejszy, a crosspoint2 na większy

        int i = 0;
        while (i < 100) {
            Vertex temp = chromosom.get(i);
            int newId = temp.id;
            if (newId != 150) {
                if (newId < crosspoint1 || newId > crosspoint2) {
                    temp.kolor = kolor1;
                } else {
                    temp.kolor = kolor2;
                }
            }
            chromosom.set(i, temp);
            i++;
        }
        return chromosom;
    }

    //A - Uniform crossover - bits are randomly copied from the first or from the second parent
    public static Vector<Vertex> uniform(Map.Entry<Integer, Integer> parents, Vector<Vertex> chromosom){
        Vertex parent1 = chromosom.get(parents.getValue());
        Vertex parent2 = chromosom.get(parents.getKey());

        int kolor1 = parent1.kolor;
        int kolor2 = parent2.kolor;

        //TODO zapytać się jak to zrobić, by procentowy udział każdego koloru był 50 procent w chromosomie wynikowym
        for(int i=0; i<23; i++) {
            Vertex temp = chromosom.get(i);
            Random generator = new Random();
            switch (generator.nextInt(2)){ //A - randomowe wybieranie koloru jednego z dwóch
                case 0: temp.kolor = kolor1;
                    break;
                case 1: temp.kolor = kolor2;
                    break;
            }
            chromosom.set(i,temp);
        }
        return chromosom;
    }
    /**
     * TODO zapytać się o cutAndSplice
     */
    /*public static Vector<Vertex> cutAndSplice(Map.Entry<Integer, Integer> parents, Vector<Vertex> chromosom) {
        Vertex parent1 = chromosom.get(parents.getValue());
        Vertex parent2 = chromosom.get(parents.getKey());

        int kolor1 = parent1.kolor;
        int kolor2 = parent2.kolor;
        /**
         * TODO
         * dodać losowe wybieranie punktu crosspoit,
         * ustawianie crosspoint1 na mniejszy, a crosspoint2 na większy
         */
       /* int crosspoint1 = new Random().nextInt(23);
        int crosspoint2;
        do{
            crosspoint2 = new Random().nextInt(23);
        }while (crosspoint1 != crosspoint2); //A - ustawianie różnych crosspointów


        return chromosom;
    }*/

    /**A
    * TODO - zapytać się o threeParent
    */
  /*  public static Vector<Vertex> threeParent(Map.Entry<Integer, Integer> parents1, Map.Entry<Integer, Integer> parents2, Vector<Vertex> chromosom) {
        Vertex parent1 = chromosom.get(parents1.getValue());
        Vertex parent2 = chromosom.get(parents1.getKey());
        Vertex parent3 = chromosom.get(parents2.getValue());

        int kolor1 = parent1.kolor;
        int kolor2 = parent2.kolor;
        int kolor3 = parent3.kolor;

        int i = 0;
        while (i < 100) {
            Vertex temp = chromosom.get(i) {
                int newId = temp.id;
                if (newId != 150) {

                }
            }
        }
        return chromosom;
    }*/
}


