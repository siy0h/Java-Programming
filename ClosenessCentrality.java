import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class ClosenessCentrality {

    public static final double Disconnect_Distance = 1891;	// this means if you do not have any connection between two ... can have graph with communities, make sure you use this edge case
                                                           // you can use total number of nodes.

    /**
     * we do not have any static or predefined parameters in Closeness Centrality formula but
     * you can add based on your code if you need it (like size, number of edges)
     */

     //no connections?? what is that number actually? I thought a graph wasnt allowed to be disconnected! Where is 1890 coming from?

    ClosenessCentrality(){
    }

    /**
     * Computes the ClosenessCentrality (CC) of each node in a graph.
     *
     * @param graph the Graph to compute CC for
     * @return returns a Map<Integer, Double> mapping each node to its ClosenessCentrality CC
     * @throws Exception
     *
     */


    public Map<Integer, Double> computeClosenessCentrality(Graph graph){

        //edge case: don't do anything if the graph is empty
        if(graph.getGraphNodes().isEmpty() || graph.getGraphEdges().isEmpty()){
            System.out.println("null");
             return null;
        }

        Map<Integer,Double> ccMap = new HashMap<Integer,Double>();//map of each node and its closeness centrality
        Map<Integer, Integer> distanceMatrix = new HashMap<Integer, Integer>(); //map corresponding to each node and its distance to other nodes in the map
        Map<Integer,Boolean> visited = new HashMap<Integer,Boolean>(); //should track if it's been visited

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>();//priority queue that we'll be iterating over
        priorityQueue.addAll(graph.getGraphNodes());

        //initialize distance matrix with the disconnect distance && marks all the nodes as unvisited
        for(int node: graph.getGraphNodes()){
            distanceMatrix.put(node, (int) Disconnect_Distance); //If the node is disconnected, it will have this value. Otherwise it will be updated
            visited.put(node, false);
        } 
        distanceMatrix.put(graph.nodes.get(0),0);//initializes the starting node

        while(!priorityQueue.isEmpty()){
            int temp =  priorityQueue.poll(); //offers the first node in the priority queue
            for(int example:getEdges(temp, graph)){//iterates over the neighbours of the node 
                if(!(visited.get(example))){ //checks to see if the nodes are not visited
                    priorityQueue.add(example);
                    visited.put(example,true); //marks node as visited
                }
                if((int) distanceMatrix.get(example) > ((int) distanceMatrix.get(temp)) +1){ //compares the different edges to find the shortest paths
                    distanceMatrix.put(example, distanceMatrix.get(temp) +1); //updates the value of the shortest path
                }
                int size = distanceMatrix.size();
                double summation = (double) addValues(distanceMatrix.values()); //computes closeness centrality score based on above findings
                ccMap.put(temp, ((size-1) /summation)); // assigns node with a closeness centrality score.

            
        }


       
    }
     return ccMap;
       
    }

    //this method adds all the values in a collection
    private int addValues(Collection<Integer> values){
        int sum = 0;
        Object[] numbers = values.toArray();
        for(Object i: numbers){
            sum+= (int) i;
        }
        return sum;

    }



    //this method gets all the neighbours of a node
    private List<Integer> getEdges(int node, Graph graph){
        List<Integer> edgeList = graph.getGraphEdges().get(node);
        return edgeList;
    }

}
