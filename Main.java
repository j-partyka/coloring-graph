import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

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
        for (int p = 0; p < 300; p++) {
            Mutation.one(Crossover.one(p1.random(chromosom), p2.random(chromosom), chromosom), 10);
        }

        Display d = new Display();

        d.graph(chromosom);
        d.goodNeighbour(chromosom);
    }


}