MAX = 10
data = [["",""] for _ in range(MAX)]
count = 0

def insert_beginning():
    global count
    if count == MAX:
        print("Array full")
        return
    for i in range(count, 0, -1):
        data[i][0] = data[i - 1][0]
        data[i][1] = data[i - 1][1]
    data[0][0] = input("Inputkan NIM: ")
    data[0][1] = input("Inputkan Nama: ")
    count += 1

def insert_position():
    global count
    if count == MAX:
        print("Array penuh")
        return
    pos = int(input(f"Inputkan posisi (1-{count+1}): "))
    if pos < 1 or pos > count + 1:
        print("Posisi tidak valid")
        return
    for i in range(count, pos-1, -1):
        data[i][0] = data[i - 1][0]
        data[i][1] = data[i - 1][1]
    data[pos-1][0] = input("Inputkan NIM: ")
    data[pos-1][1] = input("Inputkan Nama: ")
    count += 1

def insert_end():
    global count
    if count == MAX:
        print("Array penuh")
        return
    data[count][0] = input("Inputkan NIM: ")
    data[count][1] = input("Inputkan Nama: ")
    count += 1

def delete_beginning():
    global count
    if count == 0:
        print("Data Kosong")
        return
    
    for i in range(count-1):
        data[i][0] = data[i + 1][0]
        data[i][1] = data[i + 1][1]
    count -= 1

def delete_position():
    global count 
    if count == 0:
        print("Data Kosong")
        return
    
    pos = int(input(f"Inputkan posisi (1-{count}): "))
    if pos < 1 or pos > count:
        print("posisi tidak valid")
        return
    for i in range(pos-1, count-1):
        data[i][0] = data[i + 1][0]
        data[i][1] = data[i + 1][1]
    count -= 1

def delete_end():
    global count
    if count == 0:
        print("Data kosong")
        return
    count -= 1

def delete_first_occurance():
    global count
    if count == 0:
        print("Data kosong")
        return
    
    nim = input("Inputkan NIM yang ingin dihapus: ").strip()
    index = -1
    for i in range(count):
        if data[i][0] == nim:
            index = i
            break

    if index == -1:
            print("Tidak ditemukan")
            return
    for i in range(index, count - 1):
            data[i][0] = data[i + 1][0]
            data[i][1] = data[i + 1][1] 
    count -= 1

def show_data():
    print("\n === DATA MAHASISWA ===")
    if count == 0:
        print("(KOSONG)")
        return
    print("NIM\t\tNAMA")
    for i in range(count):
        print(data[i][0], "\t\t", data[i][1])

while True:
    print("\n--- MENU ---")
    print("1. Insert at beginning")
    print("2. Insert at given position")
    print("3. Insert at end")
    print("4. delete from beginning")
    print("5. delete at given position")
    print("6. delete at end")
    print("7. delete at first occurance")
    print("8. show data")
    print("9. exit")
    pilih = input("Pilih: ")

    if pilih == "1": insert_beginning()
    elif pilih == "2": insert_position()
    elif pilih == "3": insert_end()
    elif pilih == "4": delete_beginning()
    elif pilih == "5": delete_position()
    elif pilih == "6": delete_end()
    elif pilih == "7": delete_first_occurance()
    elif pilih == "8": show_data()
    elif pilih == "9":
        print("EXIT dari program")
        break
    else:
        print("Pilihan tidak valid")