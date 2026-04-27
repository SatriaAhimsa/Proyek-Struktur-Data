class Node:
    def __init__(self, nim, nama):
        self.nim = nim
        self.nama = nama
        self.next = None

class LinkedList:
    def __init__(self):
        self.head = None
        self.count = 0

    def insert_beginning(self, nim, nama):
        new = Node(nim, nama)
        new.next = self.head
        self.head = new
        self.count += 1

    def insert_end(self, nim, nama):
        new = Node(nim, nama)
        if not self.head:
            self.head = new
        else:
            temp = self.head
            while temp.next:
                temp = temp.next
            temp.next = new
        self.count += 1

    def insert_position(self, nim, nama, pos):
        if pos < 1 or pos > self.count + 1:
            print("Posisi tidak valid")
            return
        if pos == 1:
            self.insert_beginning(nim, nama)
            return
        new = Node(nim, nama)
        temp = self.head
        for _ in range(pos - 2):
            temp = temp.next
        new.next = temp.next
        temp.next = new
        self.count += 1

    def delete_beginning(self):
        if self.head:
            self.head = self.head.next
            self.count -= 1

    def delete_end(self):
        if not self.head:
            return
        if not self.head.next:
            self.head = None
        else:
            temp = self.head
            while temp.next.next:
                temp = temp.next
            temp.next = None
        self.count -= 1

    def delete_position(self, pos):
        if pos < 1 or pos > self.count:
            print("Posisi tidak valid")
            return
        if pos == 1:
            self.delete_beginning()
            return
        temp = self.head
        for _ in range(pos - 2):
            temp = temp.next
        temp.next = temp.next.next
        self.count -= 1

    def delete_by_nim(self, nim):
        if not self.head:
            return
        if self.head.nim == nim:
            self.head = self.head.next
            self.count -= 1
            return
        temp = self.head
        while temp.next and temp.next.nim != nim:
            temp = temp.next
        if temp.next:
            temp.next = temp.next.next
            self.count -= 1

    def show(self):
        temp = self.head
        i = 1
        while temp:
            print(f"{i}. {temp.nim} - {temp.nama}")
            temp = temp.next
            i += 1

ll = LinkedList()

while True:
    print("\nMENU")
    print("1. Insert Beginning")
    print("2. Insert Position")
    print("3. Insert End")
    print("4. Delete Beginning")
    print("5. Delete Position")
    print("6. Delete End")
    print("7. Delete by NIM")
    print("8. Show Data")
    print("9. Exit")

    ch = int(input("Pilih: "))

    if ch == 1:
        nim = input("NIM: ")
        nama = input("Nama: ")
        ll.insert_beginning(nim, nama)
    elif ch == 2:
        pos = int(input("Posisi: "))
        nim = input("NIM: ")
        nama = input("Nama: ")
        ll.insert_position(nim, nama, pos)
    elif ch == 3:
        nim = input("NIM: ")
        nama = input("Nama: ")
        ll.insert_end(nim, nama)
    elif ch == 4:
        ll.delete_beginning()
    elif ch == 5:
        pos = int(input("Posisi: "))
        ll.delete_position(pos)
    elif ch == 6:
        ll.delete_end()
    elif ch == 7:
        nim = input("NIM: ")
        ll.delete_by_nim(nim)
    elif ch == 8:
        ll.show()
    elif ch == 9:
        break
