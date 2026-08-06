import {
  Component,
  Inject
} from '@angular/core';


import {
  CommonModule
} from '@angular/common';


import {
  FormsModule
} from '@angular/forms';


import {
  MAT_DIALOG_DATA,
  MatDialogRef
} from '@angular/material/dialog';


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
  UserService
} from '../../../core/services/user';



@Component({

  selector:'app-edit-user-dialog',

  standalone:true,


  imports:[

    CommonModule,

    FormsModule,

    MatFormFieldModule,

    MatInputModule,

    MatButtonModule

  ],


  template:`

<h2 mat-dialog-title>
Edit User
</h2>


<div mat-dialog-content>


<mat-form-field appearance="outline">

<mat-label>
Username
</mat-label>

<input
matInput
[(ngModel)]="user.username">

</mat-form-field>



<mat-form-field appearance="outline">

<mat-label>
Email
</mat-label>

<input
matInput
[(ngModel)]="user.email">

</mat-form-field>


</div>




<div mat-dialog-actions>


<button
mat-button
(click)="close()">

Cancel

</button>



<button
mat-raised-button
color="primary"
(click)="save()">

Save

</button>


</div>

`

})
export class EditUserDialog {



constructor(


private dialogRef:MatDialogRef<EditUserDialog>,


private userService:UserService,


@Inject(MAT_DIALOG_DATA)
public user:any


){}





save(){


this.userService

.updateUser(

this.user.id,

{

username:this.user.username,

email:this.user.email

}

)

.subscribe(()=>{


this.dialogRef.close(true);


});


}






close(){


this.dialogRef.close();


}


}