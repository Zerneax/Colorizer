import { Component, OnInit, Input } from '@angular/core';
import { LoadingService } from 'src/app/services/loading/loading.service';

@Component({
  selector: 'app-loading',
  templateUrl: './loading.component.html',
  styleUrls: ['./loading.component.scss']
})
export class LoadingComponent implements OnInit {

  @Input() name: string;

  public loading: boolean = false;

  constructor(private loadingService: LoadingService) { }

  ngOnInit() {

    this.loadingService.loadingEvent.subscribe((loading: any) =>{
      if(loading.name == this.name)
        this.loading = loading.loading; 
    });
  }

}
