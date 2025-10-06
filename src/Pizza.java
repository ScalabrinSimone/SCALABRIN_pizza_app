//Classe per deserializzare il json preso dall'API.
//Il campo _id viene generato automaticamente da CRUDCRUD e serve per identificare univocamente la pizza.

public class Pizza
{
    public String _id; //ID univoco della pizza, usato per eliminare.
    public String nome;
    public String ingredienti;
    public double prezzo;

    @Override //Override serve per stampare quello che vogliamo perchè di base stampa il riferimento all'oggetto.
    public String toString()
    {
        //Mostra l'ID tra parentesi quadre se presente, utile per l'eliminazione.
        return (_id != null ? "id:[" + _id + "] " : "") + nome + ": " +  ingredienti + ". " + prezzo + "EUR.";
    }
}
