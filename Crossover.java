import java.util.LinkedList;
import java.util.Map;
import java.util.Random;
import java.util.Vector;

/**
 * Crossover
 */
public class Crossover {

    /**
     * OnePoint - randomowo wybierany jest pojedynczy crosspoint dla obojgu rodziców.
     * Pierwsze dziecko jest kolorowana przed crosspointem takimi samymi kolorami jak rodzic A miał przed nim,
     * a reszta wierzchołków jest kolorowana tamiki samymi kolorami jak rodzic B miał po crosspoincie.
     * Kolejne dziecko jest kolorowane odwrotnie, na początku ma kolory rodzica B, a potem kolory od rodzica A.
     * Połowa dzieci w populacji ma taki sam kolor.
     * @param parents element mapy(watość,klucz) zawierający dwa chromosomy - rodziców
     * @param population wektor chromosomów (wektorów wierzchołków)
     * @return population - nowa populacja po zamianie kolorów
     */

    public static Vector<Vector<Vertex>> onePoint(Map.Entry<Vector<Vertex>, Vector<Vertex>> parents, Vector<Vector<Vertex>> population) {

        int crosspoint = new Random().nextInt(population.get(1).size());  //A - generowanie losowej liczby crosspoint z ilości wierzchołków w chromosomie

        for (int j = 0; j < population.size(); j++) {
            Vector<Vertex> child = population.get(j); //A - child - jeden chromosom z populacji Offspring
            switch (j % 2) {
                case 0: //kolorowanie dwójki dzieci tak samo
                    for (int i = 0; i < child.size(); i++) { //przejście i odpowiednie ustawienie kolorów wierzchołków w dziecku
                        int kolor1 = parents.getKey().get(i).kolor; //kolor1 = kolor z pierwszego parenta
                        int kolor2 = parents.getValue().get(i).kolor; //kolor2 = kolor z drugiego parenta
                        Vertex temp = child.get(i); //temp - jeden wierzchołek dziecka
                        int newId = temp.id; //id danego wierzchołka dziecka, któremu zmieni się odpowiednio kolor
                        if (newId < crosspoint) { //kolorowanie przed crosspointem
                            temp.kolor = kolor1;
                        } else { //kolorowanie po crosspoincie
                            temp.kolor = kolor2;
                        }
                        child.set(i, temp);
                    }
                    population.set(j, child);
                    break;
                case 1: //kolorowanie dwójki dzieci odwrotnie do poprzedniego
                    for (int i = 0; i < child.size(); i++) { //przejście i odpowiednie ustawienie kolorów wierzchołków w dziecku
                        int kolor1 = parents.getKey().get(i).kolor; //kolor1 = kolor z pierwszego parenta
                        int kolor2 = parents.getValue().get(i).kolor; //kolor2 = kolor z drugiego parenta
                        Vertex temp = child.get(i); //temp - jeden wierzchołek z wybranego dziecka
                        int newId = temp.id;
                        if (newId < crosspoint) {
                            temp.kolor = kolor2;
                        } else {
                            temp.kolor = kolor1;
                        }
                        child.set(i, temp);
                    }
                    population.set(j, child);
                    break;
            }
        }
        return population;
    }

    /**
     * twoPoint - wybieramy randomowo dwa crosspointy.
     * Pierwsze dziecko jest kolorowane do pierwszego crosspointa kolorami rodzica A,
     * między pierwszym a drugim crosspointem kolorami rodzica B,
     * po drugim kolorami rodzica A.
     * Kolejne dziecko odwrotnie, na początku kolory od rodzica B, potem A, potem znowu B.
     * Połowa dzieci w populacji ma taki sam kolor.
     *
     * @param parents element mapy(watość,klucz) zawierający dwa chromosomy - rodziców
     * @param population wektor chromosomów (wektorów wierzchołków)
     * @return population - nowa populacja po zamianie kolorów
     */
    public static Vector<Vector<Vertex>> twoPoint(Map.Entry<Vector<Vertex>, Vector<Vertex>> parents, Vector<Vector<Vertex>> population) {

        int chromosomSize = population.elementAt(1).size();
        int crosspoint1 = new Random().nextInt(chromosomSize);
        int crosspoint2;
        do {
            crosspoint2 = new Random().nextInt(chromosomSize);
        } while (crosspoint1 >= crosspoint2); //A - ustawianie crosspoint1 na mniejszy, a crosspoint2 na większy

        for (int j = 0; j < population.size(); j++) {
            Vector<Vertex> child = population.get(j); //A - child - jeden chromosom z populacji Offspring
            switch (j % 2) {
                case 0: //kolorowanie dwójki dzieci tak samo
                    for (int i = 0; i < child.size(); i++) { //przejście i odpowiednie ustawienie kolorów wierzchołków w dziecku
                        int kolor1 = parents.getKey().get(i).kolor; //kolor1 = kolor z pierwszego parenta
                        int kolor2 = parents.getValue().get(i).kolor; //kolor2 = kolor z drugiego parenta
                        Vertex temp = child.get(i); //temp - jeden wierzchołek dziecka
                        int newId = temp.id; //id danego wierzchołka dziecka, któremu zmieni się odpowiednio kolor
                        if (newId < crosspoint1 || newId > crosspoint2) {
                            temp.kolor = kolor1;
                        } else {
                            temp.kolor = kolor2;
                        }
                        child.set(i, temp);
                    }
                    population.set(j, child);
                    break;
                case 1: //kolorowanie dwójki dzieci tak samo
                    for (int i = 0; i < child.size(); i++) { //przejście i odpowiednie ustawienie kolorów wierzchołków w dziecku
                        int kolor1 = parents.getKey().get(i).kolor; //kolor1 = kolor z pierwszego parenta
                        int kolor2 = parents.getValue().get(i).kolor; //kolor2 = kolor z drugiego parenta
                        Vertex temp = child.get(i); //temp - jeden wierzchołek dziecka
                        int newId = temp.id; //id danego wierzchołka dziecka, któremu zmieni się odpowiednio kolor
                        if (newId < crosspoint1 || newId > crosspoint2) {
                            temp.kolor = kolor2;
                        } else {
                            temp.kolor = kolor1;
                        }
                        child.set(i, temp);
                    }
                    population.set(j, child);
                    break;
            }
        }
        return population;
    }

    /**
     * Uniform - kolory są randomowo kopiowane albo od rodzica A, albo od rodzica B.
     * @param parents element mapy(watość,klucz) zawierający dwa chromosomy - rodziców
     * @param population wektor chromosomów (wektorów wierzchołków)
     * @return population - nowa populacja po zamianie kolorów
     */
    public static Vector<Vector<Vertex>> uniform(Map.Entry<Vector<Vertex>, Vector<Vertex>> parents, Vector<Vector<Vertex>> population) {

        for (int j = 0; j < population.size(); j++) {
            Vector<Vertex> child = population.get(j); //A - child - jeden chromosom z populacji Offspring
            switch (j % 2) {
                case 0: //kolorowanie dwójki dzieci tak samo
                    for (int i = 0; i < child.size(); i++) {
                        int kolor1 = parents.getKey().get(i).kolor; //kolor1 = kolor z pierwszego parenta
                        int kolor2 = parents.getValue().get(i).kolor; //kolor2 = kolor z drugiego parenta
                        Vertex temp = child.get(i);
                        Random generator = new Random();
                        switch (generator.nextInt(2)) { //A - randomowe wybieranie koloru jednego z dwóch
                            case 0:
                                temp.kolor = kolor1;
                                break;
                            case 1:
                                temp.kolor = kolor2;
                                break;
                        }
                        child.set(i, temp);
                    }
                    population.set(j, child);
                    break;
                case 1: //kolorowanie dwójki dzieci tak samo
                    for (int i = 0; i < child.size(); i++) {
                        int kolor1 = parents.getKey().get(i).kolor; //kolor1 = kolor z pierwszego parenta
                        int kolor2 = parents.getValue().get(i).kolor; //kolor2 = kolor z drugiego parenta
                        Vertex temp = child.get(i);
                        Random generator = new Random();
                        switch (generator.nextInt(2)) { //A - randomowe wybieranie koloru jednego z dwóch
                            case 0:
                                temp.kolor = kolor2;
                                break;
                            case 1:
                                temp.kolor = kolor1;
                                break;
                        }
                        child.set(i, temp);
                    }
                    population.set(j, child);
                    break;
            }
        }
        return population;
    }

    /**
     * Arithmetic - populacja jest tworzona przez arytmetyczne działania na kolorach rodziców.
     * Działanie - iloczyn kolorów rodziców modulo ilość kolorów występujących w danym grafie.
     * @param parents element mapy(watość,klucz) zawierający dwa chromosomy - rodziców
     * @param population wektor chromosomów (wektorów wierzchołków)
     * @param colors - ilość kolorów występujących w danym grafie
     * @return population - nowa populacja po zamianie kolorów
     */
    public static Vector<Vector<Vertex>> arithmetic(Map.Entry<Vector<Vertex>, Vector<Vertex>> parents, Vector<Vector<Vertex>> population, int colors) {

        for (int j = 0; j < population.size(); j++) {
            Vector<Vertex> child = population.get(j); //A - child - jeden chromosom z populacji Offspring
            for (int i = 0; i < child.size(); i++) {
                int kolor1 = parents.getKey().get(i).kolor; //kolor1 = kolor z pierwszego parenta
                int kolor2 = parents.getValue().get(i).kolor; //kolor2 = kolor z drugiego parenta
                Vertex temp = child.get(i);
                temp.kolor = (kolor1 * kolor2) % colors; //modulo ilość kolorów występujących w danym grafie
                child.set(i, temp);
            }
            population.set(j, child);
        }
        return population;
    }
}
