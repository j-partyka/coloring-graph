import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dell
 */
public class Graph2 {
    
    private String filename;
    private String z = "I";
    
    Graph2(String filename)
    {
        this.filename = filename;
    }
    
    Vector<Vertex> chromosom = new Vector<>();
    Graph<Integer, String> g = new SparseMultigraph<>();
    
    Graph<Integer, String> load()
    {        
        int i = 0;
        int indeks1 = 0;
        int indeks2 = 0;
        String pierwsze = "ERROR";
        int liczbawierzcholkow = 0;

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
            while (b = s2.hasNext())
            {
                String s = s2.next();
                //wyciagamy kazde slowo po kolei
                if (i == 1) pierwsze = s;
                //System.out.println(i);
                //System.out.println(s);
                // if (i==2) {i=0; System.out.println(s);System.out.println(pierwsze);System.out.println();}
                //i nalicza się od 0 (e) do dwóch (druga liczba w wierszu) i po tym korzystamy z odczytanych danych
                if (i == 2)
                {
                    i = 0;
                    //konwersja indeksów ze stringów
                    indeks1 = Integer.parseInt(pierwsze);
                    indeks2 = Integer.parseInt(s);
                    
                    g.addVertex(indeks1);
                    g.addVertex(indeks2);
                    
                    g.addEdge(z, indeks1, indeks2);
                    
                    z += "I";
                 
                }
                else i++;
            }
        } 
        
        /*for(int j=0; j<liczbawierzcholkow; j++)
        {
            g.addVertex(i+1);
        }*/
        return g;
    }
}
