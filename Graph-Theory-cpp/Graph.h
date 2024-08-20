//
// Created by Serena Iyoha on 2022-12-08.
//

#// Serena Iyoha 300187757
// Shkula Wardak 300065411

#pragma once
#include <iostream>
#include <vector>

using namespace std;

class Graph
{
private:
    int node_deg;
    vector<vector<bool> > adjMatrix;

public:
    Graph();
    Graph(int);
    Graph(const Graph&); // using the default copy constructor
    ~Graph();
    bool add_edge(int, int);
    bool remove_edge(int, int);
    bool edge_exist(int, int);
    int get_degree(int);
    Graph operator++();
    Graph operator++(int);
    Graph operator--();
    Graph operator--(int);
    //bool path_exists(int, int);
    friend ostream& operator<<(ostream&, Graph);
    // helper methods
    //bool itr_path_exists(bool*, int, int);
};