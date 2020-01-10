import { TypeDeSalleModel } from "./type-de-salle";

export class SalleModel {
    id: number;
    label: string;
    placesReservees: number;
    placesReserveesVip: number;
    fraisJournalier: number;
    typeSalle:TypeDeSalleModel;

    constructor(id?: number, 
        param1?: string, 
        param2?: number,
        param3? : number, 
        param4? : number,
        typeSalle? : TypeDeSalleModel ){
        this.id = id;
        this.label=param1;
        this.placesReservees = param2;
        this.placesReserveesVip = param3;
        this.fraisJournalier = param4;
        this.typeSalle = typeSalle;
    }
}
