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


import {
  DashboardUser
} from '../models/dashboard-user';


import {
  DashboardStats
} from '../models/dashboard-stats';



@Injectable({
  providedIn:'root'
})
export class DashboardService {



  private api =
    environment.apiUrl + '/dashboard';



  constructor(
    private http: HttpClient
  ){}



  getUsers(): Observable<DashboardUser[]> {


    return this.http.get<DashboardUser[]>(

      `${this.api}/users`

    );


  }





  getStats(): Observable<DashboardStats> {


    return this.http.get<DashboardStats>(

      `${this.api}/stats`

    );


  }


}