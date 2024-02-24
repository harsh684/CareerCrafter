import { createAction, props } from '@ngrx/store';
import { UserInfo } from 'src/app/model/UserInfo';

export const updateCurrentUserState = createAction(
    'UpdateCurrentUser',
    props<{ currentUser: UserInfo }>()
  );