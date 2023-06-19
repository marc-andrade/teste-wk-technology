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
import { QuantidadeDoadorPorEstadoComponent } from './components/quantidade-doador-por-estado/quantidade-doador-por-estado.component';
import { ImcMedioPorFaixaComponent } from './components/imc-medio-por-faixa/imc-medio-por-faixa.component';
import { PercentualObesosGeneroComponent } from './components/percentual-obesos-genero/percentual-obesos-genero.component';

const routes: Routes = [

  {path: 'login', component: LoginComponent},
  {path: '', component: NavComponent, canActivate:[AuthGuard], children: [
  {path: 'home', component: HomeComponent},
  {path: 'users', component: UserListComponent},
  {path: 'users/create', component: UserCreateComponent},
  {path: 'users/update/:id', component: UserUpdateComponent},
  {path: 'users/delete/:id', component: UserDeleteComponent},
  {path: 'quantidade-doador', component: QuantidadeDoadorPorEstadoComponent},
  {path: 'imc-medio-por-faixa-idade', component: ImcMedioPorFaixaComponent},
  {path: 'percentual-obesos-genero', component: PercentualObesosGeneroComponent},
  ]
  }
  ];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
