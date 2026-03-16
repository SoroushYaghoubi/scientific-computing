# beliebige Funktion
def f(x):
    return (x*x*x)


# ein Interval mit f(a).f(b) < 0
a = -100
b = 100

# wie präzis? (mit größere 'n's bekommt man falsche ergebnisse)
n = 12

for i in range(n):
    m = (a+b)/2

    if f(m) >= 0:
        b = m
    else:
        a = m

print((a+b)/2)