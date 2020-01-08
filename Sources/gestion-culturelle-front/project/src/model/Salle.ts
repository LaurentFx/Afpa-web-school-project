export class SalleModel {
    id: number;
    placesReservees: number;
    placesReserveesVip: number;
    fraisJournalier: number;

    constructor(id?: number, param1?: number,param2? : number, param3? : number ){
        this.id = id;
        this.placesReservees = param1;
        this.placesReserveesVip = param2;
        this.fraisJournalier = param3;
    }
}
