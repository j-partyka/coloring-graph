import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
//ID != 150

    public static void main(String[] args) {

        Graph g = new Graph("graf.txt");
        Vector<Vertex> chromosom = g.load();
    	Vector<Vector<Vertex> > population = new Vector<Vector<Vertex> >();
        population.setSize(4);
        
        for(int i=0;i<population.size();i++)
    	{
        	population.set(i, chromosom);
    	}
        
        
        Parent p1 = new Parent();
        Display d = new Display();
        for (int p = 0; p < 1000; p++) {
        	//mutujemy,wybieramy,crossujemy, sprawdzamy chromosoma,ZNOWU(p++) mutujemy...
        	//mutujemy jednego żeby mieć z niego kilka
        	for(int i=0;i<population.size();i++)
         	{
             	population.set(i, Mutation.one(chromosom,10));
         	}
        	//parent i cross
        	Map.Entry< Vector<Vertex> ,Vector<Vertex>> parent=p1.bestparent(population);  
        	//do chromosoma wrzucamy tego który nam wyszedł z crossovera
        	population=Crossover.twoPoint(parent, population); 
        	 //sprawdzenie całej populacji
        	for(int i=0;i<population.size();i++)
         	{
             if (d.silentbadNeighbour(population.get(i))==0) 
            	 {System.out.println("działa"+ p);
            	 chromosom=population.get(i);
            	 break;
            	 
            	 }     
         	}
        }

        d.goodNeighbour(chromosom);
        //d.IdColor(chromosom);
    }


}
