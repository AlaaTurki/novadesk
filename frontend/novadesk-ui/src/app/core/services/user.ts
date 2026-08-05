import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';

import { environment } from '../../../environments/environment';

import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private api = environment.apiUrl + '/users';

  constructor(
    private http: HttpClient
  ) { }

  getAll(): Observable<User[]> {

    return this.http.get<User[]>(this.api);

  }

  getById(id: string): Observable<User> {

    return this.http.get<User>(`${this.api}/${id}`);

  }

  delete(id: string): Observable<void> {

    return this.http.delete<void>(`${this.api}/${id}`);

  }

}