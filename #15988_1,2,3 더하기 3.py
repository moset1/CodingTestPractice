T = int(input())
D = [0] *1000001
D[1] = 1
D[2] = 2
D[3] = 4
p = 4
for i in range(T):
    n = int(input())
    if D[n] != 0:
        print(D[n])
    else:
        for j in range(p, n+1):
            D[j] = (D[j-1] + D[j-2] + D[j-3]) %1000000009
        p = n
        print(D[n])    
        
