class Data:
    def __init__(self, id, nama):
        self.id = id
        self.nama = nama

    def __str__(self):
        return f"ID: {self.id:<5} | Nama: {self.nama}"
    
# Min Heap
class MinHeap:
    def __init__(self):
        self.heap = []

    # Insert
    def insert(self, data):
        self.heap.append(data)
        self.heapify_up(len(self.heap)-1)

    # heapify up
    def heapify_up(self, index):
        while index > 0:
            parent = (index - 1) // 2

            # jika anak < dari parent
            if self.heap[index].id < self.heap[parent].id:
                #maka tukar
                self.heap[index], self.heap[parent] = (
                    self.heap[parent], 
                    self.heap[index]
                )
                # dan pindah ke parent
                index = parent
            else:
                break
    
    #heapify down
    def heapify_down(self, index):
        size = len(self.heap)
        while True:
            smallest = index
            left = 2 * index + 1
            right = 2 * index + 2

            # cek left child
            if(
                left < size and
                self.heap[left].id < self.heap[smallest].id 
            ):
                smallest = left

            # cek right child
            if(
                right < size and
                self.heap[right].id < self.heap[smallest].id 
            ):
                smallest = right

            # tukar jika smallest bukan index
            if smallest != index: 
                self.heap[index], self.heap[smallest] = (
                    self.heap[smallest], 
                    self.heap[index]
                )
                index = smallest

            else:
                break

    # delete min heap
    def delete_min(self):
        if len(self.heap) == 0:
            print("Min Heap kosong")
            return

        root = self.heap[0]

        # ambil node terakhir
        last = self.heap.pop()

        # jika masih ada data
        if len(self.heap) > 0:

            self.heap[0] = last

            # perbaiki heap
            self.heapify_down(0)

        print("\nData berhasil dihapus dari Min Heap")
        print(root)

    # display ascending
    def display(self):
        if len(self.heap) == 0:
            print("Min Heap kosong")
            return
        
        # backup heap
        backup = self.heap.copy()

        hasil = []

        while len(self.heap) > 0:
            root = self.heap[0]
            hasil.append(root)
            last = self.heap.pop()

            if len(self.heap) > 0:
                self.heap[0] = last
                self.heapify_down(0)

        print("\n=-= DATA ASCENDING =-=")
        for data in hasil:
            print(data)
        # restore heap
        self.heap = backup
    
# Max Heap
class MaxHeap:
    def __init__(self):
        self.heap = []

    # Insert
    def insert(self, data):
        self.heap.append(data)
        self.heapify_up(len(self.heap)-1)

    # heapify up
    def heapify_up(self, index):
        while index > 0:
            parent = (index - 1) // 2

            # jika anak > dari parent
            if self.heap[index].id > self.heap[parent].id:
                #maka tukar
                self.heap[index], self.heap[parent] = (
                    self.heap[parent], 
                    self.heap[index]
                )
                # dan pindah ke parent
                index = parent
            else:
                break

    #heapify down
    def heapify_down(self, index):
        size = len(self.heap)
        while True:
            largest = index
            left = 2 * index + 1
            right = 2 * index + 2

            # cek left child
            if(
                left < size and
                self.heap[left].id > self.heap[largest].id 
            ):
                largest = left

            # cek right child
            if(
                right < size and
                self.heap[right].id > self.heap[largest].id 
            ):
                largest = right

            # tukar jika largest bukan index
            if largest != index: 
                self.heap[index], self.heap[largest] = (
                    self.heap[largest], 
                    self.heap[index]
                )
                index = largest

            else:
                break

    # delete max heap
    def delete_max(self):
        if len(self.heap) == 0:
            print("Max Heap kosong")
            return

        root = self.heap[0]

        # ambil node terakhir
        last = self.heap.pop()

        # jika masih ada data
        if len(self.heap) > 0:

            self.heap[0] = last

            # perbaiki heap
            self.heapify_down(0)

        print("\nData berhasil dihapus dari Max Heap")
        print(root)

    # display descending
    def display(self):
        if len(self.heap) == 0:
            print("Max Heap kosong")
            return
        
        # backup heap
        backup = self.heap.copy()

        hasil = []

        while len(self.heap) > 0:
            root = self.heap[0]
            hasil.append(root)
            last = self.heap.pop()

            if len(self.heap) > 0:
                self.heap[0] = last
                self.heapify_down(0)

        print("\n=-= DATA DESCENDING =-=")
        for data in hasil:
            print(data)
        # restore heap
        self.heap = backup

min_heap = MinHeap()
max_heap = MaxHeap()

data_awal = [
    (5288, 'pensil'),
    (5993, 'pulpen'),
    (8689, 'penghapus'),
    (8043, 'buku'),
    (8699, 'sampul'),
    (2156, 'penggaris'),
    (4457, 'kertas'),
    (8938, 'cat'),
    (2618, 'stabilo'),
    (9033, 'mobil'),

    (9971, 'motor'),
    (3874, 'becak'),
    (5914, 'sepeda'),
    (2398, 'kereta'),
    (3725, 'pesawat'),
    (5210, 'perahu'),
    (7363, 'kapal'),
    (7631, 'rakit'),
    (4513, 'kipas'),
    (5656, 'charger'),

    (6453, 'peci'),
    (8783, 'sarung'),
    (8194, 'sajadah'),
    (9783, 'smartphone'),
    (3685, 'jam'),
    (4490, 'televisi'),
    (8294, 'laptop'),
    (8563, 'komputer'),
    (1070, 'mouse'),
    (5408, 'keyboard'),

    (8258, 'tablet'),
    (9309, 'jendela'),
    (1138, 'kaca'),
    (2751, 'pintu'),
    (3258, 'kompor'),
    (6402, 'lemari'),
    (7921, 'kasur'),
    (9781, 'ranjang'),
    (3818, 'bantal'),
    (5204, 'baju'),

    (6119, 'kaos'),
    (1928, 'celana'),
    (4207, 'mukena'),
    (7255, 'jilbab'),
    (5309, 'pigura'),
    (2897, 'antena'),
    (8028, 'kulkas'),
    (1660, 'dispenser'),
    (3248, 'meja'),
    (5641, 'kursi'),

    (7376, 'kemoceng'),
    (3525, 'sapu'),
    (4492, 'gayung'),
    (7187, 'sabun'),
    (1305, 'sikat'),
    (6602, 'shampo'),
    (8153, 'botol'),
    (3561, 'gelas'),
    (5082, 'piring'),
    (7151, 'panci'),

    (7524, 'wajan'),
    (9178, 'blender'),
    (9817, 'galon'),
    (4304, 'cobek'),
    (6820, 'termos'),
    (9151, 'kran'),
    (3482, 'selang'),
    (3316, 'karpet'),
    (5192, 'tikar'),
    (7572, 'keset'),

    (7660, 'sepatu'),
    (9224, 'kaos kaki'),
    (5083, 'jaket'),
    (6362, 'piama'),
    (6465, 'piano'),
    (9888, 'gitar'),
    (4159, 'angklung'),
    (4969, 'suling'),
    (5097, 'toples'),
    (6271, 'parfum'),

    (9250, 'sisir'),
    (3409, 'topi'),
    (4577, 'gunting'),
    (6244, 'pisau'),
    (8612, 'kaleng'),
    (4650, 'tisu'),
    (6799, 'tas'),
    (9298, 'ikat pinggang'),
    (4361, 'korek api'),
    (4379, 'kopi'),
    
    (6928, 'gula'),
    (3195, 'cabai'),
    (5741, 'wortel'),
    (6852, 'timun'),
    (8147, 'apel'),
    (8902, 'jeruk'),
    (8967, 'tomat'),
    (1302, 'pisang'),
    (2363, 'pepaya'),
    (6861, 'bawang')
]

# masukkan data ke min heap dan max heap
for id, nama in data_awal:
    data = Data(id, nama)
    min_heap.insert(data)
    max_heap.insert(data) 

# Menu
while True:
    print("\n")
    print("=== MENU STRUKTUR DATA HEAP ===")
    print("1. Tambah Data")
    print("2. Tampilkan Data Ascending (Min Heap)")
    print("3. Tampilkan Data Descending (Max Heap)")
    print("4. Hapus data dari Min Heap") 
    print("5. Hapus data dari Max Heap")
    print("6. Keluar")

    pilih = input("Pilih: ")
    if pilih == '1':
        id = int(input("Masukkan ID: "))
        nama = input("Masukkan Nama: ")
        data = Data(id, nama)
        min_heap.insert(data)
        max_heap.insert(data)
        print("\nData berhasil ditambahkan")

    elif pilih == '2':
        min_heap.display()

    elif pilih == '3':
        max_heap.display()

    elif pilih == '4':
        min_heap.delete_min()

    elif pilih == '5':
        max_heap.delete_max()

    elif pilih == '6':
        print("Terima kasih!")
        break

    else:
        print("Pilihan tidak valid. Silakan coba lagi.")


