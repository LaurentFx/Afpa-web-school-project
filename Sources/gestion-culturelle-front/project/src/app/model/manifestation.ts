import { SalleDto } from "./salleDto";

export class Manifestation {
    id: number;
    nom: string;
    dateManifestation: Date;
    typeManifestation: string;
    prixBillet: number;
    salle:SalleDto;

    constructor(id?: number, nom?: string, dateManifestation?: Date,typeManifestation? : string, prixBillet? : number, salle? : SalleDto ){
        this.id = id;
        this.nom=nom;
        this.dateManifestation = dateManifestation;
        this.typeManifestation = typeManifestation;
        this.prixBillet = prixBillet;
        this.salle = salle;
    }
}


