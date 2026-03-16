####################
## help functions ##
####################

def isHorn(phi):
    num_positive_literals = 0
    for literal in phi:
        separated_syntex = literal.split()
        if separated_syntex[0] != 'not':
            num_positive_literals += 1
        if num_positive_literals > 1:
            return False
    return True

def turnImplication(phi):
    premis = ()
    consequence = ''

    for literal in phi:
        separated_syntex = literal.split()
        if separated_syntex[0] == 'not':
            premis += (separated_syntex[1],)
        else:
            consequence = literal
    return (premis, consequence)
    
def isAllMarked(clause, marked_literals):
    for literal in clause:
        if literal not in marked_literals:
            return False
    return True

def removeCheckedClauses(clauses, marked_literals):
    items_to_remove = set()
    for item in clauses:
        if item[1] in marked_literals:
            items_to_remove.add(item)
    for item in items_to_remove:
        clauses.discard(item)        

###################
## main function ##
###################

def isHornSatisfiable(K_phi):
    derived_clauses = set()
    marked_literals = set()
    # is horn
    for phi in K_phi:
        if not isHorn(phi):
            return False
        
    # initialize
    for phi in K_phi:
        premises, consequence = turnImplication(phi)
        derived_clauses.add((premises, consequence))
    
    # unit propagation
    for premis, consequence in derived_clauses:
        if not premis:
            marked_literals.add(consequence)

    
    # resolution
    hasFound=True
    while hasFound:
        hasFound=False
        for premises, consequence in derived_clauses:
            if consequence and isAllMarked(premises, marked_literals):
                marked_literals.add(consequence)
                hasFound=True
        removeCheckedClauses(derived_clauses, marked_literals)

    # completion
    for premises, consequence in derived_clauses:
        if (not consequence) and isAllMarked(derived_clauses, marked_literals):
            return False
    return True

#################################################
#################################################
#################################################

phi_1 = {'not A2', 'not A4'}
phi_2 = {'not A4', 'not A2', 'not A5'}
phi_3 = {'not A2', 'not A3', 'A4', 'A5'}
phi_4 = {'not A5', 'not A4'}
phi_5 = {'A2'}

K_phi = [phi_1, phi_2, phi_3, phi_4, phi_5]


print(isHornSatisfiable(K_phi))