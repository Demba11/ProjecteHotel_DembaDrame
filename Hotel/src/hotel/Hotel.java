package hotel;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Hotel {
    static Scanner sc = new Scanner(System.in);
    
    // ArrayLists globals per emmagatzemar objectes (com demana l'enunciat)
    static List<Client> clients = new ArrayList<>();
    static List<Habitacio> habitacions = new ArrayList<>();
    static List<Servei> serveis = new ArrayList<>();
    static List<Estada> estades = new ArrayList<>();
    
    static final double IVA = 0.16;

    // ===== MÈTODE ALTA CLIENT (OPCIÓ 1) =====
    public static void altaClient() {
        System.out.print("NIF: ");
        String nif = sc.nextLine();
        if (clientExisteix(nif)) {
            System.out.println("Client ja existeix!");
            return;
        }
        System.out.print("Nom: ");
        String nom = sc.nextLine();
        System.out.print("Data naixement (dd/MM/yyyy): ");
        String dataNaix = sc.nextLine();
        System.out.print("Localitat: ");
        String localitat = sc.nextLine();

        Client nouClient = new Client(nif, nom, dataNaix, localitat, false);
        clients.add(nouClient);
        System.out.println("Client creat: " + nom);
    }

    // ===== MÈTODE LLISTAR CLIENTS (OPCIÓ 2) =====
    public static void llistarClients() {
        if (clients.isEmpty()) {
            System.out.println("No hi ha clients.");
            return;
        }
        for (Client c : clients) {
            System.out.println(c);
        }
    }

    // ===== MÈTODE MODIFICAR CLIENT (OPCIÓ 3) =====
    public static void modificarClient() {
        System.out.print("NIF client a modificar: ");
        String nif = sc.nextLine();
        Client client = buscarClient(nif);
        if (client == null) {
            System.out.println("Client no trobat!");
            return;
        }
        System.out.print("Nou nom (" + client.getNom() + "): ");
        String nouNom = sc.nextLine();
        if (!nouNom.isEmpty()) client.setNom(nouNom);
        
        System.out.print("Nova localitat (" + client.getLocalitat() + "): ");
        String novaLocalitat = sc.nextLine();
        if (!novaLocalitat.isEmpty()) client.setLocalitat(novaLocalitat);
        
        System.out.println("Client modificat!");
    }

    // ===== SUBMENÚ CLIENT (OPCIÓ 4) =====
    public static void menuClient() {
        System.out.print("NIF client: ");
        String nif = sc.nextLine();
        Client client = buscarClient(nif);
        if (client == null) {
            System.out.println("Client no trobat!");
            return;
        }
        
        int op;
        do {
            System.out.println("\n--- SUBMENÚ " + client.getNom() + " ---");
            System.out.println("1. Checkin");
            System.out.println("2. Servei Bugaderia");
            System.out.println("3. Servei Esmorzar");
            System.out.println("4. Servei Dinar");
            System.out.println("5. Servei Sopar");
            System.out.println("6. Consultar import actual");
            System.out.println("7. Checkout");
            System.out.println("0. Tornar");
            System.out.print("Opció: ");
            op = Integer.parseInt(sc.nextLine());

            switch (op) {
                case 1: checkin(client); break;
                case 2: afegirServeiClient(client, 1); break;
                case 3: afegirServeiClient(client, 2); break;
                case 4: afegirServeiClient(client, 3); break;
                case 5: afegirServeiClient(client, 4); break;
                case 6: consultarImportClient(client); break;
                case 7: checkout(client); break;
                case 0: break;
                default: System.out.println("Opció invàlida.");
            }
        } while (op != 0);
    }

    // ===== CHECKIN (crea Estada i assigna habitació) =====
    public static void checkin(Client client) {
        if (estadaActivaClient(client)) {
            System.out.println("Client ja té estada activa!");
            return;
        }
        
        Habitacio hab = assignarHabitacioLliure();
        if (hab == null) {
            System.out.println("No hi ha habitacions lliures!");
            return;
        }
        
        Estada estada = new Estada(client, hab, new ArrayList<>(), LocalDateTime.now(), null, 0, 0);
        estades.add(estada);
        System.out.println("Checkin fet! Habitació: " + hab.getNumero());
    }

    // ===== AFEGIR SERVEI A CLIENT =====
    public static void afegirServeiClient(Client client, int idServei) {
        if (!estadaActivaClient(client)) {
            System.out.println("Client sense estada activa!");
            return;
        }
        Estada estada = obtenirEstadaActiva(client);
        Servei servei = buscarServei(idServei);
        if (servei != null) {
            estada.getServeis().add(servei);
            System.out.println("Servei afegit: " + servei.getDescripcio());
        }
    }

    // ===== CONSULTAR IMPORT CLIENT =====
    public static void consultarImportClient(Client client) {
        if (!estadaActivaClient(client)) {
            System.out.println("Client sense estada activa!");
            return;
        }
        Estada estada = obtenirEstadaActiva(client);
        double total = 0;
        for (Servei s : estada.getServeis()) total += s.getPreu();
        System.out.printf("Import serveis: %.2f€\n", total);
    }

    // ===== CHECKOUT CLIENT =====
    public static void checkout(Client client) {
        if (!estadaActivaClient(client)) {
            System.out.println("Client sense estada activa!");
            return;
        }
        Estada estada = obtenirEstadaActiva(client);
        
        LocalDateTime sortida = LocalDateTime.now();
        estada.setDataHoraSortida(sortida);
        
        long dies = ChronoUnit.DAYS.between(estada.getDataHoraEntrada().toLocalDate(), sortida.toLocalDate());
        if (dies == 0) dies = 1;
        
        double preuHab = dies * estada.getHabitacio().getPreuNit();
        double preuServeis = 0;
        for (Servei s : estada.getServeis()) preuServeis += s.getPreu();
        
        double total = (preuHab + preuServeis) * (1 + IVA);
        estada.setFacturaFinal(total);
        estada.setImportActualGastat(preuServeis);
        
        estada.getHabitacio().setOcupada(false);
        client.setCobrat(true);
        
        System.out.println("FACTURA " + client.getNom() + ":");
        System.out.printf("Dies: %d, Habitació: %.2f€, Serveis: %.2f€, Total: %.2f€\n", 
                         dies, preuHab, preuServeis, total);
    }

    // ===== ALTA HABITACIÓ (OPCIÓ 5) =====
    public static void altaHabitacio() {
        System.out.print("Número habitació: ");
        int numero = Integer.parseInt(sc.nextLine());
        System.out.print("Preu per nit: ");
        double preu = Double.parseDouble(sc.nextLine());
        Habitacio hab = new Habitacio(numero, preu, false);
        habitacions.add(hab);
        System.out.println("Habitació " + numero + " creada.");
    }

    // ===== LLISTAR HABITACIONS (OPCIÓ 6) =====
    public static void llistarHabitacions() {
        for (Habitacio h : habitacions) {
            System.out.println(h);
        }
    }

    // ===== MODIFICAR HABITACIÓ (OPCIÓ 7) =====
    public static void modificarHabitacio() {
        System.out.print("Número habitació: ");
        int numero = Integer.parseInt(sc.nextLine());
        Habitacio hab = buscarHabitacio(numero);
        if (hab == null) {
            System.out.println("Habitació no trobada!");
            return;
        }
        System.out.print("Nou preu (" + hab.getPreuNit() + "€): ");
        String nouPreu = sc.nextLine();
        if (!nouPreu.isEmpty()) {
            hab.setPreuNit(Double.parseDouble(nouPreu));
        }
        System.out.println("Habitació modificada!");
    }

    // ===== ALTA SERVEI (OPCIÓ 8) =====
    public static void altaServei() {
        System.out.print("ID servei: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.print("Descripció: ");
        String desc = sc.nextLine();
        System.out.print("Preu: ");
        double preu = Double.parseDouble(sc.nextLine());
        serveis.add(new Servei(id, desc, preu));
        System.out.println("Servei creat!");
    }

    // ===== LLISTAR SERVEIS (OPCIÓ 9) =====
    public static void llistarServeis() {
        for (Servei s : serveis) {
            System.out.println(s);
        }
    }

    // ===== MÈTODE AUXILIARS =====
    public static boolean clientExisteix(String nif) {
        for (Client c : clients) if (c.getNif().equals(nif)) return true;
        return false;
    }
    
    public static Client buscarClient(String nif) {
        for (Client c : clients) if (c.getNif().equals(nif)) return c;
        return null;
    }
    
    public static Habitacio buscarHabitacio(int numero) {
        for (Habitacio h : habitacions) if (h.getNumero() == numero) return h;
        return null;
    }
    
    public static Servei buscarServei(int id) {
        for (Servei s : serveis) if (s.getId() == id) return s;
        return null;
    }
    
    public static boolean estadaActivaClient(Client client) {
        for (Estada e : estades) {
            if (e.getClient().equals(client) && e.getDataHoraSortida() == null) return true;
        }
        return false;
    }
    
    public static Estada obtenirEstadaActiva(Client client) {
        for (Estada e : estades) {
            if (e.getClient().equals(client) && e.getDataHoraSortida() == null) return e;
        }
        return null;
    }
    
    public static Habitacio assignarHabitacioLliure() {
        for (Habitacio h : habitacions) {
            if (!h.isOcupada()) {
                h.setOcupada(true);
                return h;
            }
        }
        return null;
    }

    // ===== MENÚ PRINCIPAL (11 OPCIONS COM L'ENUNCIAT) =====
    public static void main(String[] args) {
        // Inicialització inicial (pots afegir-ne més des del menú)
        serveis.add(new Servei(1, "Bugaderia", 10));
        serveis.add(new Servei(2, "Esmorzar", 11));
        serveis.add(new Servei(3, "Dinar", 20));
        serveis.add(new Servei(4, "Sopar", 18));

        for (int i = 1; i <= 3; i++) {
            habitacions.add(new Habitacio(i, 60, false));
        }

        int op;
        do {
            System.out.println("\n|-------- MENU HOTEL --------|");
            System.out.println("1. Alta nou client");
            System.out.println("2. Llistar clients");
            System.out.println("3. Modificar dades client");
            System.out.println("4. Operacions client (SUBMENÚ)");
            System.out.println("5. Alta habitació");
            System.out.println("6. Llistar habitacions");
            System.out.println("7. Modificar habitació");
            System.out.println("8. Nou servei");
            System.out.println("9. Llistar serveis");
            System.out.println("10. Modificar servei");
            System.out.println("11. Sortir");
            System.out.print("Opció: ");
            op = Integer.parseInt(sc.nextLine());

            switch (op) {
                case 1: altaClient(); break;
                case 2: llistarClients(); break;
                case 3: modificarClient(); break;
                case 4: menuClient(); break;
                case 5: altaHabitacio(); break;
                case 6: llistarHabitacions(); break;
                case 7: modificarHabitacio(); break;
                case 8: altaServei(); break;
                case 9: llistarServeis(); break;
                case 10: System.out.println("Funcionalitat pendent"); break;
                case 11: System.out.println("Sortint..."); break;
                default: System.out.println("Opció invàlida.");
            }
        } while (op != 11);
    }
}