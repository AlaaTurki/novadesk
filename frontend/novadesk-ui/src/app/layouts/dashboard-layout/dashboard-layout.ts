import {
Component
} from '@angular/core';


import {
Navbar
} from '../../shared/navbar/navbar';


import {
Sidebar
} from '../../shared/sidebar/sidebar';


import {
RouterOutlet
} from '@angular/router';



@Component({

selector:'app-dashboard-layout',

standalone:true,

imports:[

Navbar,

Sidebar,

RouterOutlet

],

templateUrl:'./dashboard-layout.html',

styleUrl:'./dashboard-layout.scss'

})
export class DashboardLayout {


}