import java.util.Scanner;
import java.util.HashMap;

abstract class MenuItems {
    public abstract void Makanan();
    public abstract void Minuman();
    public abstract void Diskon();
}


class TampilanMenu extends MenuItems {
    public void Makanan() {
        HashMap<String, Double> menuMakanan = new HashMap<>();
        menuMakanan.put("Nasi Goreng", 15.000);
        menuMakanan.put("Nasi Padang", 20.000);
        menuMakanan.put("Ketoprak", 13.000);

        System.out.println("======Menu Makanan======");
        for (String i : menuMakanan.keySet()) {
            System.out.println(i);
        }
    }

    public void Minuman() {
        HashMap<String, Double> menuMinuman = new HashMap<>();
        menuMinuman.put("Jus Jeruk", 5.000);
        menuMinuman.put("Es Teh", 3.000);
        menuMinuman.put("Kopi", 7.000);

        System.out.println("======Menu Minuman======");
        for (String i : menuMinuman.keySet()) {
            System.out.println(i);
        }
    }

    public void Diskon() {
        int[] diskon = {5, 10, 15, 20};
    }
}

public class Tugas {
    public static void main(String[] args) {
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
    }
}
