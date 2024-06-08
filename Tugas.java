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
/*
        System.out.println("======Menu Makanan======");
        String menuMam = "1. Nasi Goreng(Rp 15000)\n2. Nasi Padang(Rp 20000)\n3. Ketoprak(Rp 13000)";
        System.out.println(menuMam);
        System.out.println("======Menu Minuman======");
        String menuMimi = "4. Jus Jeruk(Rp 5000)\n5. Es Teh(Rp 3000)\n6. Kopi(Rp 7000)";
        System.out.println(menuMimi);

        HashMap<Integer, String> menu = new HashMap<>();
        menu.put(1, "Nasi Goreng");
        menu.put(2, "Nasi Padang");
        menu.put(3, "Ketoprak");
        menu.put(4, "Jus Jeruk");
        menu.put(5, "Es Teh");
        menu.put(6, "Kopi");

        HashMap<String, Integer> hargaMenu = new HashMap<>();
        hargaMenu.put("Nasi Goreng", 15000);
        hargaMenu.put("Nasi Padang", 20000);
        hargaMenu.put("Ketoprak", 13000);
        hargaMenu.put("Jus Jeruk", 5000);
        hargaMenu.put("Es Teh", 3000);
        hargaMenu.put("Kopi", 7000);

        Scanner pilihanUserObj = new Scanner(System.in);
        Scanner jumlahMakananObj = new Scanner(System.in);
        int totalBayar = 0;

        while (true) {
            System.out.print("Pilih Menu (atau masukkan '9' untuk selesai): ");
            int pilihanUser = pilihanUserObj.nextInt();

            if (pilihanUser == 9) {
                pilihanUserObj.close();
                break;
            }
            
            System.out.print("Masukkan Jumlah Makanan: ");
            int jumlahMakanan = jumlahMakananObj.nextInt();

            String namaMenu = menu.get(pilihanUser);
            int hargaSatuan = hargaMenu.get(namaMenu);

            int subtotal = jumlahMakanan * hargaSatuan;
            totalBayar += subtotal;

            System.out.println("Makanan Anda: " + namaMenu + " x " + jumlahMakanan + " = Rp." + subtotal);
            
        }
        
        System.out.println("\n--- Receipt ---");
        double serviceCharge = 20000;
        double tax = totalBayar * 0.10;
        double finalTotal = totalBayar + tax + serviceCharge;

        System.out.println("Subtotal: Rp." + totalBayar);
        System.out.println("Pajak (10%): Rp." + tax);
        System.out.println("Biaya Pelayanan: Rp." + serviceCharge);
        System.out.println("Total Bayar: Rp." + finalTotal);

        if (totalBayar > 100000) {
            double discount = totalBayar * 0.10;
            finalTotal -= discount;
            System.out.println("Discount (10%): Rp." + discount);
            System.out.println("Setelah Discount: Rp." + (finalTotal - discount));
        }  
    jumlahMakananObj.close();
*/
    }
}
