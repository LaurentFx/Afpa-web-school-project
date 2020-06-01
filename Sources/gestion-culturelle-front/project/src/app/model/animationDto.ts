import { User } from "./user";

export class AnimationDto {
    id: number;
    label: string;
    type: string;
    prix: number;
    nbreSpectateursPrevus: number;
    animateur:User;
    

    constructor(id?: number,
        label?: string,
        type?: string,
        prix? : number,
        nbreSpectateursPrevus?: number,
        animateur?:User ){
        this.id = id;
        this.label=label;
        this.type = type;
        this.prix = prix;
        this.nbreSpectateursPrevus = nbreSpectateursPrevus;
        this.animateur = animateur;
       
    }
}


