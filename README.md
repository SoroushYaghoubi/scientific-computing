# Discrete-Informatik
Here I will implement descrete mathematic concepts that are related to computer science. 

## Horn satisfiability (from Logik für Informatik 2023/24)
### This is the Markierungsstrategie
- The function ´isHornSatisfiable(K_Phi: set of clauses)´ takes a set of *Syntactic* clauses (as strings) and tells you if it is satisfiable or not, based on Horn Formulas.
- you can make a set of logical clauses like so:

```
phi_1 = {'not A2', 'not A4'}
phi_2 = {'not A4', 'not A2', 'not A5'}
phi_3 = {'not A2', 'not A3', 'A4', 'A5'}
phi_4 = {'not A5', 'not A4'}
phi_5 = {'A2'}

K_phi = [phi_1, phi_2, phi_3, phi_4, phi_5]
```

where each phi represents a clause where every literal is being Big-Or-ed together: ( (e.g.{'A', 'B', 'not C'} represents (A \or B (not \C))) and K_phi represents the conjunction of all those clauses.

**It's interesting to understand that this couldn't have been possible if we used boolean variables inside the clauses, because that would have been a Semantik representation of the literals, but that is not the case for this algorithm!** )

You can use the function directly :) `isHornSatisfiable(K_phi)`
