import { RoleDto } from './roleDto';
//import { PanierDto } from './panierDto';

export class User {
    id: number;
    nom: string;
    prenom: string;
    email: string;
    adresse: string;
    numClient: string;
  //  panier: PanierDto;
    role: RoleDto;
    entreprise: string;
 
 //  listManifestations: ManifestationDto; 

    constructor(id?: number, nom?: string, prenom?: string, email?: string,
        adresse?: string, numClient?: string, role?: RoleDto,
        // panier?: PanierDto,
        entreprise?: string, 
    //     listManifestations?: ManifestationDto

    ) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.adresse = adresse;
        this.numClient = numClient;
       // this.panier = panier;
        this.role = role;
        this.entreprise = entreprise;
      
      //  this.listManifestations = listManifestations;

    }

}
