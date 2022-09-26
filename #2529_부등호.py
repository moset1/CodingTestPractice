
n = int(input())
c = [False for i in range(10)]
d = [0] + list(map(str, input().split()))
a=[]
def go(index, ans):

    if len(ans) >= 2:
        if d[index-1] == '<':
            if int(ans[index-2]) > int(ans[index-1]):
                return
            
        elif d[index-1] == '>':
            if int(ans[index-2]) < int(ans[index-1]):
                return

    if len(ans) == n+1:
        a.append(ans)
        return

    for i in range(10):
        if c[i] == True:
            continue
        c[i] = True
        go(index+1, ans+str(i))
        c[i] = False

go(0,'')
a.sort()
print(a[-1])
print(a[0])
