import random

# Ukuran hash table
TABLE_SIZE = 20

# Membuat hash table kosong
hash_table = [[] for _ in range(TABLE_SIZE)]


# Fungsi hash
def hash_function(key):
    return key % TABLE_SIZE


# INSERT DATA
def insert_data(data):
    index = hash_function(data)

    # Cek agar tidak duplikat
    if data not in hash_table[index]:
        hash_table[index].append(data)
        print(f"Data {data} berhasil ditambahkan.")
    else:
        print("Data sudah ada.")


# HAPUS DATA
def delete_data(data):
    index = hash_function(data)

    if data in hash_table[index]:
        hash_table[index].remove(data)
        print(f"Data {data} berhasil dihapus.")
    else:
        print("Data tidak ditemukan.")


# CARI DATA
def search_data(data):
    index = hash_function(data)

    if data in hash_table[index]:
        print(f"Data {data} ditemukan pada index {index}.")
    else:
        print("Data tidak ditemukan.")


# MENAMPILKAN HASH TABLE
def display_table():
    print("\n=== HASH TABLE ===")
    for i in range(TABLE_SIZE):
        print(f"Index {i}: {hash_table[i]}")


#input 100 angka random
random_numbers = random.sample(range(1, 1000), 100)

for number in random_numbers:
    insert_data(number)

print("\n100 data random berhasil dimasukkan.\n")


# MENU PROGRAM
while True:
    print("\n===== MENU HASH TABLE =====")
    print("1. Input Data")
    print("2. Hapus Data")
    print("3. Cari Data")
    print("4. Tampilkan Hash Table")
    print("5. Keluar")

    choice = input("Pilih menu: ")

    if choice == "1":
        data = int(input("Masukkan angka: "))
        insert_data(data)

    elif choice == "2":
        data = int(input("Masukkan angka yang ingin dihapus: "))
        delete_data(data)

    elif choice == "3":
        data = int(input("Masukkan angka yang ingin dicari: "))
        search_data(data)

    elif choice == "4":
        display_table()

    elif choice == "5":
        print("Program selesai.")
        break

    else:
        print("Pilihan tidak valid.")
