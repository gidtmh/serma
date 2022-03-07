import {Component, OnInit} from '@angular/core';
import {Employee} from "../../models/employee.model";
import {AuthService} from "../../services/auth/auth.service";
import {MessageService} from "../../services/message.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  employee: Employee = {
    name: '',
    password: '',
  };
  submitted = false;
  loginForm!: FormGroup;
  loading = false;
  error = '';

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private snackBar: MessageService) {
  }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  // convenience getter for easy access to form fields
  get f() {
    return this.loginForm.controls;
  }

  login(): void {
    this.submitted = true;
    this.loading = true;
    this.authService.login(this.loginForm.value.username, this.loginForm.value.password)
      .subscribe({
        next: (res: any) => {
          this.loading = false;
          this.snackBar.showMessage('You logged in!');
        },
        error: (e: any) => {
          this.error = e.error.errors.map((x: any) => x.message).join('\n');
          this.loading = false;
        }
      });
  }
}
