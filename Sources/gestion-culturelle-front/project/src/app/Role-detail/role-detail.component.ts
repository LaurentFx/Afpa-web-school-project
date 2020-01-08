import { Component, OnInit, Input } from '@angular/core';
import { Role  } from '../model/role';

@Component({
  selector: 'cda-role-detail',
  templateUrl: './role-detail.component.html',
  styleUrls: ['./role-detail.component.css']
})
export class RoleDetailComponent implements OnInit {

  @Input() role: Role ;

  constructor() { }

  ngOnInit() {
   console.log('jai mon role super !!! ');
  }

}
