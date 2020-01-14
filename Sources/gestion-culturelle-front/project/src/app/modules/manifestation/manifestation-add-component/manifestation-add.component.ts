import { Component, OnInit } from '@angular/core';
import { ManifestationDto } from 'src/app/model/manifestationDto';
import { ManifestationService } from '../../../service/manifestation.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-manifestation-add',
  templateUrl: './manifestation-add.component.html',
  styleUrls: ['./manifestation-add.component.css']
})
export class ManifestationAddComponent implements OnInit {

  [x: string]: any;

  manifestation: ManifestationDto;

  constructor(private manifestationService: ManifestationService, private router: Router) { }

  ngOnInit() {
    this.manifestation = new ManifestationDto();
  }

  add(): void {
    this.manifestationService.add(this.animation).subscribe(
      res => {
        this.manifestationService.subjectMiseAJour.next(0);
        console.log("Ajout Ok ");
        this.goHome();
      }
    );
    this.manifestation = new ManifestationDto();
  }

  goHome() {
    this.router.navigate(['/manifestation-list']);

  }

}
