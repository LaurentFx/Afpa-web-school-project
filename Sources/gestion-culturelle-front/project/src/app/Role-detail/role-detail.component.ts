import { Component, OnInit, Input } from '@angular/core';
import { RoleModel } from '../model/Role';

@Component({
  selector: 'cda-role-detail',
  templateUrl: './role-detail.component.html',
  styleUrls: ['./role-detail.component.css']
})
export class RoleDetailComponent implements OnInit {

  @Input() role: RoleModel;

  constructor() { }

  ngOnInit() {
   console.log('jai mon role super !!! ');
  }

}
