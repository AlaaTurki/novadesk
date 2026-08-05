import {
  Component
} from '@angular/core';

import {
  MatCardModule
} from '@angular/material/card';

import {
  CommonModule
} from '@angular/common';


import {
  HttpClient
} from '@angular/common/http';



@Component({

  selector: 'app-dashboard',

  standalone: true,

  imports:[

 CommonModule,

 MatCardModule

],

  templateUrl: './dashboard.html',

  styleUrl: './dashboard.scss'

})
export class Dashboard {


  message = '';

  users:any[] = [];



  constructor(

    private http: HttpClient

  ){}



  ngOnInit(){


    this.http.get<any[]>(

      'http://localhost:8080/api/users'

    )

    .subscribe({


      next:(response)=>{


        console.log(
          'Users:',
          response
        );


        this.users = response;

        this.message =
          'JWT authentication works';


      },


      error:(error)=>{


        console.error(
          error
        );


        this.message =
          'Error loading users';


      }


    });


  }


}