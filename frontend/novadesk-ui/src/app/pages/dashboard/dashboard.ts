import { Component, OnInit } from '@angular/core';


import { CommonModule } from '@angular/common';


import { DashboardService } from '../../core/services/dashboard';


import { DashboardUser } from '../../core/models/dashboard-user';


import { MatTableModule } from '@angular/material/table';


@Component({

selector:'app-dashboard',

standalone:true,


imports:[

CommonModule,

MatTableModule

],


templateUrl:'./dashboard.html',

styleUrl:'./dashboard.scss'

})
export class Dashboard implements OnInit {



users:DashboardUser[]=[];



displayedColumns=[

'username',

'email',

'role'

];



constructor(
private dashboardService:DashboardService
){}



ngOnInit(){


this.dashboardService.getUsers()

.subscribe({

next:data=>{

this.users=data;

}


});


}



}