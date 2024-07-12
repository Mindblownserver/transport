class TripEvent{
    constructor(id, start, end, resourceId, title, description, serviceId , directionId , haveret , timeNret ,
        tripNid , grp , chauffPr , chauffRe , etat , timeDepartR , timeArriveR ,
        vMax , avanceRetard , changement , metaData , deValid , alert,recPr, recRe, ligneId, busRe, busPrId) {
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
            case "Agent":
                this.resource = String(this.chauffRe) + String(this.recRe);
                break;
            default:
                break;
        }
    }
}
export {TripEvent}

/* 
    {
      id: trip.tripsId.trip_id,
      start: moment(trip.timeDepart,"DD/MM/YYYY HH:mm:ss").toDate(),
      end: moment(trip.finalStopTime,"DD/MM/YYYY HH:mm:ss").toDate(),
      title:trip.tripName,
      resource:trip.busRe.bus_id,
      description: "Description of event Test",
      allDay: false,
      bufferBefore: 0,
      free: false,
    }
    */