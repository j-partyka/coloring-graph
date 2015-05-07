import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
//ID != 150

    public static void main(String[] args) {

        Graph g = new Graph();
        Vector<Vertex> chromosom = g.load();
      
        Parent p1 = new Parent();
        //K - zrobiłam dwie pętle, ale można to zmieniać jak się chce
        
        for (int p = 0; p < 1300; p++) {
        	Map.Entry<Integer,Integer> parent=p1.random(chromosom);
            Mutation.one(Crossover.onePoint(parent, chromosom), 15);
            
            
        }
        for (int p = 0; p < 300; p++) {
        	Map.Entry<Integer,Integer> parent=p1.bestparent(chromosom);
            Mutation.one(Crossover.twoPoint(parent, chromosom), 15);
            
        }
        //a tutaj sprawdzenie
        Display d = new Display();

        d.graph(chromosom);
        d.goodNeighbour(chromosom);
        

        
        
    }


}
