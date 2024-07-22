export class MyMarker{
    constructor(lat,lng,text){
        this.lat=lat;
        this.lng=lng;
        this.text=text;
    }
    getLat(){
        return this.lat;
    }
    getLng(){
        return this.lng;
    }
    getCoords(){
        return [this.lat,this.lng];
    }
    getText(){
        return this.text;
    }
}