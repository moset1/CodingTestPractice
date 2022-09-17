N, M = map(int, input().split())

c = [ False for  i in range(N+1)]
a = [0 for i in range(M)]
def go(index, n, m):

    if index == m:
        for i in range(len(a)):
            print(a[i], end=' ')
        print()
        return
    else:
        for i in range(1,n+1):
            if c[i] == True or i < a[index-1]:
                continue
            else:
                c[i] = True
                a[index] = i
                go(index+1, n, m)
                a[index] = 0
                c[i] = False


go(0, N, M)



