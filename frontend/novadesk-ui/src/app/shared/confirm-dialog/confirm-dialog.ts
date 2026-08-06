import {
Component,
Inject
} from '@angular/core';


import {
MAT_DIALOG_DATA,
MatDialogRef
} from '@angular/material/dialog';


import {
MatButtonModule
} from '@angular/material/button';



@Component({

selector:'app-confirm-dialog',

standalone:true,

imports:[

MatButtonModule

],

template:`

<h2>
{{data.title}}
</h2>


<p>
{{data.message}}
</p>



<button
mat-button
(click)="close(false)">
Cancel
</button>



<button
mat-raised-button
color="warn"
(click)="close(true)">
Delete
</button>

`

})
export class ConfirmDialog{


constructor(

private ref:
MatDialogRef<ConfirmDialog>,


@Inject(MAT_DIALOG_DATA)
public data:any

){}




close(value:boolean){

this.ref.close(value);

}


}