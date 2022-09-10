N = int(input())
A = list(map(int, input().split()))
D = [0] * N
E = [0] * N
D[0] = 1
E[0] = 1
for i in range(1,N):
    p=0
    for j in range(i):
        if A[i] > A[j] and p < D[j]:
            p = D[j]
    D[i] = p+1

A.reverse()
for i in range(1,N):
    p=0
    for j in range(i):
        if A[i] > A[j] and p < E[j]:
            p = E[j]
    E[i] = p+1
E.reverse()
G = [0]* N
for i in range(N):
    G[i] = D[i] + E[i]

print(max(G)-1)

