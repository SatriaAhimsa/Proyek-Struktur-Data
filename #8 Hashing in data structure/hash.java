import java.util.*;

public class hash {

    // Ukuran hash table
    static final int TABLE_SIZE = 20;

    // Membuat hash table menggunakan ArrayList
    static LinkedList<Integer>[] hashTable = new LinkedList[TABLE_SIZE];

    // Fungsi hash
    static int hashFunction(int key) {
        return key % TABLE_SIZE;
    }

    // INSERT DATA
    static void insertData(int data) {
        int index = hashFunction(data);

        if (!hashTable[index].contains(data)) {
            hashTable[index].add(data);
            System.out.println("Data " + data + " berhasil ditambahkan.");
        } else {
            System.out.println("Data sudah ada.");
        }
    }

    // HAPUS DATA
    static void deleteData(int data) {
        int index = hashFunction(data);

        if (hashTable[index].contains(data)) {
            hashTable[index].remove(Integer.valueOf(data));
            System.out.println("Data " + data + " berhasil dihapus.");
        } else {
            System.out.println("Data tidak ditemukan.");
        }
    }

    // CARI DATA
    static void searchData(int data) {
        int index = hashFunction(data);

        if (hashTable[index].contains(data)) {
            System.out.println("Data " + data + " ditemukan pada index " + index);
        } else {
            System.out.println("Data tidak ditemukan.");
        }
    }

    // TAMPILKAN HASH TABLE
    static void displayTable() {
        System.out.println("\n=== HASH TABLE ===");

        for (int i = 0; i < TABLE_SIZE; i++) {
            System.out.print("Index " + i + ": ");

            for (Integer data : hashTable[i]) {
                System.out.print(data + " ");
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {

        // Inisialisasi setiap index
        for (int i = 0; i < TABLE_SIZE; i++) {
            hashTable[i] = new LinkedList<>();
        }

        // Generate 100 angka random unik
        Random random = new Random();
        HashSet<Integer> uniqueNumbers = new HashSet<>();

        while (uniqueNumbers.size() < 100) {
            int number = random.nextInt(1000) + 1;
            uniqueNumbers.add(number);
        }

        // Masukkan ke hash table
        for (int number : uniqueNumbers) {
            insertData(number);
        }

        System.out.println("\n100 data random berhasil dimasukkan.");

        // Menu program
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== MENU HASH TABLE =====");
            System.out.println("1. Input Data");
            System.out.println("2. Hapus Data");
            System.out.println("3. Cari Data");
            System.out.println("4. Tampilkan Hash Table");
            System.out.println("5. Keluar");

            System.out.print("Pilih menu: ");
            int choice = input.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Masukkan angka: ");
                    int insert = input.nextInt();
                    insertData(insert);
                    break;

                case 2:
                    System.out.print("Masukkan angka yang ingin dihapus: ");
                    int delete = input.nextInt();
                    deleteData(delete);
                    break;

                case 3:
                    System.out.print("Masukkan angka yang ingin dicari: ");
                    int search = input.nextInt();
                    searchData(search);
                    break;

                case 4:
                    displayTable();
                    break;

                case 5:
                    System.out.println("Program selesai.");
                    System.exit(0);

                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }
}