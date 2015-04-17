DUPA DUPA


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
        System.out.println("random1:" +random1);
        System.out.println("random2: "+random2);
        System.out.println();
        //teraz z dwóch rodziców musimy wybraæ lepszego(?)
        //przejæ po wszystkich s¹siadach i naliczaæ ile ma "dobry" kolor
        //je¿eli tyle samo dla random1 i random2 to wybieramy random1
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
        System.out.println("ilosc1: " + ilosc1);
        System.out.println("ilosc2: " + ilosc2);
        System.out.println();
        if(ilosc1>=ilosc2) return random1;
        if(ilosc1<ilosc2) return random2;
        return 0;
    }
}
