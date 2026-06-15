import java.util.Random; // Mengimpor class Random untuk menghasilkan angka acak

class SumThread extends Thread { // Membuat class thread untuk menghitung sebagian array

    private int[] data; // Menyimpan array yang akan dijumlahkan
    private int start; // Indeks awal yang akan dihitung
    private int end; // Indeks akhir yang akan dihitung
    private long partialSum; // Menyimpan hasil penjumlahan sebagian

    public SumThread(int[] data, int start, int end) { // Constructor
        this.data = data; // Menyimpan array ke atribut class
        this.start = start; // Menyimpan indeks awal
        this.end = end; // Menyimpan indeks akhir
    }

    @Override
    public void run() { // Method yang otomatis dijalankan saat thread dimulai
        partialSum = 0; // Inisialisasi nilai awal

        for (int i = start; i < end; i++) { // Menjumlahkan elemen sesuai rentang thread
            partialSum += data[i];
        }
    }

    public long getPartialSum() { // Mengembalikan hasil penjumlahan thread
        return partialSum;
    }
}

public class ParallelSum {

    public static void main(String[] args) throws InterruptedException {

        int size = 10_000_000; // Menentukan ukuran array sebanyak 10 juta elemen

        int[] data = new int[size]; // Membuat array integer

        Random rand = new Random(); // Membuat objek Random

        for (int i = 0; i < size; i++) { // Mengisi array dengan angka acak
            data[i] = rand.nextInt(100); // Nilai acak 0-99
        }

        int mid = size / 2; // Membagi array menjadi dua bagian

        SumThread t1 = new SumThread(data, 0, mid); // Thread pertama untuk setengah awal array
        SumThread t2 = new SumThread(data, mid, size); // Thread kedua untuk setengah akhir array

        long startTime = System.nanoTime(); // Mencatat waktu mulai

        t1.start(); // Menjalankan thread pertama
        t2.start(); // Menjalankan thread kedua

        t1.join(); // Menunggu thread pertama selesai
        t2.join(); // Menunggu thread kedua selesai

        long total = t1.getPartialSum() + t2.getPartialSum(); // Menjumlahkan hasil kedua thread

        long endTime = System.nanoTime(); // Mencatat waktu selesai

        double executionTime =
                (endTime - startTime) / 1_000_000.0; // Mengubah nanodetik ke milidetik

        System.out.println("Total = " + total); // Menampilkan total penjumlahan
        System.out.println("Waktu = " + executionTime + " ms"); // Menampilkan waktu eksekusi
    }
}