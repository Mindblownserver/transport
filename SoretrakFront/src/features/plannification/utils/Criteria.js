class DrCentre {
    constructor(id, name, nameAr) {
        this.id = id;
        this.name = name;
        this.nameAr = nameAr;
    }
    get hash(){
        return this.id;
    }
}

class DrDeleg {
    constructor(id, name, nameAr) {
        this.id = id;
        this.name = name;
        this.nameAr = nameAr;
    }
    get hash(){
        return this.id;
    }
}

export {DrDeleg,DrCentre};