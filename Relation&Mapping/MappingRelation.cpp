// Serena Iyoha 300187757
// Shkula Wardak 300065411

#include "MappingRelation.h"
#include <iterator>
#include "Relation.h"
#include <algorithm>

template <typename type1, typename type2>
MappingRelation<type1, type2>::MappingRelation()
{
}

template <typename type1, typename type2>
MappingRelation<type1, type2>::MappingRelation(const MappingRelation<type1, type2> &ref)
{
    root = ref.root;
}

template <typename type1, typename type2>
MappingRelation<type1, type2>::~MappingRelation()
{
}

template <typename type1, typename type2>
int MappingRelation<type1, type2>::cardinality()
{
    return root.size();
}

template <typename type1, typename type2>
bool MappingRelation<type1, type2>::add_element(type1 index, type2 definition)
{
    Tuple<type1, type2> p;
    p.first = index;
    p.second = definition;

    if (is_member(index, definition))
    {
        cout << "Tuple already exists in relation" << endl;
        return false;
    }

    root.push_back(p);
    return true;
}

template <typename type1, typename type2>
void MappingRelation<type1, type2>::remove_element(type1 index, type2 definition)
{
    if (is_member(index, definition))
    {
        for (auto it = root.begin(); it != root.end(); ++it)
        {
            if (it->first == index && it->second == definition)
            {
                root.erase(it);
                break;
            }
        }
    }
    else
    {
        cout << "Element does not exist in relation" << endl;
    }
}

template <typename type1, typename type2>
bool MappingRelation<type1, type2>::is_member(type1 index, type2 definition)
{
    for (auto it = root.begin(); it != root.end(); ++it)
    {
        if ((index == it->first) && (definition == it->second))
            return true;
    }
    return false;
}

template <typename type1, typename type2>
bool MappingRelation<type1, type2>::operator==(MappingRelation<type1, type2> ref)
{
    if (cardinality() != ref.cardinality())
    {
        return false;
    }
    else
    {
        for (auto it = root.begin(); it != root.end(); ++it)
        {
            if (!ref.is_member(it->first, it->second))
                return false;
        }
    }
    return true;
}

template <typename type1, typename type2>
MappingRelation<type1, type2> MappingRelation<type1, type2>::operator+(MappingRelation<type1, type2> ref)
{
    MappingRelation<type1, type2> temp;
    for (auto it = root.begin(); it != root.end(); ++it)
        temp.add_element(it->first, it->second);
    for (auto it = ref.root.begin(); it != ref.root.end(); ++it)
        temp.add_element(it->first, it->second);
    return temp;
}

template <typename type1, typename type2>
MappingRelation<type1, type2> MappingRelation<type1, type2>::operator-(MappingRelation<type1, type2> ref)
{
    MappingRelation<type1, type2> temp;
    for (auto it = root.begin(); it != root.end(); ++it)
        temp.add_element(it->first, it->second);
    for (auto it = root.begin(); it != root.end(); ++it)
    {
        if (ref.is_member(it->first, it->second))
            temp.remove_element(it->first, it->second);
    }

    return temp;
}

template <typename type1, typename type2>
MappingRelation<type1, type2> MappingRelation<type1, type2>::intersection(MappingRelation<type1, type2> ref)
{
    MappingRelation<type1, type2> temp;
    for (auto it = root.begin(); it != root.end(); ++it)
    {
        if (ref.is_member(it->first, it->second))
            temp.add_element(it->first, it->second);
    }

    return temp;
}

template <typename type1, typename type2>
vector<type2> MappingRelation<type1, type2>::operator[](type1 index)
{
    vector<type2> kate;
    for (auto it = root.begin(); it != root.end(); it++)
    {
        if (it->first == index)
        {
            kate.insert(kate.begin(), it->second);
        }
    }

    return kate;
}

template <typename type1, typename type2>
bool MappingRelation<type1, type2>::is_function()
{
    int i, j;
    for (i = 0; i < cardinality(); ++i)
    {
        for (j = 0; j < cardinality(); ++j)
        {
            if (root[i].first == root[j].first && root[i].second != root[j].second)
                return false;
        }
    }

    return true;
}

template <typename type1, typename type2>
MappingRelation<type1, type2> MappingRelation<type1, type2>::inverse()
{
    int i;
    MappingRelation result;
    for (i = 0; i < cardinality(); ++i)
        result.add_element(root[i].second, root[i].first);

    return result;
}

template <typename typex, typename typey>
ostream &operator<<(ostream &out, MappingRelation<typex, typey> &r)
{
    out << "{";
    for (auto it = r.root.begin(); it != r.root.end(); ++it)
    {
        out << '(';
        out << it->first << ", " << it->second;
        out << ')';
        if (it->first != r.root.back().first && it->second != r.root.back().second)
            out << ", ";
    }
    out << "}";
    return out;
}