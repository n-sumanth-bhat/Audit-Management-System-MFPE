export class ProjectDetails{
    private name : string = "";
    private projectName : string = "";
    private valid : boolean = false;
    constructor(
        
    ){}

    public get Name() : string{
        return this.name;
    }

    public get ProjectName() : string{
        return this.projectName;
    }

    public get Valid() : boolean{
        return this.valid;
    }
    public set Name(name){
        this.name = name;
    }

    public set ProjectName(pname){
        this.projectName = pname;
    }

    public set Valid(valid){
        this.valid = valid;
    }
}