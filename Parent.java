import java.util.AbstractMap;
import java.util.Map;
import java.util.Random;
import java.util.Vector;

//parents
public class Parent {
    public Map.Entry<Integer,Integer> random(Vector<Vertex> chromosom1)
    {
        int ilosc1=0;
        int ilosc2=0;
        Random generator = new Random();
        int random1=generator.nextInt(100);
        int random2=generator.nextInt(100);
        while(chromosom1.get(random1).id==150)
        {
            random1=generator.nextInt(100);
        }
        while(chromosom1.get(random2).id==150 || chromosom1.get(random2).id==random1 )
        {
            random2=generator.nextInt(100);
        }
        //teraz z dwĂłch rodzicĂłw musimy wybraĂ¦ lepszego(?)
        //przejÂśĂ¦ po wszystkich sÂąsiadach i naliczaĂ¦ ile ma "dobry" kolor
        //jeÂżeli tyle samo dla random1 i random2 to wybieramy random1
        for (int d =0;d<chromosom1.get(random1).sasiedzi.size();d++)
        {
        	//chromosom.get(random1).sasiedzi.get(d)
            if(chromosom1.get(chromosom1.get(random1).sasiedzi.get(d)).kolor!=chromosom1.get(random1).kolor) ilosc1++;
            //System.out.println(chromosom1.get(random1).sasiedzi.get(d));
            //System.out.println(chromosom1.get(d).kolor);

        }
        //dla drugiego to samo
        for (int d =0;d<chromosom1.get(random2).sasiedzi.size();d++)
        {
            if(chromosom1.get(chromosom1.get(random2).sasiedzi.get(d)).kolor!=chromosom1.get(random2).kolor) ilosc2++;
        }
        int parent1id=0;
        int parent2id=0;
        if(ilosc1>=ilosc2)  parent1id=random1;
        if(ilosc1<ilosc2) parent1id=random2;
        
        
        
        random1=generator.nextInt(100);
        random2=generator.nextInt(100);
        while(chromosom1.get(random1).id==150 && chromosom1.get(random2).id==parent1id)
        {
            random1=generator.nextInt(100);
        }
        while(chromosom1.get(random2).id==150 && chromosom1.get(random2).id==random1&& chromosom1.get(random2).id==parent1id)
        {
            random2=generator.nextInt(100);
        }
        for (int d =0;d<chromosom1.get(random1).sasiedzi.size();d++)
        {
            if(chromosom1.get(chromosom1.get(random1).sasiedzi.get(d)).kolor!=chromosom1.get(random1).kolor) ilosc1++;


        }
        //dla drugiego to samo
        for (int d =0;d<chromosom1.get(random2).sasiedzi.size();d++)
        {
            if(chromosom1.get(chromosom1.get(random2).sasiedzi.get(d)).kolor!=chromosom1.get(random2).kolor) ilosc2++;

        }
        
        if(ilosc1>=ilosc2)  parent2id=random1;
        if(ilosc1<ilosc2) parent2id=random2;
           
        Map.Entry<Integer,Integer> entry = new AbstractMap.SimpleEntry< Integer, Integer>(parent1id, parent2id);
        
        return entry;
    }
    
    
    
    public  Map.Entry<Integer,Integer> bestparent(Vector<Vertex> chromosom)
	{
		Random generator = new Random();
		 int random1=generator.nextInt(100);
		 int random2=generator.nextInt(100);
		 //wybieramy randomowych rodziców na początek
		 while(chromosom.get(random1).id==150)
		 {
			 random1=generator.nextInt(100);
		 }
		 while(chromosom.get(random2).id==150)
		 {
			 random2=generator.nextInt(100);
		 }
		int parent1id=random1;
		int parent2id=random2; 
		//liczba dobrych sąsiadów dla każdego wierzchołka-rodzica
		int goodneighbour1=0;
		int goodneighbour2=0;
		int amount=0;
		//naliczamy sąśiadów
		for(int j=0; j<chromosom.size();j++)
		{
			for (int d =0;d<chromosom.get(j).sasiedzi.size();d++)
	    	{
			 if(chromosom.get(chromosom.get(j).sasiedzi.get(d)).kolor!=chromosom.get(j).kolor) amount++;

	    	}
			if (amount>goodneighbour1)
			{
				goodneighbour1=amount;
				parent1id=j;
			}
		}
		for(int j=0; j<chromosom.size();j++)
		{
			for (int d =0;d<chromosom.get(j).sasiedzi.size();d++)
	    	{
			 if(chromosom.get(chromosom.get(j).sasiedzi.get(d)).kolor!=chromosom.get(j).kolor) amount++;
	    	}
			if ((amount>goodneighbour2)&& j!=parent1id)
			{
				goodneighbour2=amount;
				parent2id=j;
			}
		
		}
		Map.Entry<Integer,Integer> entry = new AbstractMap.SimpleEntry< Integer, Integer>(parent1id, parent2id);
		return entry;		
	}
    
    
  //roulette
  		public  Map.Entry<Integer,Integer> fitparent(Vector<Vertex> chromosom)
  		{
  			Random generator = new Random();
  			int parent1id=0;
  			int parent2id=0;
  			int sumfitness=0;
  			//przeiterować po kazdym i zsumować ich fitness (liczbę dobrych sąsiadów)
  			for(int j=0; j<chromosom.size();j++)
  			{
  				for (int d =0;d<chromosom.get(j).sasiedzi.size();d++)
  		    	{
  				 if(chromosom.get(chromosom.get(j).sasiedzi.get(d)).kolor!=chromosom.get(j).kolor) sumfitness++;
  				 
  		    	}
  				
  			}			
  			//jeżeli sumfitness=0 to nie możemy dzielić!
  			if (sumfitness==0) 
  			{
  				parent1id=generator.nextInt(100);
  				parent2id=generator.nextInt(100);
  		        while(chromosom.get(parent1id).id==150)
  		        {
  		        	parent1id=generator.nextInt(100);
  		        }
  		        while(chromosom.get(parent2id).id==150 || parent2id==parent1id )
  		        {
  		        	parent2id=generator.nextInt(100);
  		        }
  				
  			}
  			//jezeli !=0
  			else
  			{
  			//TODO
  				//dwa razy: wybieram losową liczbę z sumfitness, iteruję dodając znowu sąsiadów i jak dojdę
  				//do tej liczby to wybieram to ID
  				
  				parent1id=generator.nextInt(sumfitness);
  				parent2id=generator.nextInt(sumfitness);
  				if (parent1id==0) parent1id++;
  				if (parent2id==0) parent1id++;
  				sumfitness=0;
  				for(int j=0; j<chromosom.size();j++)
  	  			{
  	  				for (int d =0;d<chromosom.get(j).sasiedzi.size();d++)
  	  		    	{
  	  				 if(chromosom.get(chromosom.get(j).sasiedzi.get(d)).kolor!=chromosom.get(j).kolor) sumfitness++;
  	  				 if(parent1id==sumfitness) parent1id=j;
  	  				 if(parent2id==sumfitness) parent2id=j;
  	  		    	} 	  				
  	  			}
  			}
  			Map.Entry<Integer,Integer> entry = new AbstractMap.SimpleEntry< Integer, Integer>(parent1id, parent2id);
  			return entry;
  		}
    
    
    
    
}
