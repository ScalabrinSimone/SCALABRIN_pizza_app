public class Main
{
    public static void main(String[] args)
    {
        App app = new App();
        app.run(); //Esecuzione spostata su un'altra classe senza dover dichiarare tutte le vatiabili static.

        //Dobbiamo fare la chiamata al web service preferibilmente in modo asincorno, così da mi migliorare la UX utente.

    }
}
