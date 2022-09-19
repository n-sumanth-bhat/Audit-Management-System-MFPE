export class AuthenticationRequest{
    private username : string = "";
    private password : string = "";
    
    constructor(
    ){}

    public get Username() : string{
        return this.username;
    }

    public set Username(username : string) {
        this.username = username;
    }
    
    public get Password() : string{
        return this.password;
    }
    
    public set Password(password : string){
        this.password = password;
    }
}