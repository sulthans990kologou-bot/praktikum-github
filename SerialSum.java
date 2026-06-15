import java.util.Random; // Mengimpor class Random untuk menghasilkan angka acak

public class SerialSum {

    public static void main(String[] args) {

        int size = 10000000; // Menentukan ukuran array sebanyak 10 juta elemen

        int[] data = new int[size]; // Membuat array integer dengan ukuran 10 juta

        Random rand = new Random(); // Membuat objek Random

        // Mengisi array dengan angka acak dari 0 sampai 99
        for (int i = 0; i < size; i++) {
            data[i] = rand.nextInt(100);
        }

        long startTime = System.nanoTime(); // Mencatat waktu mulai eksekusi

        long total = 0; // Variabel untuk menyimpan total penjumlahan

        // Menjumlahkan seluruh elemen array secara berurutan (serial)
        for (int i = 0; i < size; i++) {
            total += data[i];
        }

        long endTime = System.nanoTime(); // Mencatat waktu selesai eksekusi

        // Menghitung lama waktu eksekusi dalam satuan milidetik
        double executionTime =
                (endTime - startTime) / 1000000.0;

        System.out.println("Total = " + total); // Menampilkan hasil penjumlahan

        System.out.println("Waktu = " + executionTime + " ms"); // Menampilkan waktu eksekusi
    }
}