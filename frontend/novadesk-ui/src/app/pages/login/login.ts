import { Component } from '@angular/core';

import {
  FormsModule
} from '@angular/forms';

import {
  Router
} from '@angular/router';

import {
  CommonModule
} from '@angular/common';

import {
  MatCardModule
} from '@angular/material/card';

import {
  MatFormFieldModule
} from '@angular/material/form-field';

import {
  MatInputModule
} from '@angular/material/input';

import {
  MatButtonModule
} from '@angular/material/button';

import {
  MatIconModule
} from '@angular/material/icon';

import {
  Auth
} from '../../core/services/auth';



@Component({

  selector: 'app-login',

  standalone: true,

  imports: [

    CommonModule,

    FormsModule,

    MatCardModule,

    MatFormFieldModule,

    MatInputModule,

    MatButtonModule,

    MatIconModule

  ],

  templateUrl: './login.html',

  styleUrl: './login.scss'

})
export class Login {


  email = '';

  password = '';

  hidePassword = true;

  errorMessage = '';

  loading = false;



  constructor(

    private authService: Auth,

    private router: Router

  ) {}



  submit() {


    this.errorMessage = '';



    if (!this.email || !this.password) {


      this.errorMessage =
        'Email and password are required';


      return;

    }



    this.loading = true;



    this.authService.login({

      email: this.email,

      password: this.password

    })

    .subscribe({


      next: (response) => {


        console.log(
          'Login success:',
          response
        );



        // Save JWT

        localStorage.setItem(

          'token',

          response.token

        );



        this.loading = false;



        // Redirect

        this.router.navigate([

          '/dashboard'

        ]);


      },



      error: (error) => {


        console.error(

          'Login failed:',
          error

        );



        this.errorMessage =

          'Invalid email or password';



        this.loading = false;


      }


    });


  }


}