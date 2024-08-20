// Serena Iyoha 300187757
// Shkula Wardak 300065411

#pragma once

#include <iostream>
#include <string>
#include <set>
#include <vector>

using namespace std;

template <typename type>
struct Pair
{
	type first;
	type second;
};

template <typename type>
class Relation
{
private:
	set<type> root;
	vector<Pair<type>> elements;

public:
	Relation();
	Relation(const Relation &);
	~Relation(); // Destructor
	int cardinality();
	bool add_element(Pair<type>);
	bool remove_element(Pair<type>);
	bool is_member(Pair<type>);
	bool operator==(Relation);
	vector<type> &operator[](int);
	bool reflexive();
	bool irreflexive();
	bool symmetric();
	bool asymmetric();
	bool transitive();
	bool is_function();
	template <typename typex>
	friend ostream &operator<<(ostream &, Relation<typex> &);
	Relation inverse();
	Relation combination(Relation);
	// helper functions
	void add_to_set(type);
};