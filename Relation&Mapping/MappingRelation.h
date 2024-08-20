// Serena Iyoha 300187757
// Shkula Wardak 300065411

#pragma once
#include <iostream>
#include "Relation.h"
#include <list>
#include <vector>

using namespace std;

template <typename type1, typename type2>
struct Tuple
{
    type1 first;
    type2 second;
};

template <typename type1, typename type2>
class MappingRelation
{

private:
    vector<Tuple<type1, type2>> root;

public:
    MappingRelation();
    MappingRelation(const MappingRelation &);
    ~MappingRelation();
    int cardinality();
    bool add_element(type1, type2);
    void remove_element(type1, type2);
    bool is_member(type1, type2);
    bool operator==(MappingRelation);
    MappingRelation operator+(MappingRelation);
    MappingRelation operator-(MappingRelation);
    vector<type2> operator[](type1);
    MappingRelation intersection(MappingRelation);
    bool is_function();
    template <typename typex, typename typey>
    friend ostream &operator<<(ostream &, MappingRelation<typex, typey> &);
    MappingRelation inverse();
};