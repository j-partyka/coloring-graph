import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;


//mutacja
public class Mutation {

    public static Vector<Vertex> one(Vector<Vertex> chromosom, int iloscKolorow){
        ArrayList<Integer> colors = new ArrayList<Integer>();
        //sprawdzamy kazdy wierzchołek
        for (int i  = 0; i < chromosom.size(); i++){
            //tworzenie tablicy kolorów
            for (int j = 0; j < iloscKolorow; j++) {
                colors.add(j);
            }
            //sprawdzanie sąsiadów
            for (int d = 0;d<chromosom.get(i).sasiedzi.size();d++){
                if(chromosom.get(d).kolor==chromosom.get(i).kolor) {
                    if(colors.contains(chromosom.get(d).kolor))   colors.remove(chromosom.get(d).kolor);
                }
            }
            //dodawanie koloru ranodomowego
            chromosom.get(i).kolor = colors.get(new Random().nextInt(colors.size()));

        }
        return chromosom;
    }
}
