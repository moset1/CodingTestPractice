import sys
n = int(input())
s = list(input())
st = list()
l = dict()
for i in s:
    if i == '+':
        a = st[-1]
        st.pop()
        b = st[-1]
        st.pop()
        st.append(a+b)
    elif i == '*':
        a = st[-1]
        st.pop()
        b = st[-1]
        st.pop()
        st.append(a*b)
    elif i == '/':
        a = st[-1]
        st.pop()
        b = st[-1]
        st.pop()
        st.append(b/a)
    elif i == '-':
        a = st[-1]
        st.pop()
        b = st[-1]
        st.pop()
        st.append(b-a)
    else:
        if i not in l:
            m = int(input())
            l[i] = m
       
        st.append(l[i])


print("{:.2f}".format(st[-1]))
