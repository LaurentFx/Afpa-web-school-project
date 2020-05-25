import { ManifestationDto } from './manifestationDto';
import { User } from './user';


export class InvitationDto {
    id: number;
    manifestation: ManifestationDto;
    vip:User;
    reponse:String;
    dateInvitation:Date;


    constructor(id?: number,
        manifestation?: ManifestationDto,
        vip?:User,
        reponse?:String,
        dateInvitation?:Date) {
        this.id = id;
        this.manifestation = manifestation;
        this.vip=vip;
        this.reponse=reponse;
        this.dateInvitation=dateInvitation

    }
}


