import com.google.gson.Gson;
import de.vandermeer.asciitable.AsciiTable;
import okhttp3.*;
import java.io.IOException;

public class App
{
    public OkHttpClient client;
    private String endpointString;

    public App(String endpoint)
    {
        client = new OkHttpClient();
        endpointString = endpoint;
    }

    private Pizza[] getMenu() throws IOException
    {
        Request request = new Request.Builder()
                .url(endpointString + "/pizze") //Costruisco l'endpoin in modo "dinamico".
                .build();

        try (Response response = client.newCall(request).execute())
        {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            Gson gson = new Gson();
            //Deserializzo la risposta JSON in un array di oggetti Pizza.
            return gson.fromJson(response.body().string(), Pizza[].class);
        }
    }

    public Pizza[] getPizza(String id) throws IOException
    {
        Request request = new Request.Builder()
                .get()
                .url(endpointString + "/pizze/" + id) //Costruisco l'endpoin in modo "dinamico".
                .build();

        try (Response response = client.newCall(request).execute())
        {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            Gson gson = new Gson();
            //Deserializzo la risposta JSON in un array di oggetti Pizza.
            return gson.fromJson(response.body().string(), Pizza[].class);
        }
    }

    public void printMenu()
    {
        try
        {
            Pizza[] pizze = getMenu();


            if (pizze.length == 0)
            {
                System.out.println("Nessuna pizza presente.");
            } 
            else
            {
                AsciiTable asciiTable = new AsciiTable();
                asciiTable.addRow("Nome", "Ingredienti",  "Prezzo", "ID");
                asciiTable.addRule();

                for (Pizza p : pizze)
                {
                    asciiTable.addRow(p.nome, p.ingredienti,  p.prezzo, p._id);
                    asciiTable.addRule();
                }

                System.out.println(asciiTable.render());
            }
        }
        catch (Exception e)
        {
            System.out.println("Errore nel recupero del menù: " + e.getMessage());
        }
    }

    public void addPizza(Pizza pizza)
    {
        Gson gson = new Gson();
        String json = gson.toJson(pizza); //Serializzo l'oggetto Pizza in JSON.

        RequestBody body = RequestBody.create(json, MediaType.parse("application/json"));
        Request request = new Request.Builder()
                .url(endpointString + "/pizze")
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute())
        {
            if (!response.isSuccessful())
            {
                System.out.println("Errore nell'aggiunta della pizza: " + response);
            } 
            else
            {
                System.out.println("Pizza aggiunta con successo!");
            }
        } 
        catch (Exception e)
        {
            System.out.println("Errore: " + e.getMessage());
        }
    }

    public void deletePizza(String id)
    {
        //Elimina la pizza tramite il suo ID univoco.
        Request request = new Request.Builder()
                .url(endpointString + "/pizze/" + id)
                .delete()
                .build();

        try (Response response = client.newCall(request).execute())
        {
            if (!response.isSuccessful())
            {
                System.out.println("Errore nell'eliminazione della pizza: " + response);
            } 
            else
            {
                System.out.println("Pizza eliminata con successo!");
            }
        } 
        catch (Exception e) 
        {
            System.out.println("Errore: " + e.getMessage());
        }
    }

    public String getMenuJson()
    {
        try
        {
            Pizza[] pizze = getMenu();
            Gson gson = new Gson();
            return gson.toJson(pizze); //Restituisce il menù come stringa JSON.
        } 
        catch (Exception e) 
        {
            return "[]"; //Se errore, restituisce array vuoto.
        }
    }
}
