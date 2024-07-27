export class MyMarker {
  constructor(lat, lng) {
    this.lat = lat;
    this.lng = lng;
  }

  getLat() {
    return this.lat;
  }

  getLng() {
    return this.lng;
  }

  getCoords() {
    return [this.lng, this.lat]; // Note: Coordinates should be in [lng, lat] format for MapLibre GL
  }
}
