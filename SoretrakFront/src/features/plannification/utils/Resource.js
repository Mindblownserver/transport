class Ligne {
  constructor(
    idLigne,
    nomLigne,
    priorite,
    dectyta,
    dectyeq,
    denomla,
    detatec,
    detatea,
    destatu,
    denbrkm,
    deleg,
    deactif,
    agency_ID,
    route_TYPE,
    route_URL,
    route_COLOR,
    route_TEXT_COLOR,
    denomli_RET,
    integ_BI,
    decclie,
    decadmi,
    sae,
    type,
    centre
  ) {
    this.id = idLigne;
    this.name = nomLigne;
    this.priorite = priorite;
    this.dectyta = dectyta;
    this.dectyeq = dectyeq;
    this.denomla = denomla;
    this.detatec = detatec;
    this.detatea = detatea;
    this.destatu = destatu;
    this.denbrkm = denbrkm;
    this.deleg = deleg;
    this.deactif = deactif;
    this.agency_ID = agency_ID;
    this.route_TYPE = route_TYPE;
    this.route_URL = route_URL;
    this.route_COLOR = route_COLOR;
    this.route_TEXT_COLOR = route_TEXT_COLOR;
    this.denomli_RET = denomli_RET;
    this.integ_BI = integ_BI;
    this.decclie = decclie;
    this.decadmi = decadmi;
    this.sae = sae;
    this.type = type;
    this.centre = centre;
  }
  equals(other) {
    return other instanceof Ligne && this.id === other.id;
  }

  // Custom method to get a unique hash (using the id)
  get hash() {
    return this.id;
  }
}


class Bus{
    constructor(id, name, color){
        this.id = id;
        this.name=name;
        this.color = color;
    }
    equals(other) {
        return other instanceof Bus && this.id === other.id;
      }
    
      // Custom method to get a unique hash (using the id)
      get hash() {
        return this.id;
      }
}

class ResourceArray {
    constructor() {
      this.map = new Map();
    }
  
    add(resource) {
      this.map.set(resource.hash, resource);
    }
  
    has(resource) {
      return this.map.has(resource.hash);
    }
  
    delete(resource) {
      return this.map.delete(resource.hash);
    }
    /**
     * returns an array of resources
     * @returns {Array<ResourceType>}
     */
    values() {
      return Array.from(this.map.values());
    }
  
    get size() {
      return this.map.size;
    }
  }
  class ResourceModes {
    constructor(busResources, ligneResources, chauffResources, recevResources) {
      this._busResources = busResources || new ResourceArray();
      this._ligneResources = ligneResources || new ResourceArray();
      this._chauffResources = chauffResources || new ResourceArray();
      this._recevResources = recevResources || new ResourceArray();
    }
  
    // Get desired resource mode
    getMode(mode) {
      switch (mode) {
        case "Lignes":
          return this.ligneResources;
        case "Bus":
          return this.busResources;
        case "Agents":
          return this.chauffResources;
        case "Receveurs":
          return this.recevResources;
        default:
          return null;
      }
    }
  
    // Getter and Setter for busResources
    get busResources() {
      return this._busResources;
    }
    set busResources(value) {
      this._busResources = value;
    }
  
    // Getter and Setter for ligneResources
    get ligneResources() {
      return this._ligneResources;
    }
    set ligneResources(value) {
      this._ligneResources = value;
    }
  
    // Getter and Setter for chauffResources
    get chauffResources() {
      return this._chauffResources;
    }
    set chauffResources(value) {
      this._chauffResources = value;
    }
  
    // Getter and Setter for recevResources
    get recevResources() {
      return this._recevResources;
    }
    set recevResources(value) {
      this._recevResources = value;
    }
  }
  
export {Bus, ResourceArray, ResourceModes,Ligne};