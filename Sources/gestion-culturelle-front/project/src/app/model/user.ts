import { RoleDto } from './roleDto';

export class User {
    id: number;
    nom: string;
    prenom: string;
    email: string;
    adresse: string;
    numClient: string;
    role: RoleDto;
    entreprise: string;
 

    constructor(id?: number, nom?: string, prenom?: string, email?: string,
        adresse?: string, numClient?: string, role?: RoleDto,
        entreprise?: string, 

    ) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.adresse = adresse;
        this.numClient = numClient;
        this.role = role;
        this.entreprise = entreprise;
      

    }

}
