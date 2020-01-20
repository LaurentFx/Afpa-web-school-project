import { Component, OnInit } from '@angular/core';
import { VipService } from 'src/app/service/vip.service';
import { Router } from '@angular/router';
import { VipDto } from 'src/app/model/vip-dto';

@Component({
  selector: 'app-vip-list',
  templateUrl: './vip-list.component.html',
  styleUrls: ['./vip-list.component.css']
})
export class VipListComponent implements OnInit {

  vip: VipDto[];

  constructor(private vipSrvice: VipService,private router: Router) { }

  ngOnInit() {

    this.vipSrvice.subjectMiseAJour.subscribe(
      res=> {
        this.vipSrvice.getAll().subscribe(
          donnees=>{
            this.vip = donnees
          }
        )
      }
    )

    this.vipSrvice.getAll().subscribe(
      resultat => {
        this.vip = resultat
      }
    )
  }

  delete(id:number){
    this.vipSrvice.delete(id).subscribe(
      res=>{
        this.vipSrvice.subjectMiseAJour.next(0)
        console.log('delete ok')
      }
    )
  }

  redirectToUpdate(id:number){
    this.router.navigateByUrl('/vip-update/'+id)
  }

  redirectToShow(id:number){
    this.router.navigateByUrl('/vip-show/'+id)
  }

}
