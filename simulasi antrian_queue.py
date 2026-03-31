import tkinter as tk
from tkinter import messagebox, ttk
from gtts import gTTS
import pygame
import os

class Node:
    def __init__(self, nomor, nama, poli):
        self.nomor = nomor
        self.nama = nama
        self.poli = poli
        self.next = None

class HospitalQueue:
    def __init__(self):
        self.front = None
        self.rear = None

    def enqueue(self, nomor, nama, poli):
        new_node = Node(nomor, nama, poli)
        if self.rear is None:
            self.front = self.rear = new_node
            return
        self.rear.next = new_node
        self.rear = new_node

    def dequeue(self):
        if self.front is None:
            return None
        temp = self.front
        self.front = self.front.next
        if self.front is None:
            self.rear = None
        return temp

    def get_all(self):
        res = []
        curr = self.front
        while curr:
            res.append((curr.nomor, curr.nama, curr.poli))
            curr = curr.next
        return res

class AntrianRS:
    def __init__(self, root):
        self.root = root
        self.root.title("Sistem Antrian Rumah Sakit")
        self.queue = HospitalQueue()
        self.counter = 1
        pygame.mixer.init()

        # Input Frame
        frame_input = tk.LabelFrame(root, text="Registrasi Pasien", padx=10, pady=10)
        frame_input.pack(padx=10, pady=10, fill="x")

        tk.Label(frame_input, text="Nama Pasien:").grid(row=0, column=0, sticky="w")
        self.ent_nama = tk.Entry(frame_input)
        self.ent_nama.grid(row=0, column=1, pady=5)

        tk.Label(frame_input, text="Poli Tujuan:").grid(row=1, column=0, sticky="w")
        self.combo_poli = ttk.Combobox(frame_input, values=["Umum", "Gigi", "Anak", "Kandungan"])
        self.combo_poli.grid(row=1, column=1, pady=5)
        self.combo_poli.current(0)

        tk.Button(frame_input, text="Daftar Pasien", command=self.tambah, bg="#2ecc71", fg="white").grid(row=2, column=0, columnspan=2, sticky="we", pady=10)

        # Table
        self.tree = ttk.Treeview(root, columns=("No", "Nama", "Poli"), show='headings')
        self.tree.heading("No", text="No. Antrian")
        self.tree.heading("Nama", text="Nama Pasien")
        self.tree.heading("Poli", text="Poli")
        self.tree.pack(padx=10, pady=10)

        tk.Button(root, text="Panggil Pasien Berikutnya", command=self.panggil, bg="#3498db", fg="white", font=("Arial", 10, "bold")).pack(fill="x", padx=10, pady=5)

    def tambah(self):
        nama = self.ent_nama.get()
        poli = self.combo_poli.get()
        if nama:
            self.queue.enqueue(self.counter, nama, poli)
            self.update_view()
            messagebox.showinfo("Berhasil", f"Pasien {nama} terdaftar di nomor {self.counter}")
            self.counter += 1
            self.ent_nama.delete(0, tk.END)
        else:
            messagebox.showwarning("Input Salah", "Nama pasien harus diisi!")

    def panggil(self):
        pasien = self.queue.dequeue()
        if pasien:
            self.update_view()
            pesan = f"Nomor antrian {pasien.nomor}, pasien atas nama {pasien.nama}, silakan menuju poli {pasien.poli}."
            
            try:
                pygame.mixer.music.stop()
                pygame.mixer.music.unload() 
                
                tts = gTTS(text=pesan, lang='id')
                filename = "rs_call.mp3"
                
                if os.path.exists(filename):
                    try:
                        os.remove(filename)
                    except:
                        pass 
                
                tts.save(filename)
                
                pygame.mixer.music.load(filename)
                pygame.mixer.music.play()
            except Exception as e:
                print(f"Gagal memutar suara: {e}")
            
            messagebox.showinfo("Panggilan", pesan)
        else:
            messagebox.showwarning("Kosong", "Tidak ada pasien dalam antrian.")

    def update_view(self):
        for i in self.tree.get_children(): self.tree.delete(i)
        for data in self.queue.get_all():
            self.tree.insert("", "end", values=data)

if __name__ == "__main__":
    root = tk.Tk()
    app = AntrianRS(root)
    root.mainloop()