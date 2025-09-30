//Classe per deserializzare il json preso dall'API

public class Pizza
{
    public String nome;
    public String ingredienti;
    public double prezzo;

    @Override //Override serve per stampare quello che vogliamo perchè di base scrive l'id di base del json
    public String toString()
    {
        return nome + ": " +  ingredienti + ". " + prezzo + "€";
    }
}
