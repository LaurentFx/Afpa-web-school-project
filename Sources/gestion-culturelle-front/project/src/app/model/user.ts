import { RoleDto } from './roleDto';
import { PanierDto } from './panierDto';

export class User {
    id: number;
    nom: string;
    prenom: string;
    email: string;
    adresse: string;
    role: RoleDto;
    numClient:string;
    panier: PanierDto;
   

    constructor(id?: number, nom?: string, prenom?: string, email?:string,
         adresse?: string, numClient?:string, role?: RoleDto, panier?: PanierDto){
        this.id = id;
        this.nom= nom;
        this.prenom = prenom;
        this.email = email;
        this.adresse = adresse;
        this.numClient = numClient;
        this.role= role;
       this.panier = panier;
        
    }

}
