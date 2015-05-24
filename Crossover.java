import java.util.LinkedList;
import java.util.Map;
import java.util.Random;
import java.util.Vector;


public class Crossover {
    //A - one-point crossover
    //A - A single crossover point on both parents' organism strings is selected. A
    //A - All data beyond that point in either organism string is swapped between the two parent organisms. The resulting organisms are the children:
    public static Vector<Vector<Vertex>> onePoint(Map.Entry<Vector<Vertex>, Vector<Vertex>> parents, Vector<Vector<Vertex>> population) {

        Vector<Vector<Vertex>> newPopulation = population; //A - newPopulation - new population of Offspring
        int crosspoint = new Random().nextInt(population.get(0).size());  //A - generowanie losowej liczby crosspoint z ilości wierzchołków w chromosomie

        for (int j = 0; j < 4; j++) {
            Vector<Vertex> child = newPopulation.get(j); //A - child - jeden chromosom z populacji Offspring
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
                    newPopulation.set(j, child);
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
                    newPopulation.set(j, child);
                    break;
            }
        }
        return newPopulation;
    }

    //A - Two-point crossover
    //A - Two-point crossover calls for two points to be selected on the parent organism strings.
    //A - Everything between the two points is swapped between the parent organisms, rendering two child organisms:
    public static Vector<Vector<Vertex>> twoPoint(Map.Entry<Vector<Vertex>, Vector<Vertex>> parents, Vector<Vector<Vertex>> population) {

        Vector<Vector<Vertex>> newPopulation = population; //A - newPopulation - new population of Offspring
        int crosspoint1 = new Random().nextInt(23);
        int crosspoint2;
        do {
            crosspoint2 = new Random().nextInt(23);
        } while (crosspoint1 >= crosspoint2); //A - ustawianie crosspoint1 na mniejszy, a crosspoint2 na większy

        for (int j = 0; j < 4; j++) {
            Vector<Vertex> child = newPopulation.get(j); //A - child - jeden chromosom z populacji Offspring
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
                    newPopulation.set(j, child);
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
                    newPopulation.set(j, child);
                    break;
            }
        }
        return newPopulation;
    }

    //A - Uniform crossover - bits are randomly copied from the first or from the second parent
    public static Vector<Vector<Vertex>> uniform(Map.Entry<Vector<Vertex>, Vector<Vertex>> parents, Vector<Vector<Vertex>> population) {

        Vector<Vector<Vertex>> newPopulation = population; //A - newPopulation - new population of Offspring

        for (int j = 0; j < 4; j++) {
            Vector<Vertex> child = newPopulation.get(j); //A - child - jeden chromosom z populacji Offspring
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
                    newPopulation.set(j, child);
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
                    newPopulation.set(j, child);
                    break;
            }
        }
        return newPopulation;
    }

    //A - Arithmetic crossover - some arithmetic operation is performed to make a new offspring
    public static Vector<Vector<Vertex>> arithmetic(Map.Entry<Vector<Vertex>, Vector<Vertex>> parents, Vector<Vector<Vertex>> population) {

        Vector<Vector<Vertex>> newPopulation = population; //A - newPopulation - new population of Offspring

        for (int j = 0; j < 4; j++) {
            Vector<Vertex> child = newPopulation.get(j); //A - child - jeden chromosom z populacji Offspring
            for (int i = 0; i < child.size(); i++) {
                int kolor1 = parents.getKey().get(i).kolor; //kolor1 = kolor z pierwszego parenta
                int kolor2 = parents.getValue().get(i).kolor; //kolor2 = kolor z drugiego parenta
                Vertex temp = child.get(i);
                temp.kolor = (kolor1 * kolor2) % 10; //modulo ilość kolorów występujących w danym grafie
                child.set(i, temp);
            }
            newPopulation.set(j, child);
        }
        return newPopulation;
    }
}
