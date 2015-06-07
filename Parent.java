import java.util.AbstractMap;
import java.util.Map;
import java.util.Random;
import java.util.Vector;

//parents
public class Parent {
	 Display d = new Display();
/**
 * Random - wybiera dwa losowe chromosomy i pierwszym rodzicem zostaje ten, który jest "zdrowszy"
 * czyli ma więcej dobrze pokolorowanych wierzchołków. Drugi rodzic zostaje wybrany poprzez kolejne losowanie.
 * @param population wektor chromosomów (wektorów wierzchołków)
 * @return parents element mapy(watość,klucz) zawierający dwa chromosomy-rodziców
 */
    public Map.Entry< Vector<Vertex> ,Vector<Vertex>> random(Vector<Vector<Vertex> > population)
    {
    	//nowe :(
        int random1=new Random().nextInt(population.size());
        int random2=new Random().nextInt(population.size());
        Vector<Vertex> parent1id;
        Vector<Vertex> parent2id;
        
        if(d.silentBadNeighbour(population.get(random1))<d.silentBadNeighbour(population.get(random2)))
        {
        	parent1id=population.get(random1);
        }
        else {parent1id=population.get(random2);}
        
        random1=new Random().nextInt(population.size());
        random2=new Random().nextInt(population.size());
        
        if(d.silentBadNeighbour(population.get(random1))<d.silentBadNeighbour(population.get(random2)))
        {
        	parent2id=population.get(random1);
        }
        else {parent2id=population.get(random2);}
    	
        Map.Entry< Vector<Vertex> ,Vector<Vertex>> entry = new AbstractMap.SimpleEntry< Vector<Vertex> ,Vector<Vertex>>(parent1id, parent2id);
        return entry;
    }
    /**
     * Bestparent - wybieramy losowy wierzchołek jako rodzica. Iterujemy po wszystkich wierzchołkach i
     * porównujemy z naszym rodzicem, jeżeli któryś ma więcej dobrze pokolorowanych wierzchołków, to staje	
     * się rodzicem. Drugi rodzic jest wybierany tak samo, z tym warunkiem, że nie może być taki sam jak
     * pierwszy rodzic.    
     * @param population wektor chromosomów (wektorów wierzchołków)
     * @return parents element mapy(watość,klucz) zawierający dwa chromosomy-rodziców
     */
    public Map.Entry< Vector<Vertex> ,Vector<Vertex>> bestparent(Vector<Vector<Vertex> > population)
	{
    	//wybieramy randomowego parenta
    	//potem iterujemy po populacji, porównujemy kto ma więcej
    	
    	 int random1=new Random().nextInt(population.size());
		 int random2=new Random().nextInt(population.size());
		 Vector<Vertex> parent1id=population.get(random1);
	     Vector<Vertex> parent2id=population.get(random2);
		 
	     int j=random1;
		 for(int i=0;i<population.size();i++)
		 {
			 if(d.silentBadNeighbour(parent1id)<d.silentBadNeighbour(population.get(i)))
			 {
				 parent1id=population.get(i);
				 j=i;
			 }
		 }
		
		 for(int i=0;i<population.size();i++)
		 {
			 //zostaje rodzicem tylko jeśli jest inny od pierwszego rodzica
			 if((i!=j)&&(d.silentBadNeighbour(parent2id)<d.silentBadNeighbour(population.get(i))))
			 {
				 parent2id=population.get(i);
			 }
		 }
    	
		 Map.Entry< Vector<Vertex> ,Vector<Vertex>> entry = new AbstractMap.SimpleEntry< Vector<Vertex> ,Vector<Vertex>>(parent1id, parent2id);
		 return entry;
	}
    
  //roulette
    /**
     *  Fitparent - dla każdego chromosoma w populacji obliczamy jego zdrowie - sumę dobrych sąsiadów dla każdego
     *  wierzchołka. Następnie na podstawie tego liczymy sumę całkowitą zdrowia populacji. Wybieramy losową liczbę
     *  z przedziału od jeden do sumy całkowitej populacji. Po kolei dodajemy zdrowie kolejnych chromosomów, aż dojdziemy
     *  do liczby większej od wylosowanej - chromosom, dla którego obliczyliśmy sumę zdrowia, staje się rodzicem. 
     *  Drugi rodzic jest obliczany analogicznie.
     * @param population wektor chromosomów (wektorów wierzchołków)
     * @return parents element mapy(watość,klucz) zawierający dwa chromosomy-rodziców
     */
    public Map.Entry< Vector<Vertex> ,Vector<Vertex>> fitparent(Vector<Vector<Vertex> > population)
  		{
    	 Vector<Vertex> parent1id=null;
    	 Vector<Vertex> parent2id=null;
		int sumfitness=0;
		int[] fit = new int[population.size()]; //tablica fitness dla każdego chromosoma
    	
    	
    	//przeiterować po każdym i policzyć ich wspólną fitness?
    	//czyli to stare x razy i zsumowane?
    	
    	for(int f=0; f<population.size();f++) //dla każdego chromosoma w populcji
    	{
    		Vector<Vertex> chromosom =population.get(f);
    		sumfitness=0;
    	//dla każdego wierzchołka w chromosomie
    	
    		
    			//stare do policzenia sumfitness jednego chromosoma
    			//przeiterować po kazdym i zsumować ich fitness (liczbę dobrych sąsiadów)
      			for(int j=0; j<chromosom.size();j++)
      			{
      				for (int d =0;d<chromosom.get(j).sasiedzi.size();d++)
      		    	{
      				 if(chromosom.get(chromosom.get(j).sasiedzi.get(d)).kolor!=chromosom.get(j).kolor) sumfitness++; 
      		    	}				
      			}
    			fit[f]=sumfitness;	
    	}
    	sumfitness=0;//teraz to posłuży do policzenia sumy sum
    	//liczymy sumę całkowitą
    	for(int f=0; f<population.size();f++)
    	{
    		sumfitness=sumfitness+fit[f];
    	}
    	//dwa razy: wybieram losową liczbę z sumfitness, wybieram tego parenta w którego przedziale się znalazłam?
    	int random=new Random().nextInt(sumfitness);
    	int random1=new Random().nextInt(sumfitness);
    	//dwa parenty
    	int pomoc=0;
    	for(int f=0; f<population.size();f++)
    	{
    		pomoc=pomoc+fit[f];
    		if (random<=pomoc) 
    		{
    			parent1id=population.get(f);
    		}
    		if (random1<=pomoc) 
    		{
    			parent2id=population.get(f);
    		}
    	}
    	
    	 Map.Entry< Vector<Vertex> ,Vector<Vertex>> entry = new AbstractMap.SimpleEntry< Vector<Vertex> ,Vector<Vertex>>(parent1id, parent2id);
			return entry;	
    }  
}
