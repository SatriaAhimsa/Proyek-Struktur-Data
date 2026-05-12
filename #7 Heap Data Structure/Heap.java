import java.util.Scanner;

class Data {

    int id;
    String nama;

    public Data(int id, String nama) {
        this.id = id;
        this.nama = nama;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Nama: " + nama;
    }
}


// MIN HEAP
class Min_Heap {

    Data[] heap = new Data[200];
    int size = 0;

    // INSERT
    public void insert(Data data) {

        heap[size] = data;

        heapifyUp(size);

        size++;
    }

    // HEAPIFY UP
    public void heapifyUp(int index) {

        while (index > 0) {

            int parent = (index - 1) / 2;

            // child < parent
            if (heap[index].id < heap[parent].id) {

                swap(index, parent);

                index = parent;

            } else {
                break;
            }
        }
    }

    // HEAPIFY DOWN
    public void heapifyDown(int index) {

        while (true) {

            int smallest = index;

            int left = 2 * index + 1;
            int right = 2 * index + 2;

            // cek left child
            if (
                left < size &&
                heap[left].id < heap[smallest].id
            ) {
                smallest = left;
            }

            // cek right child
            if (
                right < size &&
                heap[right].id < heap[smallest].id
            ) {
                smallest = right;
            }

            // tukar
            if (smallest != index) {

                swap(index, smallest);

                index = smallest;

            } else {
                break;
            }
        }
    }

    // DELETE MIN
    public void deleteMin() {

        if (size == 0) {
            System.out.println("Min Heap kosong");
            return;
        }

        Data root = heap[0];

        heap[0] = heap[size - 1];

        size--;

        heapifyDown(0);

        System.out.println("\nData berhasil dihapus dari Min Heap");
        System.out.println(root);
    }

    // DISPLAY ASCENDING
    public void display() {

        if (size == 0) {
            System.out.println("Min Heap kosong");
            return;
        }

        // backup heap
        Data[] backup = new Data[200];
        int backupSize = size;

        for (int i = 0; i < size; i++) {
            backup[i] = heap[i];
        }

        System.out.println("\n===== DATA ASCENDING =====");

        while (size > 0) {

            Data root = heap[0];

            System.out.println(root);

            heap[0] = heap[size - 1];

            size--;

            heapifyDown(0);
        }

        // restore heap
        for (int i = 0; i < backupSize; i++) {
            heap[i] = backup[i];
        }

        size = backupSize;
    }

    // SWAP
    public void swap(int a, int b) {

        Data temp = heap[a];
        heap[a] = heap[b];
        heap[b] = temp;
    }
}

// MAX HEAP
class Max_Heap {

    Data[] heap = new Data[200];
    int size = 0;

    // INSERT
    public void insert(Data data) {

        heap[size] = data;

        heapifyUp(size);

        size++;
    }

    // HEAPIFY UP
    public void heapifyUp(int index) {

        while (index > 0) {

            int parent = (index - 1) / 2;

            // child > parent
            if (heap[index].id > heap[parent].id) {

                swap(index, parent);

                index = parent;

            } else {
                break;
            }
        }
    }

    // HEAPIFY DOWN
    public void heapifyDown(int index) {

        while (true) {

            int largest = index;

            int left = 2 * index + 1;
            int right = 2 * index + 2;

            // cek left child
            if (
                left < size &&
                heap[left].id > heap[largest].id
            ) {
                largest = left;
            }

            // cek right child
            if (
                right < size &&
                heap[right].id > heap[largest].id
            ) {
                largest = right;
            }

            // tukar
            if (largest != index) {

                swap(index, largest);

                index = largest;

            } else {
                break;
            }
        }
    }

    // DELETE MAX
    public void deleteMax() {

        if (size == 0) {
            System.out.println("Max Heap kosong");
            return;
        }

        Data root = heap[0];

        heap[0] = heap[size - 1];

        size--;

        heapifyDown(0);

        System.out.println("\nData berhasil dihapus dari Max Heap");
        System.out.println(root);
    }

    // DISPLAY DESCENDING
    public void display() {

        if (size == 0) {
            System.out.println("Max Heap kosong");
            return;
        }

        // backup heap
        Data[] backup = new Data[200];
        int backupSize = size;

        for (int i = 0; i < size; i++) {
            backup[i] = heap[i];
        }

        System.out.println("\n===== DATA DESCENDING =====");

        while (size > 0) {

            Data root = heap[0];

            System.out.println(root);

            heap[0] = heap[size - 1];

            size--;

            heapifyDown(0);
        }

        // restore heap
        for (int i = 0; i < backupSize; i++) {
            heap[i] = backup[i];
        }

        size = backupSize;
    }

    // SWAP
    public void swap(int a, int b) {

        Data temp = heap[a];
        heap[a] = heap[b];
        heap[b] = temp;
    }
}


public class Heap {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        Min_Heap minHeap = new Min_Heap();
        Max_Heap maxHeap = new Max_Heap();

        // DATA AWAL
        Object[][] dataAwal = {
            {5288, "pensil"},
            {5993, "pulpen"},
            {8689, "penghapus"},
            {8043, "buku"},
            {8699, "sampul"},
            {2156, "penggaris"},
            {4457, "kertas"},
            {8938, "cat"},
            {2618, "stabilo"},
            {9033, "mobil"},

            {9971, "motor"},
            {3874, "becak"},
            {5914, "sepeda"},
            {2398, "kereta"},
            {3725, "pesawat"},
            {5210, "perahu"},
            {7363, "kapal"},
            {7631, "rakit"},
            {4513, "kipas"},
            {5656, "charger"},

            {6453, "peci"},
            {8783, "sarung"},
            {8194, "sajadah"},
            {9783, "smartphone"},
            {3685, "jam"},
            {4490, "televisi"},
            {8294, "laptop"},
            {8563, "komputer"},
            {1070, "mouse"},
            {5408, "keyboard"},

            {8258, "tablet"},
            {9309, "jendela"},
            {1138, "kaca"},
            {2751, "pintu"},
            {3258, "kompor"},
            {6402, "lemari"},
            {7921, "kasur"},
            {9781, "ranjang"},
            {3818, "bantal"},
            {5204, "baju"},

            {6119, "kaos"},
            {1928, "celana"},
            {4207, "mukena"},
            {7255, "jilbab"},
            {5309, "pigura"},
            {2897, "antena"},
            {8028, "kulkas"},
            {1660, "dispenser"},
            {3248, "meja"},
            {5641, "kursi"},

            {7376, "kemoceng"},
            {3525, "sapu"},
            {4492, "gayung"},
            {7187, "sabun"},
            {1305, "sikat"},
            {6602, "shampo"},
            {8153, "botol"},
            {3561, "gelas"},
            {5082, "piring"},
            {7151, "panci"},

            {7524, "wajan"},
            {9178, "blender"},
            {9817, "galon"},
            {4304, "cobek"},
            {6820, "termos"},
            {9151, "kran"},
            {3482, "selang"},
            {3316, "karpet"},
            {5192, "tikar"},
            {7572, "keset"},

            {7660, "sepatu"},
            {9224, "kaos kaki"},
            {5083, "jaket"},
            {6362, "piama"},
            {6465, "piano"},
            {9888, "gitar"},
            {4159, "angklung"},
            {4969, "suling"},
            {5097, "toples"},
            {6271, "parfum"},

            {9250, "sisir"},
            {3409, "topi"},
            {4577, "gunting"},
            {6244, "pisau"},
            {8612, "kaleng"},
            {4650, "tisu"},
            {6799, "tas"},
            {9298, "ikat pinggang"},
            {4361, "korek api"},
            {4379, "kopi"},
            
            {6928, "gula"},
            {3195, "cabai"},
            {5741, "wortel"},
            {6852, "timun"},
            {8147, "apel"},
            {8902, "jeruk"},
            {8967, "tomat"},
            {1302, "pisang"},
            {2363, "pepaya"},
            {6861, "bawang"}
        };

        // MASUKKAN KE HEAP
        for (Object[] data : dataAwal) {

            int id = (int) data[0];
            String nama = (String) data[1];

            Data item = new Data(id, nama);

            minHeap.insert(item);
            maxHeap.insert(item);
        }

        // MENU PROGRAM
        while (true) {

            System.out.println("\n=================================");
            System.out.println(" PROGRAM STRUKTUR DATA HEAP");
            System.out.println("=================================");
            System.out.println("1. Tambah Data");
            System.out.println("2. Tampilkan Ascending");
            System.out.println("3. Tampilkan Descending");
            System.out.println("4. Hapus Min Heap");
            System.out.println("5. Hapus Max Heap");
            System.out.println("6. Keluar");
            System.out.println("=================================");

            System.out.print("Pilih menu : ");
            int pilih = input.nextInt();

            // TAMBAH DATA
            if (pilih == 1) {

                System.out.print("Masukkan ID   : ");
                int id = input.nextInt();

                input.nextLine();

                System.out.print("Masukkan Nama : ");
                String nama = input.nextLine();

                Data dataBaru = new Data(id, nama);

                minHeap.insert(dataBaru);
                maxHeap.insert(dataBaru);

                System.out.println("\nData berhasil ditambahkan");
            }

            // ASCENDING
            else if (pilih == 2) {

                minHeap.display();
            }

            // DESCENDING
            else if (pilih == 3) {

                maxHeap.display();
            }

            // DELETE MIN HEAP
            else if (pilih == 4) {

                minHeap.deleteMin();
            }

            // DELETE MAX HEAP
            else if (pilih == 5) {

                maxHeap.deleteMax();
            }

            // KELUAR
            else if (pilih == 6) {

                System.out.println("\nProgram selesai");
                break;
            }

            else {
                System.out.println("\nPilihan tidak valid");
            }
        }
    
        input.close();
    }
}
