# Pizza App

Progetto per allenarsi all'uso del GET POST e PUT delle API: Gestisce il menù di una pizzeria tramite CLI e CRUDCRUD API.

## Funzionalità

- Visualizza il menù delle pizze
- Aggiungi una nuova pizza
- Elimina una pizza tramite ID
- Salva il menù in locale alla chiusura (`backup/menu_backup.json`)

## Utilizzo

1. **Avvio**
   - Inserisci il tuo endpoint personale di CRUDCRUD.
2. **Menu CLI**
   ```
   1. Visualizza Menù.
   2. Nuova pizza.
   3. Elimina Pizza.
   4. Exit.
   ```
3. **Visualizza Menù**
   - Mostra tutte le pizze con nome, ingredienti, prezzo e ID.
   ```
   id:[64f...abc] Margherita: pomodoro, mozzarella. 5.0EUR.
   id:[64f...def] Diavola: pomodoro, mozzarella, salame piccante. 6.5EUR.
   ```
4. **Aggiungi Pizza**
   - Inserisci nome, ingredienti e prezzo.
5. **Elimina Pizza**
   - Inserisci l'ID mostrato nel menù.
6. **Exit**
   - Salva automaticamente il menù in `backup/menu_backup.json`.

## Backup

- Alla chiusura, il menù viene salvato in `backup/menu_backup.json`.
- La cartella `backup/` è ignorata dal versionamento (`.gitignore`).

## Esempio Visivo

```text
Benvenuto nella pizzeria piú fiera del mondo!

Menu di selezione:
    1. Visualizza Menù.
    2. Nuova pizza.
    3. Elimina Pizza.
    4. Exit.

Indichi che operazione vuole effettuare (1, 2, 3 o 4): 1

id:[64f...abc] Margherita: pomodoro, mozzarella. 5.0EUR.
id:[64f...def] Diavola: pomodoro, mozzarella, salame piccante. 6.5EUR.
```

## Requisiti

- Java 8+
- Librerie: OkHttp, Gson

## Note

- Il backup locale **NON** viene caricato automaticamente all'avvio.
- Per vedere il simbolo euro, usa "EUR" per compatibilità con Windows.
