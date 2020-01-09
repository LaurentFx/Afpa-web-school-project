import { Injectable, OnInit } from '@angular/core';
import { User } from '../model/user';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService implements OnInit {
  
  isConnected: boolean;
  subjectConnexion=new Subject<number>();


  constructor() { }

  ngOnInit(): void {
    this.isConnected = Boolean(localStorage.getItem('isConnected'));
  }

  login(user: User): boolean {
    if(user.login === 'clement' && user.password==='pwd'){
      console.log('auth ok');
      this.isConnected = true;
      localStorage.setItem('isConnected','true');
      this.subjectConnexion.next(3);
      return true;
    } else {
      console.log('auth ko');
      this.isConnected = false;
      return false;
    }
  }

  logout() {
    this.isConnected=false;
    localStorage.removeItem('isConnected');
  }
}
