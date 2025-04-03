package fr.diginamic.recensement.services;

import java.util.List;
import java.util.Scanner;

import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.entites.Ville;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * Recherche et affichage de toutes les villes d'un département dont la
 * population est comprise entre une valeur min et une valeur max renseignées
 * par l'utilisateur.
 * 
 * @author DIGINAMIC
 *
 */
public class RecherchePopulationBorneService extends MenuService {

	@Override
	public void traiter(Recensement rec, Scanner scanner) throws RecensementException {

		System.out.println("Quel est le code du département recherché ? ");
		String choix = scanner.nextLine();

		System.out.println("Choississez une population minimum (en milliers d'habitants): ");
		String saisieMin = scanner.nextLine();

		if (!NumberUtils.isCreatable(saisieMin)){
			throw new RecensementException ("mettez des chiffres !");
		}
		System.out.println("Choississez une population maximum (en milliers d'habitants): ");
		String saisieMax = scanner.nextLine();

		if (!NumberUtils.isCreatable(saisieMax)){
			throw new RecensementException ("mettez des chiffres !");
		}

		int min = Integer.parseInt(saisieMin) * 1000;
		int max = Integer.parseInt(saisieMax) * 1000;

		List<Ville> villes = rec.getVilles();
		boolean found = false;
		for (Ville ville : villes) {

			if (ville.getCodeDepartement().equalsIgnoreCase(choix)) {
				if (ville.getPopulation() >= min && ville.getPopulation() <= max) {
					System.out.println(ville);
					found = true;
				}
			}



			if (min > max) {
				throw new RecensementException("Erreur : La population minimale ne peut pas être supérieure à la population maximale.");
			}

			if (min < 0) {
				throw new RecensementException("Erreur : La population minimale ne peut pas être inférieure à 0.");
			}
			if (max < 0) {
				throw new RecensementException("Erreur : La population maximale ne peut pas être inférieure à 0.");
			}

			if (!found) {
				System.out.println("Aucune ville ne correspond aux critères de population dans ce département.");
			}

		}

	}
}
