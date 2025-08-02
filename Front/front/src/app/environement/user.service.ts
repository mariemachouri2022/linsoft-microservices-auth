import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from "src/app/user"; 

@Injectable({
  providedIn: "root",
})
export class UserService {
  private apiUrl = 'http://localhost:8023/api/auth'; 

  constructor(private http: HttpClient) {}

  // Get all users
  getAllUsers(): Observable<User[]> {
    return this.http.get<User[]>(`${this.apiUrl}`);
  }

  // Get user by ID
  getUserById(id: number): Observable<User> {
    return this.http.get<User>(`${this.apiUrl}/${id}`);
  }

  // Get user history by ID
  getUserHistory(id: number): Observable<[User]> {
    return this.http.get<[User]>(`${this.apiUrl}/${id}/history`);
  }

  // Get user by email
  getUserByEmail(email: string): Observable<User> {
    return this.http.get<User>(`${this.apiUrl}/email/${email}`);
  }

  // REGISTER — méthode renommée en createUser pour correspondre au component
createUser(data: User): Observable<string> {
  return this.http.post(`${this.apiUrl}/signup`, data, { responseType: 'text' });
}

  // LOGIN
  login(username: string, password: string): Observable<any> {
    const loginData = {
      username: username,
      password: password
    };
    return this.http.post<any>(`${this.apiUrl}/login`, loginData);
  }

  // Get user by username
  getUserByUsername(username: string): Observable<User> {
    return this.http.get<User>(`${this.apiUrl}/username/${username}`);
  }

  // Update user by email
  updateUser(email: string, user: User): Observable<User> {
    return this.http.put<User>(`${this.apiUrl}/email/${email}`, user);
  }

  // Delete user by email
  deleteUser(email: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/email/${email}`);
  }

  // Access protected route (for ADMIN or MANAGER)
  getProtectedRoute(): Observable<string> {
    return this.http.get(`${this.apiUrl}/protected`, { responseType: 'text' });
  }
}
