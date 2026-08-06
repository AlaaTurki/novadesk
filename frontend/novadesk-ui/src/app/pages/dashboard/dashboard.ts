import { 
  Component, 
  OnInit 
} from '@angular/core';


import {
  CommonModule
} from '@angular/common';


import {
  DashboardService
} from '../../core/services/dashboard';


import {
  DashboardUser
} from '../../core/models/dashboard-user';


import {
  DashboardStats
} from '../../core/models/dashboard-stats';


import {
  MatTableModule
} from '@angular/material/table';


import {
  MatCardModule
} from '@angular/material/card';



@Component({

selector:'app-dashboard',

standalone:true,


imports:[

CommonModule,

MatTableModule,

MatCardModule

],


templateUrl:'./dashboard.html',

styleUrl:'./dashboard.scss'

})
export class Dashboard implements OnInit {



users: DashboardUser[] = [];


admins: DashboardUser[] = [];


normalUsers: DashboardUser[] = [];



stats?:DashboardStats;



displayedColumns=[

'username',

'email',

'role'

];




constructor(

private dashboardService:DashboardService

){}





ngOnInit(){


this.loadUsers();


this.loadStats();


}




loadUsers(){


this.dashboardService
.getUsers()

.subscribe({

next:(data)=>{


this.users=data;



this.admins =
this.users.filter(

user =>
user.role === 'ADMIN'

);



this.normalUsers =
this.users.filter(

user =>
user.role === 'USER'

);



},


error:(err)=>{


console.error(
'Error loading users',
err
);


}


});


}





loadStats(){


this.dashboardService
.getStats()

.subscribe({

next:(data)=>{


this.stats=data;


}


});


}



}