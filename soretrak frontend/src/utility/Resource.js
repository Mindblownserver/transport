class Resource{
    constructor(id, name, color){
        this.id = id;
        this.name=name;
        this.color = color;
    }
    equals(other) {
        return other instanceof Resource && this.id === other.id;
      }
    
      // Custom method to get a unique hash (using the id)
      get hash() {
        return this.id;
      }
}



class UniqueResourceSet {
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
  
    values() {
      return Array.from(this.map.values());
    }
  
    get size() {
      return this.map.size;
    }
  }

export {Resource, UniqueResourceSet};