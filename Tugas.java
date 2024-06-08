import java.util.Scanner;
import java.util.HashMap;
import java.io.File;
//import java.io.IOException;
import java.io.FileNotFoundException;


abstract class MenuItems {
    public abstract void Diskon(double harga);
    public abstract void Makanan();
    public abstract void Minuman();
}


class TampilanMenu extends MenuItems {
    Scanner inputUser = new Scanner(System.in);
    public void Diskon(double harga) {
        double discount = harga * 0.10;
        System.out.printf("Diskon: Rp %.3f%n", discount);

        double dipotongDiskon = harga - discount;
        System.out.printf("Harga Akhir: Rp %.3f%n", dipotongDiskon);
        System.out.print("Bayar: ");
        double bayar = inputUser.nextDouble();
        if (bayar < dipotongDiskon) {
            System.out.println("Kurang bayar. Silakan ulang pesanan.");
            System.exit(0);
        }
        double kembalian = bayar - dipotongDiskon;
        System.out.printf("Kembalian: Rp %.3f%n", kembalian);
    }

    public void Makanan() {
        try {
            File menuMakanan = new File("makanan.txt");
            Scanner readMenu = new Scanner(menuMakanan);
            int number = 1;
            HashMap<Integer, String> listMakanan = new HashMap<Integer, String>();
            while (readMenu.hasNextLine()) {
                String makanan = readMenu.nextLine();

                String[] parts = makanan.split(",");
                String namaMakanan = parts[0].trim();
                Double hargaMakanan = Double.parseDouble(parts[1].trim());

                listMakanan.put(number, makanan);

                System.out.print(number + ". " + namaMakanan);
                System.out.printf(": Rp %.3f%n", hargaMakanan);
                number ++;
            }
            readMenu.close();

            System.out.print("Pilih Makanan: ");
            int makanan = inputUser.nextInt();
            String pilihanPelanggan = listMakanan.get(makanan);

            String[] partString = pilihanPelanggan.split(",");
            String makananPelanggan = partString[0].trim();
            double harga = Double.parseDouble(partString[1].trim());

            System.out.println("\n========== Receipt ==========");
            System.out.println("Makanan: " + makananPelanggan);
            System.out.printf("Harga: Rp %.3f%n", harga);

            System.out.print("Jumlah: ");
            int jumlah = inputUser.nextInt();

            double totalHarga = harga * jumlah;
            System.out.printf("Total: Rp %.3f%n", totalHarga);
            TampilanMenu getDiscount = new TampilanMenu();
            getDiscount.Diskon(totalHarga);
        } catch (FileNotFoundException e) {
            System.out.println("Menu Makanan saat ini tidak tersedia");
            e.printStackTrace();
        }
    }

    public void Minuman() {
        try { 
            File menuMinuman = new File("minuman.txt");
            Scanner readMenu = new Scanner(menuMinuman);
            int number = 1;
            HashMap<Integer, String> listMinuman = new HashMap<Integer, String>();
            while (readMenu.hasNextLine()) {
                String minuman = readMenu.nextLine();

                String[] parts = minuman.split(",");
                String namaMinuman = parts[0].trim();
                Double hargaMinuman = Double.parseDouble(parts[1].trim());

                listMinuman.put(number, minuman);

                System.out.print(number + ". " + namaMinuman);
                System.out.printf(": Rp %.3f%n", hargaMinuman);
                number ++;
            }
            readMenu.close();

            System.out.print("Pilih Minuman: ");
            int minuman = inputUser.nextInt();

            String pilihanPelanggan = listMinuman.get(minuman);

            String[] partString = pilihanPelanggan.split(",");
            String minumanPelanggan = partString[0].trim();
            Double harga = Double.parseDouble(partString[1].trim());
            
            System.out.println("\n========== Receipt ==========");
            System.out.println("Minuman: " + minumanPelanggan);
            System.out.printf("Harga: Rp %.3f%n", harga);
            System.out.print("Jumlah: ");
            int jumlah = inputUser.nextInt();

            double totalHarga = harga * jumlah;
            System.out.printf("Total: Rp %.3f%n", totalHarga);
            TampilanMenu getDiscount = new TampilanMenu();
            getDiscount.Diskon(totalHarga);
        } catch (FileNotFoundException e) { 
            System.out.println("Menu Minuman saat ini tidak tersedia");
            e.printStackTrace();
        }
    }
}

class Pesanan {
    static void pesananPelanggan(String[] listPesanan) {
        for (String pesanan : listPesanan) {
            System.out.println(pesanan);
        }
    }
}

public class Tugas {
    public static void main(String[] args) {
        System.out.println("========== Dashboard Restoran ==========");
        System.out.println("1. Menu\n2. Tambah Menu");
        Scanner pilihDashboardObj = new Scanner(System.in);
        System.out.print("Pilih Dashboard: ");
        int pilihDashboard = pilihDashboardObj.nextInt();


        if (pilihDashboard == 1) {
            System.out.println("\n========== Menu ==========\n :\n1. Makanan\n2. Minuman");
            TampilanMenu menuRestoran = new TampilanMenu();
            Scanner pilihMenuObj = new Scanner(System.in);
            System.out.print("Pilih Menu: ");
            int pilihMenu = pilihMenuObj.nextInt();

            if (pilihMenu == 1) {
                System.out.println("\n========== Makanan ==========");
                menuRestoran.Makanan();
            } else if (pilihMenu == 2) {
                System.out.println("\n========== Minuman ==========");
                menuRestoran.Minuman();
            } else {
                System.out.println("Pilihan tidak tersedia");
                System.exit(0);
            }
            pilihMenuObj.close();
        }
        pilihDashboardObj.close();
    }
}
