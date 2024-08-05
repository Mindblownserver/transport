export class MyMarker{
    constructor(lat,lng,statId, statNom){
        this.lat=lat;
        this.lng=lng;
        this.statId=statId;
        this.statNom = statNom
    }
    getLat(){
        return this.lat;
    }
    getLng(){
        return this.lng;
    }
    getCoords(){
        return [this.lng,this.lat];
    }
    getStatId(){
        return this.statId;
    }
    getStatNom(){
        return this.statNom;
    }
}

