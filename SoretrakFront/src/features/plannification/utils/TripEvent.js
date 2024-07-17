class TripEvent{
    constructor(id, start, end, resourceId, title, description, serviceId , directionId , haveret , timeNret ,
        tripNid , grp , chauffPr , chauffRe , etat , timeDepartR , timeArriveR ,
        vMax , avanceRetard , changement , metaData , deValid , alert,recPr, recRe, ligneId, busRe, busPrId, decdeleg,deccent) {
    this.id = id;
    this.start = start;
    this.end = end;
    this.resource = resourceId;
    this.title = title;
    this.description = description;
    this.serviceId = serviceId;
    this.directionId = directionId;
    this.haveret = haveret;
    this.timeNret = timeNret;
    this.tripNid = tripNid;
    this.grp = grp;
    this.chauffPr = chauffPr;
    this.chauffRe = chauffRe;
    this.etat = etat;
    this.timeDepartR = timeDepartR;
    this.timeArriveR = timeArriveR;
    this.vMax = vMax;
    this.avanceRetard = avanceRetard;
    this.changement = changement;
    this.metaData = metaData;
    this.deValid = deValid;
    this.alert = alert;
    this.recPr = recPr;
    this.recRe = recRe;
    this.ligneId = ligneId;
    this.busReId = busRe;
    this.busPrId = busPrId;
    this.delegId = decdeleg;
    this.centreId=deccent;
    // Mbscroll-specific settings
    this.allDay= false;
    this.bufferBefore= 0;
    this.free= false;
    }
    changeResourceId(mode){
        switch (mode) {
            case "Lignes":
                this.resource = this.ligneId;
                break;
            case "Bus":
                this.resource = this.busPrId;
                break;
            case "Agents":
                this.resource =`${this.chauffPr}|${this.recPr}`;
                break;
            default:
                break;
        }
    }
}
export {TripEvent}