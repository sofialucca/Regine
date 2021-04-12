package it.polito.tdp.regine.model;

import java.util.ArrayList;
import java.util.List;

public class Regine {

	// N è il numero di righe e colonne della scacchiera
	//   (righe e colonne numerate da 0 a N-1)
	// ad ogni livello posizioniamo una regina in una nuova riga
	
	// soluzione parziale: lista delle colonne in cui mettere le regine (prime righe)
	// 		List<Integer>
	// livello = quante righe sono già piene
	// livello = 0 => nessuna riga piena (devo mettere la regina nella riga 0)
	// livello = 3 => 3 righe piene (0, 1, 2), devo mettere la regina nella riga 3
	// [0]
	//     [0, 2]
	//            [0, 2, 1]
	private int N;
	private List<List<Integer>> soluzione;
	
	public List<List<Integer>>risolvi(int N){
		this.N=N;
		//meglio array perchè dopo si usa numerose volte get e con una LinkedList non conviene
		List<Integer >parziale=new ArrayList<Integer>();
		this.soluzione=new ArrayList<>();
		cerca(parziale, 0);
		return this.soluzione;

	}
	
	//cerca== true: trovato; cerca==false :cerca ancora
	private void cerca(List<Integer>parziale, int livello) {
		if(livello==N) {
			// caso terminale
			//System.out.println(parziale);
			//this.soluzione=parziale; // stiamo creando un nuovo riferimento ma senza copiare il valore
			this.soluzione.add(new ArrayList<>(parziale));
			
		} else {
			for(int colonna=0; colonna<N; colonna++) {
				// if la possa nella casella [livello][colonna] è valida
				// se sì, aggiungi a parziale e fai ricorsione
				if(posValida(parziale,colonna)) {
					parziale.add(colonna);
					cerca(parziale,livello+1);
								
					//posso creare nuova copia di parziale a cui aggiungo la nuova colonna, tale che non bisogna fare backtracking
						//-->non conveniente perchè molto costoso ricopiare dati

					parziale.remove(parziale.size()-1); //backtracking
				}
			}
		}
	}

	private boolean posValida(List<Integer> parziale, int colonna) {
		int livello=parziale.size();
		// controllo se viene mangiata in verticale
		if(parziale.contains(colonna)) {
			return false;
		}
		//controllo diagonali: confrontare (livello,colonna) con (r,c) delle regine già esistenti
		for(int r=0;r<livello;r++) {
			int c=parziale.get(r);
			if(r+c==livello+colonna||r-c==livello-colonna) {
				return false;
			}
		}
		return true;
	}
	
	
	
}
