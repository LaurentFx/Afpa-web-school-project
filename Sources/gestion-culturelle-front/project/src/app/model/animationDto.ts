export class AnimationDto {
    id: number;
    label: string;
    type: string;
    cout: number;
    previsionSpectateurs: number;
    

    constructor(id?: number,
        label?: string,
        type?: string,
        cout? : number,
        previsionSpectateurs?: number ){
        this.id = id;
        this.label=label;
        this.type = type;
        this.cout = cout;
        this.previsionSpectateurs = previsionSpectateurs;
       
    }
}


