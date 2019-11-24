import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-images',
  templateUrl: './images.component.html',
  styleUrls: ['./images.component.scss']
})
export class ImagesComponent implements OnInit {

  public imageSrc: any = undefined;
  public imageModified: any = undefined;
  constructor() { }

  ngOnInit() {
  }

  importFile(event: any) {
    const files = event.files;
    if(files.length > 0) {
      const reader = new FileReader();
      reader.onload = e => this.imageSrc = reader.result;
      reader.readAsDataURL(files[0]);
    }
  }
}
