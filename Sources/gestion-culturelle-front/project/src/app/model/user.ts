import { RoleDto } from './roleDto';

export class User {
    id: number;
    nom: string;
    prenom: string;
    email: string;
    role: RoleDto;

    constructor(id?: number, nom?: string, prenom?: string, email?:string, role?: RoleDto){
        this.id = id;
        this.nom= nom;
        this.prenom = prenom;
        this.email = email;
        this.role = role;
    }

}
