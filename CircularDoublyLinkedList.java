import java.util.Scanner;

class Node {
    String data;
    Node next, prev;

    Node(String data) {
        this.data = data;
        next = prev = null;
    }
}

class CircularDoublyLinkedList {

    static Node head = null;

    // Insert berita di akhir
    static void insertEnd(String data) {
        Node newNode = new Node(data);

        if (head == null) {
            head = newNode;
            head.next = head;
            head.prev = head;
            return;
        }

        Node tail = head.prev;

        tail.next = newNode;
        newNode.prev = tail;

        newNode.next = head;
        head.prev = newNode;
    }

    // Hapus berita berdasarkan posisi
    static void deletePos(int pos) {
        if (head == null) {
            System.out.println("List kosong");
            return;
        }

        Node curr = head;

        if (pos == 1) {

            if (head.next == head) {
                head = null;
                return;
            }

            Node tail = head.prev;

            head = head.next;
            head.prev = tail;
            tail.next = head;

            return;
        }

        for (int i = 1; i < pos; i++) {
            curr = curr.next;
            if (curr == head) {
                System.out.println("Posisi tidak ditemukan");
                return;
            }
        }

        curr.prev.next = curr.next;
        curr.next.prev = curr.prev;
    }

    // Tampil forward
    static void forwardTraversal() {

        if (head == null) {
            System.out.println("List kosong");
            return;
        }

        Node curr = head;

        do {
            System.out.println(curr.data);

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            curr = curr.next;

        } while (curr != head);
    }

    // Tampil backward
    static void backwardTraversal() {

        if (head == null) {
            System.out.println("List kosong");
            return;
        }

        Node tail = head.prev;
        Node curr = tail;

        do {
            System.out.println(curr.data);

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            curr = curr.prev;

        } while (curr != tail);
    }

    // Tampil berita tertentu
    static void displayAt(int pos) {

        if (head == null) {
            System.out.println("List kosong");
            return;
        }

        Node curr = head;

        for (int i = 1; i < pos; i++) {
            curr = curr.next;

            if (curr == head) {
                System.out.println("Posisi tidak ditemukan");
                return;
            }
        }

        System.out.println("Berita ke-" + pos + " : " + curr.data);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int pilih;

        do {

            System.out.println("\n=== MENU BERITA TV ===");
            System.out.println("1. Insert berita");
            System.out.println("2. Hapus berita");
            System.out.println("3. Tampilkan forward");
            System.out.println("4. Tampilkan backward");
            System.out.println("5. Tampil berita tertentu");
            System.out.println("6. Exit");
            System.out.print("Pilih: ");
            pilih = sc.nextInt();
            sc.nextLine();

            switch (pilih) {

                case 1:
                    System.out.print("Masukkan berita: ");
                    String berita = sc.nextLine();
                    insertEnd(berita);
                    break;

                case 2:
                    System.out.print("Hapus berita nomor: ");
                    int h = sc.nextInt();
                    deletePos(h);
                    break;

                case 3:
                    forwardTraversal();
                    break;

                case 4:
                    backwardTraversal();
                    break;

                case 5:
                    System.out.print("Masukkan nomor berita: ");
                    int p = sc.nextInt();
                    displayAt(p);
                    break;

                case 6:
                    System.out.println("Program selesai");
                    break;

                default:
                    System.out.println("Pilihan tidak valid");
            }

        } while (pilih != 6);

        sc.close();
    }
}