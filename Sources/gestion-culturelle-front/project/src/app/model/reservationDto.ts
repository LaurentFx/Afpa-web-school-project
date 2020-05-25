import { ManifestationDto } from './manifestationDto';
import { User } from './user';


export class ReservationDto {
    id: number;
    manifestation: ManifestationDto;
    client:User;
    numClient:String;
    dateReservation:Date;
    total:number;


    constructor(id?: number,
        manifestation?: ManifestationDto,
        client?:User,
        numClient?:String,
        dateReservation?:Date,
        total?:number) {
        this.id = id;
        this.manifestation = manifestation;
        this.client=client;
        this.numClient=numClient;
        this.dateReservation=dateReservation;
        this.total=total

    }
}


