import { Component } from '@angular/core';
import { RegisterService } from '../services/register.service';
import { Router } from '@angular/router';
import { LoginService } from '../services/login.service';

@Component({
    selector:'my-navbar',
    templateUrl: './navbar.component.html',
    styleUrls: ['./navbar.component.css'],
    providers: [RegisterService, LoginService]
})

export class NavbarComponent{
	isUserLoggedIn: boolean = false;

	constructor(private service: RegisterService, private loginService: LoginService, private router: Router){

	}

	ngDoCheck(){
		this.isUserLoggedIn = localStorage.getItem('token') ? true: false;
	}

  login(loginFormData){
  	this.loginService.login(loginFormData.value).subscribe(response => {
      if(response.token) {
        localStorage.setItem('token', response.token);
        localStorage.setItem('userId', loginFormData.value.userName);
        alert(response.message);
        this.router.navigate(['/']);
        $(".loginClose").click();
       }
       $(".loginInput").val('');
    }, error => {
      if (error.status === 401) {
        alert('Username or password is incorrect.');
      }
      $(".loginInput").val('');
    });
  }

  register(registerFormData){
  	this.service.registerUser(registerFormData.value).subscribe(response => {
      if(response.success){
        alert('Registration successful, please login to save repositories.');
        $('.registerClose').click();
      }
      $(".registerInput").val('');
    }, error => {
      if(error.status === 409){
        alert('Registration failed, user already exists');
      }
      $(".registerInput").val('');
    });
  }

  logout(){
  	localStorage.removeItem('token');
  	localStorage.removeItem('userId');
  	this.router.navigate(['/']);
  	alert("You have successfully logged out.");
  }

}