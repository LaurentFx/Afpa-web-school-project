import { Component, OnInit } from '@angular/core';
import { ManifestationDto } from '../../../model/manifestationDto';
import { Router } from '@angular/router';
import { ManifestationService } from '../../../service/manifestation.service';
import { AuthService } from '../../../service/auth.service';
import { RoleDto } from 'src/app/model/roleDto';

@Component({
  selector: 'app-manifestation-list',
  templateUrl: './manifestation-list.component.html',
  styleUrls: ['./manifestation-list.component.css']
})
export class ManifestationListComponent implements OnInit {

  isConnected: boolean;
  manifestations: ManifestationDto[];
  isResp: boolean;
  isClient: boolean;
  user: String;
  role: RoleDto;

  constructor(private manifestationService: ManifestationService, private router: Router,
    private authService: AuthService) { }


  ngOnInit() {

    this.isConnected = this.authService.isConnected();
    if (this.authService.getCurrentUser()) {
      this.isResp = this.authService.getCurrentUser().role.label === 'RESP';
      this.isClient = this.authService.getCurrentUser().role.label === 'CLIENT';
      this.user = this.authService.getCurrentUser().nom;
      this.role = this.authService.getCurrentUser().role;
    }

    this.manifestationService.subjectMiseAJour.subscribe(
      res => {
        this.manifestationService.getAll().subscribe(
          donnees => {
            this.manifestations = donnees;
          }
        );
      }
    );

    this.manifestationService.getAll().subscribe(
      resultat => {
        this.manifestations = resultat;

      }
    );

    this.authService.subjectConnexion.subscribe(
      res => {
        this.isConnected = this.authService.isConnected();

      }
    );

  }

  delete(id: number) {
    this.manifestationService.delete(id).subscribe(
      res => {
        this.manifestationService.subjectMiseAJour.next(0);
        console.log('delete Ok ');
      }
    )
  }

  redirectToUpdate(id: number) {
    this.router.navigateByUrl('/manifestation-update/' + id)
  }

  redirectToShow(id: number) {
    this.router.navigateByUrl('/manifestation-show/' + id)
  }

  redirectToAddPanier(id: number) {
    this.router.navigateByUrl('/panier-ad/' + id)
  }


}

