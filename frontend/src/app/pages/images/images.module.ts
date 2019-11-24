import { ImagesComponent } from './images.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ImagesService } from 'src/app/services/images/images.service';
import { LoadingComponent } from '../loading/loading.component';
import { LoadingService } from 'src/app/services/loading/loading.service';

@NgModule({
  declarations: [
    ImagesComponent,
    LoadingComponent
  ],
  imports: [
    CommonModule
  ],
  providers: [
    ImagesService,
    LoadingService
  ]
})
export class ImagesModule { }
