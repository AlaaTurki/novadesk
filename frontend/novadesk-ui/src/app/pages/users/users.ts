import {
Component,
OnInit
} from '@angular/core';



import {
CommonModule
} from '@angular/common';



import {
MatTableModule
} from '@angular/material/table';



import {
MatButtonModule
} from '@angular/material/button';



import {
MatDialog,
MatDialogModule
} from '@angular/material/dialog';



import {
UserService
} from '../../core/services/user';



import {
User
} from '../../core/models/user';



import {
EditUserDialog
} from './edit-user-dialog';



import {
RoleDialog
} from './role-dialog';



import {
ConfirmDialog
} from '../../shared/confirm-dialog';



import {
CreateUserDialog
} from './create-user-dialog/create-user-dialog';






@Component({

selector:'app-users',

standalone:true,


imports:[

CommonModule,

MatTableModule,

MatButtonModule,

MatDialogModule

],



templateUrl:'./users.html',


styleUrl:'./users.scss'

})


export class Users implements OnInit {



users:User[]=[];




displayedColumns=[

'username',

'email',

'role',

'actions'

];







constructor(

private userService:UserService,

private dialog:MatDialog

){}








ngOnInit(){

this.loadUsers();

}







loadUsers(){


this.userService
.getUsers()

.subscribe({

next:data=>{


this.users=data;


}


});


}









create(){



const dialogRef =

this.dialog.open(

CreateUserDialog,

{

width:'450px'

}

);





dialogRef.afterClosed()

.subscribe(result=>{



if(result){



this.userService

.createUser(result)

.subscribe(()=>{


this.loadUsers();


});



}



});



}








edit(user:User){


const dialogRef =

this.dialog.open(

EditUserDialog,

{

width:'450px',

data:user

}

);




dialogRef.afterClosed()

.subscribe(()=>{


this.loadUsers();


});


}









manageRole(user:User){


const dialogRef =

this.dialog.open(

RoleDialog,

{

width:'350px',

data:user

}

);




dialogRef.afterClosed()

.subscribe(()=>{


this.loadUsers();


});



}










delete(user:User){



const dialogRef =

this.dialog.open(

ConfirmDialog,

{

width:'350px',

data:{

title:'Delete User',

message:
`Delete ${user.username}?`

}

}

);






dialogRef.afterClosed()

.subscribe(result=>{


if(result){



this.userService

.deleteUser(user.id)

.subscribe(()=>{


this.loadUsers();


});


}



});



}




}