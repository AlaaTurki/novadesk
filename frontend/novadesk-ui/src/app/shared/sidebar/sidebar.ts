import {
Component
} from '@angular/core';


import {
RouterLink
} from '@angular/router';


import {
MatListModule
} from '@angular/material/list';


import {
MatIconModule
} from '@angular/material/icon';



@Component({

selector:'app-sidebar',

standalone:true,

imports:[

MatListModule,

MatIconModule,

RouterLink

],

templateUrl:'./sidebar.html',

styleUrl:'./sidebar.scss'

})
export class Sidebar {


}