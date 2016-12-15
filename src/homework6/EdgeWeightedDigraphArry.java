/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// package edgeweighteddigrapharry;

/******************************************************************************
 *  Execution:    java EdgeWeightedDigraphArry tinyEWD.txt
 *
 *  An edge-weighted digraph, implemented using adjacency matrix.
 *  Assumptions: perfect user. Tested on 0-10 vertexis.
 *  If the data does include 0.0 weights then initialization loop for 
 *  twoD(2d array) has to be enabled and initialized with 9999.9 values. 
 *
 *  Sample output for tinyEWD.txt:
 *  V = 8 E = 15

 *  	0	1	2	3	4	5	6	7	

 *  0	0.0	0.0	0.26	0.0	0.38	0.0	0.0	0.0
 *  1	0.0	0.0	0.0	0.29	0.0	0.0	0.0	0.0
 *  2	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.34
 *  3	0.0	0.0	0.0	0.0	0.0	0.0	0.52	0.0
 *  4	0.0	0.0	0.0	0.0	0.0	0.35	0.0	0.37
 *  5	0.0	0.32	0.0	0.0	0.35	0.0	0.0	0.28
 *  6	0.58	0.0	0.4	0.0	0.93	0.0	0.0	0.0
 *  7	0.0	0.0	0.0	0.39	0.0	0.28	0.0	0.0
 * 
 * 
 *  *  Sample output for tinyEWG.txt:
 *  V = 8 E = 16
 *
 *  	0	1	2	3	4	5	6	7	
 *
 *  0	0.0	0.0	0.26	0.0	0.38	0.0	0.0	0.16
 *  1	0.0	0.0	0.38	0.29	0.0	0.32	0.0	0.19
 *  2	0.0	0.0	0.0	0.17	0.0	0.0	0.0	0.34
 *  3	0.0	0.0	0.0	0.0	0.0	0.0	0.52	0.0
 *  4	0.0	0.0	0.0	0.0	0.0	0.35	0.0	0.37
 *  5	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.28
 *  6	0.58	0.0	0.4	0.0	0.93	0.0	0.0	0.0
 *  7	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0
 * 
 ******************************************************************************/

public class EdgeWeightedDigraphArry {
    private static final String NEWLINE = System.getProperty("line.separator");

    private final int V;         // number of vertices in this digraph
    private int E;               // number of edges in this digraph
    private int array[];         // an array for the matrix display
    private double twoD[][];     // 2-d array for storing the weights
    
    
    // Constructor that initializes E, array, and twoD
    public EdgeWeightedDigraphArry(int V) {
        if (V < 0) throw new IllegalArgumentException("Number of vertices in a Digraph must be nonnegative");
        this.V = V;
        this.E = 0;
        array = new int[V];
        twoD = new double[V][V];
        for (int i = 0; i < V; i++) {
            array[i] = i;
        }
        //This code initializes all 2d array fields to 9999.9;
        /*for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
               //twoD[i][j] = 9999.9;
            }
        }
        //decided not to use this since 0.0 looks better
        //in the output and the data does not have any weights 0.0
        */
    }
      
    // Initializes an edge-weighted digraph from the specified input stream.
    public EdgeWeightedDigraphArry(In in) {
        this(in.readInt());
        int E = in.readInt();
        if (E < 0) throw new IllegalArgumentException("Number of edges must be nonnegative");
        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            if (v < 0 || v >= V) throw new IndexOutOfBoundsException("vertex " + v + " is not between 0 and " + (V-1));
            if (w < 0 || w >= V) throw new IndexOutOfBoundsException("vertex " + w + " is not between 0 and " + (V-1));
            double weight = in.readDouble();
            addEdge(new DirectedEdge(v, w, weight));
        }
    }
    
    // Throw an IndexOutOfBoundsException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IndexOutOfBoundsException("vertex " + v + " is not between 0 and " + (V-1));
    }
    
    // Adds the directed edge {@code e} to this edge-weighted digraph.
    public void addEdge(DirectedEdge e) {
        int v = e.from();
        int w = e.to();
        double weight = e.weight();
        validateVertex(v);
        validateVertex(w);
        twoD[v][w]= weight;
        E++;
    }
    
    // Output using string builder
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("V = "+ V + " E = " + E + NEWLINE);
        s.append(NEWLINE);
        s.append("\t");
        for (int v = 0; v < V; v++) {
            s.append(array[v]+"\t");
        }
        s.append(NEWLINE);
        s.append(NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(array[v]);
            for (int i=0;i<V;i++) {
               s.append("\t"+twoD[v][i]);
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

    // Main method
    public static void main(String[] args) {
        In in = new In(args[0]);
        EdgeWeightedDigraphArry G = new EdgeWeightedDigraphArry(in);
        StdOut.println(G);
    }
}

