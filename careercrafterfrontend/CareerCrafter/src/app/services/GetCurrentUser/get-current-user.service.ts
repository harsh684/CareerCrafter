import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserInfo } from 'src/app/model/UserInfo';

@Injectable({
  providedIn: 'root'
})
export class GetCurrentUserService {

  constructor(private http:HttpClient) { }

  currentUser:UserInfo={
    id:0,
    name:"",
    email:"",
    password:"",
    role:"",
    roleId:0
  };

  getCurrentUser():Observable<UserInfo>{

    let tokenString = "Bearer "+localStorage.getItem("token");

    const headers =  new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': 'http://localhost:4200' // Ensure this matches your Spring Boot CORS configuration
    }).set("Authorization",tokenString);

    return this.http.get<UserInfo>("http://localhost:8080/api/register/getCurrentUser",{headers});
    // .subscribe((response)=>{
    //   this.currentUser.id=response.id;
    //   this.currentUser.name=response.name;
    //   this.currentUser.email=response.email;
    //   this.currentUser.password=response.password;
    //   this.currentUser.role=response.role;
    //   this.currentUser.roleId=response.roleId;
    // });
    
    // return this.currentUser;
  }

}

// export class GetCurrentUserService {


//   constructor(private http:HttpClient) { }

//   currentUser:UserInfo={
//     id:0,
//     name:"",
//     email:"",
//     password:"",
//     role:"",
//     roleId:0
//   };

//   getCurrentUser():UserInfo{

//     //this.decodeJwtToken(token);
    
//     //return decodeJwtToken(localStorage.getItem("token"));
//     let tokenString = "Bearer "+localStorage.getItem("token");

//     const headers =  new HttpHeaders({
//       'Content-Type': 'application/json',
//       'Access-Control-Allow-Origin': 'http://localhost:4200' // Ensure this matches your Spring Boot CORS configuration
//     }).set("Authorization",tokenString);

//     new Promise<UserInfo>((resolve,reject)=>{
//       this.http.get<UserInfo>("http://localhost:8080/api/register/getCurrentUser",{headers})
//       .subscribe((resInfo)=>{
//         this.currentUser = resInfo;
//         console.log(this.currentUser);
//         resolve(resInfo);
//       },
//       (err)=>{
//         reject(`Error while getting current user info: ${err}`);
//       })
//     });
//     return this.currentUser;

//   }

    // return new Promise<string>((resolve,reject)=>{
    //   this.httpClient.post<string>(this.baseURL + "authenticate", authInfo, requestOptions)
    //   .subscribe((res)=>{
    //     localStorage.setItem("token",res);
    //     resolve(res);
    //   },
    //   (err)=>{
    //     reject(err);
    //   });
    // })

//   decodeJwtToken(token:string|null){
//     if (token != null) {
//       const parts = token.split('.');
//       if (parts.length === 3) {
//           const base64Url = parts[1];
//           const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
//           const jsonPayload = decodeURIComponent(
//               atob(base64)
//                   .split('')
//                   .map(c => {
//                       return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
//                   })
//                   .join('')
//           );
//           console.log(JSON.parse(jsonPayload));
//       } else {
//           console.error('Invalid JWT token format');
//       }
//   } else {
//       console.error('Token is null');
//   }
// }
//   }
//}
