import {
Injectable
} from '@angular/core';


import {
HttpClient
} from '@angular/common/http';


import {
User
} from '../models/user';



@Injectable({
providedIn:'root'
})
export class UserService {



private api =
'http://localhost:8080/api/users';




constructor(
private http:HttpClient
){}






getUsers(){

return this.http.get<User[]>(
this.api
);

}





createUser(
data:any
){

return this.http.post<User>(
this.api,
data
);

}





updateUser(
id:string,
data:any
){

return this.http.put<User>(
`${this.api}/${id}`,
data
);

}





updateRole(
id:string,
role:string
){

return this.http.patch<User>(

`${this.api}/${id}/role`,

{
role:role
}

);

}





deleteUser(
id:string
){

return this.http.delete(

`${this.api}/${id}`

);

}




}