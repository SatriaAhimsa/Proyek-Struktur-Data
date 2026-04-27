import time

class Node:
    def __init__(self, data):
        self.data = data
        self.next = None
        self.prev = None


class CircularDoublyLinkedList:
    def __init__(self):
        self.head = None

    # Insert berita di akhir
    def insert_end(self, data):
        new_node = Node(data)

        if self.head is None:
            self.head = new_node
            new_node.next = new_node
            new_node.prev = new_node
            return

        tail = self.head.prev

        tail.next = new_node
        new_node.prev = tail

        new_node.next = self.head
        self.head.prev = new_node


    # Hapus berita berdasarkan posisi
    def delete_pos(self, pos):
        if self.head is None:
            print("List kosong")
            return

        curr = self.head

        if pos == 1:

            if self.head.next == self.head:
                self.head = None
                return

            tail = self.head.prev
            self.head = self.head.next

            self.head.prev = tail
            tail.next = self.head
            return

        for i in range(1, pos):
            curr = curr.next
            if curr == self.head:
                print("Posisi tidak ditemukan")
                return

        curr.prev.next = curr.next
        curr.next.prev = curr.prev


    # Traversal forward
    def forward(self):
        if self.head is None:
            print("List kosong")
            return

        print("(Tekan Ctrl+C untuk berhenti)")
        curr = self.head

        try:
            while True:
                print(curr.data)
                time.sleep(3)
                curr = curr.next
        except KeyboardInterrupt:
            print("\nTraversal dihentikan")


    # Traversal backward
    def backward(self):
        if self.head is None:
            print("List kosong")
            return

        print("(Tekan Ctrl+C untuk berhenti)")
        tail = self.head.prev
        curr = tail

        try:
            while True:
                print(curr.data)
                time.sleep(3)
                curr = curr.prev
        except KeyboardInterrupt:
            print("\nTraversal dihentikan")


    # Tampil berita tertentu
    def display_at(self, pos):
        if self.head is None:
            print("List kosong")
            return

        curr = self.head

        for i in range(1, pos):
            curr = curr.next
            if curr == self.head:
                print("Posisi tidak ditemukan")
                return

        print(f"Berita ke-{pos}: {curr.data}")


# Program utama
cdll = CircularDoublyLinkedList()

while True:

    print("\n=== MENU BERITA TV ===")
    print("1. Insert berita")
    print("2. Hapus berita")
    print("3. Tampilkan forward")
    print("4. Tampilkan backward")
    print("5. Tampil berita tertentu")
    print("6. Exit")

    pilih = int(input("Pilih menu: "))

    if pilih == 1:
        berita = input("Masukkan berita: ")
        cdll.insert_end(berita)

    elif pilih == 2:
        pos = int(input("Hapus berita nomor: "))
        cdll.delete_pos(pos)

    elif pilih == 3:
        cdll.forward()

    elif pilih == 4:
        cdll.backward()

    elif pilih == 5:
        pos = int(input("Masukkan nomor berita: "))
        cdll.display_at(pos)

    elif pilih == 6:
        print("Program selesai")
        break

    else:
        print("Pilihan tidak valid")
