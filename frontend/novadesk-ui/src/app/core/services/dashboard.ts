import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';


import { environment } from '../../../environments/environment';

import { DashboardUser } from '../models/dashboard-user';



@Injectable({
providedIn:'root'
})
export class DashboardService {



private api =
environment.apiUrl + '/dashboard';



constructor(
private http:HttpClient
){}




getUsers():Observable<DashboardUser[]> {


return this.http.get<DashboardUser[]>(

`${this.api}/users`

);


}



}