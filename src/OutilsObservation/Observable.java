package OutilsObservation;

public interface Observable {
	public void ajouterObservateur(Observateur obs);
	public void supprimerObservateurs();
	//a mettre quelque chose en argument en fonction du besoin
	public void prevenirObservateur();
}
