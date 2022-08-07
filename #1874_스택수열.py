s = []
a = []
n = int(input())
k=1

for _ in range(n):
    m = int(input())
    if(len(s)==0 or m > s[-1]):
        while(m>=k):
            s.append(k)
            a.append('+')
            k+=1
    
    if ( m == s[-1]):
        s.pop()
        a.append('-')
    else:
        print("NO")
        break

if len(s) == 0:
    for i in range(len(a)):
        print(a[i])
    

// 시간복잡도 : 
