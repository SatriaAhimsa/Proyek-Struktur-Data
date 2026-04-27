import java.util.Scanner;

class Node {
    String nim, nama;
    Node next;

    Node(String nim, String nama) {
        this.nim = nim;
        this.nama = nama;
        this.next = null;
    }
}

public class singly_linked_list {
    static Node head = null;
    static int count = 0;

    static void insertBeginning(String nim, String nama) {
        Node newNode = new Node(nim, nama);
        newNode.next = head;
        head = newNode;
        count++;
    }

    static void insertEnd(String nim, String nama) {
        Node newNode = new Node(nim, nama);
        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null) temp = temp.next;
            temp.next = newNode;
        }
        count++;
    }

    static void insertPosition(String nim, String nama, int pos) {
        if (pos < 1 || pos > count + 1) {
            System.out.println("Posisi tidak valid");
            return;
        }
        if (pos == 1) {
            insertBeginning(nim, nama);
            return;
        }
        Node newNode = new Node(nim, nama);
        Node temp = head;
        for (int i = 1; i < pos - 1; i++) temp = temp.next;
        newNode.next = temp.next;
        temp.next = newNode;
        count++;
    }

    static void deleteBeginning() {
        if (head == null) return;
        head = head.next;
        count--;
    }

    static void deleteEnd() {
        if (head == null) return;
        if (head.next == null) {
            head = null;
        } else {
            Node temp = head;
            while (temp.next.next != null) temp = temp.next;
            temp.next = null;
        }
        count--;
    }

    static void deletePosition(int pos) {
        if (pos < 1 || pos > count) {
            System.out.println("Posisi tidak valid");
            return;
        }
        if (pos == 1) {
            deleteBeginning();
            return;
        }
        Node temp = head;
        for (int i = 1; i < pos - 1; i++) temp = temp.next;
        temp.next = temp.next.next;
        count--;
    }

    static void deleteByNim(String nim) {
        if (head == null) return;
        if (head.nim.equals(nim)) {
            head = head.next;
            count--;
            return;
        }
        Node temp = head;
        while (temp.next != null && !temp.next.nim.equals(nim)) temp = temp.next;
        if (temp.next != null) {
            temp.next = temp.next.next;
            count--;
        }
    }

    static void showData() {
        Node temp = head;
        int i = 1;
        while (temp != null) {
            System.out.println(i + ". " + temp.nim + " - " + temp.nama);
            temp = temp.next;
            i++;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nMENU:");
            System.out.println("1. Insert Beginning");
            System.out.println("2. Insert Position");
            System.out.println("3. Insert End");
            System.out.println("4. Delete Beginning");
            System.out.println("5. Delete Position");
            System.out.println("6. Delete End");
            System.out.println("7. Delete by NIM");
            System.out.println("8. Show Data");
            System.out.println("9. Exit");
            System.out.print("Pilih: ");
            choice = sc.nextInt();
            sc.nextLine();

            String nim, nama;
            int pos;

            switch (choice) {
                case 1:
                    System.out.print("NIM: "); nim = sc.nextLine();
                    System.out.print("Nama: "); nama = sc.nextLine();
                    insertBeginning(nim, nama);
                    break;
                case 2:
                    System.out.print("Posisi: "); pos = sc.nextInt(); sc.nextLine();
                    System.out.print("NIM: "); nim = sc.nextLine();
                    System.out.print("Nama: "); nama = sc.nextLine();
                    insertPosition(nim, nama, pos);
                    break;
                case 3:
                    System.out.print("NIM: "); nim = sc.nextLine();
                    System.out.print("Nama: "); nama = sc.nextLine();
                    insertEnd(nim, nama);
                    break;
                case 4: deleteBeginning(); break;
                case 5:
                    System.out.print("Posisi: "); pos = sc.nextInt();
                    deletePosition(pos);
                    break;
                case 6: deleteEnd(); break;
                case 7:
                    System.out.print("NIM: "); nim = sc.nextLine();
                    deleteByNim(nim);
                    break;
                case 8: showData(); break;
            }
        } while (choice != 9);
    }
}