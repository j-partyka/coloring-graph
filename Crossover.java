import java.util.Vector;

/**
 * Created by asia on 17.04.15.
 */
public class Crossover {
    //crossover
    public static Vector<Vertex> one(int id1, int id2, Vector<Vertex> chromosom){
        Vertex parent1 = chromosom.get(id1);
        Vertex parent2 = chromosom.get(id2);

        int kolor1 = parent1.kolor;
        int kolor2 = parent2.kolor;

      /*(Random crosspoint = new Random();
      int random = generator.nextInt(100);*/

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


}
