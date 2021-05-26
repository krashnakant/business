import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';

const routes: Routes = [
  {
    path: 'agency', loadChildren: () => {
      return import('./agencymanager/agency-manager.module').then(e => e.AgencyManagerModule);
    }
  },
  {path: '**', redirectTo: 'agency'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
