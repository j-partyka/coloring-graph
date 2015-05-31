import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
//ID != 150

    public static void main(String[] args) {
    	 Parent p1 = new Parent();
         Display d = new Display();
        Graph g = new Graph("graf.txt");
        Vector<Vertex> chromosom = new Vector<Vertex>();
        
        
    	Vector<Vector<Vertex> > population = new Vector<Vector<Vertex> >();
        population.setSize(4);
                    
        
        //identyczny graf wszędzie
        for(int i=0;i<population.size();i++)
    	{
        	population.set(i, g.load());
    	}
        //wstępna mutacja kolorów bo tak
        for(int i=0;i<population.size();i++)
     	{
        	for(int j=0;j<population.get(i).size();j++)
        	{
        		population.get(i).get(j).kolor=new Random().nextInt(7);
        	}
     	}
       //zmienna zmienna służy do ucieczki z pętli bo głupia Kasia jest zbyt leniwa żeby ogarnąć break label;
        int zmienna=0;
        for (int p = 0; p < 20000; p++) {
        	//mutujemy,wybieramy,crossujemy, sprawdzamy chromosoma,ZNOWU(p++) mutujemy...
        
        	//parent i cross
        	Map.Entry< Vector<Vertex> ,Vector<Vertex>> parent=p1.bestparent(population);  
        	//do chromosoma wrzucamy tego który nam wyszedł z crossovera
        	population=Crossover.onePoint(parent, population);
        	//mutacja
        	for(int i=0;i<population.size();i++)
         	{
             	population.set(i, Mutation.one(population.get(i),7));
         	}
        	 //sprawdzenie całej populacji
        	
        	
        	for(int i=0;i<population.size();i++)
         	{
             if (d.silentbadNeighbour(population.get(i))==0) 
            	 {System.out.println("chromosom "+ i+" został poprawnie pokolorowany po "+ p + " iteracjach.");
            	 chromosom=population.get(i);
            	 zmienna=1;
            	 break ;
            	 }     
         	}
        	if (zmienna==1) break;
        }
        
        for(int i=0;i<population.size();i++)
     	{
        	 //d.IdColorChromosom(population.get(i));
        	//d.goodNeighbour(population.get(i));
     	}
        //d.IdColorChromosom(chromosom);
    }


}
