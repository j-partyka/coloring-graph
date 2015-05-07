import java.util.Map;
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
     * dodać losowe wybieranie punktu crosspoit
     */
        int crosspoint = 10;

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
         * dodać losowe wybieranie punktu crosspoit,
         * ustawianie crosspoint1 na mniejszy, a crosspoint2 na większy
         */
        int crosspoint1 = 5;
        int crosspoint2 = 17;

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

    public static Vector<Vertex> cutAndSplice(Map.Entry<Integer, Integer> parents, Vector<Vertex> chromosom) {
        Vertex parent1 = chromosom.get(parents.getValue());
        Vertex parent2 = chromosom.get(parents.getKey());

        int kolor1 = parent1.kolor;
        int kolor2 = parent2.kolor;
        /**
         * TODO
         * dodać losowe wybieranie punktu crosspoit,
         * ustawianie crosspoint1 na mniejszy, a crosspoint2 na większy
         */
        int crosspoint1 = 5;
        int crosspoint2 = 17;

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
            i++;
        }
        return chromosom;
    }


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


