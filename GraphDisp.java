
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.StaticLayout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.EditingModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import org.apache.commons.collections15.Factory;
import org.apache.commons.collections15.Transformer;

public class GraphDisp extends javax.swing.JFrame {
    
    Graph<Integer, String> g;
    int nodeCount, edgeCount;
    Factory <Integer> vertexFactory;
    Factory<String> edgeFactory;
    String filename = "graf.txt";
    private Vector<Vertex> chromosom;
    String z = "I"; //do nazywania krawędzi

    /**
     * Creates new form GraphDisp
     */
    public GraphDisp(Vector<Vertex> chromosom) {
        initComponents();
        g = new SparseMultigraph<>();
        nodeCount = 0; edgeCount = 0;
        vertexFactory = new Factory<Integer>() { // My vertex factory
            public Integer create() {
                return nodeCount++;
            }
        };
        edgeFactory = new Factory<String>() { // My edge factory
            public String create() {
                return "E"+edgeCount++;
            }
        };
        
        Graph2 gr = new Graph2(filename);
        g = gr.load();
        this.chromosom = chromosom;
    }
    
    public void setFilename(String filename)
    {
        this.filename = filename;
    }
    
    /*public void rysowanieWlasnegoGrafu()
    {
        GraphDisp sgv = new GraphDisp();
        // Layout<V, E>, VisualizationViewer<V,E>
        Layout<Integer, String> layout = new StaticLayout(sgv.g);
        layout.setSize(new Dimension(300,300));
        VisualizationViewer<Integer,String> vv = new VisualizationViewer<Integer,String>(layout);
        vv.setPreferredSize(new Dimension(350,350));
        // Show vertex and edge labels
        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
        vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
        // Create a graph mouse and add it to the visualization viewer
        // Our Vertices are going to be Integer objects so we need an Integer factory
        EditingModalGraphMouse gm = new EditingModalGraphMouse(vv.getRenderContext(), 
                 sgv.vertexFactory, sgv.edgeFactory); 
        vv.setGraphMouse(gm);

        
        JFrame frame = new JFrame("Editing Graph Viewer 1");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(vv);
        
        // Let's add a menu for changing mouse modes
        JMenuBar menuBar = new JMenuBar();
        JMenu modeMenu = gm.getModeMenu();
        modeMenu.setText("Mouse Mode");
        modeMenu.setIcon(null); // I'm using this in a main menu
        modeMenu.setPreferredSize(new Dimension(80,20)); // Change the size so I can see the text
        
        menuBar.add(modeMenu);
        frame.setJMenuBar(menuBar);
        gm.setMode(ModalGraphMouse.Mode.EDITING); // Start off in editing mode
        frame.pack();
        frame.setVisible(true);  
    }*/
    
    public void showGraph(Vector<Vertex> chrom)
    {
        GraphDisp sgv = new GraphDisp(chrom); //We create our graph in here
        // The Layout<V, E> is parameterized by the vertex and edge types
        Layout<Integer, String> layout = new CircleLayout(sgv.g);
        layout.setSize(new Dimension(300,300)); // sets the initial size of the layout space
        // The BasicVisualizationServer<V,E> is parameterized by the vertex and edge types
        BasicVisualizationServer<Integer,String> vv = new BasicVisualizationServer<Integer,String>(layout);
        vv.setPreferredSize(new Dimension(350,350)); //Sets the viewing area size
        
        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
        
       
        Transformer<Integer, Paint> vertexPaint = new Transformer<Integer, Paint>()
        {
            public Paint transform(Integer i)
            {
                if (chrom.get(i).kolor == 0) return Color.BLUE;
                else if (chrom.get(i).kolor == 1) return Color.RED;
                else if (chrom.get(i).kolor == 2) return Color.YELLOW;
                else if (chrom.get(i).kolor == 3) return Color.GREEN;
                else if (chrom.get(i).kolor == 4) return Color.ORANGE;
                else if (chrom.get(i).kolor == 5) return Color.PINK;
                else if (chrom.get(i).kolor == 6) return Color.GRAY;
                else if (chrom.get(i).kolor == 7) return Color.CYAN;
                else if (chrom.get(i).kolor == 8) return Color.MAGENTA;
                else if (chrom.get(i).kolor == 9) return Color.WHITE;
                else if (chrom.get(i).kolor == 10) return Color.BLACK;
                else return Color.LIGHT_GRAY;
            }  
        };
        
        vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
        
        JFrame frame = new JFrame("Simple Graph View");
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(vv); 
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>                        


    // Variables declaration - do not modify                     
    // End of variables declaration                   
}
