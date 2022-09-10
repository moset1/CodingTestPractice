N = int(input())
D = [0] * 31
D[0] = 1
D[1] = 0
D[2] = 3

for i in range(3,N+1):
    D[i] = D[i-2] * 3
    for j in range(4, i+1, 2):
        D[i] += D[i-j] * 2

print(D[N])
