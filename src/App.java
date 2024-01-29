import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Carte {
    private String titlu;
    private String autor;
    private String isbn;

    public Carte(String titlu, String autor, String isbn) {
        this.titlu = titlu;
        this.autor = autor;
        this.isbn = isbn;
    }

    public String toString() {
        return "Carte{" +
                "titlu='" + titlu + '\'' +
                ", autor='" + autor + '\'' +
                ", isbn='" + isbn + '\'' +
                '}';
    }

    public String getIsbn() {
        return isbn;
    }
}

class Biblioteca {
    private List<Carte> carti;

    public Biblioteca() {
        this.carti = new ArrayList<>();
    }

    public void adaugaCarte(String titlu, String autor, String isbn) {
        carti.add(new Carte(titlu, autor, isbn));
    }

    public void eliminaCarte(String isbn) {
        Carte carteGasita = null;
        for (Carte carte : carti) {
            if (carte.getIsbn().equals(isbn)) {
                carteGasita = carte;
                break;
            }
        }

        if (carteGasita != null) {
            carti.remove(carteGasita);
            System.out.println("Carte eliminată: " + carteGasita);
        } else {
            System.out.println("Carte cu ISBN " + isbn + " nu a fost găsită.");
        }
    }

    public void afiseazaCarti() {
        System.out.println("Cărți în bibliotecă:");
        for (Carte carte : carti) {
            System.out.println(carte);
        }
    }

    public void afiseazaCarte(String isbn) {
        for (Carte carte : carti) {
            if (carte.getIsbn().equals(isbn)) {
                System.out.println("Carte găsită: " + carte);
                return;
            }
        }
        System.out.println("Carte cu ISBN " + isbn + " nu a fost găsită.");
    }
}

public class App {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        Scanner scanner = new Scanner(System.in);
        int optiune = 0;

        do {
            afiseazaMeniu();
            System.out.print("Introduceti numarul optiunii: ");

            try {
                optiune = scanner.nextInt();
                scanner.nextLine(); // Consumăm newline-ul
            } catch (java.util.InputMismatchException e) {
                System.out.println("Va rugam introduceti un numar valid.");
                scanner.nextLine(); // Consumăm inputul invalid
                continue;
            }

            switch (optiune) {
                case 1:
                    adaugaCarte(scanner, biblioteca);
                    break;
                case 2:
                    stocheazaCarte(scanner, biblioteca);
                    break;
                case 3:
                    afiseazaCarte(scanner, biblioteca);
                    break;
                case 4:
                    afiseazaToateCartile(biblioteca);
                    break;
                case 5:
                    stergeCarte(scanner, biblioteca);
                    break;
                case 6:
                    System.out.println("Programul se încheie. La revedere!");
                    break;
                default:
                    System.out.println("Optiune invalida. Va rugam alegeti din nou.");
                    break;
            }

        } while (optiune != 6);
    }

    private static void afiseazaMeniu() {
        System.out.println("1. Adauga carte");
        System.out.println("2. Stocheaza carte");
        System.out.println("3. Afiseaza o carte");
        System.out.println("4. Afiseaza toate cartile");
        System.out.println("5. Sterge carte");
        System.out.println("6. Iesire");
    }

    private static void adaugaCarte(Scanner scanner, Biblioteca biblioteca) {
        System.out.print("Introduceti titlul cartii: ");
        String titlu = scanner.nextLine();

        System.out.print("Introduceti autorul cartii: ");
        String autor = scanner.nextLine();

        System.out.print("Introduceti ISBN-ul cartii: ");
        String isbn = scanner.nextLine();

        biblioteca.adaugaCarte(titlu, autor, isbn);
        System.out.println("Carte adaugata cu succes.");
    }

    private static void stocheazaCarte(Scanner scanner, Biblioteca biblioteca) {
        System.out.print("Introduceti ISBN-ul cartii de stocat: ");
        String isbn = scanner.nextLine();

        biblioteca.eliminaCarte(isbn);
        System.out.println("Carte stocata cu succes.");
    }

    private static void afiseazaCarte(Scanner scanner, Biblioteca biblioteca) {
        System.out.print("Introduceti ISBN-ul cartii de afisat: ");
        String isbn = scanner.nextLine();

        biblioteca.afiseazaCarte(isbn);
    }

    private static void afiseazaToateCartile(Biblioteca biblioteca) {
        biblioteca.afiseazaCarti();
    }

    private static void stergeCarte(Scanner scanner, Biblioteca biblioteca) {
        System.out.print("Introduceti ISBN-ul cartii de sters: ");
        String isbn = scanner.nextLine();

        biblioteca.eliminaCarte(isbn);
        System.out.println("Carte stearsa cu succes.");
    }
}
