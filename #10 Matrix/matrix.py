def print_matrix(matrix):
    for row in matrix:
        print(*row)
    print()

def sort_row_wise(matrix):
    for row in matrix:
        row.sort()

def sort_column_wise(matrix):
    rows = len(matrix)
    cols = len(matrix[0])

    for col in range(cols):
        temp = []

        for row in range(rows):
            temp.append(matrix[row][col])

        temp.sort()

        for row in range(rows):
            matrix[row][col] = temp[row]

def rotate_clockwise_by_one(matrix):
    rows = len(matrix)
    cols = len(matrix[0])

    top = 0
    bottom = rows - 1
    left = 0
    right = cols - 1

    prev = matrix[top + 1][left]

    for i in range(left, right + 1):
        matrix[top][i], prev = prev, matrix[top][i]
    top += 1

    for i in range(top, bottom + 1):
        matrix[i][right], prev = prev, matrix[i][right]
    right -= 1

    for i in range(right, left - 1, -1):
        matrix[bottom][i], prev = prev, matrix[bottom][i]
    bottom -= 1

    for i in range(bottom, top - 1, -1):
        matrix[i][left], prev = prev, matrix[i][left]

def rotate_counter_clockwise_by_one(matrix):
    rows = len(matrix)
    cols = len(matrix[0])

    top = 0
    bottom = rows - 1
    left = 0
    right = cols - 1

    prev = matrix[top][left + 1]

    for i in range(top, bottom + 1):
        matrix[i][left], prev = prev, matrix[i][left]
    left += 1

    for i in range(left, right + 1):
        matrix[bottom][i], prev = prev, matrix[bottom][i]
    bottom -= 1

    for i in range(bottom, top - 1, -1):
        matrix[i][right], prev = prev, matrix[i][right]
    right -= 1

    for i in range(right, left - 1, -1):
        matrix[top][i], prev = prev, matrix[top][i]

def rotate_90(matrix):
    return [list(row) for row in zip(*matrix[::-1])]

def rotate_180(matrix):
    return [row[::-1] for row in matrix[::-1]]

def row_wise_traversal(matrix):
    for row in matrix:
        for val in row:
            print(val, end=" ")
    print()

def column_wise_traversal(matrix):
    rows = len(matrix)
    cols = len(matrix[0])

    for col in range(cols):
        for row in range(rows):
            print(matrix[row][col], end=" ")
    print()

def spiral_print(matrix):
    top = 0
    bottom = len(matrix) - 1
    left = 0
    right = len(matrix[0]) - 1

    while top <= bottom and left <= right:

        for i in range(left, right + 1):
            print(matrix[top][i], end=" ")
        top += 1

        for i in range(top, bottom + 1):
            print(matrix[i][right], end=" ")
        right -= 1

        if top <= bottom:
            for i in range(right, left - 1, -1):
                print(matrix[bottom][i], end=" ")
            bottom -= 1

        if left <= right:
            for i in range(bottom, top - 1, -1):
                print(matrix[i][left], end=" ")
            left += 1

    print()

def transpose(matrix):
    return [list(row) for row in zip(*matrix)]

rows = int(input("Jumlah baris: "))
cols = int(input("Jumlah kolom: "))

matrix = []

print("Input matrix:")
for i in range(rows):
    row = list(map(int, input().split()))
    matrix.append(row)

while True:
    print("\nMENU")
    print("1-a. Sort row-wise")
    print("1-b. Sort column-wise")
    print("2-a. Rotate clockwise by 1")
    print("2-b. Rotate counter-clockwise by 1")
    print("2-c. Rotate 90")
    print("2-d. Rotate 180")
    print("3-a. Row-wise traversal")
    print("3-b. Column-wise traversal")
    print("4. Spiral form")
    print("5. Transpose")
    print("6. Quit")

    choice = input("Pilih menu: ")

    if choice == "1a":
        sort_row_wise(matrix)
        print_matrix(matrix)

    elif choice == "1b":
        sort_column_wise(matrix)
        print_matrix(matrix)

    elif choice == "2a":
        rotate_clockwise_by_one(matrix)
        print_matrix(matrix)

    elif choice == "2b":
        rotate_counter_clockwise_by_one(matrix)
        print_matrix(matrix)

    elif choice == "2c":
        matrix = rotate_90(matrix)
        print_matrix(matrix)

    elif choice == "2d":
        matrix = rotate_180(matrix)
        print_matrix(matrix)

    elif choice == "3a":
        row_wise_traversal(matrix)

    elif choice == "3b":
        column_wise_traversal(matrix)

    elif choice == "4":
        spiral_print(matrix)

    elif choice == "5":
        matrix = transpose(matrix)
        print_matrix(matrix)

    elif choice == "6":
        print("Program selesai")
        break

    else:
        print("Pilihan tidak valid")
