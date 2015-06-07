import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Vector;

/**
 * Mutation
 */
public class Mutation {
    /**
     * simple - iteruje po wszystkich wierzchołkach i w razie konfliktu losuje dla niego kolor z puli dostępnych kolorów
     * @param chromosom    wektor wierzchołków odpowiadający jednemu grafowi
     * @param iloscKolorow ilość kolorów, którymi chcemy pokolorować graf
     * @return chromosom - nowy chromosom po mutacji
     */
    public static Vector<Vertex> simple(Vector<Vertex> chromosom, int iloscKolorow) {
        ArrayList<Integer> colors = new ArrayList<>();
        //sprawdzamy każdy wierzchołek
        for (Vertex vertex : chromosom) {
            //tworzenie tablicy kolorów
            for (int j = 0; j < iloscKolorow; j++) {
                colors.add(j);
            }
            //sprawdzanie sąsiadów
            for (int d = 0; d < vertex.sasiedzi.size(); d++) {
                //jeśli istnieje wierzchołek wśród sąsiadów, który ma taki sam kolor jak wierzchołek sprawdzany
                if (chromosom.get(vertex.sasiedzi.get(d)).kolor == vertex.kolor) {
                    //jeśli tablica kolorów zawiera już taki kolor, to jest on z niej usuwany
                    if (colors.contains(chromosom.get(vertex.sasiedzi.get(d)).kolor))
                        colors.remove((Object)chromosom.get(vertex.sasiedzi.get(d)).kolor);

                }
            }
            //dodawanie koloru randomowego
            vertex.kolor = colors.get(new Random().nextInt(colors.size()));
        }
        return chromosom;
    }

    /**
     * minMaxTransposition - przechodzi przez wszystkie wierzchołki w chromosomie. Dla każdego wierzchołka sprawdza ile ma złych sąsiadów,
     * czyli pokolorowanych w tym samym kolorze co on.
     * Ilość złych sąsiadów zapisuje w tablicy, której i-ty element zawiera ilość złych sąsiadów i-tego wierzchołka.
     * Znajduje wierzchołek, który ma najwięcej złych sąsiadów <- jemu będzie zmieniany kolor.
     * Znajduje wierzchołek, który ma najmniej sąsiadów <- na kolor tego wierzchołka, będzie zmieniany kolor wierzchołka wybranego powyżej
     * Zmienia kolor wierzchołka, który miał najwięcej złych sąsiadów.
     * @param chromosom    wektor wierzchołków odpowiadający jednemu grafowi
     * @param iloscKolorow ilość kolorów, którymi jest pokolorowany graf
     * @return chromosom - nowy chromosom po zmianie koloru jednego wierzchołka, który miał najwięcej złych sąsiadów,
     * na kolor wierzchołka, który miał najmniej złych sąsiadów
     */
    public static Vector<Vertex> minMaxTransposition(Vector<Vertex> chromosom, int iloscKolorow) {
        ArrayList<Integer> colors = new ArrayList<>();

        Vertex badNeighbour = null; //wierzchołek, któremu będzie zmieniany kolor
        ArrayList<Integer> badNeighboursArray = new ArrayList<>(); //i-ty el. listy zawiera ilość złych sąsiadów i-tego wierzchołka
        int badNeighbours; //ilość złych sąsiadów konkretnego wierzchołka
        int newColor;
        //sprawdzamy każdy wierzchołek w chromosomie
        for (int i = 0; i < chromosom.size(); i++) {
            badNeighbours = 0;
            //sprawdzanie sąsiadów
            for (int d = 0; d < chromosom.get(i).sasiedzi.size(); d++) {
                //jeśli istnieje wierzchołek wśród sąsiadów, który ma taki sam kolor jak wierzchołek sprawdzany
                if (chromosom.get(chromosom.get(i).sasiedzi.get(d)).kolor == chromosom.get(i).kolor) {
                    //to obliczamy ile ma tych złych sąsiadów
                    badNeighbours++;
                    break;
                }
            }
            badNeighboursArray.add(i, badNeighbours);
        }
        int max = Collections.max(badNeighboursArray); //max - największa ilość złych sąsiadów jakie ma jeden z wierzchołków w chromosomie
        int min = Collections.min(badNeighboursArray); //min - najmniejsza ilość złych sąsiadów jakie ma jeden z wierzchołków w chromosomie
        //indeks wierzchołka, któremu będzie zmieniany kolor
        int indexBadNeighbour = badNeighboursArray.indexOf(max); // indeks wierzchołka, który ma najwięcej złych sąsiadów w chromosomie
        //indeks wierzchołka, którego kolor będzie brany do zmiany z max
        int indexMinBadNeighbour = badNeighboursArray.indexOf(min); // indeks wierzchołka, który ma najmniej złych sąsiadów w chromosomie

        //System.out.println("Bad Neighbour's number is: " + indexBadNeighbour);
        newColor = chromosom.get(indexMinBadNeighbour).kolor;
        //System.out.println("Max : Wierzchołek nr: " + indexBadNeighbour + " ma " + max + " o kolorze " + chromosom.get(indexBadNeighbour).kolor);
        //System.out.println("Min : Wierzchołek nr: " + indexMinBadNeighbour + " ma " + min + " o kolorze " + newColor);
        chromosom.get(indexBadNeighbour).kolor = newColor;
        return chromosom;
    }

    /**
     * randomTransposition - przechodzi przez wszystkie wierzchołki w chromosomie. Sprawdza sąsiadów każdego wierzchołka.
     * Jeśli istnieje wierzchołek wśród sąsiadów, który ma taki sam kolor jak wierzchołek sprawdzany,
     * to zmieniamy jego kolor na kolor randomowo wybranego wierzchołka.
     * @param chromosom    wektor wierzchołków odpowiadający jednemu grafowi
     * @param iloscKolorow ilość kolorów, którymi jest pokolorowany graf
     * @return chromosom - nowy chromosom po zmianie koloru jednego wierzchołka, który miał jako pierwszy w kolejności iterowania chromosomu złego sąsiada
     */
    public static Vector<Vertex> randomTransposition(Vector<Vertex> chromosom, int iloscKolorow) {
        ArrayList<Integer> colors = new ArrayList<>();

        Vertex badNeighbour = null; //wierzchołek, któremu będzie zmieniany kolor
        int newColor;
        int indexBadNeighbour = 0;   //indeks wierzchołka, któremu będzie zmieniany kolor
        //sprawdzamy każdy wierzchołek w chromosomie
        for (int i = 0; i < chromosom.size(); i++) {
            //sprawdzanie sąsiadów
            for (int d = 0; d < chromosom.get(i).sasiedzi.size(); d++) {
                //jeśli istnieje wierzchołek wśród sąsiadów, który ma taki sam kolor jak wierzchołek sprawdzany
                if (chromosom.get(chromosom.get(i).sasiedzi.get(d)).kolor == chromosom.get(i).kolor) {
                    //to oznaczamy go jako badNeighbour, czyli wierzchołek, który trzeba zamienić
                    badNeighbour = chromosom.get(chromosom.get(i).sasiedzi.get(d));
                    indexBadNeighbour = i;
                    break;
                }
            }
            if (badNeighbour != null) {
                break;
            }
        }
        while (true) {
            //System.out.println("Bad Neighbour's number is: " + indexBadNeighbour);
            newColor = chromosom.get(new Random().nextInt(chromosom.size())).kolor; //nowy randomowy kolor na jaki zostanie zamieniony wybrany wierzchołek
            if (chromosom.get(indexBadNeighbour).kolor != newColor) {
                chromosom.get(indexBadNeighbour).kolor = newColor;
                break;
            }
        }
        return chromosom;
    }
}