import { SalleDto } from "./salleDto";
import { AnimationDto } from './animationDto';
import { User } from './user';

export class ManifestationDto {
    id: number;
    label: string;
    dateValidation: Date;
    validateur: User;
    animation: AnimationDto;
    dateDebut: Date;
    dateFin: Date;
    cout: number;
    salle: SalleDto;
    prixBillet: number;
    reservations: number;
    reservationsVip: number;

    constructor(id?: number,
        label?: string,
        dateValidation?: Date,
        validateur?: User,
        animation?: AnimationDto,
        dateDebut?: Date,
        dateFin?: Date,
        cout?: number,
        reservations?: number,
        reservationsVip?: number,
        prixBillet?: number,
        salle?: SalleDto,
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
        this.salle = salle;

    }
}


