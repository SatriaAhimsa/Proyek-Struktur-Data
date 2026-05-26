import java.util.*;

public class graph {
    static final int MAX = 10;

    static String[] vertex = new String[MAX];
    static int jumlahVertex = 0;

    static int[][] matrix = new int[MAX][MAX];

    static void tambahVertex(String nama) {
        for (int i = 0; i < jumlahVertex; i++) {
            if (vertex[i].equals(nama)) {
                System.out.println("Vertex sudah ada");
                return;
            }
        }

        vertex[jumlahVertex] = nama;
        jumlahVertex++;

        System.out.println("Vertex berhasil ditambahkan");
    }

    static void tambahEdge(String v1, String v2) {
        int i = cariVertex(v1);
        int j = cariVertex(v2);

        if (i != -1 && j != -1) {
            matrix[i][j] = 1;
            matrix[j][i] = 1;

            System.out.println("Edge berhasil ditambahkan");
        } else {
            System.out.println("Vertex tidak ditemukan");
        }
    }

    static void hapusEdge(String v1, String v2) {
        int i = cariVertex(v1);
        int j = cariVertex(v2);

        if (i != -1 && j != -1) {
            matrix[i][j] = 0;
            matrix[j][i] = 0;

            System.out.println("Edge berhasil dihapus");
        }
    }

    static int cariVertex(String nama) {
        for (int i = 0; i < jumlahVertex; i++) {
            if (vertex[i].equals(nama)) {
                return i;
            }
        }
        return -1;
    }

    static void tampilkanGraph() {
        System.out.println("\nAdjacency Matrix:");

        System.out.print("  ");
        for (int i = 0; i < jumlahVertex; i++) {
            System.out.print(vertex[i] + " ");
        }
        System.out.println();

        for (int i = 0; i < jumlahVertex; i++) {
            System.out.print(vertex[i] + " ");

            for (int j = 0; j < jumlahVertex; j++) {
                System.out.print(matrix[i][j] + " ");
            }

            System.out.println();
        }
    }

    static void dfs(String start) {
        boolean[] visited = new boolean[MAX];

        System.out.print("DFS: ");
        dfsRekursif(cariVertex(start), visited);
        System.out.println();
    }

    static void dfsRekursif(int v, boolean[] visited) {
        visited[v] = true;

        System.out.print(vertex[v] + " ");

        for (int i = 0; i < jumlahVertex; i++) {
            if (matrix[v][i] == 1 && !visited[i]) {
                dfsRekursif(i, visited);
            }
        }
    }

    static void bfs(String start) {
        boolean[] visited = new boolean[MAX];

        Queue<Integer> queue = new LinkedList<>();

        int startIndex = cariVertex(start);

        visited[startIndex] = true;
        queue.add(startIndex);

        System.out.print("BFS: ");

        while (!queue.isEmpty()) {
            int v = queue.poll();

            System.out.print(vertex[v] + " ");

            for (int i = 0; i < jumlahVertex; i++) {
                if (matrix[v][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }

        System.out.println();
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("\nMENU");
            System.out.println("1. Tambah Vertex");
            System.out.println("2. Tambah Edge");
            System.out.println("3. Hapus Edge");
            System.out.println("4. Tampilkan Graph");
            System.out.println("5. DFS");
            System.out.println("6. BFS");
            System.out.println("7. Quit");

            System.out.print("Pilih: ");
            int pilih = input.nextInt();
            input.nextLine();

            if (pilih == 1) {
                System.out.print("Nama vertex: ");
                String v = input.nextLine();

                tambahVertex(v);

            } else if (pilih == 2) {
                System.out.print("Vertex 1: ");
                String v1 = input.nextLine();

                System.out.print("Vertex 2: ");
                String v2 = input.nextLine();

                tambahEdge(v1, v2);

            } else if (pilih == 3) {
                System.out.print("Vertex 1: ");
                String v1 = input.nextLine();

                System.out.print("Vertex 2: ");
                String v2 = input.nextLine();

                hapusEdge(v1, v2);

            } else if (pilih == 4) {
                tampilkanGraph();

            } else if (pilih == 5) {
                System.out.print("Mulai DFS dari: ");
                String start = input.nextLine();

                dfs(start);

            } else if (pilih == 6) {
                System.out.print("Mulai BFS dari: ");
                String start = input.nextLine();

                bfs(start);

            } else if (pilih == 7) {
                break;
            }
        }
    }
}