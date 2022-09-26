N = int(input())

T = [0 for i in range(N+1)]
P = [0 for i in range(N+1)]
a = 0
for i in range(1,N+1):
    T[i], P[i] = map(int, input().split())

def go(cost, d, n):
    global a
    if d > n+1:
        return
    if cost > a:
        a = cost
    if d < n+1:
        go(cost+P[d],d+T[d],n)
        go(cost,d+1, n)
    else:
        d+=1

go(0,1,N)
print(a)
