import { Routes } from '@angular/router';

import { Login } from './pages/login/login';
import { Register } from './pages/register/register';
import { Dashboard } from './pages/dashboard/dashboard';
import { authGuard } from './core/guards/auth-guard';
import { DashboardLayout } from './layouts/dashboard-layout/dashboard-layout';


export const routes: Routes = [


  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full'
  },


  {
    path: 'login',
    component: Login
  },


  {
    path: 'register',
    component: Register
  },


  
{
path:'',
component:DashboardLayout,
canActivate:[
authGuard
],

children:[

{
path:'dashboard',
component:Dashboard
}

]

},


  {
    path: '**',
    redirectTo: 'login'
  }


];