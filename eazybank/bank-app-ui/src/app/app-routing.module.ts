import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthActivateRouteGuard } from './routeguards';
import { AccountComponent, BalanceComponent, CardsComponent, ContactComponent, DashboardComponent,
  HomeComponent, LoansComponent, LoginComponent, LogoutComponent, NoticesComponent } from './components';

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'contact', component: ContactComponent },
  { path: 'notices', component: NoticesComponent },
  { path: 'dashboard', component: DashboardComponent, canActivate: [AuthActivateRouteGuard] },
  { path: 'logout', component: LogoutComponent },
  { path: 'myAccount', component: AccountComponent, canActivate: [AuthActivateRouteGuard] },
  { path: 'myBalance', component: BalanceComponent, canActivate: [AuthActivateRouteGuard] },
  { path: 'myLoans', component: LoansComponent, canActivate: [AuthActivateRouteGuard] },
  { path: 'myCards', component: CardsComponent, canActivate: [AuthActivateRouteGuard] }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
