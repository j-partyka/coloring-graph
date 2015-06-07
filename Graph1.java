import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Graph1 {
    
    private String filename;
    
    //Tworzymy listę wierzchołków grafu- "chromosom"
    Vector<Vertex> chromosom = new Vector<Vertex>();
    
    Graph1(String filename)
    {
        this.filename = filename;
    }

    public Vector<Vertex> load() {

        //inicjalizujemy bo mi nie działało jak inaczej
        // próbowałam robić sąsiedztwa. do poprawki najwyżej
        for (int j = 0; j < 100; j++) {
            chromosom.add(new Vertex());
        }
        // ładujemy plik z grafem
        int i = 0;
        int indeks1 = 0;
        int indeks2 = 0;
        String pierwsze = "ERROR";

        Scanner sc2 = null;
        try
        {
            sc2 = new Scanner(new File(filename));
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        
        while (sc2.hasNextLine())
        {
            Scanner s2 = new Scanner(sc2.nextLine());
            boolean b;
            while (b = s2.hasNext()) {
                String s = s2.next();
                //wyciagamy kazde slowo po kolei
                if (i == 1) pierwsze = s;
                //System.out.println(i);
                //System.out.println(s);
                // if (i==2) {i=0; System.out.println(s);System.out.println(pierwsze);System.out.println();}
                //i nalicza się od 0 (e) do dwóch (druga liczba w wierszu) i po tym korzystamy z odczytanych danych
                if (i == 2) {
                    i = 0;
                    //konwersja indeksów ze stringów
                    indeks1 = Integer.parseInt(pierwsze);
                    indeks2 = Integer.parseInt(s);
                    //sprawdzam czy już nie istnieje taki wierzchołek,jeżeli nie istnieje to dodaje
                    if (chromosom.get(indeks1 - 1).id == 150) {
                        chromosom.get(indeks1 - 1).id = indeks1 - 1;
                    }
                    if (chromosom.get(indeks2 - 1).id == 150) {
                        chromosom.get(indeks2 - 1).id = indeks2 - 1;
                    }
                    //dodaje ich wzajemnie do sasiadow
                    chromosom.get(indeks1 - 1).sasiedzi.add(indeks2 - 1);
                    chromosom.get(indeks2 - 1).sasiedzi.add(indeks1 - 1);
                }
                else i++;
            }
        }
        return chromosom;
        //koniec tworzenia grafu
    }
}
