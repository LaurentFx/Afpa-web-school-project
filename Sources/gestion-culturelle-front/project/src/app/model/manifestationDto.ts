import { SalleDto } from "./salleDto";
import { AnimationDto } from './animationDto';
import { AdminDto } from "./adminDto";

export class ManifestationDto {
    id: number;
    label: string;
    dateValidation: Date;
    validateur: AdminDto;
    animation: AnimationDto;
    dateDebut: Date;
    dateFin: Date;
    cout: number;
    salle: SalleDto;
    prixBillet: number;
    reservations: number;
    reservationsVip: number;
    rentabilte: number;
    annulateur: AdminDto;
    dateAnnulation: Date;


    constructor(id?: number,
        label?: string,
        dateValidation?: Date,
        validateur?: AdminDto,
        animation?: AnimationDto,
        dateDebut?: Date,
        dateFin?: Date,
        cout?: number,
        reservations?: number,
        reservationsVip?: number,
        rentabilte?: number,
        prixBillet?: number,
        salle?: SalleDto,
        annulateur?: AdminDto,
        dateAnnulation?: Date,
    ) {

        this.id = id;
        this.label = label;
        this.dateValidation = dateValidation;
        this.validateur = validateur;
        this.animation = animation;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.cout = cout;
        this.prixBillet = prixBillet;
        this.reservations = reservations;
        this.reservationsVip = reservationsVip;
        this.rentabilte = rentabilte;
        this.salle = salle;
        this.annulateur = annulateur;
        this.dateAnnulation = dateAnnulation;

    }
}


