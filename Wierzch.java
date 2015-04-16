import java.util.Vector;

//objekt wierzchołek - ma swój kolor i listę ID sąsiadów
//id jest po to w sumie tylko żeby przy tworzeniu nie było bajzlu
public class Wierzch
{
    int id;
    int kolor;
    Vector<Integer> sasiedzi = new Vector<Integer>();

    Wierzch()
    {
        kolor=0;
        id = 150;
    }
    Wierzch(int _id)
    {
        id=_id;
        kolor=0;
    }
}