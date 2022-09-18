N, M = map(int, input().split())

a = [0 for i in range(M)]

def go(k, n, m):
    if k == m:
        for i in range(len(a)):
             print(a[i], end=' ')
        print()
        return
    
    else:
        for i in range(1,n+1):
            a[k] = i
            go(k+1, n, m)

go(0, N, M)
