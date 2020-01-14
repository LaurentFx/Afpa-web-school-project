import { SalleDto } from "./salleDto";
import { AnimationDto } from './animationDto';

export class ManifestationDto {
    id: number;
    label: string;
    animation:AnimationDto;
    date: Date;
    cout: number;
    salle:SalleDto;
    prixBillet: number;
    reservations:number;
    reservationsVip:number;
    rentabilte:number;

    constructor(id?: number,
         label?: string,
          animation?: AnimationDto,
          date?: Date,
          cout?: number,
        reservations? : number,
        reservationsVip? : number,
         rentabilte? : number,
        prixBillet? : number,
         salle? : SalleDto ){

        this.id = id;
        this.label=label;
        this.animation=animation;
        this.date = date;
        this.cout=cout;
        this.prixBillet=prixBillet;
        this.reservations=reservations;
        this.reservationsVip=reservationsVip;
        this.rentabilte=rentabilte;     
        this.salle = salle;
    }
}


