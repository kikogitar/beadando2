package org.example;

import java.util.InputMismatchException;

public class Ujsag extends Kiadvany {
    class Datum {
        public int ev;
        public int nap;
        public int honap;

        public Datum() {
        }

        public Datum(Integer ev, Integer honap, Integer nap) {
            this.ev = ev;
            this.honap = honap;
            this.nap = nap;
        }
    }

    private String kiado;
    private Datum megjelent = new Datum();

    public Ujsag(int id) {
        try {
            this.id = id;
            System.out.print("Add meg a újság kiadóját: ");
            this.kiado = scanner.nextLine();
            System.out.print("Add meg a újság címét: ");
            this.cim = scanner.nextLine();
            System.out.print("Add meg a újság oldalszámát:");
            this.oldal = scanner.nextInt();
            System.out.print("Add meg a megjelenés évét:");
            int ev = scanner.nextInt();
            this.megjelent.ev=ev;
            System.out.print("Add meg a megjelenés hónapját:");
            int honap = scanner.nextInt();
            if (honap > 12 || honap <= 0) {
                throw new DateError(" ");
            }
            this.megjelent.honap=honap;
            System.out.print("Add meg a megjelenés napját:");
            int nap = scanner.nextInt();
            if (nap > 31 || nap <= 0) {
                throw new DateError(" ");
            }
            this.megjelent.nap=nap;
            scanner.nextLine();
        } catch (InputMismatchException ex) {
            //ex.printStackTrace();
            scanner.nextLine();
            System.out.println("Érvénytelen adat\n");
            return;
        } catch (DateError e) {
            //e.printStackTrace();
            scanner.nextLine();
            System.out.println("Érvénytelen adat\n");
            return;
        }
    }
    @Override
    public void modosit() {
        try {
            System.out.print("Add meg a újság kiadóját: ");
            this.kiado = scanner.nextLine();
            System.out.print("Add meg a újság címét: ");
            this.cim = scanner.nextLine();
            System.out.print("Add meg a újság oldalszámát:");
            this.oldal = scanner.nextInt();
            System.out.print("Add meg a megjelenés évét:");
            int ev = scanner.nextInt();
            this.megjelent.ev=ev;
            System.out.print("Add meg a megjelenés hónapját:");
            int honap = scanner.nextInt();
            if (honap > 12 || honap <= 0) {
                throw new DateError(" ");
            }
            this.megjelent.honap=honap;
            System.out.print("Add meg a megjelenés napját:");
            int nap = scanner.nextInt();
            if (nap > 31 || nap <= 0) {
                throw new DateError(" ");
            }
            this.megjelent.nap=nap;
            scanner.nextLine();
        } catch (InputMismatchException ex) {
            //ex.printStackTrace();
            scanner.nextLine();
            System.out.println("Érvénytelen adat\n");
            return;
        } catch (DateError e) {
            //e.printStackTrace();
            scanner.nextLine();
            System.out.println("Érvénytelen adat\n");
            return;
        }
    }

    public Datum getMegjelent() {
        return megjelent;
    }
}
