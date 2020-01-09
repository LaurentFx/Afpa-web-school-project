import { SalleModel } from "./Salle";

export class Manifestation {
    id: number;
    nom: string;
    dateManifestation: Date;
    typeManifestation: string;
    prixBillet: number;
    salle:SalleModel;

    constructor(id?: number, nom?: string, dateManifestation?: Date,typeManifestation? : string, prixBillet? : number, salle? : SalleModel ){
        this.id = id;
        this.nom=nom;
        this.dateManifestation = dateManifestation;
        this.typeManifestation = typeManifestation;
        this.prixBillet = prixBillet;
        this.salle = salle;
    }
}


