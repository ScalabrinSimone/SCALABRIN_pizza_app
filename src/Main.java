import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

public class Main
{
    public static void main(String[] args)
    {
        boolean firstRun = true;
        Scanner scanner = new Scanner(System.in);
        App app = null;

        while (true)
        {
            if (firstRun)
            {
                String endpoint = "";
                while (true)
                {
                    System.out.println("\n\nInserisci l'endopoint personale recuperato da https://crudcrud.com/ ...");
                    endpoint = scanner.nextLine();

                    //Regex: controlla che l'endpoint inizi con https://crudcrud.com/api/ seguito da almeno un carattere.
                    //^ = inizio stringa, \. = punto letterale, .+ = almeno un carattere qualsiasi.
                    if (endpoint.matches("^https://crudcrud\\.com/api/.+"))
                    {
                        break;
                    }
                    else
                    {
                        System.out.println("Endpoint non valido! Riprova.");
                    }
                }

                app = new App(endpoint);
                firstRun = false;

                System.out.println("\nBenvenuto nella pizzeria piú fiera del mondo!\n");
            }

            System.out.println("Menu di selezione:");
            System.out.println("\t1. Visualizza Menù.\n\t2. Nuova pizza.\n\t3. Elimina Pizza.\n\t4. Exit&Save.\n");
            System.out.println("Indichi che operazione vuole effettuare (1, 2, 3 o 4): ");

            switch (scanner.nextLine())
            {
                case "1": //Visualizza Menù.
                    app.printMenu();
                    break;

                case "2": //Nuova pizza.
                    System.out.print("Nome pizza: ");
                    String nome = scanner.nextLine();
                    System.out.print("Ingredienti: ");
                    String ingredienti = scanner.nextLine();
                    System.out.print("Prezzo: ");
                    double prezzo = Double.parseDouble(scanner.nextLine());
                    Pizza nuovaPizza = new Pizza();
                    nuovaPizza.nome = nome;
                    nuovaPizza.ingredienti = ingredienti;
                    nuovaPizza.prezzo = prezzo;
                    app.addPizza(nuovaPizza);
                    break;

                case "3": //Elimina Pizza.
                    System.out.print("ID della pizza da eliminare: ");
                    String id = scanner.nextLine(); //L'ID viene mostrato tra parentesi quadre nel menù.
                    app.deletePizza(id);
                    break;

                case "4": //Exit.
                    //Salva il menù delle pizze in un file JSON nella cartella backup.
                    try
                    {
                        File backupDir = new File("backup");
                        if (!backupDir.exists()) backupDir.mkdir(); //Crea la cartella se non esiste.
                        String json = app.getMenuJson(); //Ottieni il JSON del menù.
                        FileWriter writer = new FileWriter("backup/menu_backup.json");
                        writer.write(json);
                        writer.close();
                        System.out.println("Menù salvato in backup/menu_backup.json!");
                    } 
                    catch (Exception e)
                    {
                        System.out.println("Errore nel salvataggio del backup: " + e.getMessage());
                    }

                    System.out.println("Arrivederci!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Non ho capito! Riproviamo...\n");
                    break;
            }
        }
    }
}
