import java.util.AbstractMap;
import java.util.Map;
import java.util.Random;
import java.util.Vector;

/**
 * Created by asia on 17.04.15.
 */
//parents
public class Parent {

    public int random(Vector<Vertex> chromosom1)
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
        while(chromosom1.get(random2).id==150)
        {
            random2=generator.nextInt(100);
        }

//        System.out.println("random1:" +random1);
//        System.out.println("random2: "+random2);
//        System.out.println();
        //teraz z dwóch rodziców musimy wybrać lepszego(?)
        //przejść po wszystkich sąsiadach i naliczać ile ma "dobry" kolor
        //jeżeli tyle samo dla random1 i random2 to wybieramy random1

        for (int d =0;d<chromosom1.get(random1).sasiedzi.size();d++)
        {
            if(chromosom1.get(d).kolor!=chromosom1.get(random1).kolor) ilosc1++;
            //System.out.println(chromosom1.get(random1).sasiedzi.get(d));
            //System.out.println(chromosom1.get(d).kolor);

        }
        //System.out.println();
        //dla drugiego to samo
        for (int d =0;d<chromosom1.get(random2).sasiedzi.size();d++)
        {
            if(chromosom1.get(d).kolor!=chromosom1.get(random2).kolor) ilosc2++;
            //System.out.println(chromosom1.get(random2).sasiedzi.get(d));
            //System.out.println(chromosom1.get(d).kolor);
        }
//        System.out.println("ilosc1: " + ilosc1);
//        System.out.println("ilosc2: " + ilosc2);
//        System.out.println();
        if(ilosc1>=ilosc2) return random1;
        if(ilosc1<ilosc2) return random2;
        return 0;
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
		int goodneighbour1=chromosom.get(random1).id;
		int goodneighbour2=chromosom.get(random2).id;
		int amount=0;
		//naliczamy sąsiadów
		for(int j=0; j<chromosom.size();j++)
		{
			for (int d =0;d<chromosom.get(j).sasiedzi.size();d++)
	    	{
			 if(chromosom.get(d).kolor!=chromosom.get(j).kolor) amount++;
			 
			 //System.out.println(chromosom1.get(random1).sasiedzi.get(d));
			 //System.out.println(chromosom1.get(d).kolor);
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
			 if(chromosom.get(d).kolor!=chromosom.get(j).kolor) amount++;
	    	}
			if ((amount>goodneighbour2)&& j!=parent1id)
			{
				goodneighbour2=amount;
				parent2id=j;
			}
		}
		
		Map.Entry<Integer,Integer> entry = new AbstractMap.SimpleEntry< Integer, Integer>(parent1id, parent2id);
		
		 //System.out.println(Integer.toString(entry.getValue()));
		// System.out.println(Integer.toString(entry.getKey()));
		return entry;
		
	}
}
