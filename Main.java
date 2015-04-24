import java.util.*;

public class Main {
//ID != 150

    public static void main(String[] args) {

        Graph g = new Graph();
        Vector<Vertex> chromosom = g.load();
        //sprawdzam

        //chromosom.get(15).kolor = 5;
        //chromosom.get(3).kolor = 3;

        //Vector<Wierzch> newChromosom = crossover(3,15,chromosom);
        //Vector<Wierzch> newChromosom = crossover(parents(chromosom),parents(chromosom),chromosom);

        Parent p1 = new Parent();
        Parent p2 = new Parent();

        int temp1 = p1.random(chromosom);
        int temp2 = p2.random(chromosom);

        for (int p = 0; p < 300; p++) {
            Mutation.one(Crossover.onePoint(temp1,temp2, chromosom), 10);
        }

//        for (int p = 0; p < 300; p++) {
//            Crossover.onePoint(temp1, temp2, chromosom);
//        }

        for (int p = 0; p < 300; p++) {
            Crossover.twoPoint(temp1, temp2, chromosom);
        }

        Display d = new Display();

        d.IdColor(chromosom);
//        d.graph(chromosom);
//        d.goodNeighbour(chromosom);
    }


}