package org.example;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Book extends Kiadvany{

    private String szerzo;



    private Borito borito;

    public Book(int id, String szerzo, String cim, int oldal, Borito bor) {
        this.id = id;
        this.szerzo = szerzo;
        this.cim = cim;
        this.oldal = oldal;
        this.borito = bor;
    }

    public Book (int id) {
        try {this.id=id;
            System.out.print("Add meg a könyv szerzőjét: ");
            this.szerzo = scanner.nextLine();
            System.out.print("Add meg a könyv címét: ");
            this.cim = scanner.nextLine();
            System.out.print("Add meg a könyv oldalszámát:");
            this.oldal= scanner.nextInt();
            scanner.nextLine();
            System.out.print("Add meg a borító típusát:");
            String borito = scanner.nextLine();
            this.borito= Borito.valueOf(borito);
        } catch (InputMismatchException ex) {
            //ex.printStackTrace();
            scanner.nextLine();
            System.out.println("Érvénytelen adat\n");
            return;
        } catch (IllegalArgumentException ex) {
            //ex.printStackTrace();
            System.out.println("Érvénytelen adat\n");
            return;
        }
    }

    @Override
    public void modosit() {
        try {
            System.out.print("Add meg a könyv szerzőjét: ");
            this.szerzo=scanner.nextLine();
            System.out.print("Add meg a könyv címét: ");
            this.cim=scanner.nextLine();
            System.out.print("Add meg a könyv olodalszámát: ");
            this.oldal=scanner.nextInt();
            scanner.nextLine();
            System.out.print("Add meg a borító típusát:");
            this.borito=Borito.valueOf(scanner.nextLine());
            System.out.println("Sikeres változtatás\n");
            return;
        } catch (InputMismatchException ex) {
            scanner.nextLine();
            System.out.println("Érvénytelen adat\n");
            return;
        }catch (IllegalArgumentException ex) {
            //ex.printStackTrace();
            System.out.println("Érvénytelen adat\n");
            return;
        }
    }

    public String getSzerzo() {
        return szerzo;
    }

    public void setSzerzo(String szerzo) {
        this.szerzo = szerzo;
    }

    public void setBorito(Borito borito) {
        this.borito = borito;
    }


    public Borito getBorito() {
        return borito;
    }

}
