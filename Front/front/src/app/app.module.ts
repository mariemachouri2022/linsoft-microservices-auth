import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ProfileComponent } from './profile/profile.component';
import { AdminDashboardComponent } from './ADMIN/admin-dashboard.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,RegisterComponent,ProfileComponent,AdminDashboardComponent
  ],
    imports: [BrowserModule, AppRoutingModule,FormsModule,HttpClientModule,ReactiveFormsModule,ReactiveFormsModule],

  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
