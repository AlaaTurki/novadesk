import {
Component,
Inject
} from '@angular/core';


import {
MAT_DIALOG_DATA,
MatDialogRef
} from '@angular/material/dialog';


import {
FormsModule
} from '@angular/forms';


import {
CommonModule
} from '@angular/common';


import {
MatButtonModule
} from '@angular/material/button';


import {
MatSelectModule
} from '@angular/material/select';


import {
UserService
} from '../../../core/services/user';



@Component({

selector:'app-role-dialog',

standalone:true,


imports:[

CommonModule,

FormsModule,

MatButtonModule,

MatSelectModule

],


templateUrl:'./role-dialog.html'


})
export class RoleDialog {



selectedRole:string;




constructor(

private userService:UserService,


private dialogRef:
MatDialogRef<RoleDialog>,


@Inject(MAT_DIALOG_DATA)
public data:any

){



this.selectedRole =
data.role;


}






save(){



this.userService

.updateRole(

this.data.id,

this.selectedRole

)

.subscribe({

next:()=>{


this.dialogRef.close(true);


}



});



}





cancel(){

this.dialogRef.close();

}



}