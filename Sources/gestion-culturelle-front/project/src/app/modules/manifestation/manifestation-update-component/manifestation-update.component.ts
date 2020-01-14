import { Component, OnInit } from '@angular/core';
import { ManifestationDto } from 'src/app/model/manifestationDto';
import { ActivatedRoute, Router } from '@angular/router';
import { ManifestationService } from 'src/app/service/manifestation.service';

@Component({
  selector: 'app-manifestation-update',
  templateUrl: './manifestation-update.component.html',
  styleUrls: ['./manifestation-update.component.css']
})
export class ManifestationUpdateComponent implements OnInit {

  id: number;
  manifestation: ManifestationDto;
 

  constructor(private route: ActivatedRoute, private manifestationService: ManifestationService, private router: Router) { }

  ngOnInit() {
    
    this.manifestation = new ManifestationDto();
    

    let id = this.route.snapshot.params['id'];

    this.manifestationService.getOne(id).subscribe(
      res => {
        this.manifestation = res;
      }
    );   
   
  }

  update(): void {
    let id = this.route.snapshot.params['id'];
    this.manifestationService.update(id, this.manifestation).subscribe(
      res => {
       console.log("Modification Ok");
        this.goHome();
      }
    );
  }

  onSubmit() {
this.update();
  }
  
  goHome() {
    this.router.navigate(['/manifestation-list']);
  }

  }


