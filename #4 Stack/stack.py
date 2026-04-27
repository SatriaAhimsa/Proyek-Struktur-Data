import math

def prec(c):
    if c == '^':
        return 3
    elif c == '/' or c == '*':
        return 2
    elif c == '+' or c == '-':
        return 1
    else:
        return -1

def isRightAssociative(c):
    return c == '^'

def infixToPostfix(s):
    st = []
    res = []
    i = 0

    while i < len(s):
        c = s[i]

        # Skip spaces
        if c == ' ':
            i += 1
            continue

        # If digit, accumulate full number
        if c.isdigit():
            num = ''
            while i < len(s) and s[i].isdigit():
                num += s[i]
                i += 1
            res.append(num)
            continue

        # If letter (variable), add to result
        elif c.isalpha():
            res.append(c)

        # If '(', push to stack
        elif c == '(':
            st.append('(')

        # If ')', pop until '('
        elif c == ')':
            while st and st[-1] != '(':
                res.append(st.pop())
            st.pop()

        # If operator
        else:
            while st and st[-1] != '(' and \
                (prec(st[-1]) > prec(c) or (prec(st[-1]) == prec(c) \
                                    and not isRightAssociative(c))):
                res.append(st.pop())
            st.append(c)

        i += 1

    # Pop remaining operators
    while st:
        res.append(st.pop())

    return res

def evaluatePostfix(arr):
    st = []

    for token in arr:
        
        # If it's an operand (number), push it onto the stack
        if token[0].isdigit() or (len(token) > 1 and token[0] == '-'):
            st.append(int(token))
            print(f"Push: {token} -> Stack: {st}")
        
        # Otherwise, it must be an operator
        else:
            val1 = st.pop()
            val2 = st.pop()

            if token == '+':
                print(f"Pop: {val1}, {val2} -> Operator: {token}")
                st.append(val2 + val1)
                print(f"{val2} + {val1} = {val2 + val1}")
                print(f"Push: {val2 + val1} -> Stack: {st}")
            elif token == '-':
                print(f"Pop: {val1}, {val2} -> Operator: {token}")
                st.append(val2 - val1)
                print(f"{val2} - {val1} = {val2 - val1}")
                print(f"Push: {val2 - val1} -> Stack: {st}")
            elif token == '*':
                print(f"Pop: {val1}, {val2} -> Operator: {token}")
                st.append(val2 * val1)
                print(f"{val2} * {val1} = {val2 * val1}")
                print(f"Push: {val2 * val1} -> Stack: {st}")
            elif token == '/':
                print(f"Pop: {val1}, {val2} -> Operator: {token}")
                st.append(val2 // val1)
                print(f"{val2} / {val1} = {val2 // val1}")
                print(f"Push: {val2 // val1} -> Stack: {st}")
            elif token == '^':
                print(f"Pop: {val1}, {val2} -> Operator: {token}")
                st.append(int(math.pow(val2, val1)))
                print(f"{val2} ^ {val1} = {int(math.pow(val2, val1))}")
                print(f"Push: {int(math.pow(val2, val1))} -> Stack: {st}")
    return st.pop()

if __name__ == '__main__':
    exp = input("Masukkan ekspresi infix: ")
    postfix = infixToPostfix(exp)
    print("Postfix:", ' '.join(postfix))

    print("perhitungan Postfix:")
    result = evaluatePostfix(postfix)
    print(f"Hasil: {result}")
