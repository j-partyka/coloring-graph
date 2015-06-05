import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Vector;

public class Mutation {
    //TODO javadoc do One - czy jest dobrze?
    /**
     * One - ustawianie randomowego koloru wierzchołka, zmniejsza ilość źle pokolorowanych wierzchołków
     * @param chromosom wektor wierzchołków odpowiadający jednemu grafowi
     * @param iloscKolorow ilość kolorów, którymi chcemy pokolorować graf
     * @return chromosom - nowy chromosom po mutacji
     */
    public static Vector<Vertex> one(Vector<Vertex> chromosom, int iloscKolorow){
        ArrayList<Integer> colors = new ArrayList<>();
        //sprawdzamy każdy wierzchołek vertex
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
                        colors.remove((chromosom.get(vertex.sasiedzi.get(d)).kolor));
                }
            }
            //dodawanie koloru randomowego
            vertex.kolor = colors.get(new Random().nextInt(colors.size()));
        }
        return chromosom;
    }

    /**
     * transposition - przechodzi przez wszystkie wierzchołki w chromosomie. Dla każdego wierzchołka sprawdza ile ma złych sąsiadów,
     * czyli pokolorowanych w tym samym kolorze co on.
     * Ilość złych sąsiadów zapisuje w tablicy, której i-ty element zawiera ilość złych sąsiadów i-tego wierzchołka.
     * Znajduje wierzchołek, który ma najwięcej złych sąsiadów <- jemu będzie zmieniany kolor.
     * Znajduje wierzchołek, który ma najmniej sąsiadów <- na kolor tego wierzchołka, będzie zmieniany kolor wierzchołka wybranego powyżej
     * Zmienia kolor wierzchołka, który miał najwięcej złych sąsiadów.
     * C. Mutation Operators
     Two types of mutation operators described in literature
     are used. Transposition (T) is a classical type of mutation
     that exchanges colors of two randomly selected vertices in
     the assignment representation.
     https://fedcsis.org/proceedings/2014/pliks/391.pdf
     http://riad.pk.edu.pl/~zk/pubs/iccs644_8.pdf
     http://www.genetic-programming.org/sp2003/Shen.pdf
     http://forum.4programmers.net/Algorytmy/189791-kolorowanie_grafow_-_algorytm_genetyczny
     * @param chromosom wektor wierzchołków odpowiadający jednemu grafowi
     * @param iloscKolorow ilość kolorów, którymi jest pokolorowany graf
     * @return chromosom - nowy chromosom po zmianie koloru jednego wierzchołka, który miał najwięcej złych sąsiadów,
     * na kolor wierzchołka, który miał najmniej złych sąsiadów
     */
    public static Vector<Vertex> transposition(Vector<Vertex> chromosom, int iloscKolorow){
        ArrayList<Integer> colors = new ArrayList<>();

        Vertex badNeighbour = null; //wierzchołek, któremu będzie zmieniany kolor
        ArrayList<Integer> numbersOfBadNeighbours = new ArrayList<>(); //i-ty el. listy zawiera ilość złych sąsiadów i-tego wierzchołka
        int badNeighbours; //ilość złych sąsiadów konkretnego wierzchołka
        int newColor;
        //sprawdzamy każdy wierzchołek w chromosomie
        for (int i  = 0; i < chromosom.size(); i++){
            badNeighbours = 0;
            //sprawdzanie sąsiadów
            for (int d = 0; d < chromosom.get(i).sasiedzi.size(); d++){
                //jeśli istnieje wierzchołek wśród sąsiadów, który ma taki sam kolor jak wierzchołek sprawdzany
                if(chromosom.get(chromosom.get(i).sasiedzi.get(d)).kolor == chromosom.get(i).kolor)  {
                    //to obliczamy ile ma tych złych sąsiadów
                    badNeighbours++;
                    break;
                }
            }
            numbersOfBadNeighbours.add(i, badNeighbours);
        }
        int max = Collections.max(numbersOfBadNeighbours); //max - największa ilość złych sąsiadów jakie ma jeden z wierzchołków w chromosomie
        int min = Collections.min(numbersOfBadNeighbours); //min - najmniejsza ilość złych sąsiadów jakie ma jeden z wierzchołków w chromosomie
        //indeks wierzchołka, któremu będzie zmieniany kolor
        int indexBadNeighbour = numbersOfBadNeighbours.indexOf(max); // indeks wierzchołka, który ma najwięcej złych sąsiadów w chromosomie
        //indeks wierzchołka, którego kolor będzie brany do zmiany z max
        int indexMinBadNeighbour = numbersOfBadNeighbours.indexOf(min); // indeks wierzchołka, który ma najmniej złych sąsiadów w chromosomie

        System.out.println("Bad Neighbour's number is: " + indexBadNeighbour);
        newColor = chromosom.get(indexMinBadNeighbour).kolor;
        System.out.println("Max : Wierzchołek nr: " + indexBadNeighbour + " ma " + max + " o kolorze " + chromosom.get(indexBadNeighbour).kolor );
        System.out.println("Min : Wierzchołek nr: " + indexMinBadNeighbour + " ma " + min + " o kolorze " + newColor);
        chromosom.get(indexBadNeighbour).kolor = newColor;
        return chromosom;
    }

    /**
     * transposition2 - przechodzi przez wszystkie wierzchołki w chromosomie. Sprawdza sąsiadów każdego wierzchołka.
     * Jeśli istnieje wierzchołek wśród sąsiadów, który ma taki sam kolor jak wierzchołek sprawdzany,
     * to zmieniamy jego kolor na kolor randomowo wybranego wierzchołka.
     * @param chromosom wektor wierzchołków odpowiadający jednemu grafowi
     * @param iloscKolorow ilość kolorów, którymi jest pokolorowany graf
     * @return chromosom - nowy chromosom po zmianie koloru jednego wierzchołka, który miał jako pierwszy w kolejności iterowania chromosomu złego sąsiada
     */
    public static Vector<Vertex> transposition2(Vector<Vertex> chromosom, int iloscKolorow){
        ArrayList<Integer> colors = new ArrayList<>();

        Vertex badNeighbour = null; //wierzchołek, któremu będzie zmieniany kolor
        int newColor;
        int indexBadNeighbour = 0;   //indeks wierzchołka, któremu będzie zmieniany kolor
        //sprawdzamy każdy wierzchołek w chromosomie
        for (int i  = 0; i < chromosom.size(); i++){
            //sprawdzanie sąsiadów
            for (int d = 0; d < chromosom.get(i).sasiedzi.size(); d++){
                //jeśli istnieje wierzchołek wśród sąsiadów, który ma taki sam kolor jak wierzchołek sprawdzany
                if(chromosom.get(chromosom.get(i).sasiedzi.get(d)).kolor == chromosom.get(i).kolor)  {
                    //to oznaczamy go jako badNeighbour, czyli wierzchołek, który trzeba zamienić
                    badNeighbour = chromosom.get(chromosom.get(i).sasiedzi.get(d));
                    indexBadNeighbour = i;
                    break;
                }
            }
            if (badNeighbour != null){
                break;
            }
        }
        while(true){
            System.out.println("Bad Neighbour's number is: " + indexBadNeighbour);
            newColor = chromosom.get(new Random().nextInt(chromosom.size())).kolor; //nowy randomowy kolor na jaki zostanie zamieniony wybrany wierzchołek
            if(chromosom.get(indexBadNeighbour).kolor != newColor){
                chromosom.get(indexBadNeighbour).kolor = newColor;
                break;
            }
        }
        return chromosom;
    }
    /**
     * firstFit -
     * The second mutation operation
     called First Fit (FF) is designed for colorings in partition
     representation and is well suited for GCP. In First Fit mutation
     one block of the partition is selected at random and we try to
     make a conflict–free assignment of its vertices to other blocks
     using the heuristic First Fit. Vertices with no conflict–free
     assignment remain in the original block. Thus, as a result of the
     mutation First Fit the color assignment is partially rearranged
     and the number of partition blocks is often reduced by one.
     * @param chromosom wektor wierzchołków odpowiadający jednemu grafowi
     * @param iloscKolorow ilość kolorów, którymi jest pokolorowany graf
     * @return chromosom - nowy chromosom po mutacji
     */
    public static Vector<Vertex> firstFit(Vector<Vertex> chromosom, int iloscKolorow){
        return chromosom;
    }
}