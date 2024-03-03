import { createReducer, on } from '@ngrx/store';
import { UserInfo } from 'src/app/model/UserInfo';
import { updateCurrentUserState } from '../actions/current-user.action';
import { inject } from '@angular/core';
import { GetCurrentUserService } from 'src/app/services/GetCurrentUser/get-current-user.service';

export interface UserState{

    currentUser: UserInfo | null;

}

export const initialState: UserState ={
    currentUser:{
        id: 0,
        name: "",
        email: "",
        password: "",
        role: "",
        roleId: 0
    }
}


export const CurrentUserReducer = createReducer(
    initialState,
    on(updateCurrentUserState, (state,{currentUser}) => ({
        ...state,
        currentUser: currentUser,
    })
  ));
