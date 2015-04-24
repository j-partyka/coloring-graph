import java.util.Vector;

/**
 * Created by asia on 17.04.15.
 */
public class Display {

    public void graph(Vector<Vertex> chromosom) {
        for (int j = 0; j < chromosom.size(); j++) {
            if (chromosom.get(j).id != 150) {
                System.out.println("poczatek wierzchołka");
                System.out.println("ID: " + chromosom.get(j).id);
                System.out.println("Kolor: " + chromosom.get(j).kolor);
                System.out.println("Sąsiedzi:");
                for (int d = 0; d < chromosom.get(j).sasiedzi.size(); d++) {
                    System.out.print(chromosom.get(j).sasiedzi.get(d));
                    System.out.println(" kolor: " + chromosom.get(d).kolor);
                }
                System.out.println("koniec wierzchołka");
                System.out.println();
            }
        }
    }

    public void IdColor(Vector<Vertex> chromosom) {
        for (int j = 0; j < chromosom.size(); j++) {
            if (chromosom.get(j).id != 150) {
//                System.out.println("poczatek wierzchołka");
                System.out.println("ID: " + chromosom.get(j).id);
                System.out.println("Kolor: " + chromosom.get(j).kolor);
//                System.out.println("koniec wierzchołka");
                System.out.println();
            }
        }
    }

    public void goodNeighbour(Vector<Vertex> chromosom){
        int pomoc=0;
        for (int b  = 0; b < chromosom.size(); b++){

            //sprawdzanie sąsiadów
            for (int d = 0;d<chromosom.get(b).sasiedzi.size();d++){
                if(chromosom.get(d).kolor==chromosom.get(b).kolor) {
                    pomoc++;
                }
            }
        }
        System.out.println(pomoc +" - liczba niedobrych sąsiadów");
    }
}
