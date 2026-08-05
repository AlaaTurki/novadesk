import { Injectable } from '@angular/core';

import {
  HttpClient
} from '@angular/common/http';

import {
  Observable
} from 'rxjs';

import {
  environment
} from '../../../environments/environment';



export interface LoginRequest {

  email:string;

  password:string;

}



export interface AuthResponse {

  id:string;

  username:string;

  email:string;

  token:string;

}



@Injectable({

  providedIn:'root'

})
export class Auth {


private api =
environment.apiUrl + '/auth';



constructor(

private http:HttpClient

){}



login(
request:LoginRequest
):Observable<AuthResponse>{


return this.http.post<AuthResponse>(

`${this.api}/login`,

request

);


}



logout(){

localStorage.removeItem('token');

}



isLoggedIn(){

return !!localStorage.getItem('token');

}


}