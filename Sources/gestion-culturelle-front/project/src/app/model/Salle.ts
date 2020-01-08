export class SalleModel {
    id: number;
    label: string;
    placesReservees: number;
    placesReserveesVip: number;
    fraisJournalier: number;

    constructor(id?: number, label?: string, param1?: number,param2? : number, param3? : number ){
        this.id = id;
        this.label=label;
        this.placesReservees = param1;
        this.placesReserveesVip = param2;
        this.fraisJournalier = param3;
    }
}
