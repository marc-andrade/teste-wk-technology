import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './auth/auth.guard';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { NavComponent } from './components/nav/nav.component';
import { UserDeleteComponent } from './components/user/user-delete/user-delete.component';
import { UserCreateComponent } from './components/user/user-create/user-create.component';
import { UserListComponent } from './components/user/user-list/user-list.component';
import { UserUpdateComponent } from './components/user/user-update/user-update.component';
import { JewelCardComponent } from './components/jewel/jewel-card/jewel-card.component';
import { JewelListComponent } from './components/jewel/jewel-list/jewel-list.component';
import { JewelCreateComponent } from './components/jewel/jewel-create/jewel-create.component';
import { JewelUpdateComponent } from './components/jewel/jewel-update/jewel-update.component';
import { JewelDeleteComponent } from './components/jewel/jewel-delete/jewel-delete.component';

const routes: Routes = [
  
  { path: '', component: NavComponent, children: [
  {path: 'login', component: LoginComponent},
  {path: 'home', component: HomeComponent},
  {path: 'catalog', component: JewelCardComponent},
  {path: 'users', component: UserListComponent, canActivate: [AuthGuard] },
  {path: 'users/create', component: UserCreateComponent, canActivate: [AuthGuard] },
  {path: 'users/update/:id', component: UserUpdateComponent, canActivate: [AuthGuard] },
  {path: 'users/delete/:id', component: UserDeleteComponent, canActivate: [AuthGuard] },
  {path: 'jewels', component: JewelListComponent, canActivate: [AuthGuard] },
  {path: 'jewels/create', component: JewelCreateComponent, canActivate: [AuthGuard] },
  {path: 'jewels/update/:id', component: JewelUpdateComponent, canActivate: [AuthGuard] },
  {path: 'jewels/delete/:id', component: JewelDeleteComponent, canActivate: [AuthGuard] },
  ]
  }
  ];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
