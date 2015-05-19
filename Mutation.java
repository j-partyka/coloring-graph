import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

public class Mutation {
//transposition && first fit galiner hao krzyzowanie
    public static Vector<Vertex> one(Vector<Vertex> chromosom, int iloscKolorow){
        ArrayList<Integer> colors = new ArrayList<Integer>();
        //sprawdzamy kazdy wierzchoĹ‚ek
        for (int i  = 0; i < chromosom.size(); i++){
            //tworzenie tablicy kolorĂłw
            for (int j = 0; j < iloscKolorow; j++) {
                colors.add(j);
            }
            //sprawdzanie sÄ…siadĂłw
            for (int d = 0;d<chromosom.get(i).sasiedzi.size();d++){
            	if(chromosom.get(chromosom.get(i).sasiedzi.get(d)).kolor==chromosom.get(i).kolor)  {
                    if(colors.contains(chromosom.get(chromosom.get(i).sasiedzi.get(d)).kolor))   colors.remove((chromosom.get(chromosom.get(i).sasiedzi.get(d)).kolor));
                }
            }     
            
            //dodawanie koloru ranodomowego
            chromosom.get(i).kolor = colors.get(new Random().nextInt(colors.size()));

        }
        return chromosom;
    }
    
    
}
