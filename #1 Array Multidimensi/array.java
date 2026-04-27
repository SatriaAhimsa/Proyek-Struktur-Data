import java.util.Scanner;

public class array {
    static final int MAX = 10;
    static String[][] data = new String[MAX][2];
    static int count = 0;

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int pilih;

        do {
            System.out.println("=== Menu ===");
            System.out.println("1. Insert at beginning");
            System.out.println("2. Insert at given position");
            System.out.println("3. Insert at end");
            System.out.println("4. delete from beginning");
            System.out.println("5. delete at given position");
            System.out.println("6. delete from end");
            System.out.println("7. delete first occurance");
            System.out.println("8. show data");
            System.out.println("9. exit");
            System.out.println("pilih menu: ");
            pilih = scan.nextInt();
            scan.nextLine();

            switch (pilih) {
                case 1:
                    insertAtBeginning(scan);
                    break;
                case 2:
                    insertGivenPosition(scan);
                    break;
                case 3:
                    inserAtEnd(scan);
                    break;
                case 4:
                    deleteFromBeginning();
                    break;
                case 5:
                    deleteGivenposition(scan);
                    break;
                case 6:
                    deleteFromEnd();
                    break;
                case 7:
                    deleteFirstOccurance(scan);
                    break;
                case 8:
                    showData();
                    break;
                case 9:
                    System.out.println("Exit dari program");
                    break;
                default:
                    System.out.println("Pilihan tidak valid");
            }
        }while(pilih!=9);
    }

    static void insertAtBeginning(Scanner scan){
        if (count == MAX) {
            System.out.println("Array penuh!");
            return;
        }
        for (int i = count; i > 0; i--) {
            data[i][0] = data[i - 1][0];
            data[i][1] = data[i - 1][1];
        }
        System.out.print("Input NIM: ");
        data[0][0] = scan.nextLine();
        System.out.print("Input Nama Mahasiswa: ");
        data[0][1] = scan.nextLine();
        count++;
    }

    static void insertGivenPosition(Scanner scan){
        if (count == MAX) {
            System.out.println("Array penuh!");
            return;
        }

        System.out.print("Input posisi (1-" + (count + 1) + "): ");
        int pos = scan.nextInt();
        scan.nextLine();

        if (pos < 1 || pos > count + 1) {
            System.out.print("Input tidak valid!");
            return;
        }

        for (int i = count; i >= pos; i--) {
            data[i][0] = data[i - 1][0];
            data[i][1] = data[i - 1][1];
        }

        System.out.print("Input NIM: ");
        data[pos - 1][0] = scan.nextLine();
        System.out.print("Input Nama Mahasiswa: ");
        data[pos - 1][1] = scan.nextLine();
        count++;
    }

    static void inserAtEnd(Scanner scan){
        if (count == MAX) {
            System.out.println("Array penuh!");
            return;
        }

        System.out.print("Input NIM: ");
        data[count][0] = scan.nextLine();
        System.out.print("Input Nama Mahasiswa: ");
        data[count][1] = scan.nextLine();
        count++;
    }

    static void deleteFromBeginning(){
        if (count == 0) {
            System.out.println("Data kososng");
            return;
        }

        for(int i = 0; i < count - 1; i++){
            data[i][0] = data[i + 1][0];
            data[i][1] = data[i + 1][1];
        }
        count--;
    }

    static void deleteGivenposition(Scanner scan){
        if (count == 0) {
            System.out.println("Data kososng");
            return;
        }

        System.out.print("Input posisi (1-" + count + "): ");
        int pos = scan.nextInt();
        scan.nextLine();

        if(pos < 1 || pos > count){
            System.out.println("posisi tidak valid");
            return;
        }

        for (int i = pos - 1; i < count - 1; i++){
            data[i][0] = data[i + 1][0];
            data[i][1] = data[i + 1][1];
        }
        count--;
    }

    static void deleteFromEnd(){
        if (count == 0) {
            System.out.println("Data kosong!");
            return;
        }
        count--;
    }

    static void deleteFirstOccurance(Scanner scan){
        if (count == 0) {
            System.out.println("Data kosong!");
            return;
        }

        System.out.println("input NIM yang ingin dihapus: ");
        String nim = scan.nextLine();

        int index = -1;
        for (int i = 0; i < count; i++){
            if(data[i][0].equals(nim)){
                index = i;
                break;
            }
        }

        if(index == -1){
            System.out.println("data tidak ditemukan");
            return;
        }

        for(int i = index; i < count - 1; i++){
            data[i][0] = data[i + 1][0];
            data[i][1] = data[i + 1][1];
        }
        count--;
    }

    static void showData(){
        System.out.println("=== Data Mahasiswa ===");
        if(count == 0){
            System.out.println("(Kosong)");
            return;
        }
        System.out.println("NIM\t\tNAMA");
        for(int i = 0; i < count; i++){
            System.out.println(data[i][0] + "\t\t" + data[i][1]);
        }
    }
}
