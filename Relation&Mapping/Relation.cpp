// Serena Iyoha 300187757
// Shkula Wardak 300065411

#include <iostream>
#include "Relation.h"
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

template <typename type>
Relation<type>::Relation()
{
	// both set and vector are initialized as empty so there's nothing required in the constructor
}

template <typename type>
Relation<type>::Relation(const Relation<type> &r)
{
	root = r.root;
	elements = r.elements;
}

template <typename type>
Relation<type>::~Relation()
{
	// both set and vector automatically destruct when no longer used so this isn't necessary
}

template <typename type>
int Relation<type>::cardinality()
{
	return elements.size();
}

template <typename type>
bool Relation<type>::add_element(Pair<type> p)
{
	if (is_member(p))
	{
		cout << "Pair already exists in relation" << endl;
		return false;
	}

	elements.push_back(p);
	return true;
}

template <typename type>
bool Relation<type>::remove_element(Pair<type> p)
{
	if (is_member(p))
	{
		for (auto it = elements.begin(); it != elements.end(); ++it)
		{
			if (it->first == p.first && it->second == p.second)
			{
				elements.erase(it);
				return true;
			}
		}
		return false;
	}
	else
	{
		cout << "Element does not exist in relation" << endl;
		return false;
	}
}

template <typename type>
bool Relation<type>::is_member(Pair<type> p)
{
	for (auto it = elements.begin(); it != elements.end(); ++it)
	{
		if (it->first == p.first && it->second == p.second)
		{
			return true;
		}
	}
	return false;
}

template <typename type>
bool Relation<type>::operator==(Relation<type> r)
{
	if ((root != r.root) || (cardinality() != r.cardinality()))
	{
		return false;
	}
	else
	{
		for (auto it = elements.begin(); it != elements.end(); ++it)
		{
			if (!r.is_member(*it))
				return false;
		}
	}
	return true;
}

template <typename type>
vector<type> &Relation<type>::operator[](int index)
{
	// couldn't figure out how to return list of elements, so pair ref is returned instead
	if ((index < 0) || (index > elements.size() - 1))
	{
		cout << "Invalid index!" << endl;
		Pair<type> p;
		return p;
	}
	else
	{
		return elements.at(index);
	}
}

template <typename type>
bool Relation<type>::reflexive()
{
	for (auto it = root.begin(); it != root.end(); ++it)
	{
		Pair<type> p;
		p.first = p.second = *it;
		if (!is_member(p))
			return false;
	}

	return true;
}

template <typename type>
bool Relation<type>::irreflexive()
{
	for (auto it = root.begin(); it != root.end(); ++it)
	{
		Pair<type> p;
		p.first = p.second = *it;
		if (is_member(p))
			return false;
	}

	return true;
}

template <typename type>
bool Relation<type>::symmetric()
{
	for (auto it = elements.begin(); it != elements.end(); ++it)
	{
		Pair<type> p;
		p.first = it->second;
		p.second = it->first;
		if (!is_member(p))
			return false;
	}

	return true;
}

template <typename type>
bool Relation<type>::asymmetric()
{
	for (auto it = elements.begin(); it != elements.end(); ++it)
	{
		if (it->first != it->second)
		{
			Pair<type> p;
			p.first = it->second;
			p.second = it->first;
			if (is_member(p))
				return false;
		}
	}

	return true;
}

template <typename type>
bool Relation<type>::transitive()
{
	int i, j;
	for (i = 0; i < cardinality(); ++i)
	{
		for (j = 0; j < cardinality(); ++j)
		{
			if (elements[i].second == elements[j].first)
			{
				Pair<type> p;
				p.first = elements[i].first;
				p.second = elements[j].second;
				if (!is_member(p))
					return false;
			}
		}
	}

	return true;
}

template <typename type>
bool Relation<type>::is_function()
{
	int i, j;
	for (i = 0; i < cardinality(); ++i)
	{
		for (j = 0; j < cardinality(); ++j)
		{
			if (elements[i].first == elements[j].first && elements[i].second != elements[j].second)
				return false;
		}
	}

	return true;
}

template <typename typex>
ostream &operator<<(ostream &out, Relation<typex> &r)
{
	out << "{";
	for (auto it = r.elements.begin(); it != r.elements.end(); ++it)
	{
		out << '(';
		out << it->first << ", " << it->second;
		out << ')';
		if (it->first != r.elements.back().first && it->second != r.elements.back().second)
			out << ", ";
	}
	out << "}";
	return out;
}

template <typename type>
Relation<type> Relation<type>::inverse()
{
	int i;
	Relation result;
	for (auto it = root.begin(); it != root.end(); ++it)
		result.add_to_set(*it);
	for (i = 0; i < cardinality(); ++i)
	{
		Pair<type> p;
		p.first = elements[i].second;
		p.second = elements[i].first;
		result.add_element(p);
	}

	return result;
}

template <typename type>
Relation<type> Relation<type>::combination(Relation r)
{
	int i, j;
	Relation result;
	for (auto it = root.begin(); it != root.end(); ++it)
		result.add_to_set(*it);
	for (i = 0; i < cardinality(); ++i)
	{
		for (j = 0; j < r.cardinality(); ++j)
		{
			if (elements[i].second == r.elements[j].first)
			{
				Pair<type> p;
				p.first = elements[i].first;
				p.second = r.elements[j].second;
				result.add_element(p);
			}
		}
	}

	return result;
}

// // HELPER FUNCTIONS
template <typename type>
void Relation<type>::add_to_set(type x)
{
	root.insert(x);
}
