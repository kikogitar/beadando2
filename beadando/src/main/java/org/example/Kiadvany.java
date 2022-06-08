package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Kiadvany {
    protected static final Scanner scanner = new Scanner(System.in);
    protected int id;
    protected String cim;
    protected Integer oldal;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCim() {
        return cim;
    }

    public void setCim(String cim) {
        this.cim = cim;
    }

    public Integer getOldal() {
        return oldal;
    }

    public void setOldal(Integer oldal) {
        this.oldal = oldal;
    }
    public void modosit() {
        try {
            System.out.print("Add meg a kiadvány címét: ");
            this.cim=scanner.nextLine();
            System.out.print("Add meg a kiadvány olodalszámát: ");
            this.oldal=scanner.nextInt();
            scanner.nextLine();
            System.out.println("Sikeres változtatás\n");
            return;
        } catch (InputMismatchException ex) {
            scanner.nextLine();
            System.out.println("Érvénytelen adat\n");
            return;
        }
    }
}
