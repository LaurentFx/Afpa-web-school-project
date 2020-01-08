export class User {
    id: number;
    nom: string;
    prenom: string;
    login: string;
    password: string;

    constructor(id?: number, nom?: string, prenom?: string){
        this.id = id;
        this.nom= nom;
        this.prenom = prenom;
    }

}
