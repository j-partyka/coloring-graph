import java.util.Vector;


public class Display {
    /**
     * graph wyświetla cały graf, tzn. ID i kolor każdego wierzchołka oraz jego sąsiadów, wraz z kolorami
     * @param chromosom
     */
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

    /**
     * idColorChromosom wyświetla jednego osobnika (ID każdego wierzchołka i jego kolor)
     * @param chromosom
     */
    public void idColorChromosom(Vector<Vertex> chromosom) {
        for (int j = 0; j < chromosom.size(); j++) {
//                System.out.println("poczatek wierzchołka");
                System.out.println("ID: " + chromosom.get(j).id);
                System.out.println("Kolor: " + chromosom.get(j).kolor);
//                System.out.println("koniec wierzchołka");
                System.out.println();
            }
    }

    /**
     * idColorPopulation - wyświetla w kolumnach 4 osobniki z populacji (ID każdego wierzchołka i jego kolor)
     * @param population
     */
    public void idColorPopulation(Vector<Vector<Vertex>> population) {
        System.out.println("ONE        TWO        THREE         FOUR");
        for (int j = 0; j < population.get(0).size(); j++) {
//                System.out.println("poczatek wierzchołka");
            System.out.println("ID1: " + population.get(0).get(j).id + "     ID2: " + population.get(1).get(j).id + "     ID3: " + population.get(2).get(j).id + "    ID4: " + population.get(3).get(j).id);
            System.out.println("Kolor1: " + population.get(0).get(j).kolor + "   Kolor2: " + population.get(1).get(j).kolor + "  Kolor3: " + population.get(2).get(j).kolor + "  Kolor4: " + population.get(3).get(j).kolor);
//                System.out.println("koniec wierzchołka");
            System.out.println();
        }
    }

     /**
     * goodNeighbour - iteruje po wierzchołkach chromosomu i sumuje wierzchołki, które są sąsiadami i mają ten sam 
     * kolor - czyli są w konflikcie. Wyświetla podsumowanie w postaci listy skonfliktowanych wierzchołków i ich ilości.
     * @param chromosom
     */
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
        System.out.println(pomoc +" - liczba niedobrych sąsiadów");
    }

   /**
     * silentbadNeighbour -  iteruje po wierzchołkach chromosomu i sumuje wierzchołki, które są sąsiadami i mają ten sam 
     * kolor - czyli są w konflikcie. Zwraca całkowitą liczbę konfliktów. Nic nie wyświetla. 
     * @param chromosom
     * @return badneigboramount
     */
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
