MAX = 10

vertex = []
matrix = [[0] * MAX for _ in range(MAX)]


def tambah_vertex(nama):
    if nama not in vertex:
        vertex.append(nama)
        print("Vertex berhasil ditambahkan")
    else:
        print("Vertex sudah ada")


def hapus_vertex(nama):
    if nama in vertex:
        idx = vertex.index(nama)

        vertex.pop(idx)

        for i in range(MAX):
            matrix[idx][i] = 0
            matrix[i][idx] = 0

        print("Vertex berhasil dihapus")
    else:
        print("Vertex tidak ditemukan")


def tambah_edge(v1, v2):
    if v1 in vertex and v2 in vertex:
        i = vertex.index(v1)
        j = vertex.index(v2)

        matrix[i][j] = 1
        matrix[j][i] = 1

        print("Edge berhasil ditambahkan")
    else:
        print("Vertex tidak ditemukan")


def hapus_edge(v1, v2):
    if v1 in vertex and v2 in vertex:
        i = vertex.index(v1)
        j = vertex.index(v2)

        matrix[i][j] = 0
        matrix[j][i] = 0

        print("Edge berhasil dihapus")
    else:
        print("Vertex tidak ditemukan")


def tampilkan_graph():
    print("\nAdjacency Matrix:")

    print("  ", end="")
    for v in vertex:
        print(v, end=" ")
    print()

    for i in range(len(vertex)):
        print(vertex[i], end=" ")
        for j in range(len(vertex)):
            print(matrix[i][j], end=" ")
        print()


def dfs(start):
    visited = []

    def dfs_rekursif(v):
        visited.append(v)
        print(v, end=" ")

        idx = vertex.index(v)

        for i in range(len(vertex)):
            if matrix[idx][i] == 1 and vertex[i] not in visited:
                dfs_rekursif(vertex[i])

    dfs_rekursif(start)
    print()


def bfs(start):
    visited = []
    queue = []

    visited.append(start)
    queue.append(start)

    while queue:
        v = queue.pop(0)
        print(v, end=" ")

        idx = vertex.index(v)

        for i in range(len(vertex)):
            if matrix[idx][i] == 1 and vertex[i] not in visited:
                visited.append(vertex[i])
                queue.append(vertex[i])

    print()


while True:
    print("\nMENU")
    print("1. Tambah Vertex")
    print("2. Hapus Vertex")
    print("3. Tambah Edge")
    print("4. Hapus Edge")
    print("5. Tampilkan Graph")
    print("6. DFS")
    print("7. BFS")
    print("8. Quit")

    pilih = input("Pilih menu: ")

    if pilih == "1":
        v = input("Nama vertex: ")
        tambah_vertex(v)

    elif pilih == "2":
        v = input("Nama vertex: ")
        hapus_vertex(v)

    elif pilih == "3":
        v1 = input("Vertex 1: ")
        v2 = input("Vertex 2: ")
        tambah_edge(v1, v2)

    elif pilih == "4":
        v1 = input("Vertex 1: ")
        v2 = input("Vertex 2: ")
        hapus_edge(v1, v2)

    elif pilih == "5":
        tampilkan_graph()

    elif pilih == "6":
        start = input("Mulai DFS dari: ")
        dfs(start)

    elif pilih == "7":
        start = input("Mulai BFS dari: ")
        bfs(start)

    elif pilih == "8":
        break

    else:
        print("Pilihan tidak valid")
