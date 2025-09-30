import com.google.gson.Gson;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class App
{
    public OkHttpClient client;

    public App() //Costruttore su java
    {
        client = new OkHttpClient(); //Istanziato il client
    }

    public void doGet() throws IOException //Se il metodo può generare eccezzioni, bisogna scriverlo nella firma
    {
        Request request = new Request.Builder()
                .url("https://crudcrud.com/api/abc242637a6246a4bb7353d9344eb781/pizze")
                .build();

        try (Response response = client.newCall(request).execute())
        {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            Headers responseHeaders = response.headers();
            for (int i = 0; i < responseHeaders.size(); i++) {
                System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
            }

            Gson gson = new Gson();
            Pizza[] pizze = gson.fromJson(response.body().string(), Pizza[].class); //Prendiamo il json per deserializzare le pizze

            for (Pizza p : pizze)
            {
                System.out.println(p);
            }
        }
    }
    public void run()
    {
        try
        {
            doGet();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
