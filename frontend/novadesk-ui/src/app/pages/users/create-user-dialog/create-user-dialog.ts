import {
Component
} from '@angular/core';


import {
MatDialogRef
} from '@angular/material/dialog';


import {
FormsModule
} from '@angular/forms';


import {
CommonModule
} from '@angular/common';


import {
MatInputModule
} from '@angular/material/input';


import {
MatButtonModule
} from '@angular/material/button';


import {
MatSelectModule
} from '@angular/material/select';





@Component({

selector:'app-create-user-dialog',

standalone:true,


imports:[

CommonModule,

FormsModule,

MatInputModule,

MatButtonModule,

MatSelectModule

],


templateUrl:'./create-user-dialog.html'

})


export class CreateUserDialog {



user={

username:'',

email:'',

password:'',

role:'USER'

};





constructor(

private dialogRef:
MatDialogRef<CreateUserDialog>

){}






save(){


this.dialogRef.close(
this.user
);


}





cancel(){


this.dialogRef.close();


}



}