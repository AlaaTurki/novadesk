import {
  Component
} from '@angular/core';

import {
  MatToolbarModule
} from '@angular/material/toolbar';

import {
  MatButtonModule
} from '@angular/material/button';

import {
  MatIconModule
} from '@angular/material/icon';

import {
  Router
} from '@angular/router';


@Component({

  selector:'app-navbar',

  standalone:true,

  imports:[

    MatToolbarModule,

    MatButtonModule,

    MatIconModule

  ],

  templateUrl:'./navbar.html',

  styleUrl:'./navbar.scss'

})
export class Navbar {


constructor(

private router:Router

){}



logout(){


localStorage.removeItem('token');


this.router.navigate([

'/login'

]);


}


}