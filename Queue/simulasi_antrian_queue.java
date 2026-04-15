import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;

// 1. STRUKTUR DATA NODE (LINKED LIST)
class Pasien {
    int nomor;
    String nama;
    String poli;
    Pasien next;

    Pasien(int nomor, String nama, String poli) {
        this.nomor = nomor;
        this.nama = nama;
        this.poli = poli;
        this.next = null;
    }
}

// 2. STRUKTUR DATA QUEUE MANUAL (TANPA LIBRARY)
class CustomQueueRS {
    Pasien front, rear;

    // Method Tambah Antrian (Enqueue)
    void enqueue(int nomor, String nama, String poli) {
        Pasien baru = new Pasien(nomor, nama, poli);
        if (rear == null) {
            front = rear = baru;
            return;
        }
        rear.next = baru;
        rear = baru;
    }

    // Method Panggil Antrian (Dequeue)
    Pasien dequeue() {
        if (front == null) return null;
        Pasien temp = front;
        front = front.next;
        if (front == null) rear = null;
        return temp;
    }
}

// 3. GUI DAN LOGIKA UTAMA
public class simulasi_antrian_queue extends JFrame {
    private CustomQueueRS antrian = new CustomQueueRS();
    private int counter = 1;
    private DefaultTableModel modelTabel;
    private JTextField txtNama;
    private JComboBox<String> cbPoli;

    public simulasi_antrian_queue() {
        // Pengaturan Jendela Utama
        setTitle("Simulasi Antrian Rumah Sakit (Java Manual Queue)");
        setSize(500, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        setLocationRelativeTo(null); // Agar muncul di tengah layar

        // --- PANEL INPUT (ATAS) ---
        JPanel pnlInput = new JPanel(new GridLayout(4, 1, 5, 5));
        pnlInput.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        txtNama = new JTextField();
        cbPoli = new JComboBox<>(new String[]{"Poli Umum", "Poli Gigi", "Poli Anak", "Poli Mata"});
        JButton btnAmbil = new JButton("AMBIL NOMOR ANTRIAN");
        btnAmbil.setBackground(new Color(46, 204, 113));
        btnAmbil.setForeground(Color.WHITE);

        pnlInput.add(new JLabel("Nama Pasien:"));
        pnlInput.add(txtNama);
        pnlInput.add(new JLabel("Poli Tujuan:"));
        pnlInput.add(cbPoli);

        // --- TABEL DATA (TENGAH) ---
        modelTabel = new DefaultTableModel(new String[]{"No. Antrian", "Nama Pasien", "Poli"}, 0);
        JTable tabel = new JTable(modelTabel);
        JScrollPane scrollPane = new JScrollPane(tabel);

        // --- PANEL TOMBOL (BAWAH) ---
        JButton btnPanggil = new JButton("PANGGIL PASIEN BERIKUTNYA");
        btnPanggil.setFont(new Font("Arial", Font.BOLD, 14));
        btnPanggil.setPreferredSize(new Dimension(0, 60));
        btnPanggil.setBackground(new Color(52, 152, 219));
        btnPanggil.setForeground(Color.WHITE);

        // --- LOGIKA TOMBOL AMBIL ANTRIAN ---
        btnAmbil.addActionListener(e -> {
            String nama = txtNama.getText();
            String poli = cbPoli.getSelectedItem().toString();
            if (!nama.isEmpty()) {
                antrian.enqueue(counter, nama, poli);
                refreshTabel();
                JOptionPane.showMessageDialog(this, "Berhasil! Nomor Antrian Anda: " + counter);
                counter++;
                txtNama.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Masukkan nama pasien!");
            }
        });

        // --- LOGIKA TOMBOL PANGGIL ANTRIAN ---
        btnPanggil.addActionListener(e -> {
            Pasien p = antrian.dequeue();
            if (p != null) {
                refreshTabel();
                panggilSuaraManusia(p.nomor, p.nama, p.poli); // Memanggil Suara
                JOptionPane.showMessageDialog(this, "MEMANGGIL:\nNomor: " + p.nomor + "\nNama: " + p.nama + "\nTujuan: " + p.poli);
            } else {
                JOptionPane.showMessageDialog(this, "Antrian kosong.");
            }
        });

        // Menyusun Layout
        JPanel pnlAtas = new JPanel(new BorderLayout());
        pnlAtas.add(pnlInput, BorderLayout.CENTER);
        pnlAtas.add(btnAmbil, BorderLayout.SOUTH);

        add(pnlAtas, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(btnPanggil, BorderLayout.SOUTH);
    }

    // FUNGSI SUARA MENGGUNAKAN POWERSHELL
    private void panggilSuaraManusia(int nomor, String nama, String poli) {
        try {
            String pesan = "Nomor antrian " + nomor + ", atas nama " + nama + ", silakan menuju " + poli;
            
            // Perintah PowerShell untuk berbicara
            String command = "Add-Type -AssemblyName System.Speech; " +
                             "$speak = New-Object System.Speech.Synthesis.SpeechSynthesizer; " +
                             "$speak.Speak('" + pesan + "')";
            
            ProcessBuilder pb = new ProcessBuilder("powershell.exe", "-Command", command);
            pb.start();
        } catch (Exception e) {
            Toolkit.getDefaultToolkit().beep();
            System.out.println("Gagal memanggil suara: " + e.getMessage());
        }
    }

    // Fungsi Update Tampilan Tabel
    private void refreshTabel() {
        modelTabel.setRowCount(0);
        Pasien current = antrian.front;
        while (current != null) {
            modelTabel.addRow(new Object[]{current.nomor, current.nama, current.poli});
            current = current.next;
        }
    }

    public static void main(String[] args) {
        // Menjalankan GUI
        SwingUtilities.invokeLater(() -> {
            new simulasi_antrian_queue().setVisible(true);
        });
    }
}
