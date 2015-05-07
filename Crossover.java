import java.util.Map;
import java.util.Vector;

/**
 * Created by asia on 17.04.15.
 */
public class Crossover {

    //one-point crossover
    //A single crossover point on both parents' organism strings is selected. A
    // All data beyond that point in either organism string is swapped between the two parent organisms. The resulting organisms are the children:
 public static Vector<Vertex> onePoint( Map.Entry<Integer,Integer> parents, Vector<Vertex> chromosom){
        Vertex parent1 = chromosom.get(parents.getValue());
        Vertex parent2 = chromosom.get(parents.getKey());

        int kolor1 = parent1.kolor;
        int kolor2 = parent2.kolor;

      /*
    /**
     * TO DO
     * dodać losowe wybieranie punktu crosspoit
     */
        int crosspoint = 10;

        int i = 0;
        while (i < 100) {
            Vertex temp = chromosom.get(i);
            int newId = temp.id;
            if(newId != 150) {
                if (newId < crosspoint) {
                    temp.kolor = kolor1;
                } else {
                    temp.kolor = kolor2;
                }
            }
            i++;
        }
        return chromosom;
    }
    
    //Two-point crossover
    //Two-point crossover calls for two points to be selected on the parent organism strings.
    // Everything between the two points is swapped between the parent organisms, rendering two child organisms:
   public static Vector<Vertex> twoPoint( Map.Entry<Integer,Integer> parents, Vector<Vertex> chromosom) {
	   Vertex parent1 = chromosom.get(parents.getValue());
       Vertex parent2 = chromosom.get(parents.getKey());

        int kolor1 = parent1.kolor;
        int kolor2 = parent2.kolor;
        /**
         * TO DO
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
}
