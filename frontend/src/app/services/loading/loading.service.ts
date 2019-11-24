import { Injectable, EventEmitter } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoadingService {
  public loadingEvent = new EventEmitter<any>();
  constructor() {
    // this.loading = false;
    //this.loadingEvent.emit(this.loading);
 }


  startLoading(name: string) {
    this.loadingEvent.emit({name: name, loading: true});
  }

  stopLoading(name: string) {
    this.loadingEvent.emit({name: name, loading: false});
  }
}
