D = [0] * 9

for i in range(9):
    D[i] = int(input())

s = 0

for i in range(8):
    for j in range(i+1, 9):
        S = D[:]
        S.pop(j)
        S.pop(i)
        s = sum(S)
        if s == 100:
            break
    if s == 100:
        break

S.sort()
for i in S:
    print(i)
