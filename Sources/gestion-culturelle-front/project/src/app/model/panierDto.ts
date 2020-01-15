import { ManifestationDto } from './manifestationDto';

export class PanierDto {
    id: number;
    dateValidation : Date;
    numClient: number;
    manifestation: ManifestationDto;
    nbreBillets: number;
    total : number;

    constructor(id?: number,
        numClient?: number,
        manifestation?: ManifestationDto,
        nbreBillets? : number, ){
        this.id = id;
        this.numClient=numClient;
        this.manifestation = manifestation;
        this.nbreBillets = nbreBillets;       
       
    }
}

