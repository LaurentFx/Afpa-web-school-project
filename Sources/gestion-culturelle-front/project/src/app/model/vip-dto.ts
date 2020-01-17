import { RoleDto } from './roleDto';

export class VipDto {
    id: number;
    nom: string;
    prenom: string;
    email: string;
    login: string;
    password: string;
    adresse: string;
    role: RoleDto;
    entreprise: string;

    constructor(id?: number,
        nom?: string,
        prenom?: string,
        email?: string,
        login?: string,
        password?: string,
        adresse?: string,
        role?: RoleDto,
        entreprise?: string
        ){
        this.id = id;
        this.nom= nom;
        this.prenom = prenom;
        this.email = email;
        this.login=login;
        this.password = password;
        this.adresse = adresse;
        this.role = role;
        this.entreprise = entreprise;
    }
}