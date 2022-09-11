E,S,M = map(int,input().split())
e=0
s = 0
m = 0
y=0
while(e!=E or s!=S or m!= M):
    if e <15:
        e+=1
    else:
        e =1
    if s<28:
        s+=1
    else:
        s=1
    if m<19:
        m+=1
    else:
        m=1
    y+=1
print(y)
