import { Component, OnInit } from '@angular/core';
import { ImagesService } from 'src/app/services/images/images.service';
import { LoadingService } from 'src/app/services/loading/loading.service';
import { FileSaverService } from 'ngx-filesaver';

@Component({
  selector: 'app-images',
  templateUrl: './images.component.html',
  styleUrls: ['./images.component.scss']
})
export class ImagesComponent implements OnInit {

  public formData: FormData = null;
  public file: File = null;
  public imageSrc: any = undefined;
  public imageModified: any = undefined;
  public imageModifiedBlob: Blob = undefined;

  constructor(private imagesService: ImagesService,
    private loadingService: LoadingService,
    private fileSaverService: FileSaverService) { }

  ngOnInit() {
  }

  importFile(event: any) {
    this.loadingService.startLoading("loading-import");
    const files = event.files;

    if(files.length > 0) {
      this.file = files[0];
      const reader = new FileReader();
      reader.onload = e => {
        this.loadingService.stopLoading("loading-import");
        this.imageSrc = reader.result;
      };
      reader.readAsDataURL(files[0]);
    }
  }

  getImageInverted() {
    this.resetImageModified();
    this.loadingService.startLoading("loading-process");
    this.formData = new FormData();
    this.formData.append('file', this.file, this.file.name);

    this.imagesService.getInverted(this.formData).subscribe(
      response => {
        this.imageModifiedBlob = response;
        this.convertBlobToImage(response);
        this.loadingService.stopLoading("loading-process");
      },
      error => {
        console.log("KO")
        this.loadingService.stopLoading("loading-process");
      }
    )
  }

  getImageBlackAndWhite() {
    this.resetImageModified();
    this.loadingService.startLoading("loading-process");
    this.formData = new FormData();
    this.formData.append('file', this.file, this.file.name);

    this.imagesService.getBlackAndWhite(this.formData).subscribe(
      response => {
        this.imageModifiedBlob = response;
        this.convertBlobToImage(response);
        this.loadingService.stopLoading("loading-process");
      },
      error => {
        console.log("KO")
        this.loadingService.stopLoading("loading-process");
      }
    )
  }

  getImageSepia() {
    this.resetImageModified();
    this.loadingService.startLoading("loading-process");
    this.formData = new FormData();
    this.formData.append('file', this.file, this.file.name);

    this.imagesService.getSepia(this.formData).subscribe(
      response => {
        this.imageModifiedBlob = response;
        this.convertBlobToImage(response);
        this.loadingService.stopLoading("loading-process");
      },
      error => {
        console.log("KO")
        this.loadingService.stopLoading("loading-process");
      }
    )
  }

  getImageShake() {
    this.resetImageModified();
    this.loadingService.startLoading("loading-process");
    this.formData = new FormData();
    this.formData.append('file', this.file, this.file.name);

    this.imagesService.getShake(this.formData).subscribe(
      response => {
        this.imageModifiedBlob = response;
        this.convertBlobToImage(response);
        this.loadingService.stopLoading("loading-process");
      },
      error => {
        console.log("KO")
        this.loadingService.stopLoading("loading-process");
      }
    )
  }

  downloadImageModified() {
    this.fileSaverService.save(this.imageModifiedBlob, "new_image.png", "image/png");
  }


  private convertBlobToImage(blob: Blob) {
    const reader = new FileReader();
    reader.onload = e => this.imageModified = reader.result;
    reader.readAsDataURL(blob);
  }

  private resetImageModified() {
    this.imageModified = undefined;
    this.imageModifiedBlob = undefined;
  }
}
