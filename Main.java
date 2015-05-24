import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
//ID != 150

    public static void main(String[] args) {

        Graph g = new Graph();
        Vector<Vertex> chromosom = g.load();
        Display d = new Display();
        Parent p1 = new Parent();
        //K - zrobiłam dwie pętle, ale można to zmieniać jak się chce
        //for(int p = 0; p < 20000; p++) K
        {
        	//while zli sasiedzi!=0 K 
        	//i tutaj kombinacje co Ola robi do wyboru użytkownika K
        	
        }
        
        for (int p = 0; p < 500; p++) {
        	Map.Entry<Integer,Integer> parent=p1.fitparent(chromosom);
            chromosom =Mutation.one(Crossover.onePoint(parent, chromosom), 6);
            
        }
        for (int p = 0; p < 20000; p++) {
        	Map.Entry<Integer,Integer> parent=p1.bestparent(chromosom);
        	 chromosom =Mutation.one(Crossover.twoPoint(parent, chromosom),6);

             if (d.silentbadNeighbour(chromosom)==0) 
            	 {System.out.println("działa"+ p);
            	 break;
            	 
            	 }
        }
        
        //a tutaj sprawdzenie

        
        //Map.Entry<Integer,Integer> parent=p1.fitnessparent(chromosom);
        for (int p = 0; p < 1; p++) {
        //chromosom=Crossover.twoPoint(parent, chromosom);
        }
        //d.graph(chromosom);
        d.goodNeighbour(chromosom);
        //d.IdColor(chromosom);
    }


}
