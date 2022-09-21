n = int(input())

c = list(map(int, input().split()))

a = [0 for i in range(n)]
b = [False for i in range(n)]
ans = 0
sum = 0

def go(index, x):
    global sum
    global ans
    if index== x:
        for i in range(x-1):
            sum += abs(a[i]-a[i+1])
        if sum > ans:
            ans= sum
        sum = 0
        return
    for i in range(x):
        if b[i] == True:
            continue
        a[index] = c[i]
        b[i] = True
        go(index+1, x)
        b[i] = False


go(0, n)
print(ans)
