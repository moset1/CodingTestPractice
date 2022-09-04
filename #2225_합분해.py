N, M = map(int, input().split())
D = [[0 for col in range(M+1)] for row in range(N+1)]

for i in range(1, M+1):
    D[0][i] = 1
for i in range(1,N+1):
    D[i][1] = 1
for j in range(2, M+1):
    for i in range(1,N+1):
        D[i][j] = D[i-1][j] + D[i][j-1]
print(D[N][M]%1000000000)
