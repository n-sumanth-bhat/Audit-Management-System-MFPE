export class LoginStatus{
    private status : boolean = false;

    constructor(){}

    get Status() : boolean{
        return this.status;
    }

    set Status(status : boolean){
        this.status = status;
    }
}