// Serena Iyoha 300187757
// Shkula Wardak 300065411

#include <iostream>
#include "Graph.h"
#include <vector>
#include <algorithm>

using namespace std;

Graph::Graph()
{
    // default number of nodes is 3
    this->node_deg = 3;
    vector<vector<bool>> adjMatrix(node_deg, vector<bool>(node_deg, false));

    for (int i = 0; i < adjMatrix.size(); i++) { // printing the 2D vector.
        for (int j = 0; j < adjMatrix[i].size(); j++)
            cout << adjMatrix[i][j] << " ";
        cout << endl;
    }
}

Graph::Graph(int n)
{
    this->node_deg = n;
    vector<vector<bool>> adjMatrix(node_deg, vector<bool>(node_deg, false));
    for(int i = 0; i < adjMatrix.size(); i++) { // printing the 2D vector.
        for (int j = 0; j < adjMatrix[i].size(); j++)
            cout << adjMatrix[i][j] << " ";
        cout << endl;
    }
}

 Graph::Graph(const Graph &g)
 {
      node_deg = g.node_deg;
      vector<vector<bool>> temp(node_deg, vector<bool>(node_deg, false));
      for (int i = 0; i < node_deg; ++i) {
          for (int j = 0; j < node_deg; ++j){
              cout << "\ntemp " << temp[i][j] << endl;
              cout << "adj " << g.adjMatrix[i][j] << endl;
              temp[i][j] = g.adjMatrix[i][j];
              cout << "this too" << endl;
          }
      }
      adjMatrix = temp;

 }

Graph::~Graph()
{
    //cout << "this is where it fails kinda" << endl;
     // there might be some stuff that needs to be added here
     for (int i = 0; i < node_deg; ++i)
         adjMatrix[i].clear();
     adjMatrix.clear();
     cout << "maybe here" << endl;
}

bool Graph::add_edge(int start, int end)
{
    // first we'll check if the start and end nodes exist in the graph
    if((start>end) || ((start - 1) > node_deg  || (end-1)) > node_deg || (start< 0 || end< 0)){
        cout<<"Edge is out of boundary"<<endl;
        return false;
    }
    if(edge_exist(start, end))
        return true;

    adjMatrix[start-1][end-1] = true;
    adjMatrix[end-1][start-1] = true;

    for(int i = 0; i < adjMatrix.size(); i++) { // printing the 2D vector.
        for (int j = 0; j < adjMatrix[i].size(); j++)
            cout << adjMatrix[i][j] << " ";
        cout << endl;
    }
    return true;

//    if ((((start - 1) < node_deg) && ((end - 1) < node_deg)) && ((start > 0) && (end > 0))){
//        // then we'll check that the edge doesn't already exist in the graph
//        if (!(this->edge_exist(start, end))){
//            adjMatrix[start - 1][end - 1] = true;
//            adjMatrix[end - 1][start - 1] = true;
//            return true;
//        }
//        cout << "Edge already exists in graph!" << endl;
//    }
//    cout << "Edge is outside of graph boundaries" << endl;
//    for(int i = 0; i < adjMatrix.size(); i++) { // printing the 2D vector.
//        for (int j = 0; j < adjMatrix[i].size(); j++)
//            cout << adjMatrix[i][j] << " ";
//        cout << endl;
//    }
//    return false; // if we can't add it, return false
}

bool Graph::remove_edge(int start, int end)
{
    // if the edge exists in the graph, remove the end from the edge LL
    if (this->edge_exist(start, end)){
        adjMatrix[start - 1][end - 1] = false;
        adjMatrix[end - 1][start - 1] = false;
        return true;
    }
    cout << "Edge does not exist in graph!" << endl;
    return false;

}

bool Graph::edge_exist(int start, int end)
{
    // first checking if the start and end nodes exist in graph
    if ((((start - 1) < node_deg) && ((end - 1) < node_deg)) && ((start > 0) && (end > 0))){
        return (adjMatrix[start - 1][end - 1] && adjMatrix[end - 1][start - 1]);
    }
    return false;
}

int Graph::get_degree(int vertex)
{
    if (vertex > 0 && vertex < node_deg){
        // returns the number of edges of the vertice
        int count = 0;
        for (int i = 0; i < node_deg; ++i){
            if (adjMatrix[vertex][i])
                count++;
        }
        return count;
    }
    cout << "Vertex is outside of graph boundaries" << endl;
    return -1;
}

Graph Graph::operator++()
{
    cout << "hehe1" << endl;
    node_deg = node_deg + 1;
    adjMatrix.resize(node_deg);  // resize top level vector
    for (int i = 0; i < node_deg; i++)
        adjMatrix[i].resize(node_deg);  // resize each of the contained vectors
    cout << "hehe3" << endl;
    return (*this);
}

Graph Graph::operator++(int a)
{
    cout << "hehe2" << endl;
    Graph result = (*this);
    node_deg = node_deg + 1;
    adjMatrix.resize(node_deg);  // resize top level vector
    for (int i = 0; i < node_deg; i++)
        adjMatrix[i].resize(node_deg);  // resize each of the contained vectors
    return result;
}

Graph Graph::operator--()
{

    // first check if its in any of the other edge lists
    for (int i = 0; i < node_deg - 1; ++i)
        // if it is, remove it from there first
        if (edge_exist(i, node_deg - 1)) remove_edge(i, node_deg - 1);

    // then we can remove the vertex
    adjMatrix.pop_back();
    node_deg = node_deg - 1;
    for (int i = 0; i < node_deg; i++)
        adjMatrix[i].pop_back();  // resize each of the contained vectors

    return (*this);
}

Graph Graph::operator--(int a)
{
    Graph result = (*this);

    // first check if its in any of the other edge lists
    for (int i = 0; i < node_deg; ++i)
        // if it is, remove it from there first
        if (edge_exist(i, node_deg - 1)) remove_edge(i, node_deg - 1);

    // then we can remove the vertex
    adjMatrix.pop_back();
    node_deg = node_deg - 1;
    for (int i = 0; i < node_deg; i++)
        adjMatrix[i].pop_back();

    return result;
}

// bool Graph::path_exists(int start, int end){
//     //

// }

// bool Graph::itr_path_exists(bool* visited, int start, int end){
//     //
// }

ostream& operator<<(ostream &out, Graph g) {
    // printing the verticies
    out << "V = {";
    for (int i = 0; i < g.node_deg; ++i)
    {
        out << i + 1;
        if (i != (g.node_deg - 1))
            out << ", ";
    }
    // ok this is good

    // printing the edges
//    out << "}\nE = {\n";
//    for (int i = 0; i < g.node_deg; ++i){
//        out << "\t" << i + 1 << " => ";
//        bool is_empty = true; // flag to check if there are no edges in the graph
//        for (int j = 0; j < g.node_deg; ++j){
//            if (g.adjMatrix[i][j]){
//                is_empty = false;
//                out << (j + 1);
//                if (j != (g.node_deg - 2)){
//                    // issue with this function is that
//                    // the last "true" element might not be at the end of the matrix
//                    // so for edge (5, 1) 1, gets printed bc it still has to go thru
//                    // [5][2], [5][3], [5][4] etc. to check if true
//                    out << ", ";
//                }
//            }
//        }
//        if (is_empty)
//            out << "None";
//
//        out << "\n";
//    }
//    out << "}";
    return out;
}