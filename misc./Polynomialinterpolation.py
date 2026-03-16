import numpy as np
import matplotlib.pyplot as plt


Punkte = [(-4, 11), (-3, -2), (-2, 23), (-1, -10), (0, 0),
          (4, -1), (3, 12), (2, -3), (1, 14)]

# data adjuster
Punkte = sorted(Punkte)
for i in range(len(Punkte)-1):
    if Punkte[i][0] == Punkte[i+1][0]:
        print(
            'Not graphable with a polynomial because it is not linkseindeutig!')


###################################################
# Precision degree 1
Px = np.poly1d([Punkte[0][1]])

# next degree precision
for k in range(1, len(Punkte)):
    # Rest(x)= Y_k+1 - Pk(X_k+1)
    Rx = np.poly1d([Punkte[k][1] - Px(Punkte[k][0])])

    # the product of all the linear factors:
    LinearFactor = np.poly1d([1])
    # Produkt_j|0->k ((X - X_j) / (Xk+1 - X_j))
    for j in range(0, k):
        LinearFactor *= np.poly1d([1, -Punkte[j][0]]) / \
            (Punkte[k][0] - Punkte[j][0])

    Rx *= LinearFactor

    Px = Px + Rx

###########################################################
# plotting:

x_values = np.linspace(-5, 5, 200)
y_values = Px(x_values)
plt.plot(x_values, y_values)
for a, b in Punkte:
    plt.plot(a, b, 'ro')
plt.xlabel('x')
plt.ylabel('y')
plt.title(Px)
plt.grid(True)
plt.show()