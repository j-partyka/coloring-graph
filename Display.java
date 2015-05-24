import java.util.Vector;


public class Display {

    public void graph(Vector<Vertex> chromosom) {
        for (int j = 0; j < chromosom.size(); j++) {
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

    public void IdColorChromosom(Vector<Vertex> chromosom) {
        for (int j = 0; j < chromosom.size(); j++) {
//                System.out.println("poczatek wierzchołka");
                System.out.println("ID: " + chromosom.get(j).id);
                System.out.println("Kolor: " + chromosom.get(j).kolor);
//                System.out.println("koniec wierzchołka");
                System.out.println();
            }
    }

    public void IdColorPopulation(Vector<Vector<Vertex> > population) {
        System.out.println("ONE        TWO        THREE         FOUR");
        for (int j = 0; j < population.get(0).size(); j++) {
//                System.out.println("poczatek wierzchołka");
            System.out.println("ID1: " + population.get(0).get(j).id + "     ID2: " + population.get(1).get(j).id + "     ID3: " + population.get(2).get(j).id + "    ID4: " + population.get(3).get(j).id);
            System.out.println("Kolor1: " + population.get(0).get(j).kolor + "   Kolor2: " + population.get(1).get(j).id + "  Kolor3: " + population.get(2).get(j).kolor + "  Kolor4: " + population.get(3).get(j).kolor);
//                System.out.println("koniec wierzchołka");
            System.out.println();
        }
    }

    public void goodNeighbour(Vector<Vertex> chromosom){
        int pomoc=0;
        for (int b  = 0; b < chromosom.size(); b++){
            for (int d = 0;d<chromosom.get(b).sasiedzi.size();d++){
                if(chromosom.get(chromosom.get(b).sasiedzi.get(d)).kolor==chromosom.get(b).kolor&& !(chromosom.get(b).equals(chromosom.get(b).sasiedzi.get(d)))) {
                	System.out.println(b +" "+ chromosom.get(b).id+" konflikt z "+chromosom.get(b).sasiedzi.get(d));
                    pomoc++;
                }
            }
        }
        System.out.println(pomoc +" - liczba niedobrych sÄ…siadĂłw");
    }
    
     public int silentbadNeighbour(Vector<Vertex> chromosom){
        int pomoc=0;
        for (int b  = 0; b < chromosom.size(); b++){

          
            for (int d = 0;d<chromosom.get(b).sasiedzi.size();d++){
                if(chromosom.get(chromosom.get(b).sasiedzi.get(d)).kolor==chromosom.get(b).kolor&& !(chromosom.get(b).equals(chromosom.get(b).sasiedzi.get(d)))) {
                    pomoc++;
                }
            }
        }
        return pomoc;
    }
    
}
