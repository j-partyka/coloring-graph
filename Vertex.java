import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

import static jdk.nashorn.internal.objects.NativeMath.random;

public class Vertex {
//ID != 150

//parents
public static int parents(Vector<Wierzch> chromosom1)
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
//crossover
  public static Vector<Wierzch> crossover(int id1, int id2, Vector<Wierzch> chromosom){
      Wierzch parent1 = chromosom.get(id1);
      Wierzch parent2 = chromosom.get(id2);

      int kolor1 = parent1.kolor;
      int kolor2 = parent2.kolor;

      /*(Random crosspoint = new Random();
      int random = generator.nextInt(100);*/

      int crosspoint = 10;

      int i = 0;
      while (i < 100) {
          Wierzch temp = chromosom.get(i);
          int newId = temp.id;
          if(newId != 150) {
              if (newId < crosspoint) {
                  temp.kolor = kolor1;
              } else {
                  temp.kolor = kolor2;
              }
          }
          i++;
      }
      return chromosom;
  }

//mutacja

    public static Vector<Wierzch> mutation(Vector<Wierzch> chromosom, int iloscKolorow){
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

    public static void main(String[] args) {

        //Tworzymy listę wierzchołków grafu- "chromosom"
        Vector<Wierzch> chromosom = new Vector<Wierzch>();
        //inicjalizujemy bo mi nie działało jak inaczej
        // próbowałam robić sąsiedztwa. do poprawki najwyżej
        for (int j = 0; j < 100; j++) {
            chromosom.add(new Wierzch());
        }
        // ładujemy plik z grafem
        int i = 0;
        int indeks1 = 0;
        int indeks2 = 0;
        String pierwsze = "ERROR";

        Scanner sc2 = null;
        try {
            sc2 = new Scanner(new File("/home/asia/Coding/IdeaProjects/ColorGraph/src/graf.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (sc2.hasNextLine()) {
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
                } else i++;
            }
        }
        //koniec tworzenia grafu
        //sprawdzam

        //chromosom.get(15).kolor = 5;
        //chromosom.get(3).kolor = 3;

        //Vector<Wierzch> newChromosom = crossover(3,15,chromosom);
        //Vector<Wierzch> newChromosom = crossover(parents(chromosom),parents(chromosom),chromosom);

        for(int p=0;p<300;p++) {
            mutation(crossover(parents(chromosom), parents(chromosom),chromosom),10);
        }
       // mutation(chromosom, 10);
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
        /*for (int j =0;j<newChromosom.size();j++) {
            if (newChromosom.get(j).id != 150) {
                 System.out.println("poczatek wierzchołka");
                System.out.println("ID: " + newChromosom.get(j).id);
                System.out.println("Kolor: " + newChromosom.get(j).kolor);
                System.out.println("Sąsiedzi:");
                for (int d = 0; d < newChromosom.get(j).sasiedzi.size(); d++) {
                    System.out.println(newChromosom.get(j).sasiedzi.get(d));
                }
                 System.out.println("koniec wierzchołka");
                System.out.println();
            }
        }*/

            //rusza funkcja parents
            //System.out.println(parents(chromosom));
            //System.out.println(parents(chromosom));
        }

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